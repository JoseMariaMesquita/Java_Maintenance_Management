/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.UsuarioDAO;
import entities.Usuario;
import enums.Roles;
import exceptions.DBException;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Usuario
 */
public class VentanaLogin extends JFrame {

    //Labels
    private JLabel lbTitulo = new JLabel("LOGIN", JLabel.CENTER);
    private JLabel lbDNI = new JLabel("DNI: ", JLabel.CENTER);
    private JLabel lbContrasena = new JLabel("Contraseña: ", JLabel.CENTER);

    //Botones
    private JButton btnLogin = new JButton("Entrar");
    private JButton btnSalir = new JButton("Salir");

    //TextField y PassWordField
    private JTextField tfDNI = new JTextField(10);
    private JPasswordField pfContrasena = new JPasswordField(10);

    //Paneles
    private JPanel pTitulo = new JPanel();
    private JPanel pDNI = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pContrasena = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pBotones = new JPanel(new GridLayout(1, 2, 10, 10));

    public VentanaLogin() {
        innit();
    }

    private void innit() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(4, 1, 10, 10));

        this.lbTitulo.setFont(new Font("arial", Font.BOLD, 15));
        this.pTitulo.add(this.lbTitulo);
        this.pTitulo.setBorder(new EmptyBorder(10, 0, 0, 0));
        this.add(this.pTitulo);

        this.pDNI.add(this.lbDNI);
        this.pDNI.add(this.tfDNI);
        this.pDNI.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.add(this.pDNI);

        this.pContrasena.add(this.lbContrasena);
        this.pContrasena.add(this.pfContrasena);
        this.pContrasena.setBorder(new EmptyBorder(0, 0, 0, 10));
        this.add(this.pContrasena);

        this.pBotones.add(this.btnLogin);
        this.pBotones.add(this.btnSalir);
        this.pBotones.setBorder(new EmptyBorder(0, 10, 10, 10));
        this.add(this.pBotones);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        /*
        ToDo: Cambiar el sistema de encryotado de contraseñas al terminar el curso
         */
        this.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ResultSet rS = UsuarioDAO.listarUsuario();
                    Usuario s = null;
                    while (rS.next()) {

                        String contrasena = "";
                        char[] arrayContrasena = pfContrasena.getPassword();
                        for (int i = 0; i < arrayContrasena.length; i++) {
                            contrasena += arrayContrasena[i];
                        }
                        if (tfDNI.getText().equals(rS.getString(5)) && Integer.toString(contrasena.hashCode()).equals(rS.getString(7))) {
                            s = new Usuario(rS.getInt(1), rS.getInt(2), rS.getString(3), rS.getString(4), rS.getString(5), rS.getString(6), rS.getString(7));
                        }
                    }
                    
                    if (s != null) {
                        if (s.getIdRol() == Roles.PersonalMantenimiento.idRol) {
                            VentanaPrincipalMantenimiento vP = new VentanaPrincipalMantenimiento(s);
                        } else if(s.getIdRol() == Roles.Managers.idRol){
                            VentanaPrincipalManager vpm = new VentanaPrincipalManager(s);
                        }
                        dispose();
                    } else {
                        tfDNI.setText("");
                        pfContrasena.setText("");
                        JOptionPane.showMessageDialog(VentanaLogin.this, "Error: DNI o Contraseña invalidos");
                    }
                } catch (DBException ex) {
                    System.out.println(ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Error recorrer lista: " + ex.getMessage());
                }

            }
        });

        this.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
