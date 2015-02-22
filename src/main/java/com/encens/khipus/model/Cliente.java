/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@MappedSuperclass
/*@PrimaryKeyJoinColumn(referencedColumnName = "PI_ID")*/
/*@Inheritance(strategy = InheritanceType.JOINED)*/
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "Cliente.findByCodigo", query = "SELECT c FROM Cliente c WHERE c.codigo = :codigo")})
public class Cliente extends Personas {
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Size(max = 40)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 10)
    @Column(name = "CODIGO")
    private String codigo;
    @JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE")
    @ManyToOne(optional = false)
    private Tipocliente idtipocliente;

    public Cliente() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Tipocliente getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(Tipocliente idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Cliente[ idcliente=" + super.getPiId() + " ]";
    }
    
}
