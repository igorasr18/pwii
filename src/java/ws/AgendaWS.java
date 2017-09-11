/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import WebService.dao.UsuarioDAO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import WebService.modelo.Usuario;

/**
 * REST Web Service
 *
 * @author Lucas Carvalho
 */
@Path("Agenda")
public class AgendaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AgendaWS
     */
    public AgendaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.AgendaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/text")
    public String getJson() {
        return "testando ws json";
    }
    
    @GET
    @Produces("application/json")
    @Path("Usuario/get/{login}")
    public String getUsuario(@PathParam("login")String login){
        
        Usuario u = new Usuario();
        u.setUsuario(login);
        
        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);
        
        Gson g = new Gson();
        return g.toJson(u);
    }
    
    @GET
    @Produces("application/json")
    @Path("Usuario/List")
    public String listUsuarios(){
        
        List<Usuario> lista;
        UsuarioDAO dao = new UsuarioDAO();
        lista = dao.listar();
        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    /**
     * PUT method for updating or creating an instance of AgendaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
