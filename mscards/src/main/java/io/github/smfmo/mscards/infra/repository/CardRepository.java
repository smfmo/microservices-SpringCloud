package io.github.smfmo.mscards.infra.repository;

import io.github.smfmo.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findByIncomeLessThanEqual(BigDecimal income);
}
