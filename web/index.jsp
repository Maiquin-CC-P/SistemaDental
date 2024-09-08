<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    <head>
        <%@include file="WEB-INF/jspf/cstyles_login.jspf" %>
    </head>
    <form action="usuariocontroller.do" method="post" >
        <div class="">
            <input type="hidden" name="txtProceso" value="login">
        </div> 
        <div class="login-form">
            <h1>
                <div class="header_logo"> <img src="resources/imgs/logo_inicio.png" alt=""/><br>      
                </div>
            </h1>      
            <div class="form-group ">
                <input type="text" class="form-control" placeholder="Email" id="txtCorreo"  name="txtCorreo" 
                       oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                <i class="fa fa-user"></i>
            </div>
            <div class="form-group log-status">
                <input type="password" class="form-control" placeholder="Password" id="txtClave" name="txtClave" 
                       oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);" required/>
                <i class="fa fa-lock"></i>
            </div>
            <a class="link" href="login_recupera.jsp">Olvidaste tu Contraseña?</a>
            <button type="submit" class="log-btn">Iniciar Sesion</button><br><br>
            <div class="vali">${requestScope.validaciones}</div>
        </div>
    </form
        
        
    <%@include file="WEB-INF/jspf/cjs.jspf" %>
</html>
