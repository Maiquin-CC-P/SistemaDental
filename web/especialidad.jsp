
<html>
    <head>
        <title>Especialidad</title>
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
                <form   action="especialidadcontroller.do" method="post" >
                    <div class="">
                        <input type="hidden" name="txtProceso" value="registro">
                    </div> 
                    <fieldset>
                        <legend>Registro Especialidad</legend><br>
                        <div>
                            <label for="casilla">Descripción &nbsp;</label>
                            <input type="text" name="txtDesc" placeholder="Especialidad" id="txtDesc"
                                   oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                        </div><br>
                        <label for="casilla">&nbsp;</label>                          
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe146;</i> <span>Grabar</span></button>
                        <a href="especialidadcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>  
                        <br><br>
                        <div class="vali">${requestScope.validaciones}</div>
                    </fieldset>    
                </form>
                <% } else if (x.equals("2")) {%>
                <form action="especialidadcontroller.do" method="post">
                    <div class="">
                        <input type="hidden" name="txtProceso" value="editar">
                    </div> 
                    <fieldset>
                        <legend>Actualizar Especialidad</legend><br>
                        <div>
                            <label for="casilla">ID &nbsp;</label>
                            <input type="number" name="txtID" id="txtID" value='${especialidad.getIdEspecialidad()}' readonly="readonly" />     
                        </div>
                        <div>
                            <label for="casilla">Especialidad &nbsp;</label>
                            <input type="text" name="txtDesc" id="txtDesc" value='${especialidad.getEspecialidad()}' required/>
                        </div>
                        
                    <div>
                              <label for="casilla">Estado &nbsp;</label>  
                            <form action="#" method="post">   
                                
                              
                                    
                                    <input type="hidden" name="est" id="est" value='${especialidad.getEstado()}'> 
                                    <select class="transporte" name="txtEstado" id="txtEstado">
                                        <option value="0">Activo</option>
                                        <option value="1">Inactivo</option>
                                    </select>
                               
                            </form>                                   
                        </div>
                        
                        
                  
                                    
                                    
                        <label for="casilla">&nbsp;</label>    
                        <button type="submit" class="btn btn-primary"><i class="material-icons">&#xe150;</i><span>Modificar</span></button>
                        <a href="especialidadcontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xe879;</i> <span>Salir</span></a>                         
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