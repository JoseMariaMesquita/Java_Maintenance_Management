/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConfigDB;
import exceptions.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ToDo: Al terminar lo basico añadir como funcionalidad extra
 * @author Usuario
 */
public class RolDAO {
    
    public static ResultSet listarRoles() throws DBException{
    
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM roles";
        
        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            return rS;
        } catch (SQLException ex) {
            throw new DBException("Error listado role: " + ex.getMessage());
        }finally{
            if(conn != null){
                ConfigDB.closeDB();
            }
        }
    }
    
}
