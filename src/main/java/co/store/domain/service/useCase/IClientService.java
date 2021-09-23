package co.store.domain.service.useCase;

import co.store.domain.model.Client;

public interface IClientService {

	void saveClient(Client client) throws Exception; 
	void updateClient(Client client) throws Exception; 
	Client getClientByIdentification(String identification) throws Exception;
	void deleteClientByIdentification(String identification);
}
