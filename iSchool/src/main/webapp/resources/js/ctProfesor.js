function soloLetras(e){
       key = e.keyCode || e.which;
       tecla = String.fromCharCode(key).toLowerCase();
       letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
       especiales = "8-37-39-46";

       tecla_especial = false
       for(var i in especiales){
            if(key == especiales[i]){
                tecla_especial = true;
                break;
            }
        }

        if(letras.indexOf(tecla)==-1 && !tecla_especial){
            return false;
        }
    }

function soloNumero(n){
    key = n.keyCode || n.which;
    tecla = String.fromCharCode(key).toLowerCase();
    numeros = " 0123456789 ";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
         if(key == especiales[i]){
             tecla_especial = true;
             break;
         }
     }

     if(numeros.indexOf(tecla)==-1 && !tecla_especial){
         return false;
     }
 }

function Fecha(){
	
	var f = new Date();
	document.write(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
	
	$('#Form_sysUsuMenu_ctMenu input#cUsuario').val
}


function add_ctProfesor() {
	
	
	
	$('#AddctProfesor_Dialog').dialog("option", "title", 'Agregar Profesor');
	$('#AddctProfesor_Dialog').dialog('open');

}

function edit_ctProfesor(id) {
	
	$.get("get_ctProfesor/" + id, function(result) {
		$("#AddctProfesor_Dialog").html(result);
		$("#AddctProfesor_Dialog").dialog("option", "title", 'Editar Usuario');
		$("#AddctProfesor_Dialog").dialog('open');

		// initializeDatePicker();
	});	
	
}

function initializeDatePicker() {
	$(".datepicker").datepicker({
		dateFormat : "yy-mm-dd",
		changeMonth : true,
		changeYear : true,
		showButtonPanel : true
	});
}

function resetDialog(form) {

	form.find("input").val("");
	form.find("#iIdProfesor").val("0");
	
}

$(document).ready(function() {

	$('#AddctProfesor_Dialog').dialog({

		autoOpen : false,
		position : 'center',
		modal : true,
		resizable : false,
		width : 800,
		buttons : {
			"Save" : function() {
				
				confirm('¿Tus datos son los correctos?');
								
				$('#Form_ctProfesor_Add').submit();
			},
			"Cancel" : function() {
				$(this).dialog('close');
			}
		},
		close : function() {

			resetDialog($('#Form_ctProfesor_Add'));

			$(this).dialog('close');
		}
	});

	initializeDatePicker();
	
});
