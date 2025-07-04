package io.github.smfmo.msclients.application;

import io.github.smfmo.msclients.domain.Client;
import io.github.smfmo.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
