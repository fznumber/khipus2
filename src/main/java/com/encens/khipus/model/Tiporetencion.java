/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "tiporetencion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiporetencion.findAll", query = "SELECT t FROM Tiporetencion t"),
    @NamedQuery(name = "Tiporetencion.findByIdtiporetencion", query = "SELECT t FROM Tiporetencion t WHERE t.idtiporetencion = :idtiporetencion"),
    @NamedQuery(name = "Tiporetencion.findByDescripcion", query = "SELECT t FROM Tiporetencion t WHERE t.descripcion = :descripcion")})
public class Tiporetencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "idtiporetencion")
    private Long idtiporetencion;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "PI_ID", referencedColumnName = "PI_ID")
    @ManyToOne(optional = false)
    private Cliente clienteRetencion;
    @JoinColumn(name = "idretencion", referencedColumnName = "IDRETENCION")
    @ManyToOne(optional = false)
    private Retencion retencion;

    public Tiporetencion() {
    }

    public Tiporetencion(Long idtiporetencion) {
        this.idtiporetencion = idtiporetencion;
    }

    public Long getIdtiporetencion() {
        return idtiporetencion;
    }

    public void setIdtiporetencion(Long idtiporetencion) {
        this.idtiporetencion = idtiporetencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getClienteRetencion() {
        return clienteRetencion;
    }

    public void setClienteRetencion(Cliente clienteRetencion) {
        this.clienteRetencion = clienteRetencion;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporetencion != null ? idtiporetencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiporetencion)) {
            return false;
        }
        Tiporetencion other = (Tiporetencion) object;
        if ((this.idtiporetencion == null && other.idtiporetencion != null) || (this.idtiporetencion != null && !this.idtiporetencion.equals(other.idtiporetencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Tiporetencion[ idtiporetencion=" + idtiporetencion + " ]";
    }
    
}
