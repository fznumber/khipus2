/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 *
 * @author Diego
 */
@Entity
/*@XmlRootElement*/
/*@MappedSuperclass*/
/*@PrimaryKeyJoinColumn(name = "idcliente", referencedColumnName = "idpersonas")*/
/*@Inheritance(strategy = InheritanceType.JOINED)*/
@DiscriminatorValue("cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "Cliente.findByCodigo", query = "SELECT c FROM Cliente c WHERE c.codigo = :codigo")})

public class Cliente extends Persona {
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Size(max = 40)
    @Column(name = "NIT")
    private String nit;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "DESCUENTO")
    private Double descuento;
    @Size(max = 10)
    @Column(name = "CODIGOCLIENTE")
    private String codigo;
    @JoinColumn(name = "IDRETENCION", referencedColumnName = "IDRETENCION")
    @ManyToOne(optional = true)
    private Retencion retencion;
    @JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE")
    @ManyToOne(optional = true)
    private Tipocliente tipocliente;
    @JoinColumn(name = "IDDEPARTAMENTO",referencedColumnName = "IDDEPARTAMENTO")
    @ManyToOne(optional = true)
    private Departamento departamento;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "cliente")
    private Collection<Ventaarticulo> ventaarticulos;

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
    public String getDescuentoPorcentaje()
    {
        if(descuento != null)
            return descuento.toString()+" "+"%";
        else
            return "";

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

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Cliente[ piId=" + super.getPiId() + " ]";
    }

    @Override
    public String getNombreCompleto()
    {
        if(razonsocial != null)
        return razonsocial;
        return  super.getNombreCompleto();
    }
    
}
