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
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
    @NamedQuery(name = "Institucion.findByIdinstitucion", query = "SELECT i FROM Institucion i WHERE i.idinstitucion = :idinstitucion"),
    @NamedQuery(name = "Institucion.findByRazonsocial", query = "SELECT i FROM Institucion i WHERE i.razonsocial = :razonsocial")})
public class Institucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "IDINSTITUCION")
    private Long idinstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idinstitucion")
    private Collection<Cliente> clienteCollection;

    public Institucion() {
    }

    public Institucion(Long idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public Institucion(Long idinstitucion, String razonsocial) {
        this.idinstitucion = idinstitucion;
        this.razonsocial = razonsocial;
    }

    public Long getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(Long idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstitucion != null ? idinstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idinstitucion == null && other.idinstitucion != null) || (this.idinstitucion != null && !this.idinstitucion.equals(other.idinstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Institucion[ idinstitucion=" + idinstitucion + " ]";
    }
    
}
