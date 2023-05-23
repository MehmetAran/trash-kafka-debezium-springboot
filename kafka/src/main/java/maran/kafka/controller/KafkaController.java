package maran.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import maran.kafka.infrastructure.publisher.KafkaPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaPublisher kafkaPublisher;
    @PostMapping("/{id}")
    public void triggerSendingKafkaEvent(@PathVariable  String id, @RequestBody Event event) throws JsonProcessingException {
        kafkaPublisher.publishOrOutbox("maran",id, event);
    }
}
