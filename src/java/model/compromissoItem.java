/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Igor
 */
public class compromissoItem {
    private String usuario;
    private String compromisso;
    private String data;
    private String hora;
    private int id;
 
    public compromissoItem(){
        usuario = "";
        compromisso = "";
        data = "";
        hora = "";
        id = 0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getCompromisso() {
        return compromisso;
    }
    public void setCompromisso(String compromisso) {
        this.compromisso = compromisso;
    }   
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    } 
}
    

