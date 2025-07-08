package io.github.smfmo.mscreditassessor.application;

import io.github.smfmo.mscreditassessor.application.exception.CustomerDataNotFoundException;
import io.github.smfmo.mscreditassessor.application.exception.MicroservicesCommunicationErrorException;
import io.github.smfmo.mscreditassessor.domain.AssessmentData;
import io.github.smfmo.mscreditassessor.domain.CustomerFeedback;
import io.github.smfmo.mscreditassessor.domain.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> carryOutAssessments(@RequestBody AssessmentData data){
        try{
            CustomerFeedback feedback = service.carryOutAssessment(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(feedback);

        }catch (CustomerDataNotFoundException e){
            return ResponseEntity.notFound().build();

        } catch (MicroservicesCommunicationErrorException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusCode())).body(e.getMessage());
        }
    }
}
