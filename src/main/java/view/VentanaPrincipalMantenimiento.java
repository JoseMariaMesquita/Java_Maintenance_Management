/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entities.Usuario;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Usuario
 */
public class VentanaPrincipalMantenimiento extends JFrame{
    private Usuario u;
    
    //Labels
    private JLabel lbTitulo = new JLabel("Ventana Control",JLabel.CENTER);
    
    //Botones
    private JButton btnCrearReporte = new JButton("Crear Reporte");
    private JButton btnListarReportes = new JButton("Listar Reportes");
    private JButton btnListarTareas = new JButton("Listar Tareas");
    
    //Paneles
    private JPanel pnTitulo = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pnBotones = new JPanel(new GridLayout(3, 1, 10, 10));
    
    public VentanaPrincipalMantenimiento(Usuario u){
        this.u = u;
        innit();
    }
    
    private void innit(){
        
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.setLayout(new GridLayout(2, 1, 10, 10));
        
        this.lbTitulo.setFont(new Font("arial",Font.BOLD,15));
        this.pnTitulo.add(this.lbTitulo);
        this.add(this.pnTitulo);
        
        this.pnBotones.add(this.btnCrearReporte);
        this.pnBotones.add(this.btnListarReportes);
        this.pnBotones.add(this.btnListarTareas);
        this.pnBotones.setBorder(new EmptyBorder(2,20,10,20));
        this.add(this.pnBotones);
        
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        
        
        this.btnCrearReporte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VentanaCrearReporte vcr = new VentanaCrearReporte();
            }
        });
        
        this.btnListarReportes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VentanaListarReporte vlr = new VentanaListarReporte();
            }
        });
        
        this.btnListarTareas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VentanaListarTarea vlt = new VentanaListarTarea();
            }
        
        });
        
    }
}
