package co.store.infrastructure.repository.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.store.domain.model.Client;
import co.store.infrastructure.repository.entity.ClientEntity;

@Scope("singleton")
@Component
public class ClientMapper {

	public Client toDomain(ClientEntity clientEntity) {
		Client client = new Client();
		BeanUtils.copyProperties(clientEntity, client);
		return client;
	}
	
	public ClientEntity toEntity(Client client) {
		ClientEntity clienEntity = new ClientEntity();
		BeanUtils.copyProperties(client, clienEntity);
		return clienEntity;
	}
}
