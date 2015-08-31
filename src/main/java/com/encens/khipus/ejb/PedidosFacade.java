/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.ArticulosPedido;
import com.encens.khipus.model.Pedidos;
import com.encens.khipus.model.Persona;
import com.encens.khipus.model.Territoriotrabajo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
@Stateless
public class PedidosFacade extends AbstractFacade<Pedidos> {
    @PersistenceContext(unitName = "khipusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidosFacade() {
        super(Pedidos.class);
    }

    public List<Pedidos> findReposicionesPorPersona(Persona personaElegida) {
        List<Pedidos> pedidos = new ArrayList<>();
        try{
            //todo:cambiar yo no habra el estado rechazado
            //listar por el campo "por_reponer" 
            pedidos =(List<Pedidos>)em.createQuery("select pe from Pedidos pe " +
                    "inner join pe.articulosPedidos articulos " +
                    "where articulos.estado = 'RECHAZADO' AND pe.cliente =:cliente")
                                            .setParameter("cliente",personaElegida)
                                            .getResultList();
        }catch (NoResultException e){
            return pedidos;
        }        
        return pedidos;
    }

    public Pedidos findById(Long id){
        return (Pedidos)em.createQuery("select ped from Pedidos ped where ped.idpedidos =:idPedido")
                .setParameter("idPedido",id)
                .getSingleResult();
    }

    public String getSiguienteCodigo() {

        String numero = "0";
        try{
            numero = (String)em.createNativeQuery("SELECT getNextSeq('SECUENCIAPEDIDO')")
                    .getSingleResult();
        }catch (NoResultException e){
            return "0";
        }
        return numero;

    }

    public List<Object[]> recepcionDePedidos(){
        List<Object[]> resultado = new ArrayList<>();
        try {
            resultado = (List<Object[]>)em.createQuery("select concat(pe.codigo,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                    "               ,articulos.invArticulos.nombrecorto as PRODUCTO\n" +
                    "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                    "               ,pe.cliente.territoriotrabajo.nombre \n" +
                    "        from Pedidos pe join pe.articulosPedidos articulos" +
                    "        where pe.estado = 'PENDIENTE' and pe.estado = 'PREPARAR'")
                    .getResultList();

        }catch (NoResultException e){
            return  resultado;
        }
        return  resultado;
    }

    public Integer getTotalPedidos(Date fechaEntrega,Territoriotrabajo territoriotrabajo){
        Integer cantidad=0;
        try{
            if(fechaEntrega == null && territoriotrabajo == null) {
                cantidad = ((List<Pedidos>)em.createQuery("select  pe from Pedidos pe where pe.estado != 'ANULADO' and pe.estado != 'CONTABILIZADO' ").getResultList()).size();
            }
            else{
                if(territoriotrabajo != null)
                    cantidad = ((List<Pedidos>)em.createQuery("select  pe from Pedidos pe "
                        + "where pe.fechaEntrega =:fechaEntrega and pe.cliente.territoriotrabajo =:territoriotrabajo and pe.estado != 'ANULADO' and pe.estado != 'CONTABILIZADO'")
                        .setParameter("fechaEntrega", fechaEntrega)
                        .setParameter("territoriotrabajo",territoriotrabajo)
                        .getResultList()).size();
                else
                    cantidad = ((List<Pedidos>)em.createQuery("select  pe from Pedidos pe "
                            + "where pe.fechaEntrega =:fechaEntrega and pe.estado != 'ANULADO' and pe.estado != 'CONTABILIZADO' ")
                            .setParameter("fechaEntrega", fechaEntrega)
                            .getResultList()).size();
            }
        }catch (NoResultException e){
            return cantidad;
        }
        return cantidad;
    }

