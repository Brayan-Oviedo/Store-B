package co.store.infrastructure.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "co.store.infrastructure.repository.repo")
public class PostgresPersistenceDB {

}
