package co.store.domain.service.impl;

import co.store.domain.exception.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.store.domain.exception.ClientException;
import co.store.domain.model.Client;
import co.store.domain.repository.IClientRepository;
import co.store.domain.service.useCase.IClientService;
import co.store.infrastructure.repository.entity.ClientEntity;
import co.store.infrastructure.repository.mapper.ClientMapper;

@Service
public class ClientService implements IClientService{
	
	@Autowired
	private IClientRepository repository;

	@Autowired
	private ClientMapper mapper;

	@Override
	public Client saveClient(Client client) throws Exception {
		return mapper.toDomain(repository.saveClient(mapper.toEntity(client)));
	}

	@Override
	public void updateClient(Client client) throws Exception {
		ClientEntity entity = repository.getClientByIdentification(client.getIdentification());
		
		if(entity != null)
			repository.saveClient(mapper.toEntity(client));
		
		throw new ClientException(Messages.MESSAGE_CLIENT_NO_EXISTING);
	}

	@Override
	public Client getClientByIdentification(String identification) throws Exception {
		ClientEntity client = repository.getClientByIdentification(identification);
		
		if(client != null) 
			return mapper.toDomain(client);
		
		throw new ClientException(Messages.MESSAGE_CLIENT_NO_EXISTING);
	}

	@Override
	public void deleteClientByIdentification(String identification) {
		repository.deleteClientByIdentification(identification);
	}

	
	
}
