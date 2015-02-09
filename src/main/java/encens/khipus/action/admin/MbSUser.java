/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encens.khipus.action.admin;


import encens.khipus.model.admin.Usuario;
import encens.khipus.services.admin.ServiceBeanUser;
import encens.khipus.util.Encrypt;
import encens.khipus.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author Diego Loza
 */
@ManagedBean(name = "mbSUser")
@SessionScoped
public class MbSUser implements Serializable {

    private String userName;
    private String contrasenia;
    
    private Session session;
    private Transaction transaccion;
    
    public MbSUser()
    {
        HttpSession miSession=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);
    }
    
    public String login()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            ServiceBeanUser serviceBeanUser=new ServiceBeanUser();
            
            this.session= HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            
            Usuario user =serviceBeanUser.getByUserName(this.session, this.userName);
            
            if(user!=null)
            {
                if(user.getClave().equals(Encrypt.sha512(this.contrasenia)))
                {
                    HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("userName", this.userName);
                    
                    return "/usuario/registroPedido";
                }
            }
            
            this.transaccion.commit();
            
            this.userName=null;
            this.contrasenia=null;
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o contraseña incorrecto"));
            
            return "/index";
            
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public String cerrarSesion()
    {
        this.userName=null;
        this.contrasenia=null;
        
        HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        
        return "/index";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
