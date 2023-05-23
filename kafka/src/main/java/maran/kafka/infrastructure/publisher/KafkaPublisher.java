package maran.kafka.infrastructure.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import maran.kafka.infrastructure.Outbox;
import maran.kafka.infrastructure.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class KafkaPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OutboxRepository outboxRepository;


    @Transactional
    public void publishOrOutbox(String topicName, String key, Object message) throws JsonProcessingException {
        try {
            kafkaTemplate.send(topicName, key, message);
        } catch (Exception ex) {
            outboxRepository.save(new Outbox(topicName, key, topicName, message));
        }
    }
}
