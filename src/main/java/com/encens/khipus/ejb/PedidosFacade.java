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

    public List<ArticulosPedido> findReposicionesPorPersona(Persona personaElegida) {
        List<ArticulosPedido> articulosPedidos = new ArrayList<>();
        try{
            //todo:cambiar yo no habra el estado rechazado
            //listar por el campo "por_reponer" 
            articulosPedidos =(List<ArticulosPedido>)em.createQuery("select ap from ArticulosPedido ap "
                                            + "where ap.pedidos.cliente =:cliente and ap.estado = 'RECHAZADO'")
                                            .setParameter("cliente",personaElegida)
                                            .getResultList();
        }catch (NoResultException e){
            return articulosPedidos;
        }        
        return articulosPedidos;
    }

    public List<Object[]> recepcionDePedidos(){
        List<Object[]> resultado = new ArrayList<>();
        try {
            resultado = (List<Object[]>)em.createQuery("select concat(pe.codigo.secuencia,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
                    "               ,articulos.invArticulos.descri as PRODUCTO\n" +
                    "               ,articulos.cantidad + articulos.reposicion as CANTIDAD\n" +
                    "               ,pe.cliente.territoriotrabajo.distribuidor.nom as DISTRIBUIDOR\n" +
                    "        from Pedidos pe join pe.articulosPedidos articulos")
                    .getResultList();

        }catch (NoResultException e){
            return  resultado;
        }
        return  resultado;
    }

    public List<Object[]> recepcionDePedidos(Date fechaEntrega,Territoriotrabajo territoriotrabajo){
        List<Object[]> resultado = new ArrayList<>();
        try {
            resultado = (List<Object[]>)em.createQuery("select concat(pe.codigo.secuencia,'-',pe.cliente.nom,' ',pe.cliente.ap,' ',pe.cliente.am,pe.cliente.razonsocial) as CLIENTE\n" +
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
            return  resultado;
        }
        return  resultado;
    }
}
