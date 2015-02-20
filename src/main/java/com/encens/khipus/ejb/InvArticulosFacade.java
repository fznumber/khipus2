/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.InvArticulos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
@Stateless
public class InvArticulosFacade extends AbstractFacade<InvArticulos> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvArticulosFacade() {
        super(InvArticulos.class);
    }

    public InvArticulos findByName(String descri)
    {
        InvArticulos invArticulo;
        try {
            invArticulo = (InvArticulos)em.createNamedQuery("InvArticulos.findByDescri")
                    .setParameter("descri", descri)
                    .getSingleResult();
        }catch (NoResultException e)
        {
            return null;
        }
        return invArticulo;
    }

    public List<InvArticulos> findAllInvArticulos() {

        List<InvArticulos> articulos = new ArrayList<>();
        try{
            articulos = (List<InvArticulos>)em.createQuery("select articulo from InvArticulos articulo inner join articulo.invGrupos grupo where grupo.tipo =:venta")
                    .setParameter("venta","venta")
                    .getResultList();
        }catch (NoResultException e)
        {
            return articulos;
        }
        return articulos;
    }


}
