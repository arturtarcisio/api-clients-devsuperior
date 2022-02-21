package io.github.arturtcs.apiclients.repositories;

import io.github.arturtcs.apiclients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
