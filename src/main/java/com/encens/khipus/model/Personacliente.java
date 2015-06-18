package com.encens.khipus.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by Diego on 17/06/2015.
 */
@Entity
public class Personacliente {
    private Collection<Pago> pagosByIdpersonacliente;

    @OneToMany(mappedBy = "personaclienteByIdpersonacliente")
    public Collection<Pago> getPagosByIdpersonacliente() {
        return pagosByIdpersonacliente;
    }

    public void setPagosByIdpersonacliente(Collection<Pago> pagosByIdpersonacliente) {
        this.pagosByIdpersonacliente = pagosByIdpersonacliente;
    }
}
