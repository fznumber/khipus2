/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.Ventadirecta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class VentadirectaFacade extends AbstractFacade<Ventadirecta> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentadirectaFacade() {
        super(Ventadirecta.class);
    }

    public Integer getSiguienteNumeroVenta()
    {
        String numero = "";
        try{
            numero = (String)em.createNativeQuery("SELECT getNextSeq('VENTADIRECTA')").getSingleResult();
        }catch (NoResultException e){
            return 0;
        }
        return Integer.parseInt(numero);
    }
}
