/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.ejb;

import com.encens.khipus.model.ArticulosPedido;
import com.encens.khipus.model.Pedidos;
import com.encens.khipus.model.Persona;

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
        List<Pedidos> pedidosConReposicion;
        try{
            //todo:cambiar yo no habra el estado rechazado
            //listar por el campo "por_reponer" 
            pedidosConReposicion =(List<Pedidos>)em.createQuery("select pe from Pedidos pe " +
                                            " inner join pe.articulosPedidos ap" +
                                            " where pe.cliente =:cliente" +
                                            " and ap.estado = 'RECHAZADO'")
                                            .setParameter("cliente",personaElegida)
                                            .getResultList();
        }catch (NoResultException e){
            return articulosPedidos;
        }
        for(Pedidos pedidos:pedidosConReposicion)
        {
            for(ArticulosPedido articulos:pedidos.getArticulosPedidos())
            {
                if(articulos.getEstado().equals("RECHAZADO"))
                articulosPedidos.add(articulos);
            }
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
                    "        from Pedidos pe join pe.articulosPedidos articulos").getResultList();
        }catch (NoResultException e){
            return  resultado;
        }
        return  resultado;
    }
}
