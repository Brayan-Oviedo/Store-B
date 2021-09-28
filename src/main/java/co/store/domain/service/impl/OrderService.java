package co.store.domain.service.impl;


import java.util.ArrayList;
import java.util.List;

import co.store.domain.service.useCase.*;
import co.store.infrastructure.repository.entity.order.OrderSaleEntity;
import co.store.infrastructure.repository.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.store.application.request.ProductRequest;
import co.store.application.request.order.OrderSaleRequest;
import co.store.application.request.order.OrderSeparateRequest;
import co.store.domain.exception.Messages;
import co.store.domain.exception.OrderException;
import co.store.domain.model.Cash;
import co.store.domain.model.order.Order;
import co.store.domain.model.order.OrderSale;
import co.store.domain.model.order.OrderSeparate;
import co.store.domain.model.product.Product;
import co.store.domain.repository.IOrderRepository;
import co.store.infrastructure.repository.entity.order.OrderEntity;
import co.store.infrastructure.repository.entity.product.ProductOrder;
import co.store.infrastructure.repository.mapper.OrderMapper;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private IOrderRepository repository;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IProductOrderService productOrderService;

	@Autowired
	private IClientService clientService;
	
	@Autowired
	private ICashService cashService;
	
	@Autowired
	private OrderMapper mapper;

	@Autowired
	private ClientMapper clientMapper;


	@Override
	public Order getOrderById(Long id) throws Exception {

		return mapper.toDomain(repository.getOrderById(id));
	}

	@Override
	public OrderSale createOrderSale(OrderSaleRequest orderRequest) throws Exception {
		try {
			validateUnitsToOrder(orderRequest.getProducts());
			OrderSale order = OrderSale.buildOf(orderRequest);
			OrderEntity entity = repository.saveOrder(new OrderSaleEntity());

			for(ProductRequest productRequest : orderRequest.getProducts()) {
				productService.sellProduct(productRequest);
				Product product = productService.getProductByReference(productRequest.getReference());
				product.setUnits(productRequest.getUnits());
				order.addProduct(product);
				ProductOrder productOrder = new ProductOrder(product.getId(), entity.getId(), productRequest.getUnits());
				entity.addProduct(productOrderService.saveProductOrder(productOrder));
			}

			order.calculateTotalCost();
			order.calculateTotalOriginalCost();
			entity = mapper.toEntity(order, entity.getId());
			if (orderRequest.getClient() != null && orderRequest.getClient().getIdentification() != null)
				entity.setClient(clientMapper.toEntity(clientService.saveClient(order.getClient())));

			return (OrderSale) mapper.toDomain(repository.saveOrder(entity));
		}catch (Exception e) {
			throw new OrderException(e.getMessage());
		}
	}

	@Override
	public void deteleOrderById(Long id) {
		repository.deleteOrderById(id);
	}

	@Override
	public void cancelOrderById(Long id) throws Exception {
		OrderEntity entity = repository.getOrderById(id);
		double totalCostSale = 0;
		double totalOriginalCost = 0;
		
		for(ProductOrder productOrder: entity.getProducts()) {

			Product prod = productService.getProductById(productOrder.getProductId());

			totalCostSale = productOrder.getUnits() * prod.getCostSale();
			totalOriginalCost = productOrder.getUnits() * prod.getOriginalCost();

			cashService.removeCash(totalCostSale - totalOriginalCost, totalOriginalCost);
			productService.addUnitsToProduct(new ProductRequest(prod.getReference(), "", productOrder.getUnits()));

		}

		deteleOrderById(id);
	}

	@Override
	public void validateUnitsToOrder(List<ProductRequest> products) throws Exception {
		String productsWitoutUnits = Messages.MESSAGE_INSUFFICIENT_UNITS;
		
		for(ProductRequest product : products) {
			
			if(!productService.checkValidUnits(product))
				productsWitoutUnits = productsWitoutUnits + product.getReference() + "\n";
		}

		if(productsWitoutUnits != Messages.MESSAGE_INSUFFICIENT_UNITS)
			throw new OrderException(productsWitoutUnits);
	}

	@Override
	public OrderSeparate createoOrderSeparate(OrderSeparateRequest orderSeparate) throws Exception {
		validateUnitsToOrder(orderSeparate.getProducts());
		OrderSeparate order= OrderSeparate.buildOf(orderSeparate);
		

		if(order.getPayReceived() < order.getTotalOriginalCost())
			cashService.addCash(0, order.getPayReceived());
		else
			cashService.addCash(order.getTotalCost() - order.getTotalOriginalCost(), order.getTotalOriginalCost());
		

		return (OrderSeparate) mapper.toDomain(repository.saveOrder(mapper.toEntity(order, null)));
	}

	@Override
	public void finalizeOrdenSeparate(Long id) throws Exception {
		OrderSeparate order = (OrderSeparate) getOrderById(id);
		Cash cash = cashService.getCash();
		
		for(Product product : order.getProducts()) {
			
			double gain = product.getCostSale() - product.getOriginalCost();
			
			if(order.getPayReceived() < product.getOriginalCost()) {
				
				cash.setMinorCash(product.getOriginalCost() - order.getPayReceived());
			}else {
				
				double gainReceived = product.getCostSale() - order.getPayReceived();
				cash.setMajorCash(gain - gainReceived);
			}
				
		}	
	}

	
}
