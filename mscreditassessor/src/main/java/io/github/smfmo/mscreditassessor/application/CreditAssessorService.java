package io.github.smfmo.mscreditassessor.application;

import feign.FeignException;
import io.github.smfmo.mscreditassessor.application.exception.CustomerDataNotFoundException;
import io.github.smfmo.mscreditassessor.application.exception.MicroservicesCommunicationErrorException;
import io.github.smfmo.mscreditassessor.domain.CustomerCard;
import io.github.smfmo.mscreditassessor.domain.CustomerData;
import io.github.smfmo.mscreditassessor.domain.CustomerSituation;
import io.github.smfmo.mscreditassessor.infra.clients.CardsResourceClient;
import io.github.smfmo.mscreditassessor.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
