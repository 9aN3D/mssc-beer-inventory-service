package guru.springframework.mssc.beer.inventory.service.web.mapper;

import guru.springframework.mssc.beer.inventory.service.domain.BeerInventory;
import guru.cfg.brewery.model.BeerInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);

}
