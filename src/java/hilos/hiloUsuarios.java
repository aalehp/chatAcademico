package hilos;

import servidor.Servidor;
import config.conexion;
import java.net.ServerSocket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class hiloUsuarios extends Thread {

    private JList usuariosConectados;
    private Servidor s;
    private Connection conexion;
    private conexion con;
    
    public hiloUsuarios(JList chatMsg, Servidor s){
        this.usuariosConectados = chatMsg;
        this.s = s;
        con = new conexion();
        try {
            conexion = con.getConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(hiloUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    PreparedStatement ps;
    ResultSet rs;

    @Override
    public void run() {
        try {
            while (true) {
                
                sleep(1000);
                ps = conexion.prepareStatement("SELECT matricula FROM usuarios WHERE edoSesion = 1 ORDER BY edoSesion ASC");
                rs = ps.executeQuery();
                
                DefaultListModel modelo = new DefaultListModel();
                usuariosConectados.setModel(modelo);
                modelo = (DefaultListModel) usuariosConectados.getModel();

                while (rs.next()) {
                    modelo.addElement(rs.getString("matricula"));
                }
                
            }

        } catch (InterruptedException | SQLException e) {
            System.out.println("Erro al Registrar el mensaje");
        }

    }

}
