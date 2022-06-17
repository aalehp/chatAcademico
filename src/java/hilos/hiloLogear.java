package hilos;

import config.conexion;
import java.io.DataInputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextArea;
import servidor.Servidor;

public class hiloLogear extends Thread {

    private JTextArea chatMsg;
    private Servidor s;
    private String usuarios[];

    public hiloLogear(JTextArea chatMsg, Servidor s) {
        this.chatMsg = chatMsg;
        this.s = s;
    }

    @Override
    public void run() {

        try {
            ServerSocket servidor = new ServerSocket(9997);
            while (true) {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String[] tiempo = timeStamp.split(" ");

                Socket misocket = servidor.accept();
                DataInputStream recibir_datos = new DataInputStream(misocket.getInputStream());
                String matricula = recibir_datos.readUTF();
                out.print(matricula);

                Connection conexion;
                conexion con = new conexion();
                try {
                    conexion = con.getConexion();
                    PreparedStatement ps;

                    ps = conexion.prepareStatement("INSERT INTO registrosesiones (idSesion, matricula, horaLogin, fechaLogin, horaLogOut, FechaLogOut, tiempoSesion) values (?,?,?,?,?,?,?);");
                    ps.setString(1, null);
                    ps.setString(2, matricula);
                    ps.setString(3, tiempo[1]);
                    ps.setString(4, tiempo[0]);
                    ps.setString(5, null);
                    ps.setString(6, null);
                    ps.setString(7, null);
                    ps.execute();
                    conexion.close();
                } catch (ClassNotFoundException | SQLException e) {
                }

            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}
