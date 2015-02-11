package encens.khipus.services.purchases;

import encens.khipus.model.purchases.InvArticulos;

import java.util.List;

/**
 * Created by Diego on 09/02/2015.
 */

public interface ServiceInvArticulos {
    InvArticulos finByCodArt(String codArt);
    List<InvArticulos> findAllInvArticulos();
}
