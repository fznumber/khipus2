package encens.khipus.services.purchases;

import encens.khipus.model.purchases.InvArticulos;
import encens.khipus.util.HibernateUtil;
import org.hibernate.Session;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.NoResultException;

/**
 * Created by Diego on 09/02/2015.
 */
@ManagedBean(name="serviceInvArticulos", eager = true)
@ApplicationScoped
public class ServiceBeanInvArticulos implements ServiceInvArticulos {
    @Override
    public InvArticulos finByCodArt(String codArt) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        InvArticulos invArticulo;
        try {
            invArticulo = (InvArticulos)session.createQuery("from InvArticulos articulo where articulo.codArt =:codArt")
                    .setParameter("codArt",codArt)
                    .uniqueResult();
        }catch (NoResultException e)
        {
            return null;
        }

        return invArticulo;
    }
}
