/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.Dosificacion;
import javassist.bytecode.stackmap.BasicBlock;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author Diego
 */
@Stateless
public class DosificacionFacade extends AbstractFacade<Dosificacion> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DosificacionFacade() {
        super(Dosificacion.class);
    }

    public Dosificacion findByPeriodo(Date date) {
        Dosificacion dosificacion;
        try{
            dosificacion =(Dosificacion)em.createNamedQuery("Dosificacion.findByPeriodo")
                    .setParameter("fechaFactura",date)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return dosificacion;
    }
}
