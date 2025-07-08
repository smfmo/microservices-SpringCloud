package io.github.smfmo.mscreditassessor.application;

import io.github.smfmo.mscreditassessor.application.exception.CustomerDataNotFoundException;
import io.github.smfmo.mscreditassessor.application.exception.MicroservicesCommunicationErrorException;
import io.github.smfmo.mscreditassessor.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getCustomerSituation(
            @RequestParam("cpf") String cpf) {
        try{
            CustomerSituation situation = service.getCustomerStatus(cpf);
            return ResponseEntity.ok(situation);

        }catch (CustomerDataNotFoundException e){
            return ResponseEntity.notFound().build();

        } catch (MicroservicesCommunicationErrorException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusCode())).body(e.getMessage());
        }

    }
}
