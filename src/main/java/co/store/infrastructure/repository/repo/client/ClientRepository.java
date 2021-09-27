package co.store.infrastructure.repository.repo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import co.store.domain.repository.IClientRepository;
import co.store.infrastructure.repository.entity.ClientEntity;

@Component
@Primary
public class ClientRepository implements IClientRepository {

	@Autowired
	private ClientRepositoryDB repo;
	
	@Override
	public ClientEntity saveClient(ClientEntity client) {
		return repo.save(client);
	}

	@Override
	public ClientEntity getClientByIdentification(String identification) {
		return repo.findByIdentification(identification).orElse(null);
	}

	@Override
	public void deleteClientByIdentification(String identification) {
		repo.deleteByIdentification(identification);
	}

	
}
