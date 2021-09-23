package co.store.infrastructure.repository.repo.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.store.infrastructure.repository.entity.order.OrderEntity;

@Repository
public interface OrderRepositoryDB extends JpaRepository<OrderEntity, Long>{

}
