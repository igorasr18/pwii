/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import infra.Compromisso;
import infra.CompromissoDAO;
import infra.SessionUtil;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Lucas Carvalho
 */
@ManagedBean
@ViewScoped
public class EventoBean {
    
    private String usuario;
    private String titulo;
    private Date inicio;
    private Date fim;
    private boolean status;
    private String descricao;
    
    private Compromisso evento;
    List<Compromisso> eventos;
    private ScheduleModel eventModel;
    private List<Compromisso> listaEvento;
    private CompromissoDAO eDao;
    
    
    public void salvar(Compromisso comp) {
        
        CompromissoDAO dao = new CompromissoDAO();
        dao.salvarDAO(comp);
    }
    
    public void atualizar(Compromisso comp){
        
        CompromissoDAO dao = new CompromissoDAO();
        dao.atualizar(comp);
    }
    
    public void deletar(){
            try {
                eDao.deletar(evento);
                inicializar();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public void quandoSelecionado(SelectEvent selectEvent){
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();
        
        for(Compromisso ev : listaEvento){
            if(ev.getId() == (Long)event.getData()){
                evento = ev;
                break;
            }
        }
    }
    
    public void quandoNovo(SelectEvent selectEvent){
        ScheduleEvent event = new DefaultScheduleEvent("", (Date)selectEvent.getObject(), (Date)selectEvent.getObject());
        evento = new Compromisso();
        evento.setInicio(new java.sql.Date(event.getStartDate().getTime()));
        evento.setFim(new java.sql.Date(event.getEndDate().getTime()));
    }
    
    public void quandoMovido(ScheduleEntryMoveEvent event){
        for(Compromisso ev : listaEvento){
            if(ev.getId() == (Long)event.getScheduleEvent().getData()){
                evento = ev;
                eDao = new CompromissoDAO();
                try {
                    eDao.atualizar(evento);
                    inicializar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    public void quandoRedimensionado(ScheduleEntryResizeEvent event){
        for(Compromisso ev : listaEvento){
            if(ev.getId() == (Long)event.getScheduleEvent().getData()){
                evento = ev;
                eDao = new CompromissoDAO();
                try {
                    eDao.atualizar(evento);
                    inicializar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    public void salvarEvento(){
        if(evento.getId() == null){
            if(evento.getInicio().getTime() <= evento.getFim().getTime()){
                eDao = new CompromissoDAO();
                try {
                    eDao.salvarDAO(evento);
                    inicializar();
                } catch (Exception e) {
                    e.printStackTrace();    
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data inicial não pode ser maior que a final","Data inicial não pode ser maior que a final"));
            }
        }else{
            try {
                eDao.atualizar(evento);
                inicializar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    @PostConstruct
    public void inicializar(){
        eDao = new CompromissoDAO();
        evento = new Compromisso();
        eventModel = new DefaultScheduleModel();
        
        try {
            listaEvento = eDao.todosEventos();
        } catch (SQLException ex) {
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Erro no SQL"));
        }
        
        for(Compromisso ev : listaEvento){
            DefaultScheduleEvent evt = new DefaultScheduleEvent();
            evt.setEndDate(ev.getFim());
            evt.setStartDate(ev.getInicio());
            evt.setTitle(ev.getTitulo());
            evt.setData(ev.getId());
            evt.setDescription(ev.getDescricao());
            evt.setAllDay(true);
            evt.setEditable(true);
            
            if(ev.isStatus() == true){
                evt.setStyleClass("emp1");
            }else if(ev.isStatus() == false){
                evt.setStyleClass("emp2");
            }
            eventModel.addEvent(evt);
        }
    }

    public Compromisso getEvento() {
        return evento;
    }

    public void setEvento(Compromisso evento) {
        this.evento = evento;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public List<Compromisso> getListaEvento() {
        return listaEvento;
    }

    public void setListaEvento(List<Compromisso> listaEvento) {
        this.listaEvento = listaEvento;
    }

    public CompromissoDAO geteDao() {
        return eDao;
    }

    public void seteDao(CompromissoDAO eDao) {
        this.eDao = eDao;
    }
    
    
}
