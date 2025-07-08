package io.github.smfmo.mscreditassessor.application;

import feign.FeignException;
import io.github.smfmo.mscreditassessor.application.exception.CustomerDataNotFoundException;
import io.github.smfmo.mscreditassessor.application.exception.MicroservicesCommunicationErrorException;
import io.github.smfmo.mscreditassessor.domain.*;
import io.github.smfmo.mscreditassessor.infra.clients.CardsResourceClient;
import io.github.smfmo.mscreditassessor.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final CustomerResourceClient customerResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public CustomerSituation getCustomerStatus(String cpf)
            throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException {
        try{
            ResponseEntity<CustomerData> customerDataResponse = customerResourceClient.customerData(cpf);
            ResponseEntity<List<CustomerCard>> cardsReponse =  cardsResourceClient.getCardsByCustomer(cpf);
            return CustomerSituation
                    .builder()
                    .customer(customerDataResponse.getBody())
                    .cards(cardsReponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
        }
    }

    public CustomerFeedback carryOutAssessment(String cpf, Long income)
            throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException {
        try{
            ResponseEntity<CustomerData> customerDataResponse = customerResourceClient.customerData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsResourceClient.getCardsIncome(income);

           List<Card> cards = cardsResponse.getBody();
           var approvedCardsList = cards.stream().map(card -> {

                CustomerData customerData = customerDataResponse.getBody();
                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBd = BigDecimal.valueOf(customerData.getAge());

                var fator = ageBd.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = fator.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setFlag(card.getCardsFlags());
                approvedCard.setApprovedLimit(approvedLimit);

                return approvedCard;
           }).toList();

           return new CustomerFeedback(approvedCardsList);

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
        }

    }
}
