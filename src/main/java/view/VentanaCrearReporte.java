/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
class VentanaCrearReporte extends JFrame {

    //Labels
    private JLabel lbTitulo = new JLabel("Crear Reporte",JLabel.CENTER);
    private JLabel lbTrabajador = new JLabel();
    private JLabel lbMaquina = new JLabel();
    private JLabel lbProblema = new JLabel();
    private JLabel lbTipoProblema = new JLabel();

    
    public VentanaCrearReporte() {
        innit();
    }
    
    private void innit(){
    this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.setLayout(new GridLayout(4, 2, 10, 10));
        
    }
    
}
