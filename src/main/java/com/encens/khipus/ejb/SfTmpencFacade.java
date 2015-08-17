/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.Kardex;
import com.encens.khipus.model.Persona;
import com.encens.khipus.model.SfTmpenc;
import com.encens.khipus.model.Sucursal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public String getSiguienteNumeroPorNombre(String nombre) {
        String numero = "0";
        try{
            numero = (String)em.createNativeQuery("SELECT khipus.getNextSeq('"+nombre+"')")
                    .getSingleResult();
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

    public Collection<Kardex> getKardexcliente(Persona personaElegida, Date fechaIni, Date fechaFin,String cuenta,Sucursal sucursal) {
        List<Kardex> kardex = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicio = formato.format(fechaIni);
        String fechaFinal = formato.format(fechaFin);
        String sql = "SELECT * FROM\n" +
                "(" +
                "SELECT enc.fecha,enc.tipo_doc,enc.no_doc,enc.glosa,det.debe,0 AS haber FROM sf_tmpenc enc\n" +
                            "JOIN sf_tmpdet det\n" +
                            "ON det.id_tmpenc = enc.id_tmpenc\n" +
                            "JOIN pedidos ped\n" +
                            "ON ped.id_tmpenc = enc.id_tmpenc \n" +
                            "WHERE det.cuenta = '" + cuenta + "'\n" +
                            "AND enc.fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFinal + "' \n" +
                            "AND ped.IDCLIENTE = '" + personaElegida.getPiId() + "'\n" +
                            "and ped.IDSUCURSAL = '" +sucursal.getIdsucursal()+ "'\n" +
                            "UNION\n" +
                            "SELECT enc.fecha,enc.tipo_doc,enc.no_doc,enc.glosa,0 AS debe,det.haber FROM sf_tmpenc enc\n" +
                            "JOIN sf_tmpdet det\n" +
                            "ON det.id_tmpenc = enc.id_tmpenc\n" +
                            "JOIN pago pago\n" +
                            "ON pago.id_tmpenc = enc.id_tmpenc \n" +
                            "WHERE det.cuenta = '" + cuenta + "'\n" +
                            "AND enc.fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFinal + "' \n" +
                            "AND pago.IDPERSONACLIENTE = '" + personaElegida.getPiId() + "'\n" +
                            "and pago.IDSUCURSAL = '" +sucursal.getIdsucursal()+ "'\n" +
                            ") AS kardex\n" +
                            "ORDER BY kardex.fecha DESC";
        try{
            datos = (List<Object[]>)em.createNativeQuery(sql)
                    .getResultList();
        }catch (NoResultException e){
                return kardex;
        }
        Double debe = 0.0;
        Double haber = 0.0;
        Double saldo = 0.0;
        for(Object[] dato:datos)
        {
            debe  = ((BigDecimal)dato[4]).doubleValue();
            haber = ((BigDecimal)dato[5]).doubleValue();
            saldo += debe;
            saldo -= haber;
            Kardex kar = new Kardex();
            kar.setFecha((Date)dato[0]);
            kar.setTipoDoc((String) dato[1]);
            kar.setNoDoc((String) dato[2]);
            kar.setGlosa((String)dato[3]);
            kar.setDebe(debe);
            kar.setHaber(haber);
            kar.setSaldo(saldo);
            kardex.add(kar);
        }

        return kardex;
    }
}
