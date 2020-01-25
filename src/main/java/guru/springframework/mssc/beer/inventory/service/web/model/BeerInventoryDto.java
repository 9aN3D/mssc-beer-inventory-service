package guru.springframework.mssc.beer.inventory.service.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerInventoryDto {

    private UUID id;

    private OffsetDateTime createdDate;

    private OffsetDateTime lastModifiedDate;

    private UUID beerId;

    private String upc;

    private Integer quantityOnHand;

}
