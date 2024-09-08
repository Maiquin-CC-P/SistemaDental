

<html>
    <head>
        <title>Cita</title>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>        
        <%@include file="WEB-INF/jspf/cstyles.jspf" %>
        <%@include file="WEB-INF/jspf/cheader.jspf" %>
        <%@include file="WEB-INF/jspf/cnav.jspf" %>

        <link rel="stylesheet" href="resources/css/styles_formulario.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>  
        <section>
            <div id="wrapper" >
                <% String x = (String) session.getAttribute("accion");
                if (x.equals("1")) { %>
                <form action="citacontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="registro">
                    </div> 
                    <fieldset>
                        <legend>Programación Cita</legend><br>
                        <div>
                            <label for="casilla">Paciente &nbsp;</label>
                            <select class="transporte" name="txtPaciente" id="txtPaciente">
                                <c:forEach items="${listaP}" var="paciente" >
                                    <option value=<c:out value='${paciente.getIdPaciente()}'/>> ${paciente.getApellidos()} ${paciente.getNombre()}</option>                                                                    
                                </c:forEach>
                            </select>
                        </div>
<div>
                            <label for="casilla">Especialidad - Doctor &nbsp;</label>
                            <select class="transporte" name="txtDoctor_Especialidad" id="txtDoctor_Especialidad">
                                <c:forEach items="${listaD_E}" var="doctor_especialidad" >
                                    <option value=<c:out value='${doctor_especialidad.getIdEspecialidad() }'/>> ${doctor_especialidad.getEspecialidad()} - ${doctor_especialidad.getDoctor()}</option>                                                                     
                                </c:forEach>
                            </select>
</div>                        
                        
                        <div>
                            <label for="casilla">Doctor &nbsp;</label>
                            <select class="transporte" name="txtDoctor" id="txtDoctor">
                                <c:forEach items="${listaD}" var="doctor" >
                                    <option value=<c:out value='${doctor.idDoctor}'/>>${doctor.getNombre()} ${doctor.getApellidos()}</option>                                                                    
                                </c:forEach>
                            </select>
                        </div>
                        
                        
                        
                        
                        <div>
                            <label for="casilla">Especialidad &nbsp;</label>
                            <select class="transporte" name="txtEspecialidad" id="txtEspecialidad">
                                <c:forEach items="${listaE}" var="especialidad" >
                                    <option value=<c:out value='${especialidad.getIdEspecialidad()}'/>> ${especialidad.getEspecialidad()}</option>                                                                    
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="casilla">Fecha Atención &nbsp;</label>
                            <input type="date" class="datepicker" name="txtFecha" placeholder="Date" id="txtFecha"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Hora Atención &nbsp;</label>
                            <input type="time" name="txtHora" placeholder="Time" id="txtHora"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <label for="casilla">&nbsp;</label>                           
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Grabar</span></button>
                        <a href="agenda.jsp" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset>    
                </form>
                <% } else if (x.equals("2")) {%>
                <form action="citacontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="editar">
                    </div> 
                    <fieldset>
                        <legend>Actualizar Cita</legend><br>
                        <form action="#" method="post">
                            <div>
                                <label for="casilla">ID &nbsp;</label>
                                <input type="number" name="txtID" id="txtID" value='${cita.getIdCita()}' readonly="readonly" />     
                            </div>
                            <div>
                                <input type="hidden" name="pac" id="pac" value='${cita.getIdPaciente()}'> 
                                <label for="casilla">Paciente &nbsp;</label>
                                <select class="transporte" name="txtPaciente" id="txtPaciente">
                                    <c:forEach items="${listaP}" var="paciente" >
                                        <option value=<c:out value='${paciente.getIdPaciente()}'/>> ${paciente.getApellidos()} ${paciente.getNombre()}</option>                                                                    
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <input type="hidden" name="doc" id="doc" value='${cita.getIdDoctor()}'> 
                                <label for="casilla">Doctor &nbsp;</label>
                                <select class="transporte" name="txtDoctor" id="txtDoctor">
                                    <c:forEach items="${listaD}" var="doctor" >
                                        <option value=<c:out value='${cita.getIdDoctor()}'/>>${doctor.getNombre()}</option>                                                                    
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <input type="hidden" name="esp" id="esp" value='${cita.getIdEspecialidad()}'> 
                                <label for="casilla">Especialidad &nbsp;</label>
                                <select class="transporte" name="txtEspecialidad" id="txtEspecialidad">
                                    <c:forEach items="${listaE}" var="especialidad" >
                                        <option value=<c:out value='${especialidad.getIdEspecialidad()}'/>> ${especialidad.getEspecialidad()}</option>                                                                    
                                    </c:forEach>
                                </select>
                            </div>
                        </form>
                        <div>
                            <label for="casilla">Fecha Atención &nbsp;</label>
                            <input type="date" class="datepicker" name="txtFecha" id="txtFecha" value='${cita.getFechaAtencion()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Hora Atención &nbsp;</label>
                            <input type="time" name="txtHora" id="txtHora" value='${cita.getHoraAtencion()}' required/>
                        </div>
                        <label for="casilla">&nbsp;</label>                           
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Modificar</span></button>
                        <a href="agenda.jsp" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset> 
                </form>
                <%}%>
            </div>	
        </section>
        <%@include file="WEB-INF/jspf/cfooter.jspf" %>
        <%@include file="WEB-INF/jspf/cjs.jspf" %>
    </body>
</html>