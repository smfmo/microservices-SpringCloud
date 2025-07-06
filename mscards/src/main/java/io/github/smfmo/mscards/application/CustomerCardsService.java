package io.github.smfmo.mscards.application;

import io.github.smfmo.mscards.domain.CustomerCards;
import io.github.smfmo.mscards.infra.repository.CustomerCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardsService {

    private final CustomerCardsRepository repository;

    public List<CustomerCards> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
