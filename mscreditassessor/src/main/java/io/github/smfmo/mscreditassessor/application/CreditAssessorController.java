package io.github.smfmo.mscreditassessor.application;

import io.github.smfmo.mscreditassessor.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-assessments")
@RequiredArgsConstructor
public class CreditAssessorController {

    private final CreditAssessorService service;

    @GetMapping
    public String status(){
        return "OK";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity<CustomerSituation> getCustomerSituation(
            @RequestParam("cpf") String cpf){

        CustomerSituation situation = service.getCustomerStatus(cpf);

        return ResponseEntity.ok(situation);
    }
}
