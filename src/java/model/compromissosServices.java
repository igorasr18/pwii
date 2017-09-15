/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javax.servlet.DispatcherType;

/**
 *
 * @author Igor
 */
public class compromissosServices {
    public Connection getDbConn() {
        if(_dbConn == null){ 
           _dbConn = CheckConnection();
        }
        return _dbConn;
    }
    public void setDbConn(Connection aDbConn) {
        _dbConn = aDbConn;
    }
    public static String getDbUrl() {
        return _dbUrl;
    }
    public static void setDbUrl(String aDbUrl) {
 
        _dbUrl = aDbUrl;
 
    }  
    public static String getUser() {
        return _user;
    }
    public static void setUser(String aUser) {
        _user = aUser;
    }
    public static String getDbPassword() {
        return _dbPassword;
    }
 
    public static void setDbPassword(String aDbPassword) {
        _dbPassword = aDbPassword;
    }
 
    private List<compromissoItem> compromissosLista;
    private static Connection _dbConn;
    private static String _dbUrl = "jdbc:mysql://localhost:3311/mysql";
    private static String _user = "root";
    private static String _dbPassword = "root";
    private final static Logger LOGGER = Logger.getLogger(compromissosServices.class.getName());
 
    public compromissosServices() {
        compromissosLista = new ArrayList<>();
        _dbUrl = "jdbc:mysql://localhost:3311/mysql";
        _user = "root";
        _dbPassword = "root";      
    }
    
    public compromissoItem getCompromisso(String idCompromisso) {
        ResultSet rs = null;
        compromissoItem item = new compromissoItem();
        String strsql = "select * from compromissos where id = ?";
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            PreparedStatement prepStatement = conn.prepareStatement(strsql);
            prepStatement.setString(1, idCompromisso);
            rs = prepStatement.executeQuery();
 
            while (rs.next()) {                
                item.setId(Integer.parseInt(rs.getString("id")));
                item.setUsuario(rs.getString("usuario"));
                item.setCompromisso(rs.getString("compromisso"));
                item.setData(rs.getString("data"));
                item.setHora(rs.getString("hora"));
            }
 
        } catch (SQLException ex) {
            //handle catch
        } finally {
            closeConnection();
        }
        return item;
 
    }
    
    
    public List<compromissoItem> getCompromissos(String usuario) {
        ResultSet rs = null;
       
        String strsql = "select * from compromissos where usuario = ? order by data, hora";
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            PreparedStatement prepStatement = conn.prepareStatement(strsql);
            prepStatement.setString(1, usuario);
            rs = prepStatement.executeQuery();
 
            while (rs.next()) {
                compromissoItem item = new compromissoItem();
                item.setId(Integer.parseInt(rs.getString("id")));
                item.setUsuario(rs.getString("usuario"));
                item.setCompromisso(rs.getString("compromisso"));
                item.setData(rs.getString("data"));
                item.setHora(rs.getString("hora"));
                compromissosLista.add(item);
            }
 
        } catch (SQLException ex) {
            //handle catch
        } finally {
            closeConnection();
        }
        return compromissosLista;
 
    }
    
    public void editarClick(int id){
        //document.getElementById('btnEditar').style.visibility="hidden";
    }
     
     public boolean updateCompromisso (String id, String compromisso, String data, String hora) throws SQLException{
        boolean isSuccess = false;
        ResultSet rs = null;
        String strsql = "update compromissos set compromisso=?, data=?, hora=? where id=?";
        PreparedStatement prepStatement = null;        
        Connection conn = null;
        conn = CheckConnection();
        int rtnCode = 0;
        prepStatement = conn.prepareStatement(strsql);
        prepStatement.setString(1, compromisso);
        prepStatement.setString(2, data);
        prepStatement.setString(3, hora);
        prepStatement.setString(4, id);
        rtnCode = prepStatement.executeUpdate();
        isSuccess = true;
        return isSuccess;
     }
     
     public boolean deleteCompromisso(String id) throws SQLException{
        boolean isSuccess = false;
        ResultSet rs = null;
        String strsql = "delete from compromissos where id=?";
        PreparedStatement prepStatement = null;        
        Connection conn = null;
        conn = CheckConnection();
        int rtnCode = 0;
        prepStatement = conn.prepareStatement(strsql);
        prepStatement.setString(1, id);
        rtnCode = prepStatement.executeUpdate();
        isSuccess = true;
        return isSuccess;
    }
     public boolean insertCompromisso(String usuario, String compromisso, String data, String hora) throws SQLException{
        boolean isSuccess = false;
        ResultSet rs = null;
        String strsql = "insert into compromissos (usuario, compromisso, data, hora) values (?,?,?,?)";
        PreparedStatement prepStatement = null;        
        Connection conn = null;
        conn = CheckConnection();
        int rtnCode = 0;
        prepStatement = conn.prepareStatement(strsql);
        prepStatement.setString(1, usuario);
        prepStatement.setString(2, compromisso);
        prepStatement.setString(3, data);
        prepStatement.setString(4, hora);
        rtnCode = prepStatement.executeUpdate();
        isSuccess = true;
        return isSuccess;
    }
    
    public void closeConnection() {
        try {
            if (_dbConn != null) {
                _dbConn.close();
            }
        } catch (SQLException ex) {
            //handle catch
        }
    }
    public Connection CheckConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.info(e.getMessage());                    
        }     
 
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(_dbUrl, _user, _dbPassword);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());         
        }
        if (connection != null) {
            LOGGER.info("Conexão bem sucedida!");            
             
        } else {
            LOGGER.info("Falha na conexão!");    
        }
        return connection;
    }
    public List<compromissoItem> getCompromissosLista() {
        return compromissosLista;
    }
    public void setCompromissosLista(List<compromissoItem> compromissosLista) {
        this.compromissosLista = compromissosLista;
    }
}
    

