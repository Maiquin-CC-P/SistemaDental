
$('#deleteEmployeeModal').on('show.bs.modal', function(e) {
   $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
});


function ocultar() {
    document.getElementById("mensaje1").style.display = "none";
    document.getElementById("mensaje2").style.display = "none";
}

//// Selección roles
var rol = document.getElementById("rol").value;
$("#txtRol option[value=" + rol + "]").attr("selected", true);
$("#txtTipo option[value=" + rol + "]").attr("selected", true);

//Seleccion estado
var est = document.getElementById("est").value;
$("#txtEstado option[value=" + est + "]").attr("selected", true);

//Seleccion sexo
var sex = document.getElementById("sex").value;
$("#txtSexo option[value=" + sex + "]").attr("selected", true);

//Seleccion paciente
var pac = document.getElementById("pac").value;
$("#txtPaciente option[value=" + pac + "]").attr("selected", true);

//Seleccion doctor
var doc = document.getElementById("doc").value;
$("#txtDoctor option[value=" + doc + "]").attr("selected", true);

//Seleccion especialidad
var esp = document.getElementById("esp").value;
$("#txtEspecialidad option[value=" + esp + "]").attr("selected", true);

function busqueda(){
    var dato = document.getElementById("pac").value;
    console.log(dato);
    $.ajax({
        type: 'POST',
        url: "reportescontroller.do?txtProceso=busqueda",
        dataType: 'HTML',
        data: {
           txtDato:dato 
        },
        beforeSend: function (xhr) {
            
        },
        success: function (data) {
           $("#data").html(data); 
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        },
        complete: function (jqXHR, textStatus) {
            
        }
  
    });  
}

function busqueda2(){
    var dato = document.getElementById("txtEspecialidad").value;
    console.log(dato);
    $.ajax({
        type: 'POST',
        url: "reportescontroller.do?txtProceso=busqueda2",
        dataType: 'HTML',
        data: {
           txtDato:dato 
        },
        beforeSend: function (xhr) {
            
        },
        success: function (data) {
           $("#data").html(data); 
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        },
        complete: function (jqXHR, textStatus) {
            
        }
  
    });  
    
    }
    
    function busqueda3(){
    var dato = document.getElementById("txtDoctor").value;
    console.log(dato);
    $.ajax({
        type: 'POST',
        url: "reportescontroller.do?txtProceso=busqueda3",
        dataType: 'HTML',
        data: {
           txtDato:dato 
        },
        beforeSend: function (xhr) {
            
        },
        success: function (data) {
           $("#data").html(data); 
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        },
        complete: function (jqXHR, textStatus) {
            
        }
  
    }); 
    
    function exportDivToExcel() {
       document.formexcel.exceldata.value=$('#htmlbody').html();
       $("#lblReportForPrint").html("Procurement operation review report");
       document.formstyle.method='POST';
       document.formstyle.action='${pageContext.servletContext.contextPath}/generateexcel';
       document.formstyle.submit();
        };


    
    
    
}


