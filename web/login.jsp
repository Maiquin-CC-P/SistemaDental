

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/cmeta.jspf" %>
        <title>JSP Page</title>
        <%@include file="WEB-INF/jspf/cstyles.jspf" %>
    </head>
    <body>  
        <div class="container-fluid">
            <%@include file="WEB-INF/jspf/cheader.jspf" %>
            <%@include file="WEB-INF/jspf/cnav.jspf" %>
            <section>
               <div class="header_fondo"> <img src="resources/imgs/fondo.png" alt=""/></div>
            </section>
            <%@include file="WEB-INF/jspf/cfooter.jspf" %>
        </div> 
        <%@include file="WEB-INF/jspf/cjs.jspf" %>
    </body>
</html>
