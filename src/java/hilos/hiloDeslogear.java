package hilos;

import config.conexion;
import java.io.DataInputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import servidor.Servidor;

public class hiloDeslogear extends Thread {

    private JTextArea chatMsg;
    private Servidor s;
    private String usuarios[];

    public hiloDeslogear(JTextArea chatMsg, Servidor s) {
        this.chatMsg = chatMsg;
        this.s = s;
    }

    @Override
    public void run() {

        try {
            ServerSocket servidor = new ServerSocket(9996);
            while (true) {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String[] tiempo = timeStamp.split(" ");
                String[] hora = tiempo[1].split(":");
                String id = null, horaLog = null, fechaLog = null;

                Socket misocket = servidor.accept();
                DataInputStream recibir_datos = new DataInputStream(misocket.getInputStream());
                String matricula = recibir_datos.readUTF();
                out.print(matricula);

                Connection conexion;
                conexion con = new conexion();
                try {
                    conexion = con.getConexion();
                    PreparedStatement ps;
                    ResultSet rs;

                    ps = conexion.prepareStatement("SELECT * FROM registrosesiones WHERE matricula = '" + matricula + "' AND FechaLogOut IS NULL AND horaLogOut IS NULL AND tiempoSesion IS NULL;");
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        id = rs.getString(1);
                        horaLog = rs.getString(3);
                        fechaLog = rs.getString(4);
                    }

                    out.println(fechaLog + " " + horaLog);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
                    timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                    Date fechaInicial = dateFormat.parse(fechaLog + " " + horaLog);
                    Date fechaFinal = dateFormat.parse(timeStamp);
                    out.println(timeStamp);
                    int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);

                    int dias = 0;
                    int horas = 0;
                    int minutos = 0;
                    if (diferencia > 86400) {
                        dias = (int) Math.floor(diferencia / 86400);
                        diferencia = diferencia - (dias * 86400);
                    }
                    if (diferencia > 3600) {
                        horas = (int) Math.floor(diferencia / 3600);
                        diferencia = diferencia - (horas * 3600);
                    }
                    if (diferencia > 60) {
                        minutos = (int) Math.floor(diferencia / 60);
                        diferencia = diferencia - (minutos * 60);
                    }

                    String sesionTime = horas + ":" + minutos + ":" + diferencia;

                    ps = conexion.prepareStatement("UPDATE registrosesiones SET horaLogOut = '"+horaLog+"', FechaLogOut = '"+fechaLog+"', tiempoSesion = '"+sesionTime+"' WHERE registrosesiones.idSesion = "+id+";");
                    ps.execute();

                    conexion.close();
                } catch (ClassNotFoundException | SQLException e) {
                    out.print("Error: " + e);
                } catch (ParseException ex) {
                    Logger.getLogger(hiloDeslogear.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

}
