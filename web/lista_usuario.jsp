
<html>
    <head>      
        <title>Lista Usuario</title>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <%@include file="WEB-INF/jspf/cstyles.jspf" %>       
        <%@include file="WEB-INF/jspf/cheader.jspf" %>
        <%@include file="WEB-INF/jspf/cnav.jspf" %>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>      
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <link rel="stylesheet" href="resources/css/styles_lista.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">        
    </head>     
    <body>            
        <section>                        
            <div class="container">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>Lista <b>Usuarios</b></h2>
                            </div>
                            <div class="col-sm-7">
                                <a href="usuariocontroller.do?txtProceso=nuevo" class="btn btn-primary"><i class="material-icons">&#xE147;</i> <span>Nuevo Usuario</span></a>
                                <input type="button" onclick="tableToExcel('resultado', 'Usuarios')" value="Export to Excel" class="btn btn-primary" class="material-icons">
                                
                                <form id="formexcel" action="" method="post" name="formexcel"> <input type="hidden" name="exceldata" id="exceldata" value="" /> </form> 
                            </div>
                        </div>
                    </div>
                    <table id="resultado" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Apellidos</th>   <!-- Celda de cabecera de la columna 1 -->
                                <th>Nombres</th>   <!-- Celda de cabecera de la columna 2 -->
                                <th>Correo</th>   <!-- Celda de cabecera de la columna 3 -->
                                <th>Rol</th>
                             
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>                   
                            <c:forEach items="${lista}" var="usuario" >
                                <tr>
                                    <td>${usuario.getId()}</td>
                                    <td>${usuario.getNombre()}</td>
                                    <td>${usuario.getApellidos()}</td>  
                                    <td>${usuario.getCorreo()}</td>
                                    <td>${usuario.getRol()}</td>
                                   
                                    <td>${usuario.getEstado()}</td>
                                    <td>                                     
                                        <a href="usuariocontroller.do?txtProceso=mostrarID&id=<c:out value='${usuario.id}'/>" class="settings" title="Editar" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                        <a href="#" data-href="usuariocontroller.do?txtProceso=borrar&id=<c:out value='${usuario.id}'/>" data-target="#deleteEmployeeModal" class="delete" title="Borrar" data-toggle="modal"><i class="material-icons">&#xE5C9;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>                     
                    </table>
                </div>
            </div> 

            <div id="deleteEmployeeModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">                                                  
                        <div class="modal-header">						
                            <h4 class="modal-title">Eliminar Usuario</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Estas seguro que quieres eliminar este usuario?</p>
                            <p class="text-warning"><small>Esta acción no se puede regresar.</small></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                            <a class="btn btn-danger btn-ok">Eliminar</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
    <%@include file="WEB-INF/jspf/cjs.jspf" %>
    <%@include file="WEB-INF/jspf/cfooter.jspf" %>
</body>
</html>