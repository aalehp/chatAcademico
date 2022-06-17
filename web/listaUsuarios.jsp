<%-- 
    Document   : index
    Created on : 9/05/2022, 12:55:51 AM
    Author     : pato_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>

<html lang="es-mx">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <body>
        <h1 class="text-center">Usuarios Registrados</h1>

        <br/><br/>

        <table class="table table-dark table-hover, text-center">

            <thead>
                <tr>
                    <th> Matricula </th>
                    <th> Nombre </th>
                    <th> Materia </th>
                    <th> Promedio</th>

                </tr>
            </thead>

            <tbody>

                <c:forEach var="usuario" items="${lista}">
                    
                    <tr>
                        <td><c:out value="${usuario.matricula}"/></td>
                        <td><c:out value="${usuario.nombre}"/></td>
                        <td><c:out value="${usuario.materia}"/></td>
                        <td><c:out value="${usuario.promedio}"/></td>
                    </tr>
                    
                </c:forEach>

            </tbody>

        </table>

    </body>
</html>
        