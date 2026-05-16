/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Alumno
 */
public class Tarea {

    private int idTarea;
    private int idMaquina;
    private Date dia;

    public Tarea(int idMaquina, Date dia) {
        this.idMaquina = idMaquina;
        this.dia = dia;
    }

    public Tarea(int idTarea, int idMaquina, Date dia) {
        this.idTarea = idTarea;
        this.idMaquina = idMaquina;
        this.dia = dia;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", idMaquina=" + idMaquina + ", dia=" + dia + '}';
    }

}
