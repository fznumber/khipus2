/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import javax.persistence.*;
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
    @NamedQuery(name = "Distribuidor.findByObservacion", query = "SELECT d FROM Distribuidor d WHERE d.observacion = :observacion")})
public class Distribuidor extends Personas {
    @Size(max = 50)
    @Column(name = "OBSERVACION")
    private String observacion;

    public Distribuidor() {
    }


    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Distribuidor[ iddistribuidor=" + super.getPiId() + " ]";
    }
    
}
