package guru.springframework.mssc.beer.inventory.service.events.producers;

import guru.cfg.brewery.model.messages.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class JmsEventProducer implements EventProducer {

    private final JmsTemplate jmsTemplate;

    @Override
    public void produce(String destination, Message message) {
        jmsTemplate.convertAndSend(destination, message);
    }

}
