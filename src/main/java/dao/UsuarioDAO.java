/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.ConfigDB;
import entities.Usuario;
import exceptions.DBException;
import exceptions.DatoNoEncontradoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alumno
 */
public class UsuarioDAO {

    /**
     *
     * Da de alta a un usuario en la base de datos
     *
     * @param u Usuario al que se le va a dar de alta
     * @throws DBException Excepcion relacionada con errores relativos a la base
     * de datos
     */
    public static void altaUsuario(Usuario u) throws DBException {

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO usuarios(id_rol,nombre,apellido,dni,telefono,contraseña) VALUES(?,?,?,?,?,?)";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, u.getIdRol());
            pS.setString(2, u.getNombre());
            pS.setString(3, u.getApellido());
            pS.setString(4, u.getDni());
            pS.setString(5, u.getContraseña());
            pS.execute();
        } catch (SQLException ex) {
            throw new DBException("Error durante alta usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }

    }

    /**
     *
     * Da de baja un usuario en la base de datos
     *
     * @param idUsuario id del usuario al que se le quiere dar de baja
     * @throws DBException Excepcion relacionada con errores relativos a la base
     * de datos
     * @throws DatoNoEncontradoException Excepcion que se lanza cuando un dato
     * no se encuentra en la base dde datos
     */
    public static void bajaUsuario(int idUsuario) throws DBException, DatoNoEncontradoException {
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM usuarios WHERE id_usr = ?";

        try {
            conn = ConfigDB.connectDB();

            if (existeUsuario(idUsuario)) {
                pS = conn.prepareStatement(sqlStatement);
                pS.setInt(1, idUsuario);
                pS.execute();
            } else {
                throw new DatoNoEncontradoException("Usuario Inexistente");
            }
        } catch (SQLException ex) {
            throw new DBException("Error baja usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

    /**
     *
     * Lista todos los usuarios de la base de datos
     *
     * @return rS Result set conteniendo todos los usuarios de la base de datos
     * @throws exceptions.DBException Excepcion que se lanza cuando courre algun
     * error relacionado ala base de datos
     */
    public static ResultSet listarUsuario() throws DBException {
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM usuarios";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            return rS;
        } catch (SQLException ex) {
            throw new DBException("Error a listar usuarios: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

    /**
     *
     * @param idUsuario
     * @return
     * @throws DBException
     */
    private static boolean existeUsuario(int idUsuario) throws DBException {
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS = null;
        String sqlStatement = "SELECT * FROM usuarios WHERE id_usr = ?";

        try {
            conn = ConfigDB.connectDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1, idUsuario);
            rS = pS.executeQuery();

            while (rS.next()) {
                if (rS.getInt("id_usr") == idUsuario) {
                    return true;
                }
            }

            return false;
        } catch (SQLException ex) {
            throw new DBException("Error al buscar usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                ConfigDB.closeDB();
            }
        }
    }

}
