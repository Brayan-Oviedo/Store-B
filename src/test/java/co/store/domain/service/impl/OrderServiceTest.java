package co.store.domain.service.impl;

import co.store.application.request.ProductRequest;
import co.store.application.request.order.OrderSaleRequest;
import co.store.domain.exception.Messages;
import co.store.domain.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService service;

    private OrderSaleRequest orderSaleRequest;
    List<ProductRequest> listRequest;

    private static final Long ID = 1L;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        listRequest = new ArrayList();
        listRequest.add(new ProductRequest(ID.toString(), "", 1));
        orderSaleRequest = new OrderSaleRequest(listRequest, null);

    }

    @Test
    void validateUnitsToOrderWithoutEnoughUnits() throws Exception {
        when(productService.checkValidUnits(listRequest.get(0))).thenReturn(false);

        OrderException exception = assertThrows(OrderException.class, () -> service.validateUnitsToOrder(listRequest));

        verify(productService).checkValidUnits(listRequest.get(0));
        assertTrue(exception.getMessage().contains(Messages.MESSAGE_INSUFFICIENT_UNITS));
    }
}