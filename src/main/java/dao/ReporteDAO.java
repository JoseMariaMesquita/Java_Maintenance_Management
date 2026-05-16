/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConfigDB;
import entities.Reporte;
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
public class ReporteDAO {

    /**
     *
     * Registra reporte ne la base de datos
     *
     * @param re Reporte que se desea insertar en la base detos
     * @throws DBException Excepcionde errores relacionados a la base de datos
     */
    public static void insertarReporte(Reporte re) throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO reportes(id_user,id_maquina,descripcion,fecha,hora_inicio,hora_final) VALUES(?,?,?,?,?,?)";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, re.getIdUser());
            pS.setInt(2, re.getIdMaquina());
            pS.setString(3, re.getDescripcion());
            pS.setDate(4, re.getFecha());
            pS.setTime(5, re.getHoraInicio());
            pS.setTime(6, re.getHoraFinal());
            pS.execute();
        } catch (SQLException ex) {
            throw new DBException("Error Insercion de Reporte: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

    /**
     * 
     * Elimina elreporte de la base de datos
     * 
     * @param idReporte id del reporte que se desea eliminar
     * @throws DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     * @throws DatoNoEncontradoException Excepcion que selanza cuando no se encuentra un valor en la base de datos
     */
    public static void eliminarReporte(int idReporte) throws DBException, DatoNoEncontradoException {

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM reportes WHERE id_reporte = ?";

        try {
            conn = ConfigDB.connectDB();

            if (existeReporte(idReporte)) {
                pS = conn.prepareStatement(sqlStatement);
                pS.setInt(1, idReporte);
                pS.execute();
            } else {
                throw new DatoNoEncontradoException("Reporte Inexistente");
            }
        } catch (SQLException ex) {
            throw new DBException("Error eliminar reporte: " + ex.getMessage());
        }finally{
            if(conn != null){
                ConfigDB.closeDB();
            }
        }

    }

    /**
     *
     * Lista todos los elementos de la tabla reportes de la base dde datos
     *
     * @return Result Set con todos los elementos de la tabla reportes
     * @throws DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     */
    public static ResultSet listarReportes() throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM reportes";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            return rS;
        } catch (SQLException ex) {
            throw new DBException("Error al listar reportes: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

    /**
     * 
     * Busca el reporte en la base de datos
     * 
     * @param idReporte id del reporte que se esta buscando
     * @return true si el reporte se encuentra en la base de datos falso si el valor no esta
     * @throws DBException Excepcion que se lanza cuando courre algun error
     * relacionado ala base de datos
     */
    private static boolean existeReporte(int idReporte) throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM reportes WHERE id_reporte = ?";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, idReporte);
            rS = pS.executeQuery();

            while (rS.next()) {
                if (rS.getInt("id_reporte") == idReporte) {
                    return true;
                }
            }

            return false;
        } catch (SQLException ex) {
            throw new DBException("Error al buscar reporte: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

}
