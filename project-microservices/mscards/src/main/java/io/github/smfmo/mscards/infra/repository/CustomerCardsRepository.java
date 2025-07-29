package io.github.smfmo.mscards.infra.repository;

import io.github.smfmo.mscards.domain.CustomerCards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerCardsRepository extends JpaRepository<CustomerCards, UUID> {
    List<CustomerCards> findByCpf(String cpf);
}
