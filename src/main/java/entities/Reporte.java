/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Alumno
 */
public class Reporte {

    private int idReporte;
    private int idUser;
    private int idMaquina;
    private String descripcion;
    private Date fecha;
    private Time horaInicio;
    private Time horaFinal;

    public Reporte(int idUser, int idMaquina, String descripcion, Date fecha, Time horaInicio, Time horaFinal) {
        this.idUser = idUser;
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public Reporte(int idReporte, int idUser, int idMaquina, String descripcion, Date fecha, Time horaInicio, Time horaFinal) {
        this.idReporte = idReporte;
        this.idUser = idUser;
        this.idMaquina = idMaquina;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public String toString() {
        return "Reporte{" + "idReporte=" + idReporte + ", idUser=" + idUser + ", idMaquina=" + idMaquina + ", descripcion=" + descripcion + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + '}';
    }

}
