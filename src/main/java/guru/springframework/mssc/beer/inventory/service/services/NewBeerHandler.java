package guru.springframework.mssc.beer.inventory.service.services;

import guru.springframework.mssc.beer.inventory.service.domain.BeerInventory;
import guru.cfg.brewery.model.events.NewBeerEvent;
import guru.springframework.mssc.beer.inventory.service.repository.BeerInventoryRepository;
import guru.cfg.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface NewBeerHandler {

    void handle(NewBeerEvent event);

    @Slf4j
    @Service
    @RequiredArgsConstructor
    class DefaultNewBeerHandler implements NewBeerHandler {

        private final BeerInventoryRepository beerInventoryRepository;

        @Transactional
        @Override
        public void handle(NewBeerEvent event) {
            log.trace("Handling new beer event: {}", event);

            BeerDto beerDto = event.getBeer();
            BeerInventory result = beerInventoryRepository.save(BeerInventory.builder()
                    .beerId(beerDto.getId())
                    .upc(beerDto.getUpc())
                    .quantityOnHand(beerDto.getQuantityOnHand())
                    .build());

            log.info("Handled new beer event: {result: {}}", result);
        }

    }

}
