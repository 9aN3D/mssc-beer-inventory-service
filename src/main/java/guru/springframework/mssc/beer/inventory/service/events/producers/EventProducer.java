package guru.springframework.mssc.beer.inventory.service.events.producers;

import guru.cfg.brewery.model.messages.AllocateOrderResult;
import guru.cfg.brewery.model.messages.Message;

import static guru.springframework.mssc.beer.inventory.service.configuration.JmsConfiguration.ALLOCATING_ORDER_RESULT_QUEUE;

public interface EventProducer {

    void produce(String destination, Message message);

    default void produceToAllocateOrderResultQueue(AllocateOrderResult message) {
        produce(ALLOCATING_ORDER_RESULT_QUEUE, message);
    }

}
