package co.store.infrastructure.repository.repo.cash;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.store.domain.repository.ICashRepository;
import co.store.infrastructure.repository.entity.CashEntity;

@Component
@Primary
public class CashRepository implements ICashRepository {
	
	@Autowired
	private CashRepositoryDB repo;

	@Override
	public void saveCash(CashEntity cash) {
		repo.save(cash);
	}

	@Override
	public CashEntity getCashByName(String name) {
		return repo.findByName(name).orElse(null);
	}

	@Override
	public List<CashEntity> getAll() {
		return repo.findAll();
	}


}
