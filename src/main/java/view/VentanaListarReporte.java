/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.MaquinaDAO;
import dao.ReporteDAO;
import dao.UsuarioDAO;
import exceptions.DBException;
import exceptions.DatoNoEncontradoException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
class VentanaListarReporte extends JFrame{

    //Label
    VentanaPrincipalMantenimiento origen;

    //Labels
    private JLabel lbTitulo = new JLabel("Listado Reportes", JLabel.CENTER);

    //Tabele
    private JTable tReportes = new JTable();

    //Buttons
    private JButton btnVolver = new JButton("Volver");

    //Panels
    private JPanel pTitulo = new JPanel();
    private JPanel pTabla = new JPanel(new GridLayout(1, 1, 10, 10));
    private JPanel pBotones = new JPanel(new GridLayout(1, 1, 10, 10));

    //Scroll
    private JScrollPane scroll = new JScrollPane(this.tReportes);

    public VentanaListarReporte(VentanaPrincipalMantenimiento origen) {
        this.origen = origen;
        innit();
    }

    private void innit() {
        this.setTitle("Listado Reportes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(origen);
        this.setLayout(new GridLayout(3, 1, 10, 10));

        this.lbTitulo.setFont(new Font("arial", Font.BOLD, 15));
        this.pTitulo.add(this.lbTitulo);
        this.add(this.pTitulo);

        DefaultTableModel dtf = new DefaultTableModel();
        dtf.addColumn("ID");
        dtf.addColumn("Trabajador");
        dtf.addColumn("Maquina");
        dtf.addColumn("Descripcion");
        dtf.addColumn("Fecha");
        dtf.addColumn("Hora Inicio");
        dtf.addColumn("Hora Finalizacion");
        ResultSet ReportesDAO;
        
        try {
            ResultSet rS = ReporteDAO.listarReportes();
            Object[] reportes = new Object[7];
            while(rS.next()){
                reportes[0] = rS.getInt(1);
                reportes[1] = UsuarioDAO.obtenerUsuario(rS.getInt(2)).getNombre() + " " + UsuarioDAO.obtenerUsuario(rS.getInt(2)).getApellido();
                reportes[2] = MaquinaDAO.obtenerMaquina(rS.getInt(3)).getTipoMaquina();
                reportes[3] = rS.getString(4);
                reportes[4] = rS.getDate(5);
                reportes[5] = rS.getTime(6);
                reportes[6] = rS.getTime(7);
                dtf.addRow(reportes);
            }
            this.tReportes.setModel(dtf);
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(origen, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(origen, "Error alrecorrer ResultSet","Error",JOptionPane.ERROR_MESSAGE);
        } catch (DatoNoEncontradoException ex) {
            JOptionPane.showMessageDialog(origen, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
        this.pTabla.add(this.scroll);
        this.pTabla.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(this.pTabla);

        this.pBotones.add(this.btnVolver);
        this.pBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(this.pBotones);

        this.setSize(900, 900);
        this.setResizable(false);
        this.setVisible(true);
        
        this.btnVolver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                origen.setVisible(true);
                dispose();
            }
        });
    }

}
