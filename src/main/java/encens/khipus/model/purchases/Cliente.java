package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Cliente {
    private long idcliente;
    private String direccion;
    private Integer telefono;
    private String nit;
    private String codigo;
    private Collection<ArticulosPaquete> articulosPaquetesByIdcliente;
    private Institucion institucionByIdinstitucion;
    private Tipocliente tipoclienteByIdtipocliente;
    private Collection<Personas> personasesByIdcliente;
    private Collection<Retencion> retencionsByIdcliente;
    private Collection<Pedidos> pedidosesByIdcliente;
    private Collection<Ventaarticulo> ventaarticulosByIdcliente;

    @Id
    @Column(name = "IDCLIENTE", nullable = false, insertable = true, updatable = true)
    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "DIRECCION", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "TELEFONO", nullable = true, insertable = true, updatable = true)
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "NIT", nullable = true, insertable = true, updatable = true, length = 40)
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @Basic
    @Column(name = "CODIGO", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (idcliente != cliente.idcliente) return false;
        if (codigo != null ? !codigo.equals(cliente.codigo) : cliente.codigo != null) return false;
        if (direccion != null ? !direccion.equals(cliente.direccion) : cliente.direccion != null) return false;
        if (nit != null ? !nit.equals(cliente.nit) : cliente.nit != null) return false;
        if (telefono != null ? !telefono.equals(cliente.telefono) : cliente.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idcliente ^ (idcliente >>> 32));
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (nit != null ? nit.hashCode() : 0);
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cliente")
    public Collection<ArticulosPaquete> getArticulosPaquetesByIdcliente() {
        return articulosPaquetesByIdcliente;
    }

    public void setArticulosPaquetesByIdcliente(Collection<ArticulosPaquete> articulosPaquetesByIdcliente) {
        this.articulosPaquetesByIdcliente = articulosPaquetesByIdcliente;
    }

    @ManyToOne
    @JoinColumn(name = "IDINSTITUCION", referencedColumnName = "IDINSTITUCION", nullable = false)
    public Institucion getInstitucionByIdinstitucion() {
        return institucionByIdinstitucion;
    }

    public void setInstitucionByIdinstitucion(Institucion institucionByIdinstitucion) {
        this.institucionByIdinstitucion = institucionByIdinstitucion;
    }

    @ManyToOne
    @JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE", nullable = false)
    public Tipocliente getTipoclienteByIdtipocliente() {
        return tipoclienteByIdtipocliente;
    }

    public void setTipoclienteByIdtipocliente(Tipocliente tipoclienteByIdtipocliente) {
        this.tipoclienteByIdtipocliente = tipoclienteByIdtipocliente;
    }

    @OneToMany(mappedBy = "clientePersona")
    public Collection<Personas> getPersonasesByIdcliente() {
        return personasesByIdcliente;
    }

    public void setPersonasesByIdcliente(Collection<Personas> personasesByIdcliente) {
        this.personasesByIdcliente = personasesByIdcliente;
    }

    @OneToMany(mappedBy = "clienteRetencion")
    public Collection<Retencion> getRetencionsByIdcliente() {
        return retencionsByIdcliente;
    }

    public void setRetencionsByIdcliente(Collection<Retencion> retencionsByIdcliente) {
        this.retencionsByIdcliente = retencionsByIdcliente;
    }

    @OneToMany(mappedBy = "cliente")
    public Collection<Pedidos> getPedidosesByIdcliente() {
        return pedidosesByIdcliente;
    }

    public void setPedidosesByIdcliente(Collection<Pedidos> pedidosesByIdcliente) {
        this.pedidosesByIdcliente = pedidosesByIdcliente;
    }

    @OneToMany(mappedBy = "clienteByIdcliente")
    public Collection<Ventaarticulo> getVentaarticulosByIdcliente() {
        return ventaarticulosByIdcliente;
    }

    public void setVentaarticulosByIdcliente(Collection<Ventaarticulo> ventaarticulosByIdcliente) {
        this.ventaarticulosByIdcliente = ventaarticulosByIdcliente;
    }
}
