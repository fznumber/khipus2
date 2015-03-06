/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@XmlRootElement
@DiscriminatorValue("institucion")
@NamedQueries({
        @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
        @NamedQuery(name = "Institucion.findByRazonsocial", query = "SELECT i FROM Institucion i WHERE i.razonsocial = :razonsocial")})
public class Institucion extends Persona {

    @Size(min = 1, max = 100)
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;

    public Institucion() {
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @Override
    public String toString() {
        return razonsocial;
    }

}
