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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "tipocliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocliente.findAll", query = "SELECT t FROM Tipocliente t"),
    @NamedQuery(name = "Tipocliente.findByIdtipocliente", query = "SELECT t FROM Tipocliente t WHERE t.idtipocliente = :idtipocliente"),
    @NamedQuery(name = "Tipocliente.findByNombre", query = "SELECT t FROM Tipocliente t WHERE t.nombre = :nombre")})
public class Tipocliente implements Serializable {
    @OneToMany(mappedBy = "idtipocliente")
    private Collection<Institucion> institucionCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDTIPOCLIENTE")
    private Long idtipocliente;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipocliente")
    private Collection<Cliente> clientes;

    public Tipocliente() {
    }

    public Tipocliente(Long idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    public Long getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(Long idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clienteCollection) {
        this.clientes = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocliente != null ? idtipocliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocliente)) {
            return false;
        }
        Tipocliente other = (Tipocliente) object;
        if ((this.idtipocliente == null && other.idtipocliente != null) || (this.idtipocliente != null && !this.idtipocliente.equals(other.idtipocliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @XmlTransient
    public Collection<Institucion> getInstitucionCollection() {
        return institucionCollection;
    }

    public void setInstitucionCollection(Collection<Institucion> institucionCollection) {
        this.institucionCollection = institucionCollection;
    }
    
}
