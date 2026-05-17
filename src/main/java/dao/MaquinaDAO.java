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
 * ToDo: Al terminar lo basico añadir como fncionalidad extra
 *
 * @author Usuario
 */
public class MaquinaDAO {

    /**
     * 
     * Lista los elementos en la tabla maquinas
     * 
     * @return ResultSet con los elementos de la tabla maquinas
     * @throws DBException Excepcion que se lanza con errores relacionados con la base de datos
     */
    public static ResultSet listarMaquinas() throws DBException{
        
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "Select * FROM maquinas";
        
        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            return rS;
        } catch (SQLException ex) {
            throw new DBException("Error listado maquinas: " + ex.getMessage());
        }finally{
            if(conn != null){
                ConfigDB.closeDB();
            }
        }
 
    }
    
}
