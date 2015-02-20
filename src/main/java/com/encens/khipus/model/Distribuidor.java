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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "distribuidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distribuidor.findAll", query = "SELECT d FROM Distribuidor d"),
    @NamedQuery(name = "Distribuidor.findByIddistribuidor", query = "SELECT d FROM Distribuidor d WHERE d.iddistribuidor = :iddistribuidor"),
    @NamedQuery(name = "Distribuidor.findByObservacion", query = "SELECT d FROM Distribuidor d WHERE d.observacion = :observacion")})
public class Distribuidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDISTRIBUIDOR")
    private Long iddistribuidor;
    @Size(max = 50)
    @Column(name = "OBSERVACION")
    private String observacion;

    public Distribuidor() {
    }

    public Distribuidor(Long iddistribuidor) {
        this.iddistribuidor = iddistribuidor;
    }

    public Long getIddistribuidor() {
        return iddistribuidor;
    }

    public void setIddistribuidor(Long iddistribuidor) {
        this.iddistribuidor = iddistribuidor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistribuidor != null ? iddistribuidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distribuidor)) {
            return false;
        }
        Distribuidor other = (Distribuidor) object;
        if ((this.iddistribuidor == null && other.iddistribuidor != null) || (this.iddistribuidor != null && !this.iddistribuidor.equals(other.iddistribuidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Distribuidor[ iddistribuidor=" + iddistribuidor + " ]";
    }
    
}
