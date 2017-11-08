/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Lucas Carvalho
 */
public class UsuarioDAO {
    
    List<Usuario> usuarios;
    
    public List<Usuario> listaCompleta(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select c from Compromisso c", Compromisso.class);
        this.usuarios = q.getResultList();
        em.close();
        return usuarios;
    }
    
    public Boolean verificarUsuario(String usuario, String senha) {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager em = emf.createEntityManager();
        Query queryUsr = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :usuario and u.senha= :senha", Usuario.class);
        queryUsr.setParameter("usuario", usuario);
        queryUsr.setParameter("senha", senha);
        usuarios = queryUsr.getResultList();
        if (usuarios.size() == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public Usuario cadastrarUsuario(Usuario t){
        EntityManagerFactory em = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager emf = em.createEntityManager();
        Query queryUsr = emf.createQuery("SELECT u FROM Usuario u WHERE u.login = :usuario", Usuario.class);
        queryUsr.setParameter("usuario", t.getLogin());
        usuarios = queryUsr.getResultList();
        if (usuarios.size() != 1){
            try {
            emf.getTransaction().begin();
            if(t.getId() == null){
                emf.persist(t);
            }
            emf.getTransaction().commit();
        } catch (Exception e) {
        }finally{
            FacesMessage msg = new FacesMessage("O usuário " + t.getLogin() + " foi cadastrado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
            Iterator<UIComponent> filhos = view.getFacetsAndChildren();
            clearComponents(filhos);
            em.close();
        }
        }else{
        FacesMessage msg = new FacesMessage("O usuário " + t.getLogin() + " já está em uso");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        Iterator<UIComponent> filhos = view.getFacetsAndChildren();
        clearComponents(filhos);
            return t;
        }
        return null;
    }
    
    private static void clearComponents(Iterator<UIComponent> filhosid) {
        while (filhosid.hasNext()) {
            UIComponent component = filhosid.next();
            if (component instanceof HtmlInputText) {
                HtmlInputText com = (HtmlInputText) component;
                com.resetValue();
            }
            if (component instanceof HtmlSelectOneMenu) {
                HtmlSelectOneMenu com = (HtmlSelectOneMenu) component;
                com.resetValue();
            }
            if (component instanceof HtmlSelectBooleanCheckbox) {
                HtmlSelectBooleanCheckbox com = (HtmlSelectBooleanCheckbox) component;
                com.resetValue();
            }
            if (component instanceof HtmlSelectManyCheckbox) {
                HtmlSelectManyCheckbox com = (HtmlSelectManyCheckbox) component;
                com.resetValue();
            }

            clearComponents(component.getFacetsAndChildren());

        }
    }
}
