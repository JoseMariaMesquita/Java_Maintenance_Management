/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import exceptions.DBException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConfigDB {
    
    private static final String url = "jdbc:mariadb://localhost:3306/empresa";
    private static final String usr = "root";
    private static final String passwd = "";
    
    /**
     * Estable Conexion con la base de datos
     * 
     * @return Conexion con la base de datos
     * @throws DBException Excepcionde errores relacionados a la base de datos
     */
    public static Connection connectDB() throws DBException{
    
        try {
            return DriverManager.getConnection(url,usr,passwd);
        } catch (SQLException ex) {
            throw new DBException("Error Conexion: " + ex.getMessage());
        }
    }
    
    /**
     * 
     * Cierrra la conexion con la base de datos
     * 
     * @throws DBException Excepcionde errores relacionados a la base de datos
     */
    public static void closeDB() throws DBException{
        
        try {
            connectDB().close();
        } catch (SQLException ex) {
            throw new DBException("Error Cierre Conexion: " + ex.getMessage());
        }
        
    }
    
}
