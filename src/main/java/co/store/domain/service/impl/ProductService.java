package co.store.domain.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.store.application.request.ProductRequest;
import co.store.domain.exception.ProductException;
import co.store.domain.model.product.Product;
import co.store.domain.repository.IProductRepository;
import co.store.domain.service.useCase.ICashService;
import co.store.domain.service.useCase.IProductService;
import co.store.infrastructure.repository.entity.product.ProductEntity;
import co.store.infrastructure.repository.mapper.ProductMapper;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private IProductRepository repository;
	
	@Autowired
	private ICashService cashService;
	
	@Autowired
	private ProductMapper mapper;

	@Override
	public void sellProduct(ProductRequest productReq) throws Exception {
		
		Product product = getProductByReference(productReq.getReference());
		double totalCostSale = product.getCostSale() * productReq.getUnits();
		double totalOriginalCost = product.getOriginalCost() * productReq.getUnits();
		
		System.out.println(totalCostSale);
		System.out.println(totalOriginalCost);
		
		cashService.addCash(totalCostSale - totalOriginalCost, totalOriginalCost);
		removeUnitsToProduct(productReq);
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		
		if(repository.getProductByReference(product.getReference()) == null ) {
			ProductEntity entity = mapper.toEntityWithNewId(Product.buildOf(product));
			repository.saveProduct(entity);
			return;
		}
		
		throw new ProductException("");
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		
		ProductEntity entity = repository.getProductByReference(product.getReference());
		if(entity != null ) {
			BeanUtils.copyProperties(product, entity, "id");
			repository.saveProduct(entity);
			return;
		}
		
		throw new ProductException("Que falla");
	}

	@Override
	public boolean checkValidUnits(ProductRequest productReq) throws Exception {
		boolean isValid = true;
		Product product = getProductByReference(productReq.getReference());
		
		if(product != null) {
			ProductRequest prod = product;
			if(product.getUnits() < prod.getUnits())
				isValid = false;
		}
		
		return isValid;
	}

	@Override
	public Product getProductByReference(String reference) throws Exception {
		
		ProductEntity product = repository.getProductByReference(reference);
		
		if(product != null) 
			return mapper.toDomain(product);
		
		throw new ProductException("No existe ningun producto con esta referencia: " + reference);
	}
	
	@Override
	public Product getProductById(Long id) throws Exception {
		
		ProductEntity product = repository.getProductById(id);
		
		if(product != null) 
			return mapper.toDomain(product);
		
		throw new ProductException("No existe ningun producto con este id: " + id);
	}

	@Override
	public void addUnitsToProduct(ProductRequest productRequest) throws Exception {
		Product product = getProductByReference(productRequest.getReference());
		
		product.setUnits(productRequest.getUnits() + product.getUnits());
		
		updateProduct(product);
	}

	@Override
	public void removeUnitsToProduct(ProductRequest productRequest) throws Exception {
		Product product = getProductByReference(productRequest.getReference());
		
		if(product.getUnits() >= productRequest.getUnits()) {
			product.setUnits(product.getUnits() - productRequest.getUnits());
			
			updateProduct(product);
			
			return;
		}
		
		throw new ProductException("");
	}

	
}
