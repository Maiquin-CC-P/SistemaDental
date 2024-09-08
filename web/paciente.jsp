

<html>
    <head>  
        <title>Paciente</title>
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
            <div id="wrapper">
                <% String x = (String) session.getAttribute("accion");
                        if (x.equals("1")) { %>
                <form action="pacientecontroller.do" method="post"> 
                    <div class="">
                        <input type="hidden" name="txtProceso" value="registro">
                    </div> 
                    <fieldset>
                        <legend>Registro Paciente</legend><br>
                        <div>
                            <label for="casilla">Nombre &nbsp;</label>
                            <input type="text" name="txtNombre" placeholder="First Name" id="txtNombre"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Apellidos &nbsp;</label>
                            <input type="text" name="txtApellido" placeholder="Last Name" id="txtApellido"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Tipo Documento &nbsp;</label>
                            <select class="tipo" name="txtTipo" id="txtTipo">
                                <option value='01'>BOLETA MILITAR</option>
                                <option value='02'>CARNE DE EXTRANJERIA</option>
                                <option value='03'>DNI</option>
                                <option value='04'>PASAPORTE</option>
                            </select>

                            <label for="casilla">Doc. Indentidad &nbsp;</label>
                            <input class="docu" name="txtDocu" placeholder="Identity Document" id="txtDocu"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">E-Mail &nbsp;</label>
                            <input type="email" name="txtCorreo" placeholder="Email" id="txtCorreo"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Sexo &nbsp;</label>
                            <select class="transporte" name="txtSexo" id="txtSexo">
                                <option value="F">FEMENINO</option>
                                <option value="M">MASCULINO</option>
                            </select>
                        </div>
                        <div>
                            <label for="casilla">Fecha Nacimiento &nbsp;</label>
                            <input type="Date" class="datepicker" name="txtFecha" id="txtFecha"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <input type="hidden" name="txtCiudad" id="txtCiudad" value="1">
                        <input type="hidden" name="txtPais" id="txtPais" value="1">
                        <input type="hidden" name="txtCiudad" id="txtCiudad" value="1">
                        <div>
                            <label for="casilla">Dirección &nbsp;</label>
                            <input type="text" name="txtDire"  placeholder="Address" id="txtDire"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Fijo &nbsp;</label>
                            <input type="tel" name="txtTlf1"  placeholder="Landline" id="txtTlf1"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Móvil &nbsp;</label>
                            <input type="tel" name="txtTlf2"  placeholder="Phone" id="txtTlf2"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>   
                        <div>
                             <label for="casilla">&nbsp; </label>
                            <textarea name="txtObse" id="txtObse" rows="10" cols="50"></textarea>                           
                        </div>    
                        <label for="casilla">&nbsp;</label>                           
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Grabar</span></button>
                        <a href="pacientecontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset>    
                </form>
                <% } else if (x.equals("2")) {%>
                <form action="pacientecontroller.do" method="post">
                    <div class="">
                        <input type="hidden" name="txtProceso" value="editar">
                    </div> 
                    <fieldset>
                        <legend>Actualizar Paciente</legend><br>
                        <div>
                            <label for="casilla">ID &nbsp;</label>
                            <input type="number" name="txtID" id="txtID" value='${paciente.getIdPaciente()}' readonly="readonly" />     
                        </div>
                        <div>
                            <label for="casilla">Nombre &nbsp;</label>
                            <input type="text" name="txtNombre" id="txtNombre" value='${paciente.getNombre()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Apellidos &nbsp;</label>
                            <input type="text" name="txtApellido" id="txtApellido" value='${paciente.getApellidos()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Tipo Documento &nbsp;</label>  
                            <form action="#" method="post"> 
                                <input type="hidden" name="rol" id="rol" value='${paciente.getTipoDocumento()}'> 
                                <select class="tipo" name="txtTipo" id="txtTipo">                                       
                                    <option value='01'>BOLETA MILITAR</option>
                                    <option value='02'>CARNE DE EXTRANJERIA</option>
                                    <option value='03'>DNI</option>
                                    <option value='04'>PASAPORTE</option>
                                </select>
                                <label for="casilla">Doc. Indentidad &nbsp;</label>
                                <input class="docu" name="txtDocu" id="txtDocu" value='${paciente.getNumDocumento()}' required/>                                
                                <div>
                                    <label for="casilla">E-Mail &nbsp;</label>
                                    <input type="text" name="txtCorreo" id="txtCorreo" value='${paciente.getCorreo()}' required/>
                                </div>
                                <div>
                                    <label for="casilla">Sexo &nbsp;</label>
                                    <input type="hidden" name="sex" id="sex" value='${paciente.getSexo()}'> 
                                    <select class="tipo" name="txtSexo" id="txtSexo">                                       
                                        <option value="F">Femenino</option>
                                        <option value="M">Masculino</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="casilla">Fecha Nacimiento &nbsp;</label>
                                    <input type="Date" name="txtFecha" id="txtFecha" value='${paciente.getFechaNAcimeinto()}' required/>
                                </div>
                                <input type="hidden" name="txtCiudad" id="txtCiudad" value="1">
                                <input type="hidden" name="txtPais" id="txtPais" value="1">
                                <input type="hidden" name="txtCiudad" id="txtCiudad" value="1">
                            </form>                                   
                        </div>
                        <div>
                            <label for="casilla">Dirección &nbsp;</label>
                            <input type="text" name="txtDire" id="txtDire" value='${paciente.getDireccion()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Fijo &nbsp;</label>
                            <input type="text" name="txtTlf1" id="txtTlf1" value='${paciente.getTelefonoFijo()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Móvil &nbsp;</label>
                            <input type="text" name="txtTlf2" id="txtTlf2" value='${paciente.getTelefonoMovil()}' required/>
                        </div>
                        <div>
                            <label for="casilla">&nbsp; </label>
                            <textarea name="txtObse" id="txtObse" rows="10" cols="50">${paciente.getObservaciones()}</textarea>                           
                        </div>  
                        <label for="casilla">&nbsp;</label>    
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe150;</i> <span>Modificar</span></button>
                        <a href="pacientecontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>                         
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