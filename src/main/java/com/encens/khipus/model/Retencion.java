/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "retencion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencion.findAll", query = "SELECT r FROM Retencion r"),
    @NamedQuery(name = "Retencion.findByIdretencion", query = "SELECT r FROM Retencion r WHERE r.idretencion = :idretencion"),
    @NamedQuery(name = "Retencion.findByNombre", query = "SELECT r FROM Retencion r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Retencion.findActive", query = "SELECT r FROM Retencion r WHERE r.estado = true"),
    @NamedQuery(name = "Retencion.findByPorcentage", query = "SELECT r FROM Retencion r WHERE r.porcentage = :porcentage")})
public class Retencion implements Serializable {

    @Id
    @Column(name = "IDRETENCION")
    private Long idretencion;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private Boolean estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PORCENTAGE")
    private Double porcentage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retencion")
    private Collection<Persona> personas;

    public Retencion() {
    }

    public Retencion(Long idretencion) {
        this.idretencion = idretencion;
    }

    public Retencion(Long idretencion, long idcliente) {
        this.idretencion = idretencion;
    }

    public Long getIdretencion() {
        return idretencion;
    }

    public void setIdretencion(Long idretencion) {
        this.idretencion = idretencion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(Double porcentage) {
        this.porcentage = porcentage;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idretencion != null ? idretencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencion)) {
            return false;
        }
        Retencion other = (Retencion) object;
        if ((this.idretencion == null && other.idretencion != null) || (this.idretencion != null && !this.idretencion.equals(other.idretencion))) {
            return false;
        }
        return true;
    }

    public String getPorcentageString()
    {
        return porcentage.toString() +" %";
    }

    @XmlTransient
    public Collection<Persona> getClientes() {
        return personas;
    }

    public void setClientes(Collection<Persona> pers) {
        this.personas = pers;
    }

}
