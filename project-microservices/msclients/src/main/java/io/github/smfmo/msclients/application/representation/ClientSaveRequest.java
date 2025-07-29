package io.github.smfmo.msclients.application.representation;

import io.github.smfmo.msclients.domain.Client;

public record ClientSaveRequest(
        String cpf,
        String name,
        Integer age) {

    public Client toModel() {
        return new Client(cpf, name, age);
    }
}
