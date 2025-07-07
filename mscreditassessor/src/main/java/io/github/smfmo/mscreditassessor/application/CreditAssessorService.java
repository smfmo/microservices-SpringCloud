package io.github.smfmo.mscreditassessor.application;

import io.github.smfmo.mscreditassessor.domain.CustomerData;
import io.github.smfmo.mscreditassessor.domain.CustomerSituation;
import io.github.smfmo.mscreditassessor.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final CustomerResourceClient customerResourceClient;

    public CustomerSituation getCustomerStatus(String cpf){
        // obter dados do cliente - msclients
        // obter cartões do cliente - mscards
       ResponseEntity<CustomerData> customerDataResponse = customerResourceClient.customerData(cpf);
       return CustomerSituation
               .builder()
               .customer(customerDataResponse.getBody())
               .build();
    }

}
