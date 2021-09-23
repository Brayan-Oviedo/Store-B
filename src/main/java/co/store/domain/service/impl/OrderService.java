package co.store.domain.service.impl;


import java.util.ArrayList;
import java.util.List;

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
import co.store.domain.service.useCase.ICashService;
import co.store.domain.service.useCase.IOrderService;
import co.store.domain.service.useCase.IProductOrderService;
import co.store.domain.service.useCase.IProductService;
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
	private ICashService cashService;
	
	@Autowired
	private OrderMapper mapper;

	@Override
	public Order getOrderById(Long id) throws Exception {
		
		return mapper.toDomain(repository.getOrderById(id));
	}

	@Override
	public Long createOrderSale(OrderSaleRequest orderRequest) throws Exception {
		validateUnitsToOrder(orderRequest.getProducts());
		OrderSale order = OrderSale.buildOf(orderRequest);
		List<ProductOrder> productOrders = new ArrayList<>();
		order.setId(repository.saveOrder(mapper.toEntityWithNewId(order)));
		
		for(ProductRequest productRequest : orderRequest.getProducts()) {
			productService.sellProduct(productRequest);
			Product product = productService.getProductByReference(productRequest.getReference());
			order.setProduct(product);
			ProductOrder productOrder= new ProductOrder(product.getId(), order.getId(), productRequest.getUnits());
			productOrders.add(productOrderService.saveProductOrder(productOrder));
		}	
		
		order.setTotalCost();
		order.setTotalOriginalCost();
		OrderEntity entity = mapper.toEntityWithId(order);
		entity.setProducts(productOrders);
		
		return repository.saveOrder(entity);
		
	}
	
	

	@Override
	public void deteleOrderById(Long id) {
		repository.deleteOrderById(id);
	}

	@Override
	public void cancelOrderById(Long id) throws Exception {
		OrderEntity entity = repository.getOrderById(id);
		System.out.println("s: " + entity.getId());
		//Order order =  mapper.toDomain(entity);
		double totalCostSale = 0;
		double totalOriginalCost = 0;
		
		System.out.println("size: " + entity.getProducts().size());
		
		for(ProductOrder productOrder: entity.getProducts()) {
			
			try {
				System.out.println(0);
				Product prod = productService.getProductById(productOrder.getProductId());
				
				System.out.println(productOrder.getProductId());
				
				totalCostSale = productOrder.getUnits() * prod.getCostSale();
				totalOriginalCost = productOrder.getUnits() * prod.getOriginalCost();
				
				System.out.println("tc: " + totalCostSale + ", toc: " + totalOriginalCost);
				cashService.removeCash(totalCostSale - totalOriginalCost, totalOriginalCost);
				productService.addUnitsToProduct(new ProductRequest(prod.getReference(), "", productOrder.getUnits()));
			} catch (Exception e) { }
		}
		
		repository.deleteOrderById(id);
	}

	@Override
	public void validateUnitsToOrder(List<ProductRequest> products) throws Exception {
		String productsWitoutUnits = Messages.MESSAGE_INSUFFICIENT_UNITS;
		
		for(ProductRequest product : products) {
			
			if(!productService.checkValidUnits(product))
				productsWitoutUnits = productsWitoutUnits + product.getReference() + " - " + product.getDescription() + ",\n";			
		}
		
		if(productsWitoutUnits != Messages.MESSAGE_INSUFFICIENT_UNITS)
			throw new OrderException(productsWitoutUnits);
	}

	@Override
	public Long createoOrderSeparate(OrderSeparateRequest orderSeparate) throws Exception {
		validateUnitsToOrder(orderSeparate.getProducts());
		OrderSeparate order= OrderSeparate.buildOf(orderSeparate);
		

		if(order.getPayReceived() < order.getTotalOriginalCost())
			cashService.addCash(0, order.getPayReceived());
		else
			cashService.addCash(order.getTotalCost() - order.getTotalOriginalCost(), order.getTotalOriginalCost());
		
		
		//order.getProducts().stream().ma
		return repository.saveOrder(mapper.toEntityWithNewId(order));
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
