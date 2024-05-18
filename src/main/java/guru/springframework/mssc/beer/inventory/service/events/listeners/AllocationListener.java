package guru.springframework.mssc.beer.inventory.service.events.listeners;

import guru.cfg.brewery.model.messages.AllocateOrderRequest;
import guru.cfg.brewery.model.messages.NewBeerEvent;
import guru.springframework.mssc.beer.inventory.service.services.AllocationHandler;
import guru.springframework.mssc.beer.inventory.service.services.NewBeerHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static guru.springframework.mssc.beer.inventory.service.configuration.JmsConfiguration.ALLOCATING_ORDER_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationListener {

    private final AllocationHandler allocationHandler;

    @JmsListener(destination = ALLOCATING_ORDER_QUEUE)
    public void on(AllocateOrderRequest message) {
        allocationHandler.handle(message);
    }

}
