package guru.springframework.mssc.beer.inventory.service.repository;

import guru.springframework.mssc.beer.inventory.service.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

@Repository
public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {

    List<BeerInventory> findAllByBeerId(UUID beerId);

    List<BeerInventory> findAllByUpc(String upc);

    @SuppressWarnings("unchecked")
    @Override
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    BeerInventory save(BeerInventory beerInventory);

}
