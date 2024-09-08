
<html>
    <head>
        <title>Lista Citas por Doctor</title>
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
                                <h2>Citas por <b>Doctor</b></h2>
                            </div>
                            <div class="col-sm-7">
                                 <label for="casilla">Seleccionar Doctor &nbsp;</label>
                                <select class="transporte" name="txtDoctor" id="txtDoctor">
                                <option value="" selected >Seleccione el Doctor</option>
                                <c:forEach items="${listaD}" var="doctor" >
                                    <option value=<c:out value='${doctor.idDoctor}'/>>${doctor.getNombre()} ${doctor.getApellidos()}</option>                                                                    
                                </c:forEach>
                            </select>
                                <a class="btn btn-primary" onclick="busqueda3()" ><i class="material-icons">&#xe8b6;</i> <span>Buscar</span></a>
                            </div>
                        <input type="button" onclick="tableToExcel('resultado', 'Citas por Doctor')" value="Export to Excel" class="btn btn-primary" class="material-icons">
                        </div>
                    </div>
                    <table id="resultado" class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID Cita</th>
                                <th>Paciente</th>   <!-- Celda de cabecera de la columna 1 -->
                                <th>Fecha Atencion</th>
                                <th>Hora Atencion</th>
                                <th>Especialidad</th> 
                            </tr>
                        </thead>                
                        <tbody id="data">

                        </tbody>       
                    </table>
                </div>
            </div>
        </section>
        <%@include file="WEB-INF/jspf/cfooter.jspf" %>
        <%@include file="WEB-INF/jspf/cjs.jspf" %>


    </body>
</html>