/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "estado_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPedidos.findAll", query = "SELECT e FROM EstadoPedidos e"),
    @NamedQuery(name = "EstadoPedidos.findByIdestadopedido", query = "SELECT e FROM EstadoPedidos e WHERE e.idestadopedido = :idestadopedido"),
    @NamedQuery(name = "EstadoPedidos.findByEstado", query = "SELECT e FROM EstadoPedidos e WHERE e.estado = :estado")})
public class EstadoPedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDESTADOPEDIDO")
    private Long idestadopedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoPedido")
    private Collection<Pedidos> pedidosCollection;

    public EstadoPedidos() {
    }

    public EstadoPedidos(Long idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public EstadoPedidos(Long idestadopedido, String estado) {
        this.idestadopedido = idestadopedido;
        this.estado = estado;
    }

    public Long getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(Long idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Pedidos> getPedidosCollection() {
        return pedidosCollection;
    }

    public void setPedidosCollection(Collection<Pedidos> pedidosCollection) {
        this.pedidosCollection = pedidosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadopedido != null ? idestadopedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPedidos)) {
            return false;
        }
        EstadoPedidos other = (EstadoPedidos) object;
        if ((this.idestadopedido == null && other.idestadopedido != null) || (this.idestadopedido != null && !this.idestadopedido.equals(other.idestadopedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.EstadoPedidos[ idestadopedido=" + idestadopedido + " ]";
    }
    
}
