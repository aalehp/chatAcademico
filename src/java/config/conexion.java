package config;

import java.sql.*;
import servidor.Hash;
public class conexion {
    
    public Connection getConexion() throws ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String ruta = "jdbc:mysql://localhost:3306/chatacademico";
        
        try {
            Connection conexion = DriverManager.getConnection(ruta, "consultas", "root0408");
            System.out.println("Conexion lista");
            return conexion;
        } catch (SQLException e) {
            
            System.out.println("Error al conectar al servidor \n " + e.toString());
            return null;
        }
        
    }
    
    public String logear(String matricula, String contra){
        Connection conexion;
        conexion con = new conexion();
        
        String matri = null;
        
        String qry = "SELECT * FROM usuarios WHERE matricula = '"+ matricula + "' AND psw = '"+Hash.sha1(contra) +"';" ;
        
        System.out.println(""+matricula+" "+contra);
        
        try {
            conexion = con.getConexion();
            PreparedStatement ps = conexion.prepareStatement(qry);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                matri = rs.getString("matricula");
            }
            
            conexion.close();
            
        } catch (ClassNotFoundException | SQLException e) {
        }
        
        
        return matri;
    }
    
    
}
