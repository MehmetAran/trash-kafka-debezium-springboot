package maran.kafka.consumers;

import lombok.RequiredArgsConstructor;
import maran.kafka.publisher.KafkaPublisher;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.channels.AlreadyConnectedException;
import java.util.jar.JarException;

@Component
@RequiredArgsConstructor
public class Consumer {
    private final KafkaPublisher kafkaPublisher;

    @KafkaListener(topics = {"maran"}, groupId = "213123r2ed23", containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload Event event) throws Exception {
        try {
            consume(event);
        } catch (AlreadyConnectedException ex) {
        }
        catch (JarException exception) {
            kafkaPublisher.publish("maran.maranconsumer.RETRY", event);
        }
    }

    @KafkaListener(topics = {"maran.maranconsumer.RETRY"}, groupId = "213123r2ed23", containerFactory = "kafkaListenerContainerFactory")
    public void listener2(@Payload Event event, @Headers MessageHeaders headers) throws Exception {
        try {
            consume(event);
        } catch (AlreadyConnectedException ex) {
        }
        catch (JarException exception) {
            kafkaPublisher.publish("maran.maranconsumer.ERROR", event,headers);
        }
    }

    private void consume(Event event) throws Exception {
        System.out.println(event);
        if (event.getTest().equals("error")) {
            throw new AlreadyConnectedException();
        } else if (event.getTest().equals("sendRetryError")) {
            throw new JarException();
        }
    }

}
