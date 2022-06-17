<%-- 
    Document   : registrarUsuario
    Created on : 9/05/2022, 10:16:59 AM
    Author     : pato_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-mx">
    <head>
        <title>Registro</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>

        <section>

            <div class="row g-0">
                <div class="col-lg-8" style="padding-top: 5%;">
                    <div class="text-center">
                        <h1>Registrate</h1>
                        <br>
                        <h4>Crea Tu Cuenta</h4>
                    </div>
                    <div class=" row g-0" style="padding-left: 25%; padding-right:25%; padding-top: 5%">
                        <form action="registroCompleto.jsp">
                            <div class="mb-3">
                                <label for="InputMatricula" class="form-label">Matricula:</label>
                                <input type="number" class="form-control" name="inputMatricula">
                            </div>
                            <div class="mb-3">
                                <label for="InputNombre" class="form-label">Nombre:</label>
                                <input type="text" class="form-control" name="inputNombre">
                            </div>
                            <div class="mb-3">
                                <label for="InputContraseña" class="form-label">Contraseña:</label>
                                <input type="password" class="form-control" name="inputContraseña">
                            </div>
                            <div class="mb-3">
                                <label for="InputMateria" class="form-label">Materia:</label>
                                <input type="text" class="form-control" name="inputMateria">
                            </div>
                            <div class="mb-3">
                                <label for="InputPromedio" class="form-label">Promedio</label>
                                <input type="text" class="form-control" name="inputPromedio">
                            </div>
                            
                            <button onclick="location.href='UsuariosController?accion=insertUser'" type="submit" class="btn btn-primary">Regsitrar</button>
                            <button onclick="location.href='UsuariosController?accion=logear'" type="button" class="btn btn-secondary">iniciar Sesión</button>
                        </form>
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
