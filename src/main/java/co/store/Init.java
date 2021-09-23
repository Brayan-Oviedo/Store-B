package co.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.store.domain.model.Cash;
import co.store.domain.model.product.Categories;
import co.store.domain.model.product.Product;
import co.store.domain.service.useCase.ICashService;
import co.store.domain.service.useCase.IProductService;




@Component
public class Init implements CommandLineRunner{

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICashService cashService;
	
	@Override
	public void run(String... args) throws Exception {
		
		// PRODUCTS
		Product P1 = new Product("P1", "T-SHIRT", 5, Categories.T_SHIRT.toString(), 1000, 1500);
		Product P2 = new Product("P2", "JACKET", 5, Categories.JACKET.toString(), 1000, 1500);
		Product P3 = new Product("P3", "COAT", 5, Categories.COAT.toString(), 1000, 1500);
		Product P4 = new Product("P4", "JEAN", 5, Categories.JEAN.toString(), 1000, 1500);
		
		// CASH
		Cash cash = new Cash(0, 0, 0);
		
		
/** ----------ESTO SOLO SE DEBE EJEUTAR UNA VEZ---------- **/
		
        productService.saveProduct(P1);
        productService.saveProduct(P2);
        productService.saveProduct(P3);
        productService.saveProduct(P4);
        
        cashService.saveCash(cash);
        
        
	}

}
