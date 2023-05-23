package maran.kafka.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topicName, Object message) {
        kafkaTemplate.send(topicName, message);
    }

    public void publish(String topicName, Object value, MessageHeaders headers) {
        Object retryCount = headers.getOrDefault("RetryCount", 1);
        System.out.println("retryCount" + retryCount);
        Message<Object> message = MessageBuilder
                .withPayload(value)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader("RetryCount",retryCount)
                .build();
        kafkaTemplate.send(message);
    }
}
