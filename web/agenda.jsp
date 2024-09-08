
<html>
    <head>
        <%@page contentType="text/html"%>
        <%@page pageEncoding="UTF-8"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Agenda</title>
        <%@include file="WEB-INF/jspf/cstyles.jspf" %>
        <%@include file="WEB-INF/jspf/cheader.jspf" %>
        <%@include file="WEB-INF/jspf/cnav.jspf" %>

        <link rel="stylesheet" href="resources/css/fullcalendar.css"/>

        <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <script type='text/javascript' src='resources/js/moment.min.js'></script>
        <script type='text/javascript' src='resources/js/fullcalendar.min.js'></script>
        <script type='text/javascript' src='resources/js/es.js'></script>

        <link rel="stylesheet" href="resources/css/styles_calendar.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <script>
            $(document).ready(function () {
                $('#calendar').fullCalendar({
                    header: {
                        left: 'today,prev,next',
                        center: 'title',
                        right: 'month,basicWeek'
                    },
                   
                    buttonIcons: true, // show the prev/next text
                    editable: true,
                    events: 'citacontroller.do?txtProceso=mostrara',
                    eventClick: function (calEvent, jsEvent, view) {
                        $('#event-title').text(calEvent.title);

                        $('#txtid').val(calEvent.id);
                        $('#event-description').html(calEvent.description);
                        $('#exampleModal').modal();
                    }
                });
            });
            System.out.println("Error en el login");
        </script>

    </head>
    <body>
        <section>       
            <div id="wrapper" > 
                <div class="col-sm-7"><h2>Agenda de Citas ${requestScope.userLogeo}</h2> </div>
                <div class="row">
                    <div class="col-sm-8">
                        <a href="citacontroller.do?txtProceso=nuevo" class="btn btn-primary"><i class="material-icons">&#xE147;</i> <span>Nueva Cita</span></a>
                        <a href="citacontroller.do?txtProceso=mostrar" class="btn btn-primary"><i class="material-icons">&#xE24D;</i> <span>Lista de Citas</span></a>						
                    </div>
                </div>
                <div class="row"> 
                    <div class="col"></div>  
                    <div class="col-8"><div id="calendar"></div></div>  
                    <div class="col"></div>  
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"  >
            <div class="modal-dialog" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="event-title"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="event-description"><br></div>                           
                    </div>
                    <div class="modal-footer">  
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>    
    </body>
</html>