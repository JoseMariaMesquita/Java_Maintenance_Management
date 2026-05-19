/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConfigDB;
import entities.Maquina;
import exceptions.DBException;
import exceptions.DatoNoEncontradoException;
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

    public static void altaMaquina(Maquina m) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO maquinas(tipo_maquina) VALUES(?)";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setString(1, m.getTipoMaquina());
            pS.execute();
        } catch (SQLException ex) {
            throw new DBException();
        }finally{
            if(conn != null){
                ConfigDB.closeDB();
            }
        }
    }
    
    /**
     *
     * Lista los elementos en la tabla maquinas
     *
     * @return ResultSet con los elementos de la tabla maquinas
     * @throws DBException Excepcion que se lanza con errores relacionados con
     * la base de datos
     */
    public static ResultSet listarMaquinas() throws DBException {

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
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }

    }

    public static Maquina obtenerMaquina(int idMaquina) throws DBException, DatoNoEncontradoException {
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM maquinas WHERE id_maquina = ?";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, idMaquina);
            rS = pS.executeQuery();
            Maquina m;
            if (rS.next()) {
                m =  new Maquina(rS.getInt(1),rS.getString(2));
            } else {
                throw new DatoNoEncontradoException("Maquina Inexistente");
            }
            return m;
        } catch (SQLException ex) {
            throw new DBException("Error al obtener maquina: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

}
