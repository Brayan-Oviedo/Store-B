package co.store.domain.repository;

import co.store.infrastructure.repository.entity.ClientEntity;

public interface IClientRepository {

	void saveClient(ClientEntity client); 
	ClientEntity getClientByIdentification(String identification);
	void deleteClientByIdentification(String identification);
}
