package guru.springframework.mssc.beer.inventory.service.web.controller;

import guru.springframework.mssc.beer.inventory.service.repository.BeerInventoryRepository;
import guru.springframework.mssc.beer.inventory.service.web.mapper.BeerInventoryMapper;
import guru.springframework.mssc.beer.inventory.service.web.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId) {
        log.debug("Finding Inventory for {beerId: {}}", beerId);

        List<BeerInventoryDto> beerInventories = beerInventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(toList());

        log.info("Found Inventories {total elements: {}}", beerInventories.size());
        return beerInventories;
    }

}
