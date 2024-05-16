package guru.springframework.mssc.beer.inventory.service.events.listeners;

import guru.springframework.mssc.beer.inventory.service.configuration.JmsConfiguration;
import guru.springframework.mssc.common.events.NewBeerEvent;
import guru.springframework.mssc.beer.inventory.service.services.NewBeerHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewBeerListener {

    private final NewBeerHandler newBeerHandler;

    @JmsListener(destination = JmsConfiguration.NEW_INVENTORY_QUEUE)
    public void on(NewBeerEvent newBeerEvent) {
        newBeerHandler.handle(newBeerEvent);
    }

}
