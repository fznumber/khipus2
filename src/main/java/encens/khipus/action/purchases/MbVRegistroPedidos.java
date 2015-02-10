package encens.khipus.action.purchases;


import encens.khipus.model.purchases.*;
import encens.khipus.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 08/02/2015.
 */
@ManagedBean(name = "pedidosRegistro")
@ViewScoped
public class MbVRegistroPedidos {
    private List<ArticulosPedido> articulosPedidos;
    private List<ArticulosPedido> articulosPedidosElegidos;
    private List<InvArticulos> articulos;
    private Cliente cliente;
    private Distribuidor distribuidor;
    private Tipopedido tipopedido;
    private Integer importeTotal = 0;
    private Pedidos pedido;
    private Session session;
    private Transaction transaccion;
    private InvArticulos articuloElegido;

    public MbVRegistroPedidos() {
        this.session=null;
        this.transaccion=null;

        try
        {

            this.session= HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            articulos = session.createCriteria(InvArticulos.class).list();

        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }

    public List<InvArticulos> completarArticulo(String query) {
        List<InvArticulos> articulosFiltrados = new ArrayList<InvArticulos>();

        for(InvArticulos articulo:articulos) {

            if(articulo.getDescri().toLowerCase().contains(query)) {
                articulosFiltrados.add(articulo);
            }
        }

        return articulosFiltrados;
    }

    public List<ArticulosPedido> getArticulosPedidos() {
        return articulosPedidos;
    }

    public void setArticulosPedidos(List<ArticulosPedido> articulosPedidos) {
        this.articulosPedidos = articulosPedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Tipopedido getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(Tipopedido tipopedido) {
        this.tipopedido = tipopedido;
    }

    public List<ArticulosPedido> getArticulosPedidosElegidos() {
        return articulosPedidosElegidos;
    }

    public void setArticulosPedidosElegidos(List<ArticulosPedido> articulosPedidosElegidos) {
        this.articulosPedidosElegidos = articulosPedidosElegidos;
    }

    public Integer getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Integer importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<InvArticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<InvArticulos> articulos) {
        this.articulos = articulos;
    }

    public InvArticulos getArticuloElegido() {
        return articuloElegido;
    }

    public void setArticuloElegido(InvArticulos articuloElegido) {
        this.articuloElegido = articuloElegido;
    }
}
