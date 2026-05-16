/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author Usuario
 */
public enum Roles {
    
    Supervisores(0,"Supervisor"),
    PersonalMantenimiento(1,"Mantenimiento");
    
    int idRol;
    String nombreRol;
    
    Roles(int idRol,String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }
    
}
