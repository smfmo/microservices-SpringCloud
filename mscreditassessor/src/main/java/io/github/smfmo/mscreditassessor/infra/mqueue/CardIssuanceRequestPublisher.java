package io.github.smfmo.mscreditassessor.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.smfmo.mscreditassessor.domain.CardIssuanceData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceRequestPublisher {

    private final RabbitTemplate template;
    private final Queue cardIssuanceQueue;

    public void requestCard(CardIssuanceData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        template.convertAndSend(cardIssuanceQueue.getName(), json);
    }

    private String convertIntoJson(CardIssuanceData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
