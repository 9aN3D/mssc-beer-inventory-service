package guru.springframework.mssc.beer.inventory.service.events.listeners;

import guru.cfg.brewery.model.messages.DeallocateOrderRequest;
import guru.springframework.mssc.beer.inventory.service.services.AllocationHandler;
import guru.springframework.mssc.beer.inventory.service.services.DeallocationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static guru.springframework.mssc.beer.inventory.service.configuration.JmsConfiguration.DEALLOCATING_ORDER_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeallocationListener {

    private final DeallocationHandler deallocationHandler;

    @JmsListener(destination = DEALLOCATING_ORDER_QUEUE)
    public void on(DeallocateOrderRequest message) {
        deallocationHandler.handle(message);
    }

}
