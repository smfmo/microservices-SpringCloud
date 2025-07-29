package io.github.smfmo.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.smfmo.mscards.domain.Card;
import io.github.smfmo.mscards.domain.CardIssuanceData;
import io.github.smfmo.mscards.domain.CustomerCards;
import io.github.smfmo.mscards.infra.repository.CardRepository;
import io.github.smfmo.mscards.infra.repository.CustomerCardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IssueCardSubscriber {

    private final CardRepository cardRepository;
    private final CustomerCardsRepository customerCardsRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveIssuanceRequest(@Payload String payload) {
        try{
            var mapper = new ObjectMapper();
            CardIssuanceData data = mapper.readValue(payload, CardIssuanceData.class);
            Card card = cardRepository.findById(data.getCardId()).orElseThrow();

            CustomerCards customerCards = new CustomerCards();
            customerCards.setCard(card);
            customerCards.setCpf(data.getCpf());
            customerCards.setLimit(data.getLimitReleased());

            customerCardsRepository.save(customerCards);

        } catch (Exception e) {
            log.error("Erro ao receber solicitação de emissão de cartão: {}", e.getMessage());
        }
    }
}
