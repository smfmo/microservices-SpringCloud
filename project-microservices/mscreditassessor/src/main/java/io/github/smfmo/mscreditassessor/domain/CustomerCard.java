package io.github.smfmo.mscreditassessor.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCard {

    private String name;
    private String cardsFlags;
    private BigDecimal limit;

}
