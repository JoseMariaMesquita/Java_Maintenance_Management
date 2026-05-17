/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConfigDB;
import entities.Tarea;
import exceptions.DBException;
import exceptions.DatoNoEncontradoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class TareaDAO {

    /**
     * 
     * Inserta una tarea en la base de datos en la tabla tareas
     * 
     * @param ta tarea que se desea insertar
     * @throws DBException DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     */
    public static void insertarTarea(Tarea ta) throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO tareas(id_maquina,dia) VALUES(?,?)";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, ta.getIdMaquina());
            pS.setDate(2, ta.getDia());
            pS.execute();
        } catch (SQLException ex) {
            throw new DBException("Error Insertar Tarea: " + ex.getMessage());
        }
    }

    /**
     * 
     * Elimina la tarea pasada de la taba tareas en la base de datos
     * 
     * @param idTarea id de la tarea que se desea eliminar
     * @throws DBException DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     * @throws DatoNoEncontradoException Excepcion que salta cuando no se encuentra el dato pasado en la base de datos
     */
    public static void eliminarTarea(int idTarea) throws DBException, DatoNoEncontradoException {
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM tareas WHERE id_tarea = ?";
        
        try {
            conn = ConfigDB.connectDB();

            if (existeTarea(idTarea)) {
                pS = conn.prepareStatement(sqlStatement);
                pS.setInt(1, idTarea);
                pS.execute();
            } else {
                throw new DatoNoEncontradoException("Tarea Inexistente");
            }
        } catch (SQLException ex) {
            throw new DBException("Error al eliminar tarea");
        }
    }

    /**
     * 
     * Lista todas las tareas que se encuentra en la base de datos
     * 
     * @return Result Set con todas las tareas de la base de datos
     * @throws DBException DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     */
    public static ResultSet listarTareas() throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM tareas";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            return rS;
        } catch (SQLException ex) {
            throw new DBException("Error al listar tareas: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }

    }

    /**
     * 
     * Busca la tarea en la base dde datos, si esta existe devolvera true
     * 
     * @param idTarea id de la  tarea busccada
     * @return true si la tarea existe, false si no lo hace
     * @throws DBException DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     */
    private static boolean existeTarea(int idTarea) throws DBException {
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM tareas WHERE id_tarea = ?";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, idTarea);
            rS = pS.executeQuery();
            while (rS.next()) {
                if (rS.getInt("id_tarea") == idTarea) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            throw new DBException(("Error al buscar tarea: " + ex.getMessage()));
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }

    }

}
