package co.store.domain.service.useCase;

import co.store.domain.model.Cash;

public interface ICashService {

	void addCash(double amountMajorCash, double amountMinorCash) throws Exception;
	void removeCash(double amountMajorCash, double amountMinorCash) throws Exception;
	void saveCash(Cash cash) throws Exception;
	void updateCash(Cash cash) throws Exception;
	Cash getCash() throws Exception;
}
