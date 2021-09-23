package co.store.infrastructure.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.store.domain.model.Cash;
import co.store.infrastructure.repository.entity.CashEntity;

@Scope("singleton")
@Component
public class CashMapper {

	public Cash toDomain(CashEntity cashEntity) {
		Cash cash = new Cash();
		BeanUtils.copyProperties(cashEntity, cash);
		return cash;
	}
	
	public CashEntity toEntity(Cash cash) {
		CashEntity cashEntity = new CashEntity();
		BeanUtils.copyProperties(cash, cashEntity);
		return cashEntity;
	}
}
