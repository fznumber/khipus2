package encens.khipus.services.admin;

import encens.khipus.model.admin.User;
import encens.khipus.model.admin.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Diego on 04/02/2015.
 */
public class ServiceBeanUser implements  ServiceUser{
    @Override
    public Usuario getByUserName(Session session, String userName) {
        String hql="from Usuario where  usuario=:userName";
        Query query=session.createQuery(hql);
        query.setParameter("userName", userName);

        Usuario user=(Usuario) query.uniqueResult();

        return user;
    }
}
