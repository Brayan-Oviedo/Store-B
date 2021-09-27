package co.store.domain.service.useCase;


import co.store.application.request.ProductRequest;
import co.store.domain.model.product.Product;

import java.util.List;

public interface IProductService {

	void sellProduct(ProductRequest productReq) throws Exception;
	List<Product> getAllProducts();
	void saveProduct(Product product) throws Exception; 
	void updateProduct(Product product) throws Exception; 
	boolean checkValidUnits(ProductRequest productReq) throws Exception;
	Product getProductByReference(String reference) throws Exception;
	Product getProductById(Long id) throws Exception;
	void addUnitsToProduct(ProductRequest productRequest) throws Exception;
	void removeUnitsToProduct(ProductRequest productRequest) throws Exception;
}
