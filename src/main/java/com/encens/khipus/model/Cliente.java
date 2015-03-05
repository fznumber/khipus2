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

    @Override
    public String getDireccion() {
        return super.getDireccion();
    }

    @Override
    public Integer getTelefono() {
        return super.getTelefono();
    }

    @Override
    public String getNit() {
        return super.getNit();
    }

    @Override
    public String getRazonsocial() {
        return super.getRazonsocial();
    }

    @Override
    public Double getDescuento() {
        return super.getDescuento();
    }

    @Override
    public String getCodigo() {
        return super.getCodigo();
    }

    @Override
    public Retencion getRetencion() {
        return super.getRetencion();
    }

    @Override
    public Tipocliente getTipocliente() {
        return super.getTipocliente();
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
