package io.github.smfmo.mscards.application.representation;

import io.github.smfmo.mscards.domain.CustomerCards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardsPerCustomerResponse{

    private String name;
    private String cardsFlags;
    private BigDecimal limit;

    public static CardsPerCustomerResponse fromModel(CustomerCards model){
        return new CardsPerCustomerResponse(
                model.getCard().getName(),
                model.getCard().getCardsFlags().toString(),
                model.getLimit()
        );
    }
}
