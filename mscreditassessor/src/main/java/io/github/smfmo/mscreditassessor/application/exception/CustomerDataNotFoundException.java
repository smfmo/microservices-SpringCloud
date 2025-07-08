package io.github.smfmo.mscreditassessor.application.exception;

public class CustomerDataNotFoundException extends Exception {
  public CustomerDataNotFoundException() {
    super("Dados do cliente não encontrados para o CPF informado.");
  }
}
