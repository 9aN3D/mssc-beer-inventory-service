package guru.springframework.mssc.beer.inventory.service.services;

import guru.cfg.brewery.model.BeerOrderDto;
import guru.cfg.brewery.model.BeerOrderLineDto;
import guru.springframework.mssc.beer.inventory.service.domain.BeerInventory;
import guru.springframework.mssc.beer.inventory.service.repository.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public interface AllocationService {

    boolean allocateOrder(BeerOrderDto beerOrder);

    void deallocateOrder(BeerOrderDto beerOrder);

    @Slf4j
    @Service
    @RequiredArgsConstructor
    class DefaultAllocationService implements AllocationService {

        private final BeerInventoryRepository beerInventoryRepository;

        @Transactional
        @Override
        public boolean allocateOrder(BeerOrderDto beerOrderDto) {
            log.debug("Allocating orderId: {}", beerOrderDto.getId());
            AtomicInteger totalOrdered = new AtomicInteger();
            AtomicInteger totalAllocated = new AtomicInteger();

            for (BeerOrderLineDto beerOrderLine : requireNonNull(beerOrderDto.getBeerOrderLines())) {
                int qtyToAllocate = beerOrderLine.calculateQtyToAllocate();
                if (nonNull(beerOrderLine.getUpc()) && qtyToAllocate > 0) {
                    beerInventoryRepository.findAllByUpc(beerOrderLine.getUpc()).forEach(beerInventory -> allocateBeerOrderLine(beerInventory, qtyToAllocate, beerOrderLine));
                }
                totalOrdered.set(totalOrdered.get() + beerOrderLine.getOrderQuantity());
                totalAllocated.set(totalAllocated.get() + beerOrderLine.getQuantityAllocatedOrZero());
            }
            log.info("Total Ordered: {} Total Allocated: {}", totalOrdered.get(), totalAllocated.get());
            return totalOrdered.get() == totalAllocated.get();
        }

        @Override
        public void deallocateOrder(BeerOrderDto beerOrder) {
            for (BeerInventory beerInventory : prepareBeerInventories(beerOrder.getBeerOrderLines())) {
                BeerInventory savedBeerInventory = beerInventoryRepository.save(beerInventory);
                log.debug("Saved Inventory for beer upc: {} inventory id: {}", savedBeerInventory.getUpc(), savedBeerInventory.getId());
            }
        }

        private void allocateBeerOrderLine(BeerInventory beerInventory, int qtyToAllocate, BeerOrderLineDto beerOrderLine) {
            int inventory = beerInventory.getQuantityOnHandOrZero();
            if (inventory >= qtyToAllocate) {
                allocateFull(beerInventory, beerOrderLine);
            }
            if (inventory > 0) {
                allocatePartial(beerInventory, beerOrderLine);
            }
        }

        private void allocatePartial(BeerInventory beerInventory, BeerOrderLineDto beerOrderLine) {
            beerOrderLine.setQuantityAllocated(beerOrderLine.getQuantityAllocatedOrZero() + beerInventory.getQuantityOnHandOrZero());
            beerInventory.setQuantityOnHand(0);
            beerInventoryRepository.delete(beerInventory);
        }

        private void allocateFull(BeerInventory beerInventory, BeerOrderLineDto beerOrderLine) {
            beerOrderLine.setQuantityAllocated(beerOrderLine.getOrderQuantityOrZero());
            beerInventory.setQuantityOnHand(beerInventory.getQuantityOnHandOrZero() - beerOrderLine.calculateQtyToAllocate());

            beerInventoryRepository.save(beerInventory);
        }

        private List<BeerInventory> prepareBeerInventories(List<BeerOrderLineDto> beerOrderLines) {
            return isNull(beerOrderLines)
                    ? List.of()
                    : beerOrderLines.stream()
                    .map(BeerInventory::of)
                    .collect(Collectors.toList());
        }

    }

}
