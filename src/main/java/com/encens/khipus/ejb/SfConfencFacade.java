/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.SfConfenc;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Stateless
public class SfConfencFacade extends AbstractFacade<SfConfenc> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SfConfencFacade() {
        super(SfConfenc.class);
    }

    public SfConfenc getOperacion(String pedidosinfactura) {
        SfConfenc operacion;
        try {
            operacion = (SfConfenc) em.createQuery("select op from SfConfenc op where op.opracion =:pedidosinfactura")
                    .setParameter("pedidosinfactura", pedidosinfactura).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return operacion;
    }

    public String getSiguienteNumeroDocumento(String tipoDoc) {

            String numero = "0";
            try{
                numero = (String)em.createNativeQuery("SELECT khipus.getNextSeq(:tipoDoc)")
                        .setParameter("tipoDoc",tipoDoc)
                        .getSingleResult();
            }catch (NoResultException e){
                return "0";
            }
            return numero;

    }
}
