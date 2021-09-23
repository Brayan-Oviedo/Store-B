package co.store.domain.repository;

import java.util.List;

import co.store.infrastructure.repository.entity.CashEntity;

public interface ICashRepository {
	
	void saveCash(CashEntity cash);
	CashEntity getCashByName(String name);
	List<CashEntity> getAll();
	
}
