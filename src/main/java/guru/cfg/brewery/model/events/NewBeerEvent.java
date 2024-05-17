package guru.cfg.brewery.model.events;

import guru.cfg.brewery.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewBeerEvent implements Serializable {

    private static final long serialVersionUID = 103745828558417299L;

    private BeerDto beer;

}
