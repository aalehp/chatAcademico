package modelo;

import config.conexion;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class usuariosDAO {
    
    Connection conexion;
    
    public usuariosDAO() throws ClassNotFoundException{
        conexion con = new conexion();
        conexion = con.getConexion();

    }
    
    public List<usuarios> listaUsuarios(){
        
        PreparedStatement ps;
        ResultSet rs;
        
        List<usuarios> lista = new ArrayList<>();
        
        try {
            ps = conexion.prepareStatement("SELECT matricula, nombre, psw, materia, promedio FROM usuarios");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nombre = rs.getString("nombre");
                String psw = rs.getString("psw");
                String materia = rs.getString("materia");
                double promedio = rs.getDouble("promedio");
                
                usuarios usuario = new usuarios(matricula, nombre, psw, materia, promedio);
                
                lista.add(usuario);
            }
            
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
        
    }
    
    
    public void RegistrarUsuario (String matricula, String nombre, String contraseña, String materia, String promedio){
         PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("INSERT INTO usuarios (matricula, nombre, psw, materia, promedio, edoSesion) values (?,?,?,?,?,?);");
            ps.setString(1, matricula);
            ps.setString(2, nombre);
            ps.setString(3, contraseña);
            ps.setString(4, materia);
            ps.setString(5, promedio);
            ps.setString(6, "0");

            ps.execute();
            
            
        } catch (SQLException e) {
            
            System.out.println("Error"+e);
            
        }
    }
    
    public void LogearUsuario(String matricula){
        PreparedStatement ps;
        
        try {
            
            ps = conexion.prepareStatement("UPDATE usuarios SET edoSesion = '1' WHERE usuarios.matricula ='"+matricula+"';");
            ps.execute();
            
            Socket socket = new Socket("192.168.100.13", 9997);
            DataOutputStream objeto = new DataOutputStream(socket.getOutputStream());


            objeto.writeUTF(matricula);
            socket.close();
            
        } catch (Exception e) {
        }
    }
    
    public void DeslogUsuario(String matricula){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("UPDATE usuarios SET edoSesion = '0' WHERE usuarios.matricula ='"+matricula+"';");
            ps.execute();
            
            Socket socket = new Socket("192.168.100.13", 9996);
            DataOutputStream objeto = new DataOutputStream(socket.getOutputStream());


            objeto.writeUTF(matricula);
            socket.close();
        } catch (Exception e) {
        }
    }
}
