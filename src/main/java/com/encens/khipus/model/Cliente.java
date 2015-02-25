/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@XmlRootElement
/*@MappedSuperclass*/
/*@PrimaryKeyJoinColumn(name = "PI_ID",referencedColumnName = "PI_ID")*/
/*@Inheritance(strategy = InheritanceType.JOINED)*/
/*@PrimaryKeyJoinColumn(name = "PI_ID",referencedColumnName="PI_ID")*/
@DiscriminatorValue("cliente")
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
    @Column(name = "DESCUENTO")
    private Double descuento;
    @Size(max = 10)
    @Column(name = "CODIGO")
    private String codigo;
    @JoinColumn(name = "IDRETENCION", referencedColumnName = "IDRETENCION")
    @ManyToOne(optional = true)
    private Retencion retencion;
    @JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE")
    @ManyToOne(optional = true)
    private Tipocliente tipocliente;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "cliente")
    private Collection<Ventaarticulo> ventaarticulos;

    public Cliente() {
        super();
    }

    public Cliente(Long piId, String direccion, Integer telefono, String nit, Double descuento, String codigo, Retencion retencion, Tipocliente tipocliente, Collection<Ventaarticulo> ventaarticulos) {
        super(piId);
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
        this.descuento = descuento;
        this.codigo = codigo;
        this.retencion = retencion;
        this.tipocliente = tipocliente;
        this.ventaarticulos = ventaarticulos;
    }

    public Cliente(Long piId) {
        super(piId);
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

    public Collection<Ventaarticulo> getVentaarticulos() {
        return ventaarticulos;
    }

    public void setVentaarticulos(Collection<Ventaarticulo> ventaarticulos) {
        this.ventaarticulos = ventaarticulos;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Tipocliente getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(Tipocliente tipocliente) {
        this.tipocliente = tipocliente;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Cliente[ piId=" + super.getPiId() + " ]";
    }
    
}
