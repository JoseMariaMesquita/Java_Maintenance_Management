/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.MaquinaDAO;
import dao.ReporteDAO;
import static dao.ReporteDAO.insertarReporte;
import dao.UsuarioDAO;
import entities.Maquina;
import entities.Reporte;
import entities.Usuario;
import enums.Roles;
import exceptions.DBException;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Usuario
 */
class VentanaCrearReporte extends JFrame {

    VentanaPrincipalMantenimiento origen;
    
    //Labels
    private JLabel lbTitulo = new JLabel("Crear Reporte", JLabel.CENTER);
    private JLabel lbTrabajador = new JLabel("Trabajador: ");
    private JLabel lbMaquina = new JLabel("Maquina: ");
    private JLabel lbDescripcion = new JLabel("Descripcion: ");
    private JLabel lbFecha = new JLabel("Fecha: ");
    private JLabel lbHoraInicio = new JLabel("Hora Inicio: ");
    private JLabel lbHoraFinal = new JLabel("Hora Final: ");

    //Text Fields
    private JTextField tfFecha = new JTextField(10);
    private JTextField tfHoraInicio = new JTextField(10);
    private JTextField tfHoraFinal = new JTextField(10);

    //Text Area
    private JTextArea taDescripcion = new JTextArea(100, 100);

    //Combo Box
    private JComboBox cbTrabajadores = new JComboBox();
    private JComboBox cbMaquinas = new JComboBox();

    //Buttons
    private JButton btnRegistrar = new JButton("Registar");
    private JButton btnVolver = new JButton("Volver");

    //Panels
    private JPanel pTitulo = new JPanel();
    private JPanel pComboBoxes = new JPanel(new GridLayout(2, 2, 10, 10));
    private JPanel pTexto = new JPanel(new GridLayout(4, 2, 10, 10));
    private JPanel pBotones = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel pPaneles = new JPanel(new GridLayout(1, 2, 10, 10));

    //Scroll
    private JScrollPane scroll = new JScrollPane(this.taDescripcion);

    public VentanaCrearReporte(VentanaPrincipalMantenimiento origen) {
        this.origen = origen;
        innit();
    }

    private void innit() {
        this.setTitle("Crear Reportes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(origen);
        this.setLayout(new GridLayout(3, 1, 10, 10));

        this.lbTitulo.setFont(new Font("arial", Font.BOLD, 15));
        this.pTitulo.add(this.lbTitulo);
        this.add(this.pTitulo);

        ResultSet rS;
        try {
            rS = UsuarioDAO.listarUsuario();
            while (rS.next()) {
                Usuario u = new Usuario(rS.getInt(1), rS.getInt(2), rS.getString(3), rS.getString(4), rS.getString(5), rS.getString(6), rS.getString(7));
                if (u.getIdRol() == Roles.PersonalMantenimiento.idRol) {
                    this.cbTrabajadores.addItem(u.getNombre() + " " + u.getApellido());
                }
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recorrer ResultSet", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.pComboBoxes.add(this.lbTrabajador);
        this.pComboBoxes.add(this.cbTrabajadores);
        this.pComboBoxes.add(this.lbMaquina);
        ResultSet rSM;
        try {
            rSM = MaquinaDAO.listarMaquinas();
            while (rSM.next()) {
                Maquina m = new Maquina(rSM.getInt(1), rSM.getString(2));

                this.cbMaquinas.addItem(m.getTipoMaquina() + ": " + m.getIdMaquina());

            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recorrer ResultSet", "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.pComboBoxes.add(this.cbMaquinas);
        this.pPaneles.add(this.pComboBoxes);

        this.pTexto.add(this.lbFecha);
        this.tfFecha.setText(LocalDate.now().toString());
        this.tfFecha.setEnabled(false);
        this.pTexto.add(this.tfFecha);

        this.pTexto.add(this.lbHoraInicio);
        this.pTexto.add(this.tfHoraInicio);

        this.pTexto.add(this.lbHoraFinal);
        this.pTexto.add(this.tfHoraFinal);

        this.pTexto.add(this.lbDescripcion);
        this.pTexto.add(this.scroll);

        this.pPaneles.add(this.pTexto);
        this.pPaneles.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(this.pPaneles);

        this.btnRegistrar.setEnabled(false);
        this.pBotones.add(this.btnRegistrar);
        this.pBotones.add(this.btnVolver);
        this.pBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(this.pBotones);

        this.setSize(900, 900);
        this.setResizable(false);
        this.setVisible(true);

        this.tfHoraInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pattern patron = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");
                Matcher match = patron.matcher(tfHoraInicio.getText());

                if (match.matches()) {
                    tfHoraFinal.grabFocus();
                }

            }
        });

        this.tfHoraFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pattern patron = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");
                Matcher match = patron.matcher(tfHoraFinal.getText());

                if (match.matches() && !tfHoraInicio.getText().equals(tfHoraFinal.getText())) {
                    btnRegistrar.setEnabled(true);
                    taDescripcion.grabFocus();
                }

            }
        });
        
        this.btnRegistrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Usuario> trabajadores = new ArrayList<Usuario>();
                ArrayList<Maquina> maquina = new ArrayList<Maquina>();
                
                try {
                    ResultSet rSU = UsuarioDAO.listarUsuario();
                    ResultSet rSM = MaquinaDAO.listarMaquinas();
                    
                    while(rSU.next()){
                        trabajadores.add(new Usuario(rSU.getInt(1), rSU.getInt(2), rSU.getString(3), rSU.getString(4), rSU.getString(5), rSU.getString(6), rSU.getString(7)));
                    }
                    while(rSM.next()){
                        maquina.add(new Maquina(rSM.getInt(1), rSM.getString(2)));
                    }
                    
                    Reporte r = new Reporte(trabajadores.get(cbTrabajadores.getSelectedIndex()).getIdUser(),maquina.get(cbMaquinas.getSelectedIndex()).getIdMaquina(),taDescripcion.getText(),Date.valueOf(tfFecha.getText()),Time.valueOf(tfHoraInicio.getText()+":00"),Time.valueOf(tfHoraFinal.getText()+":00"));
                    ReporteDAO.insertarReporte(r);
                    JOptionPane.showMessageDialog(rootPane, "Reporte Registrado","Registro",JOptionPane.INFORMATION_MESSAGE);
                    tfHoraInicio.setText("");
                    tfHoraFinal.setText("");
                    taDescripcion.setText("");
                    btnRegistrar.setEnabled(false);
                } catch (DBException ex) {
                    System.getLogger(VentanaCrearReporte.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (SQLException ex) {
                    System.getLogger(VentanaCrearReporte.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });
        
        this.btnVolver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                origen.setVisible(true);
                dispose();
            }
        });
        
        
        

    }

}
