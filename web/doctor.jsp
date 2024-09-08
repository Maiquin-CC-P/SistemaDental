
<html>
    <head>
        <title>Doctor</title>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>      
        <%@include file="WEB-INF/jspf/cstyles.jspf" %>
        <%@include file="WEB-INF/jspf/cheader.jspf" %>
        <%@include file="WEB-INF/jspf/cnav.jspf" %>

        <link rel="stylesheet" href="resources/css/styles_formulario.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    </head>
    <body>  
        <section>
            <div id="wrapper" >
                <% String x = (String) session.getAttribute("accion");
                        if (x.equals("1")) { %>
                <form   action="doctorcontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="registro">
                    </div> 
                    <fieldset>
                        <legend>Registro Doctor</legend><br>
                        <div>
                            <label for="casilla">Nombres &nbsp;</label>
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
                            <label for="casilla">N° Colegiatura &nbsp;</label>
                            <input class="docu" name="txtColegiatura" placeholder="Colegiatura" id="txtColegiatura"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div>
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
                        </div><br>
                        <label for="casilla">&nbsp;</label>                          
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Grabar</span></button>
                        <a href="doctorcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset>    
                </form>
                <% } else if (x.equals("2")) {%>
                <form action="doctorcontroller.do" method="post">
                    <div class="">
                        <input type="hidden" name="txtProceso" value="editar">
                    </div> 
                    <fieldset>
                        <legend>Actualizar Doctor</legend><br>
                        <div>
                            <label for="casilla">ID &nbsp;</label>
                            <input type="number" name="txtID" id="txtID" value='${doctor.getIdDoctor()}' readonly="readonly" />     
                        </div>
                        <div>
                            <label for="casilla">Nombre &nbsp;</label>
                            <input type="text" name="txtNombre" id="txtNombre" value='${doctor.getNombre()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Apellidos &nbsp;</label>
                            <input type="text" name="txtApellido" id="txtApellido" value='${doctor.getApellidos()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Tipo Documento &nbsp;</label>  
                            <form action="#" method="post">   
                                <input type="hidden" name="rol" id="rol" value='${doctor.getTipoDocumento()}'> 
                                <select class="tipo" name="txtTipo" id="txtTipo">                                       
                                    <option value='01'>BOLETA MILITAR</option>
                                    <option value='02'>CARNE DE EXTRANJERIA</option>
                                    <option value='03'>DNI</option>
                                    <option value='04'>PASAPORTE</option>
                                </select>
                            </form>                                   
                        </div>
                        <div>
                            <label for="casilla">Doc. Indentidad &nbsp;</label>
                            <input type="text" name="txtDocu" id="txtDocu" value='${doctor.getNumDocumento()}' required/>
                        </div>
                        <div>
                            <label for="casilla">N° Colegiatura &nbsp;</label>
                            <input type="text" name="txtColegiatura" id="txtColegiatura" value='${doctor.getNumColegiatura()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Dirección &nbsp;</label>
                            <input type="text" name="txtDire" id="txtDire" value='${doctor.getDireccion()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Fijo &nbsp;</label>
                            <input type="text" name="txtTlf1" id="txtTlf1" value='${doctor.getTelefonoFijo()}' required/>
                        </div>
                        <div>
                            <label for="casilla">Teléfono Móvil &nbsp;</label>
                            <input type="text" name="txtTlf2" id="txtTlf2" value='${doctor.getTelefonoMovil()}' required/>
                        </div>
                        
                         <div>
                                    <label for="casilla">Estado &nbsp;</label>
                                    <input type="hidden" name="est" id="est" value='${doctor.getEstado()}'> 
                                    <select class="transporte" name="txtEstado" id="txtEstado">
                                        <option value="0">Activo</option>
                                        <option value="1">Inactivo</option>
                                    </select>
                                </div>
                        
                        <label for="casilla">&nbsp;</label>    
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe150;</i><span>Modificar</span></button>
                        <a href="doctorcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>                         
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