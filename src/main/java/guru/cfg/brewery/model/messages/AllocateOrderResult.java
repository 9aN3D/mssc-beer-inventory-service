package guru.cfg.brewery.model.messages;

import guru.cfg.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocateOrderResult implements Message {

    private static final long serialVersionUID = -7899258823593909559L;

    private BeerOrderDto beerOrder;
    @Builder.Default
    private Boolean error = false;
    @Builder.Default
    private Boolean pendingInventory = false;

}
