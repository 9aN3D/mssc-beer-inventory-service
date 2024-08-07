package guru.cfg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderLineDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("version")
    private Integer version;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @JsonProperty("lastModifiedDate")
    private OffsetDateTime lastModifiedDate;

    private String upc;

    private String beerName;

    private BeerStyle beerStyle;

    private UUID beerId;

    private Integer orderQuantity = 0;

    private Integer quantityAllocated;

    private BigDecimal price;

    @JsonIgnore
    public int getOrderQuantityOrZero() {
        return isNull(orderQuantity)
                ? 0
                : orderQuantity;
    }

    @JsonIgnore
    public int getQuantityAllocatedOrZero() {
        return isNull(quantityAllocated)
                ? 0
                : quantityAllocated;
    }

    @JsonIgnore
    public int calculateQtyToAllocate() {
        return getOrderQuantityOrZero() - getQuantityAllocatedOrZero();
    }

}
