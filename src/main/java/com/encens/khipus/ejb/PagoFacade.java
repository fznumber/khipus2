/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.Banco;
import com.encens.khipus.model.Pago;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
@Stateless
public class PagoFacade extends AbstractFacade<Pago> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }

    public List<Banco> findBancos() {
        List<Banco> bancos = new ArrayList<>();
        List<Object[]> datos= new ArrayList<>();
        try{
                datos = (List<Object[]>)em.createNativeQuery("SELECT descri,cuenta FROM ck_ctas_bco")
                        .getResultList();
        }catch (Exception e){
            return bancos;
        }
        for(Object[] dato:datos){
            Banco banco = new Banco();
            banco.setCuenta((String)dato[0]);
            banco.setDescri((String)dato[1]);
            bancos.add(banco);
        }

        return bancos;
    }
}
