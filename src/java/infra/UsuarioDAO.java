/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
}
