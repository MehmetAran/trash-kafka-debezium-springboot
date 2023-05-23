package maran.kafka.consumers;

import lombok.RequiredArgsConstructor;
import maran.kafka.publisher.KafkaPublisher;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class ConsumerOutbox {
    private final KafkaPublisher kafkaPublisher;

    @KafkaListener(topics = {"maran.public.outbox"}, groupId = "213123r2ed23", containerFactory = "kafkaListenerContainerFactory")
    public void listener(ConsumerRecord<String,PayloadWrapper> payloadWrapper) throws Exception {
        System.out.println(payloadWrapper);
    }
}
