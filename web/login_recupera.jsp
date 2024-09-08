<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    <head>
        <%@include file="WEB-INF/jspf/cstyles_login.jspf" %>
        <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script>
       $(document).ready(function () {
                $('#txtCorreo').blur(function(){
                  //llamada a controlador
                  $.post('usuariocontroller.do?txtProceso=validarCorreo',
                  {txtCorreo:$('#txtCorreo').val()},
                  function(result){
                     var obj = result;
                    console.log(obj);
                    var obj2 = obj.replace("]",' ');         
                    var obj1=  obj2.replace("[",' '); 
                    
                    var respuesta =obj1;
                   if(respuesta.trim() != "" || respuesta != null){
                        var msg = respuesta.split(",");
                        if(msg[0].trim()=='false'){
                         $('#msgVali').html(msg[1].trim());
                         $('#txtClave').attr('disabled','disabled');//deshabilitado
                         $('#btnSubmit').attr('disabled','disabled');
                         
                         } 
                   }
                        $("#txtCorreo").focus(function() {
                        $("#txtClave").removeAttr('disabled');
                        $("#btnSubmit").removeAttr('disabled');
                        $('#msgVali').html("");
                     });
                        })
                     });
		});
		//	});
        </script> 
    </head>
    <form action="usuariocontroller.do" method="post" >
        <div class="">
            <input type="hidden" name="txtProceso" value="recupera">
        </div> 
        <div class="login-form">
            <h1>
                <div class="header_logo"> <img src="resources/imgs/logo_inicio.png" alt=""/><br>
                 Recuperar Clave  
                </div>
            </h1> 
            <div class="form-group ">
                <input type="text" class="form-control" placeholder="Email" id="txtCorreo"  name="txtCorreo" 
                       oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                <i class="fa fa-user"></i>
                <div id ="mensaje1" /></div>
            </div>
            <div class="form-group log-status">
                <input type="password" class="form-control" placeholder="New Password" id="txtClave" name="txtClave" 
                       oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                <i class="fa fa-lock"></i>
                <div id ="mensaje2" /></div>
            </div>
            <button id="btnSubmit" type="submit" class="log-btn" >Enviar</button><br><br>
            <a class="link" href="index.jsp">Volver</a>
            <div class="vali" id="msgVali">${requestScope.validaciones}</div>
        </div>
    </form
  <%@include file="WEB-INF/jspf/cjs.jspf" %>
</html>
