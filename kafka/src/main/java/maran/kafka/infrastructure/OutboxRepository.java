package maran.kafka.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OutboxRepository extends JpaRepository<Outbox, Long> {
}
