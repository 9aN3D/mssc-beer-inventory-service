package guru.springframework.mssc.beer.inventory.service.services;

import guru.cfg.brewery.model.messages.AllocateOrderRequest;
import guru.cfg.brewery.model.messages.AllocateOrderResult;
import guru.cfg.brewery.model.messages.AllocateOrderResult.AllocateOrderResultBuilder;
import guru.springframework.mssc.beer.inventory.service.events.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

public interface AllocationHandler {

    void handle(AllocateOrderRequest request);

    @Slf4j
    @Service
    @RequiredArgsConstructor
    class DefaultAllocationHandler implements AllocationHandler {

        private final AllocationService allocationService;
        private final EventProducer eventProducer;

        @Override
        public void handle(AllocateOrderRequest request) {
            log.trace("Handling AllocateOrderRequest: {}", request);

            AllocateOrderResultBuilder resultBuilder = AllocateOrderResult.builder();
            resultBuilder.beerOrder(request.getBeerOrder());
            try {
                requireNonNull(request.getBeerOrder());
                resultBuilder.pendingInventory(allocationService.allocateOrder(request.getBeerOrder()));
            } catch (Exception ex) {
                log.error("Failed to handle AllocateOrderRequest: {}", request.getBeerOrder(), ex);
                resultBuilder.error(true);
            }
            eventProducer.produceToAllocateOrderResultQueue(resultBuilder.build());

            log.trace("Handled AllocateOrderRequest: {}", request);
        }

    }

}
