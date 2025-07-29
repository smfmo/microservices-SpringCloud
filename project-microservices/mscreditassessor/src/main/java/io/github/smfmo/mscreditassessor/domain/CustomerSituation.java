package io.github.smfmo.mscreditassessor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSituation {

    private CustomerData customer;
    private List<CustomerCard> cards;

}
