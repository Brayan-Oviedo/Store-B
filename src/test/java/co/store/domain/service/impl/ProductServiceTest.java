package co.store.domain.service.impl;

import co.store.application.request.ProductRequest;
import co.store.domain.exception.Messages;
import co.store.domain.exception.ProductException;
import co.store.domain.model.product.Product;
import co.store.domain.service.useCase.ICashService;
import co.store.infrastructure.repository.entity.product.ProductEntity;
import co.store.infrastructure.repository.mapper.ProductMapper;
import co.store.infrastructure.repository.repo.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// @RunWith(MockitoJUnitRunner.class)  /** OPTIONAL ++/
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private ICashService cashService;

    @InjectMocks
    private ProductService service;

    private static final String REF = "01";
    private Product product;
    private ProductEntity entity;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        product = new Product(REF, "", 1, "", 0, 0);
        entity = new ProductEntity(REF, "", 1, "", 0, 0, false);
    }

    @Test
    void testCheckValidUnitsAllOk() throws Exception {
        ProductRequest req = new ProductRequest(REF, "", 1);
        when(repository.getProductByReference(REF)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(product);

        boolean isValid = service.checkValidUnits(req);

        verify(repository).getProductByReference(REF);
        verify(mapper).toDomain(entity);
        assertTrue(isValid);
    }

    @Test
    void testCheckValidUnitsWithoutEnoughUnits() throws Exception {
        ProductRequest req = new ProductRequest(REF, "", 2);
        when(repository.getProductByReference(REF)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(product);

        boolean isValid = service.checkValidUnits(req);

        verify(repository).getProductByReference(REF);
        verify(mapper).toDomain(entity);
        assertFalse(isValid);
    }

    @Test
    void testRemoveUnitsWithoutEnoughUnits() {
        ProductRequest req = new ProductRequest(REF, "", 2);
        when(repository.getProductByReference(REF)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(product);

        ProductException exception = assertThrows(ProductException.class, () -> service.removeUnitsToProduct(req));

        verify(repository, times(2)).getProductByReference(REF);
        verify(mapper, times(2)).toDomain(entity);
        assertTrue(exception.getMessage().contains(Messages.MESSAGE_INSUFFICIENT_UNITS));
    }
}
