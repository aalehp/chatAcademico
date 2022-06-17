<%-- 
    Document   : index
    Created on : Jun 11, 2020, 7:06:53 PM
    Author     : Learn new here
--%>
<%@page import="modelo.usuariosDAO"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es-mx">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style media="screen" type="text/css">
            .chat {
                width: 100%;
                height: 200px;
                border: 1px solid silver;
                overflow-y: scroll;
            }
            #msg {
                width: 99%;
            }
            h1 {
                text-align: center;
            }
        </style>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css"/>

    </head>
    <script type="text/javascript">

        var mensajes = {};

        var wsUrl;
        if (window.location.protocol === 'http:') {
            wsUrl = 'ws://';
        } else {
            wsUrl = 'wss://';
        }
        var ws = new WebSocket(wsUrl + window.location.host + "/chatAcademico/chat");

        ws.onmessage = function (event) {
            var mySpan = document.getElementById("chat");
            mySpan.innerHTML += event.data + "<br/>";
        };

        ws.onerror = function (event) {
            console.log("Error ", event);
        };

        function sendMsg() {
            var msg = document.getElementById("msg").value;
            var usr = "${matricula}";
            
            if (msg)
            {
                ws.send(usr+'#'+msg, usr);
            }
            document.getElementById("msg").value = "";
        }
        ;


    </script>
    <body style="margin: 1%">

        <%
            HttpSession sesion = request.getSession();
            String matricula = sesion.getAttribute("matricula").toString();
            usuariosDAO us = new usuariosDAO();

            if (sesion.getAttribute("matricula") != null) {
                us.LogearUsuario(matricula);
                out.print("<a href='login.jsp?cerrar=true'><h5>Cerrar Sesión " + matricula + "<h5> </a>");
                out.print("<p hidden id='usuario'>" + matricula + "</p>");
            } else if (sesion.getAttribute("matricula") == null) {
                response.sendRedirect("login.jsp");
            } else {
                //us.DeslogUsuario(matricula);
                sesion.invalidate();
                out.print("<script>location.replace('login.jsp');</script>");
            }

        %>


        <h1>CHAT ACADEMICO BUAP</h1>

        <div>
            <div id="chat" class="chat" style="margin-bottom: 1%; height: 500px" ></div>
        </div>

        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Escribe Tu Mensaje" aria-label="Recipient's username" aria-describedby="button-addon2" id="msg" style="width: 10% ">
            <button class="btn btn-outline-secondary" type="button" id="button-addon1" onclick="return sendMsg();">Enviar</button>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>