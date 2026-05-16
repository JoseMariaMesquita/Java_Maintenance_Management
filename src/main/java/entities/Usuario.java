/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Alumno
 */
public class Usuario {

    private int idUser;
    private int idRol;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String hummm;

    public Usuario(int idRol, String nombre, String apellido, String dni, String telefono, String hummm) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.hummm = hummm;
    }

    public Usuario(int idUser, int idRol, String nombre, String apellido, String dni, String telefono, String hummm) {
        this.idUser = idUser;
        this.idRol = idRol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.hummm = hummm;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHummm() {
        return hummm;
    }

    public void setHummm(String hummm) {
        this.hummm = hummm;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUser=" + idUser + ", idRol=" + idRol + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", telefono=" + telefono + '}';
    }

}
