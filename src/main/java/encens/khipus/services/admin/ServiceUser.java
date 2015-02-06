package encens.khipus.services.admin;

import encens.khipus.model.admin.User;
import encens.khipus.model.admin.Usuario;
import org.hibernate.Session;

/**
 * Created by Diego on 04/02/2015.
 */
public interface ServiceUser {
    Usuario getByUserName(Session session, String userName);
}
