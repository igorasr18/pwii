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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
    public List<compromissoItem> getCompromissos() {
        ResultSet rs = null;
        String strsql = "Select * from compromissos order by data, hora";
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            PreparedStatement prepStatement = conn.prepareStatement(strsql);
            rs = prepStatement.executeQuery();
 
            while (rs.next()) {
                compromissoItem item = new compromissoItem();
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
    //criar novo id na tabela e refazer o método
     public boolean deleteCompromisso(String userid){
        boolean isSuccess = false;
        ResultSet rs = null;
        String strsql = "delete from usersprofile where UserID=?";
        PreparedStatement prepStatement = null;        
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            int rtnCode = 0;
            getDbConn().setAutoCommit(false);
            prepStatement = conn.prepareStatement(strsql);
            prepStatement.setString(1, userid);
            rtnCode = prepStatement.executeUpdate();
            if (rtnCode > 0) {
                _dbConn.commit();
                isSuccess  =true;
            }else{                
                _dbConn.rollback();
                isSuccess  = false;
            }     
 
        } catch (SQLException ex) {
           //catch handler
        } finally {
            closeConnection();
        }       
 
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
    

