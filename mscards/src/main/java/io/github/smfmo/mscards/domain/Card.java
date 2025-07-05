package io.github.smfmo.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(schema = "public",
        name = "card")
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "cards_flags")
    @Enumerated(EnumType.STRING)
    private Flags cardsFlags;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "basic_limit")
    private BigDecimal basicLimit;

    public Card(String name, Flags cardsFlags,
                BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.cardsFlags = cardsFlags;
        this.income = income;
        this.basicLimit = basicLimit;
    }

}
