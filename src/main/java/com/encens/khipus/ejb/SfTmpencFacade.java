/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.Persona;
import com.encens.khipus.model.SfTmpenc;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<SfTmpenc> findKardex() {
        List<SfTmpenc> sfTmpencs = new ArrayList<>();
        try{
            sfTmpencs = (List<SfTmpenc>)em.createQuery("select  asiento.fecha\n" +
                    "               ,asiento.tipoDoc\n" +
                    "               ,asiento.noDoc\n" +
                    "               ,asiento.nombreCliente\n" +
                    "               ,asiento.debe\n" +
                    "               ,asiento.haber\n" +
                    "        from SfTmpenc asiento\n" +
                    "        join asiento.pedidos pedido\n" +
                    "        join asiento.pagos  pago\n" +
                    "        join asiento.ventadirectas venta\n" +
                    "        where asiento.cliente.tipoPersona = 'cliente' and asiento.cliente.tipoPersona = 'institucion'\n" +
                    "        group by asiento.cliente.piId\n")
                        .getResultList();
        }catch (NoResultException e){
            return sfTmpencs;
        }
        return sfTmpencs;
    }

    public List<SfTmpenc> findKardexByClienteAndRangoFecha(Persona cliente,Date fechaIni,Date fechaFin){
        List<SfTmpenc> kardex = new ArrayList<>();
        try{
            kardex = (List<SfTmpenc>)em.createQuery("select  asiento.fecha\n" +
                    "               ,asiento.tipoDoc\n" +
                    "               ,asiento.noDoc\n" +
                    "               ,asiento.nombreCliente\n" +
                    "               ,asiento.debe\n" +
                    "               ,asiento.haber\n" +
                    "        from SfTmpenc asiento\n" +
                    "        join asiento.pedidos pedido\n" +
                    "        join asiento.pagos  pago\n" +
                    "        join asiento.ventadirectas venta\n" +
                    "        where asiento.cliente.piId =:idCliente\n" +
                    "        and asiento.fecha between :fechaIni and :fechaFin\n" +
                    "        group by asiento.cliente.piId")
                    .setParameter("idCliente",cliente.getPiId())
                    .setParameter("fechaIni",fechaIni)
                    .setParameter("fechaFin",fechaFin)
                    .getResultList();
        }catch (NoResultException e)
        {
            return kardex;
        }
        return kardex;
    }
}
