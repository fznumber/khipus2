/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.SfTmpenc;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class SfTmpencFacade extends AbstractFacade<SfTmpenc> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SfTmpencFacade() {
        super(SfTmpenc.class);
    }

    public String getSiguienteNumeroTransacccion() {
        String numero = "0";
        try{
            numero = (String)em.createNativeQuery("SELECT khipus.getNextSeq('ASIENTO')").getSingleResult();
        }catch (NoResultException e){
            return "0";
        }
        return numero;
    }
}
