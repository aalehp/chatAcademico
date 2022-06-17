package hilos;

import config.conexion;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;
import modelo.mensaje;
import servidor.Servidor;

/**
 *
 * @author pato_
 */
public class hiloMensajes extends Thread {

    private JTextArea chatMsg;
    private Servidor s;

    public hiloMensajes(JTextArea chatMsg, Servidor s) {
        this.chatMsg = chatMsg;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(9998);
            
            while (true) {
                Socket misocket = servidor.accept();
                ObjectInputStream recibir_datos = new ObjectInputStream(misocket.getInputStream());
                mensaje paquete = (mensaje) recibir_datos.readObject();

                Connection conexion;
                conexion con = new conexion();
                conexion = con.getConexion();

                PreparedStatement ps;

                ps = conexion.prepareStatement("INSERT INTO chats (idMensaje, matricula, mensaje, idMsgRetroalimentado) values (?,?,?,?);");
                ps.setString(1, paquete.getId());
                ps.setString(2, paquete.getUsuario());
                ps.setString(3, paquete.getMensaje());
                ps.setString(4, paquete.getMsgRetroalimentado());
                ps.execute();
                conexion.close();

                chatMsg.append("\n"+paquete.getUsuario()+": "+ paquete.getMensaje());

            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
