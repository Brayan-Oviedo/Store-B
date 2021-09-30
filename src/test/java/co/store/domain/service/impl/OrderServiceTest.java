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

    /*@Mock
    private OrderRepository repository;

    @Mock
    private OrderMapper mapper;*/

    @Mock
    private ProductService productService;

   /* @Mock
    private ProductOrderService productOrderService;

    @Mock
    private CashService cashService;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientMapper clientMapper;*/

    @InjectMocks
    private OrderService service;

   /* private Order order;
    private OrderEntity orderEntity;
    private Client client;
    private ClientEntity clientEntity;
    private Product product;
    List<ProductOrder> list;*/
    private OrderSaleRequest orderSaleRequest;
    List<ProductRequest> listRequest;

    private static final Long ID = 1L;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        listRequest = new ArrayList();
        listRequest.add(new ProductRequest(ID.toString(), "", 1));
        orderSaleRequest = new OrderSaleRequest(listRequest, null);

        /*
        product = new Product(id.toString(), "", 1, "", 0, 0);
        client = new Client("Fan", id.toString(), 1);
        clientEntity = new ClientEntity("Fan", id.toString(), new ArrayList<>(), 1);



        order = new OrderSale().buildOf(orderSaleRequest);

        list = new ArrayList();
        list.add(new ProductOrder(id, id, 1));
        orderEntity = new OrderSaleEntity(list, clientEntity);
        orderEntity.setId(id); */
    }

    @Test
    void createOrderSaleAllOkWithClient() throws Exception {
       /* orderSaleRequest.setClient(client);
        when(productService.checkValidUnits(listRequest.get(0))).thenReturn(true);
        when(repository.saveOrder(orderEntity)).thenReturn(orderEntity);
        when(repository.saveOrder(new OrderSaleEntity())).thenReturn(orderEntity);
        when(productService.getProductByReference(id.toString())).thenReturn(product);
        when(productOrderService.saveProductOrder(list.get(0))).thenReturn(list.get(0));
        when(mapper.toEntity(order, id)).thenReturn(orderEntity);
        when(mapper.toDomain(orderEntity)).thenReturn(order);
        when(clientMapper.toEntity(client)).thenReturn(clientEntity);
        when(clientMapper.toDomain(clientEntity)).thenReturn(client);
        when(clientService.saveClient(client)).thenReturn(client);

        Order order = service.createOrderSale(orderSaleRequest);

        verify(productService).checkValidUnits(listRequest.get(0));
        verify(repository).saveOrder(orderEntity);
        verify(repository).saveOrder(new OrderSaleEntity());
        verify(productService).getProductByReference(id.toString());
        verify(productOrderService).saveProductOrder(list.get(0));
        verify(mapper).toEntity(order, id);
        verify(mapper).toDomain(orderEntity);
        verify(clientMapper).toDomain(clientEntity);
        verify(clientMapper).toEntity(client);
        verify(clientService).saveClient(client);

        assertSame(order.getClient(), orderSaleRequest.getClient());*/
    }

    @Test
    void createOrderSaleAllOkWithoutClient() throws Exception {
       /* when(productService.checkValidUnits(listRequest.get(0))).thenReturn(true);
        when(repository.saveOrder(orderEntity)).thenReturn(orderEntity);
        when(repository.saveOrder(new OrderSaleEntity())).thenReturn(orderEntity);
        when(productService.getProductByReference(id.toString())).thenReturn(product);
        when(productOrderService.saveProductOrder(list.get(0))).thenReturn(list.get(0));
        when(mapper.toEntity(order, id)).thenReturn(orderEntity);
        when(mapper.toDomain(orderEntity)).thenReturn(order);

        Order order = service.createOrderSale(orderSaleRequest);

        verify(productService).checkValidUnits(listRequest.get(0));
        verify(repository).saveOrder(orderEntity);
        verify(repository).saveOrder(new OrderSaleEntity());
        verify(productService).getProductByReference(id.toString());
        verify(productOrderService).saveProductOrder(list.get(0));
        verify(mapper).toEntity(order, id);
        verify(mapper).toDomain(orderEntity);

        assertSame(order.getDate(), orderEntity.getDate());*/
    }

    @Test
    void validateUnitsToOrderWithoutEnoughUnits() throws Exception {
        when(productService.checkValidUnits(listRequest.get(0))).thenReturn(false);

        OrderException exception = assertThrows(OrderException.class, () -> service.validateUnitsToOrder(listRequest));

        verify(productService).checkValidUnits(listRequest.get(0));
        assertTrue(exception.getMessage().contains(Messages.MESSAGE_INSUFFICIENT_UNITS));
    }
}