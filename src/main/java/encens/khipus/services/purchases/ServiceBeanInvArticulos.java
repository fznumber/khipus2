package encens.khipus.services.purchases;

import encens.khipus.model.purchases.InvArticulos;
import encens.khipus.util.HibernateUtil;
import org.hibernate.Session;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

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

        finally
        {
            if(session!=null)
            {
                session.close();
            }
        }
        return invArticulo;
    }

    @Override
    public List<InvArticulos> findAllInvArticulos() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        List<InvArticulos> articulos = new ArrayList<>();
        try{
            articulos = (List<InvArticulos>)session.createQuery("from InvArticulos articulo inner join articulo.invGrupos grupo where grupo.tipo =:venta")
                    .setParameter("venta","venta")
                    .list();
        }catch (NoResultException e)
        {
            return articulos;
        }
        finally
        {
            if(session!=null)
            {
                session.close();
            }
        }
        return articulos;
    }
}
