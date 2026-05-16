/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Alumno
 */
public class Maquina {
    
    private int idMaquina;
    private String tipoMaquina;

    public Maquina(int idMaquina, String tipoMaquina) {
        this.idMaquina = idMaquina;
        this.tipoMaquina = tipoMaquina;
    }

    public Maquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    @Override
    public String toString() {
        return "Maquina{" + "idMaquina=" + idMaquina + ", tipoMaquina=" + tipoMaquina + '}';
    }
 
    
}
