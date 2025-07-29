package io.github.smfmo.mscards.application;

import io.github.smfmo.mscards.application.representation.CardSaveRequest;
import io.github.smfmo.mscards.application.representation.CardsPerCustomerResponse;
import io.github.smfmo.mscards.domain.Card;
import io.github.smfmo.mscards.domain.CustomerCards;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {

    private final CardService cardService;
    private final CustomerCardsService customerCardsService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CardSaveRequest request){
        var card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncome(@RequestParam("income") Long income){
        List<Card> cardsList = cardService.getCardsEqualLowerIncome(income);
        return ResponseEntity.ok(cardsList);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerCustomerResponse>> getCardsByCustomer(
            @RequestParam("cpf") String cpf){
        List<CustomerCards> list = customerCardsService.listCardsByCpf(cpf);
        List<CardsPerCustomerResponse> resultList = list
                .stream().map(CardsPerCustomerResponse::fromModel)
                .toList();

        return ResponseEntity.ok(resultList);
    }
}
