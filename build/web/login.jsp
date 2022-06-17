<%-- 
    Document   : login
    Created on : 12/05/2022, 12:32:33 AM
    Author     : pato_
--%>

<%@page import="modelo.usuariosDAO"%>
<%@page import="config.conexion"%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="es-mx">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>

    <body>

        <section>

            <div class="row g-0">
                <div class="col-lg-5" style="padding-top: 5%;">
                    <div class="text-center">
                        <h1>Bienvenido</h1>
                        <br>
                        <h4>Ingresa Tus Datos Para Iniciar Sesión</h4>
                    </div>
                    <div class=" row g-0" style="padding-left: 25%; padding-right:25%; padding-top: 5%">

                        <form action="login.jsp" method="POST">
                            <div class="mb-3">
                                <label for="InputMatricula" class="form-label">Matricula</label>
                                <input type="number" class="form-control" name="inputMatricula">
                            </div>
                            <div class="mb-3">
                                <label for="InputContraseña" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" name="inputContrasena">
                            </div>

                            <!--  Controller iniciar sesion accion logear --> 
                            <button onclick="location.href='UsuariosController?accion=chat'" type="submit" class="btn btn-primary" value="ingresar" name="btnIngresar">Iniciar Sesión</button> 
                            <!--  Controller registrar Usuario accion nuevo --> 
                            <button onclick="location.href='UsuariosController?accion=nuevo'" type="button" class="btn btn-secondary " style="margin-left: 2%">Registrarse</button>
                        </form>

                        <%
                            conexion op = new conexion();
                            usuariosDAO us = new usuariosDAO();
                            String matricula = request.getParameter("inputMatricula");
                            String pass = request.getParameter("inputContrasena");
                            
                            if (request.getParameter("btnIngresar") != null) {
                                HttpSession sesion = request.getSession();
                                if (op.logear(matricula, pass) != null) {
                                    sesion.setAttribute("matricula", matricula);
                                    response.sendRedirect("prueba.jsp");
                                }
                            }
                            if (request.getParameter("cerrar") == "true") {
                                //us.DeslogUsuario(session.getAttribute("matricula").toString());
                                session.invalidate();
                            }

                        %>

                    </div>

                </div>

                <div class="col-lg-4">
                    <div class="px-lg-5 pt-lg-4 pb-lg-3 p-4">
                        <img src="imgs/logoBuapCuadrado.jpg" class="img-fluid"/>
                    </div>

                </div>

            </div>

        </section>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>

