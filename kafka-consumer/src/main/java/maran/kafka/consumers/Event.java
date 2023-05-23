package maran.kafka.consumers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String test;
    private String test2;
    private List<String> test3;
}


