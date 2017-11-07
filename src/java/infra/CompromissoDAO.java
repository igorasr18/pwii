/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas Carvalho
 */
public class CompromissoDAO {
    
    List<Compromisso> eventos;
    Compromisso comp;
    CompromissoDAO daoComp;
    
    public List<Compromisso> todosEventos() throws SQLException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select c from Compromisso c where c.usuario = :usuario", Compromisso.class);
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String yourVariable = (String) sessionMap.get("UsuarioLogado");
        q.setParameter("usuario", SessionUtil.getParam("UsuarioLogado"));
        this.eventos = q.getResultList();
        em.close();
        return eventos;
    }
    
    public Compromisso salvarDAO(Compromisso t){
        EntityManagerFactory em = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager emf = em.createEntityManager();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String yourVariable = (String) sessionMap.get("UsuarioLogado");
        t.setUsuario(yourVariable);
        try {
            emf.getTransaction().begin();
            if(t.getId() == null){
                emf.persist(t);
            }
            emf.getTransaction().commit();
        } catch (Exception e) {
        }finally{
            em.close();
        }
        return t;
    }
    
    public Compromisso atualizar(Compromisso t) {
        EntityManagerFactory em = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager emf = em.createEntityManager();
        try {
            emf.getTransaction().begin();
            if (t.getId() != null) {
                t = emf.merge(t);        
            }
            emf.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return t;
    }
    
    public void deletar(Compromisso t){
        EntityManagerFactory em = Persistence.createEntityManagerFactory("Agenda3.0PU");
        EntityManager emf = em.createEntityManager();
        EntityTransaction tx = emf.getTransaction();
        tx.begin();
        t = emf.merge(t);
        emf.remove(t);
        tx.commit();
        em.close();
    }
    
}
