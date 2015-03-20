/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "ventaarticulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventaarticulo.findAll", query = "SELECT v FROM Ventaarticulo v"),
    @NamedQuery(name = "Ventaarticulo.findByIdventaarticulo", query = "SELECT v FROM Ventaarticulo v WHERE v.idventaarticulo = :idventaarticulo"),
    @NamedQuery(name = "Ventaarticulo.findByPrecio", query = "SELECT v FROM Ventaarticulo v WHERE v.precio = :precio"),
    @NamedQuery(name = "Ventaarticulo.findByInvArticulo", query = "SELECT v FROM Ventaarticulo v WHERE v.invArticulos = :invArticulos")})
public class Ventaarticulo implements Serializable {
    @OneToMany(mappedBy = "idpaquete")
    private Collection<Ventaarticulo> ventaarticuloCollection;
    @JoinColumn(name = "IDPAQUETE", referencedColumnName = "IDVENTAARTICULO")
    @ManyToOne
    private Ventaarticulo idpaquete;
    @Id
    @Column(name = "IDVENTAARTICULO")
    private Long idventaarticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @JoinColumns({
        @JoinColumn(name = "NO_CIA", referencedColumnName = "NO_CIA"),
        @JoinColumn(name = "COD_ART", referencedColumnName = "COD_ART")})
    @ManyToOne(optional = false)
    private InvArticulos invArticulos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaarticulo")
    private Collection<Ventacliente> ventaclientes;
    private static final long serialVersionUID = 1L;

    public Ventaarticulo() {
    }

    public Ventaarticulo(Long idventaarticulo) {
        this.idventaarticulo = idventaarticulo;
    }

    public Long getIdventaarticulo() {
        return idventaarticulo;
    }

    public void setIdventaarticulo(Long idventaarticulo) {
        this.idventaarticulo = idventaarticulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public InvArticulos getInvArticulos() {
        return invArticulos;
    }

    public void setInvArticulos(InvArticulos invArticulos) {
        this.invArticulos = invArticulos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventaarticulo != null ? idventaarticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventaarticulo)) {
            return false;
        }
        Ventaarticulo other = (Ventaarticulo) object;
        if ((this.idventaarticulo == null && other.idventaarticulo != null) || (this.idventaarticulo != null && !this.idventaarticulo.equals(other.idventaarticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Ventaarticulo[ idventaarticulo=" + idventaarticulo + " ]";
    }

    @XmlTransient
    public Collection<Ventacliente> getVentaclientes() {
        return ventaclientes;
    }

    public void setVentaclientes(Collection<Ventacliente> ventaclienteCollection) {
        this.ventaclientes = ventaclienteCollection;
    }

    @XmlTransient
    public Collection<Ventaarticulo> getVentaarticuloCollection() {
        return ventaarticuloCollection;
    }

    public void setVentaarticuloCollection(Collection<Ventaarticulo> ventaarticuloCollection) {
        this.ventaarticuloCollection = ventaarticuloCollection;
    }

    public Ventaarticulo getIdpaquete() {
        return idpaquete;
    }

    public void setIdpaquete(Ventaarticulo idpaquete) {
        this.idpaquete = idpaquete;
    }
    
}
