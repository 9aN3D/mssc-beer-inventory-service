package guru.springframework.mssc.beer.inventory.service.services;

import guru.cfg.brewery.model.messages.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface DeallocationHandler {

    void handle(DeallocateOrderRequest message);

    @Slf4j
    @Service
    @RequiredArgsConstructor
    class DefaultDeallocationHandler implements DeallocationHandler {

        private final AllocationService allocationService;

        @Override
        public void handle(DeallocateOrderRequest request) {
            log.trace("Handling DeallocateOrderRequest: {}", request);

            allocationService.deallocateOrder(request.getBeerOrder());

            log.trace("Handled DeallocateOrderRequest: {}", request);
        }

    }

}
