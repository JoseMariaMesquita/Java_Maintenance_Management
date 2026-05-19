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
    
    Managers(1,"Manager"),
    PersonalMantenimiento(2,"Mantenimiento");
    
    public final int idRol;
    public final String nombreRol;
    
    Roles(int idRol,String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }
    
}
