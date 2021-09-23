package co.store.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.store.domain.exception.CashException;
import co.store.domain.model.Cash;
import co.store.domain.repository.ICashRepository;
import co.store.domain.service.useCase.ICashService;
import co.store.infrastructure.repository.entity.CashEntity;
import co.store.infrastructure.repository.mapper.CashMapper;

@Service
public class CashService implements ICashService {
	
	@Autowired
	private ICashRepository repository;
	
	@Autowired
	private CashMapper mapper;


	@Override
	public void saveCash(Cash cash) throws Exception {
		if(repository.getAll().size() < 1) 
			repository.saveCash(mapper.toEntity(cash));
		else 
			throw new CashException("");
		
	}

	@Override
	public void updateCash(Cash cash) throws Exception {
		if(repository.getCashByName(cash.getName()) != null) 
			repository.saveCash(mapper.toEntity(cash));
		else
			throw new CashException("");
	}

	@Override
	public void addCash(double amountMajorCash, double amountMinorCash) throws Exception {
		
		Cash cash = getCash();
		
		cash.setMajorCash(amountMajorCash);
		cash.setMinorCash(amountMinorCash);
		
		updateCash(cash);
	}

	@Override
	public Cash getCash() throws Exception {
		List<CashEntity> cashes = repository.getAll();
		
		if(cashes.size() > 0) {
			return mapper.toDomain(cashes.get(0));
		}
		
		throw new CashException("No se encuentra ninguna caja registrada."); 
	}

	@Override
	public void removeCash(double amountMajorCash, double amountMinorCash) throws Exception {
		
		Cash cash = getCash();
		
		cash.setMajorCash(-amountMajorCash);
		cash.setMinorCash(-amountMinorCash);
		
		updateCash(cash);
	}

}
