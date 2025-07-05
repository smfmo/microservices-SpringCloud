package io.github.smfmo.mscards.application.representation;

import io.github.smfmo.mscards.domain.Card;
import io.github.smfmo.mscards.domain.Flags;

import java.math.BigDecimal;

public record CardSaveRequest(String name,
                              Flags cardsFlags,
                              BigDecimal income,
                              BigDecimal basicLimit) {
    public Card toModel(){
        return new Card(name, cardsFlags, income, basicLimit);
    }
}
