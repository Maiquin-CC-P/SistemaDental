

<html>
    <head>
        <title>Doctor-Especialidad</title>
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
                <form   action="doctor_especialidadcontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="registro">
                    </div> 
                    <fieldset>
                        <legend>Asignación Doctor - Especialidad</legend><br>                          
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
                            <select class="transporte" name="txtEspecia" id="txtEspecia">
                                <c:forEach items="${listaE}" var="especialidad" >
                                    <option value=<c:out value='${especialidad.idEspecialidad}'/>> ${especialidad.getEspecialidad()}</option>                                                                    
                                </c:forEach>
                            </select>
                        </div><br>
                        <label for="casilla">&nbsp;</label>                          
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Grabar</span></button>
                        <a href="doctor_especialidadcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset>    
                </form>  
                <% } else if (x.equals("2")) {%>
                <form   action="doctor_especialidadcontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="editar">
                    </div> 
                    <fieldset>
                        <legend> Actualizar Asignación Doctor - Especialidad</legend><br>                          
                        <div>
                            <label for="casilla">Doctor &nbsp;</label>
                            <select class="transporte" name="txtDoctor" id="txtDoctor">
                                <c:forEach items="${listaD}" var="doctor" >
                                    <option value=<c:out value='${doctor.idDoctor}'/>>${doctor.getNombre()} </option>                                                                    
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="casilla">Especialidad &nbsp;</label>
                            <select class="transporte" name="txtEspecia" id="txtEspecia">
                                <c:forEach items="${listaE}" var="especialidad" >
                                    <option value=<c:out value='${especialidad.idEspecialidad}'/>> ${especialidad.getEspecialidad()}</option>                                                                    
                                </c:forEach>
                            </select>
                        </div><br>
                        <label for="casilla">&nbsp;</label>                          
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Modificar</span></button>
                        <a href="doctor_especialidadcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
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
