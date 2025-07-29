package io.github.smfmo.mscreditassessor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerFeedback {

    private List<ApprovedCard> approvedCards;

}
