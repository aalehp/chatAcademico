<%-- 
    Document   : registroCompleto
    Created on : 14/05/2022, 03:49:56 AM
    Author     : pato_
--%>

<%@page import="servidor.Hash"%>
<%@page import="config.conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- importacion de librerias -->

<%
    try {
        RequestDispatcher dispatcher = null;
        Connection conexion;
        conexion con = new conexion();
        conexion = con.getConexion();

        String matricula = request.getParameter("inputMatricula");
        String nombre = request.getParameter("inputNombre");
        String contraseña = request.getParameter("inputContraseña");
        String materia = request.getParameter("inputMateria");
        String promedio = request.getParameter("inputPromedio");

        PreparedStatement ps;

//        System.out.println("Hola ---" + matricula);
        ps = conexion.prepareStatement("INSERT INTO usuarios (matricula, nombre, psw, materia, promedio, edoSesion) values (?,?,?,?,?,?);");
        ps.setString(1, matricula);
        ps.setString(2, nombre);
        ps.setString(3, Hash.sha1(contraseña));
        ps.setString(4, materia);
        ps.setString(5, promedio);
        ps.setString(6, "0");
        ps.execute();
        conexion.close();
        dispatcher = request.getRequestDispatcher("login.jsp");

    } catch (Exception e) {
        System.out.println("Error");
    }
%>

<html lang="es-mx">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body Class="m-0 row justify-content-center">

        <div class="col-lg-5">

            <div class="px-lg-5 pt-lg-4 pb-lg-3 p-4">
                <img src="imgs/logoBuapCuadrado.jpg" class="img-fluid"/>
            </div>

            <div class="text-center">
                <h1>GRACIAS</h1>
                <br>
                <h2>Tu Registro se ha completado</h2>
                <button onclick="location.href = 'UsuariosController?accion=logear'"  type="button" class="btn btn-primary">Iniciar Sesión</button> 
            </div>
    </body>
</html>
