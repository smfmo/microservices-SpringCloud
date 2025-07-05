package io.github.smfmo.mscards.application;

import io.github.smfmo.mscards.domain.Card;
import io.github.smfmo.mscards.infra.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card save(Card card) {
        return repository.save(card);
    }

    public List<Card> getCardsEqualLowerIncome(Long income) {
        var incomeBigdecimal = BigDecimal.valueOf(income);
       return repository.findByIncomeLessThanEqual(incomeBigdecimal);
    }
}
