package guru.springframework.mssc.beer.inventory.service.domain;

import guru.cfg.brewery.model.BeerOrderLineDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

import static java.util.Objects.isNull;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class BeerInventory extends BaseEntity {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID beerId;

    private String upc;

    private Integer quantityOnHand = 0;

    @Builder
    public BeerInventory(UUID id,
                         Long version,
                         Timestamp createdDate,
                         Timestamp lastModifiedDate,
                         UUID beerId,
                         String upc,
                         Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
    }

    public int getQuantityOnHandOrZero() {
        return isNull(quantityOnHand) ? 0 : quantityOnHand;
    }

    public static BeerInventory of(BeerOrderLineDto beerOrderLineDto) {
        return BeerInventory.builder()
                .beerId(beerOrderLineDto.getBeerId())
                .upc(beerOrderLineDto.getUpc())
                .quantityOnHand(beerOrderLineDto.getOrderQuantityOrZero())
                .build();
    }

}
