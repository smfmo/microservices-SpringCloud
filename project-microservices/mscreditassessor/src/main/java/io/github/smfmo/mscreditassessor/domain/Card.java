package io.github.smfmo.mscreditassessor.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Card {

    private UUID id;
    private String name;
    private String cardsFlags;
    private BigDecimal basicLimit;

}
