/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import infra.CompromissoDAO;
import infra.SessionUtil;
import infra.UsuarioDAO;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Lucas Carvalho
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable{
    private String usuario;
    private String senha;
    UsuarioDAO daoUser = new UsuarioDAO();
   
    public LoginBean(){
       
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
     }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
   
    public String autentica() {
        String url="";
        if(daoUser.verificarUsuario(this.usuario, this.senha)){
            //Object b = new Object();
            String b = this.usuario;
            SessionUtil.setParam("UsuarioLogado", b);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("UsuarioLogado", b);
            url="/Agenda/agendaFaces?faces-redirect=true";
        } else{
            url="newjsf?faces-redirect=true";
        }
        return url;
    }
}