    public List<Object[]> recepcionDePedidos(Date fechaEntrega,Territoriotrabajo territoriotrabajo){
        List<Object[]> resultado = new ArrayList<>();
        try {
            if(territoriotrabajo != null)
                resultado = (List<Object[]>)em.createQuery("select concat(pe.codigo,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                    "               ,articulos.invArticulos.nombrecorto as PRODUCTO\n" +
                    "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                    "               ,pe.cliente.territoriotrabajo.nombre as DISTRIBUIDOR\n" +
                    "        from Pedidos pe join pe.articulosPedidos articulos" +
                    "        where pe.fechaEntrega =:fechaEntrega and pe.cliente.territoriotrabajo =:territoriotrabajo " +
                    "              and pe.estado != 'ANULADO' and pe.estado != 'CONTABILIZADO'")
                    .setParameter("fechaEntrega", fechaEntrega, TemporalType.DATE)
                    .setParameter("territoriotrabajo",territoriotrabajo)
                    .getResultList();
            else
                resultado = (List<Object[]>)em.createQuery("select concat(pe.codigo,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                        "               ,articulos.invArticulos.nombrecorto as PRODUCTO\n" +
                        "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                        "               ,pe.cliente.territoriotrabajo.nombre as DISTRIBUIDOR\n" +
                        "        from Pedidos pe join pe.articulosPedidos articulos" +
                        "        where pe.fechaEntrega =:fechaEntrega and pe.estado != 'ANULADO' and pe.estado != 'CONTABILIZADO'")
                        .setParameter("fechaEntrega", fechaEntrega, TemporalType.DATE)
                        .getResultList();

        }catch (NoResultException e){
            return  resultado;
        }
        return  resultado;
    }

    public List<RecepcionPedido> recepcionDePedidosTodos(){
        List<Object[]> datas = new ArrayList<>();
        List<RecepcionPedido> recepcionPedidos = new ArrayList<>();
        try {
            datas = (List<Object[]>)em.createQuery("select concat(pe.codigo,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                    "               ,articulos.invArticulos.nombrecorto as PRODUCTO\n" +
                    "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                    "               ,pe.cliente.territoriotrabajo.nombre as DISTRIBUIDOR\n" +
                    "        from Pedidos pe join pe.articulosPedidos articulos")
                    .getResultList();

        }catch (NoResultException e){
            return  recepcionPedidos;
        }
        for(Object[] data: datas)
        {
            RecepcionPedido recepcionPedido = new RecepcionPedido((String)data[0],(String)data[1],(Integer)data[2],(String)data[3]);
            recepcionPedidos.add(recepcionPedido);
        }
        return  recepcionPedidos;
    }

    public List<RecepcionPedido> recepcionDePedidosPorFechaTerritorio(Date fechaEntrega,Territoriotrabajo territoriotrabajo){
        List<Object[]> datas = new ArrayList<>();
        List<RecepcionPedido> recepcionPedidos = new ArrayList<>();
        try {
            datas = (List<Object[]>)em.createQuery("select concat(pe.codigo,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                    "               ,articulos.invArticulos.descri as PRODUCTO\n" +
                    "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                    "               ,pe.cliente.territoriotrabajo.distribuidor.nom as DISTRIBUIDOR\n" +
                    "        from Pedidos pe join pe.articulosPedidos articulos" +
                    "        where pe.fechaEntrega =:fechaEntrega" +
                    "        or pe.cliente.territoriotrabajo =:territoriotrabajo")
                    .setParameter("fechaEntrega", fechaEntrega, TemporalType.DATE)
                    .setParameter("territoriotrabajo",territoriotrabajo)
                    .getResultList();

        }catch (NoResultException e){
            return  recepcionPedidos;
        }
        for(Object[] data: datas)
        {
            RecepcionPedido recepcionPedido = new RecepcionPedido((String)data[0],(String)data[1],(Integer)data[2],(String)data[3]);
            recepcionPedidos.add(recepcionPedido);
        }
        return  recepcionPedidos;
    }

    public List<Pedidos> findPedidosOrdDecs() {
        List<Pedidos> result = new ArrayList<>();
        try{
            result = (List<Pedidos>)em.createQuery("select pe from Pedidos pe order by pe.idpedidos desc").getResultList();
        }catch (NoResultException e){
            return result;
        }
        return result;
    }

    public class RecepcionPedido{
        private String cliente;
        private String producto;
        private Integer cantidad;
        private String distribuidor;

        public RecepcionPedido(String cliente, String producto, Integer cantidad, String distribuidor) {
            this.cliente = cliente;
            this.producto = producto;
            this.cantidad = cantidad;
            this.distribuidor = distribuidor;
        }

        public String getCliente() {
            return cliente;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public String getDistribuidor() {
            return distribuidor;
        }

        public void setDistribuidor(String distribuidor) {
            this.distribuidor = distribuidor;
        }
    }
}
