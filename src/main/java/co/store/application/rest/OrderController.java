package co.store.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.store.application.request.order.OrderSaleRequest;
import co.store.domain.exception.ResException;
import co.store.domain.model.order.Order;
import co.store.domain.service.useCase.IOrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("localhost:4200")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping("/getOrder/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
		try {
			Order order = orderService.getOrderById(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(ResException.getBadException(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/createOrderSale")
	public ResponseEntity<?> createOrderSale(@RequestBody(required = true) OrderSaleRequest order) {
		try {
			return new ResponseEntity<>(orderService.createOrderSale(order), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(ResException.getBadException(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<?> deleteOrderById(@RequestParam(name = "id") Long id) {
		try {
			orderService.deteleOrderById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(ResException.getBadException(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/cancelOrder/{id}")
	public ResponseEntity<?> cancelOrderById(@PathVariable("id") Long id) {
		try {
			orderService.cancelOrderById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(ResException.getBadException(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
