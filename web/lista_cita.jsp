

<html>
    <head>
        <title>Lista Cita</title>
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
                                <h2>Lista <b>Citas</b></h2>
                            </div>
                            <div class="col-sm-7">
                                <a href="agenda.jsp" class="btn btn-primary"><i class="material-icons">&#xE147;</i> <span>Regresar</span></a>
                                                               <input type="button" onclick="tableToExcel('resultado', 'Citas')" value="Export to Excel" class="btn btn-primary" class="material-icons">						
                            </div>
                        </div>
                    </div>
                    <table id="resultado" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Paciente</th>   <!-- Celda de cabecera de la columna 1 -->
                                <th>Doctor</th>   <!-- Celda de cabecera de la columna 2 -->
                                <th>Especialidad</th>   <!-- Celda de cabecera de la columna 3 -->
                                <th>Fecha Atencion</th>
                                <th>Hora Atencion</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>                   
                            <c:forEach items="${lista}" var="cita" >
                                <tr>
                                    <td>${cita.getIdCita()}</td>
                                    <td>${cita.getPaciente()}</td>
                                    <td>${cita.getDoctor()}</td>  
                                    <td>${cita.getEspecialidad()}</td>
                                    <td>${cita.getFechaAtencion()}</td>
                                    <td>${cita.getHoraAtencion()}</td>  
                                    <td>${cita.getEstado()}</td>
                                    <td>                                     
                                        <a href="citacontroller.do?txtProceso=mostrarID&id=<c:out value='${cita.idCita}'/>" class="settings" title="Editar" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                        <a href="#" data-href="citacontroller.do?txtProceso=borrar&id=<c:out value='${cita.idCita}'/>" data-target="#deleteEmployeeModal" class="delete" title="Borrar" data-toggle="modal"><i class="material-icons">&#xE5C9;</i></a>
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
                            <h4 class="modal-title">Eliminar Cita</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Estas seguro que quieres eliminar esta cita?</p>
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
        <%@include file="WEB-INF/jspf/cfooter.jspf" %>
        <%@include file="WEB-INF/jspf/cjs.jspf" %>
    </body>
</html>