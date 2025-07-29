package io.github.smfmo.mscreditassessor.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CardIssuanceData {
    private UUID cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}
