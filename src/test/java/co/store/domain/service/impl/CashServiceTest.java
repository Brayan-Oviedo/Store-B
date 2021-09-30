package co.store.domain.service.impl;

import co.store.domain.exception.CashException;
import co.store.domain.exception.Messages;
import co.store.domain.model.Cash;
import co.store.infrastructure.repository.entity.CashEntity;
import co.store.infrastructure.repository.mapper.CashMapper;
import co.store.infrastructure.repository.repo.cash.CashRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CashServiceTest {

    @Mock
    private CashRepository repository;

    @Mock
    private CashMapper mapper;

    @InjectMocks
    private CashService service;

    private CashEntity cashEntity;
    private Cash cashDomain;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cashEntity = new CashEntity("Cash", 0, 0, 0);
        cashDomain = new Cash(0, 0, 0);
    }

    @Test
    public void testGetCashAllOk() throws Exception {
        List<CashEntity> cashes = new ArrayList<>();
        cashes.add(cashEntity);
        when(repository.getAll()).thenReturn(cashes);
        when(mapper.toDomain(cashEntity)).thenReturn(cashDomain);

        Cash cash = service.getCash();

        verify(repository).getAll();
        verify(mapper).toDomain(cashEntity);
        assertEquals(cashEntity.getName(), cash.getName());
    }

    @Test
    public void testGetCashWithCashNoExisting() throws Exception {
        List<CashEntity> cashes = new ArrayList<>();
        when(repository.getAll()).thenReturn(cashes);

        CashException exception = assertThrows(CashException.class, () -> service.getCash());

        verify(repository).getAll();
        assertEquals(Messages.MESSAGE_CASH_NO_EXISTING, exception.getMessage());
    }
}
