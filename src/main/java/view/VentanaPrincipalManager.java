/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.MaquinaDAO;
import dao.ReporteDAO;
import dao.UsuarioDAO;
import entities.Maquina;
import entities.Reporte;
import entities.Usuario;
import exceptions.DBException;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Usuario
 */
public class VentanaPrincipalManager extends JFrame {

    private Usuario u;

    //Labels
    private JLabel lbTitulo = new JLabel("Ventana Manager", JLabel.CENTER);

    //Botones
    private JButton btnExportarListado = new JButton("Exportar Reportes");
    private JButton btnImportarTrabajadores = new JButton("Importar Trabajadores");
    private JButton btnImportarMaquinas = new JButton("Importar Maquinas");

    //Paneles
    private JPanel pnTitulo = new JPanel(new GridLayout(1, 1, 10, 10));
    private JPanel pnBotones = new JPanel(new GridLayout(3, 1, 10, 10));

    public VentanaPrincipalManager(Usuario u) {
        this.u = u;
        innit();
    }

    private void innit() {

        this.setTitle("Ventana Principal Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(2, 1, 10, 10));

        this.lbTitulo.setFont(new Font("arial", Font.BOLD, 15));
        this.pnTitulo.add(this.lbTitulo);
        this.add(this.pnTitulo);

        this.pnBotones.add(this.btnExportarListado);
        this.pnBotones.add(this.btnImportarTrabajadores);
        this.pnBotones.add(this.btnImportarMaquinas);
        this.pnBotones.setBorder(new EmptyBorder(2, 20, 10, 20));
        this.add(this.pnBotones);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        this.btnExportarListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivo = new File("./src/main/resources/Reportes/Reportes.txt");
                if (archivo.exists()) {
                    try {
                        FileWriter fW = new FileWriter(archivo);
                        String reportes = "";
                        Reporte r = null;
                        ResultSet rS = ReporteDAO.listarReportes();
                        while (rS.next()) {
                            r = new Reporte(rS.getInt(1), rS.getInt(2), rS.getInt(3), rS.getString(4), rS.getDate(5), rS.getTime(6), rS.getTime(7));
                        }
                        fW.write(r.toString());
                        fW.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al escribir en archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (DBException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al recorrer ResultSet", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        archivo.createNewFile();
                        try {
                            FileWriter fW = new FileWriter(archivo);
                            String reportes = "";
                            Reporte r = null;
                            ResultSet rS = ReporteDAO.listarReportes();
                            while (rS.next()) {
                                r = new Reporte(rS.getInt(1), rS.getInt(2), rS.getInt(3), rS.getString(4), rS.getDate(5), rS.getTime(6), rS.getTime(7));
                                reportes += r.toString() + "\n";
                            }
                            
                            fW.write(reportes);
                            fW.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al escribir en archivo", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (DBException ex) {
                            JOptionPane.showMessageDialog(VentanaPrincipalManager.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al recorrer ResultSet", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al crear archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Reportes Exportadoos Correctamente", "Reportes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.btnImportarTrabajadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileInputStream fis = null;
                DataInputStream dis = null;
                try {
                    File archivo = new File("./src/main/resources/Files/usuarios.dat");
                    if (archivo.exists()) {
                        fis = new FileInputStream(archivo);
                        dis = new DataInputStream(fis);
                        while (dis.available() > 0) {
                            UsuarioDAO.altaUsuario(new Usuario(dis.readInt(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                        }
                    } else {
                        archivo.createNewFile();
                        fis = new FileInputStream(archivo);
                        dis = new DataInputStream(fis);
                        while (dis.available() > 0) {
                            UsuarioDAO.altaUsuario(new Usuario(dis.readInt(), dis.readInt(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                        }
                    }
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Usuarios Cargados Correctamente", "Usuarios", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error no se ha encontrado archivo", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error no se ha podido crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DBException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        dis.close();
                        fis.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al cerrar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.btnImportarMaquinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileInputStream fis = null;
                DataInputStream dis = null;
                try {
                    File archivo = new File("./src/main/resources/Files/maquinas.dat");
                    if (archivo.exists()) {
                        fis = new FileInputStream(archivo);
                        dis = new DataInputStream(fis);
                        while (dis.available() > 0) {
                            MaquinaDAO.altaMaquina(new Maquina(dis.readUTF()));
                        }
                    } else {
                        archivo.createNewFile();
                        fis = new FileInputStream(archivo);
                        dis = new DataInputStream(fis);
                        while (dis.available() > 0) {
                            MaquinaDAO.altaMaquina(new Maquina(dis.readUTF()));
                        }
                    }
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Maquinas Cargadas Correctamente", "Maquinas", JOptionPane.INFORMATION_MESSAGE);

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error no se ha encontrado archivo", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error no se ha podido crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DBException ex) {
                    JOptionPane.showMessageDialog(VentanaPrincipalManager.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        dis.close();
                        fis.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipalManager.this, "Error al cerrar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }
}
