/**
 * 
 */

function Add_curso() {

	vid = $('select#idGrupo option:selected').val();

	vlExiste = false;
	vdetotal = 0;

	$.each($("#mytable tbody").find("tr"), function() {
		if ($(this).closest("tr").find(".id_Grupo").text() == vid) {
			vlExiste = true;
			alert("el curso ya fue registrado");
		}
	});

	if (vlExiste)
		return;

	$.ajax({
		type : "GET",
		url : "ctAlumno/getGrupo",
		data : {
			id : vid
		},
		success : function(data, textStatus, jqXHR) {

			$('#mytable > tbody')
					.append(
							'<tr>'
									+ '<td class="id_Grupo">'
									+ data.iIdGrupo
									+ '</td>'
									+ '<td class="cNombre_Grupo">'
									+ data.cNombre
									+ '</td>'
									+ '<td class="id_Curso">'
									+ data.curso.iIdCurso
									+ '</td>'
									+ '<td class="cNombre_Curso">'
									+ data.curso.cNombre
									+ '</td>'
									+ '<td class="cHorario">'
									+ data.cHorario
									+ '</td>'
									+ '<td class="dePrecio">'
									+ data.curso.dePrecio
									+ '</td>'
									+ '<td>'
									+ '<button class="btnDelete" onclick="Borrar();" style="background-color:#FF4000; color:black;">Quitar</button>'
									+ '</td> </tr>');
	
			calculaTotal();
	
			$("#mytable > tfoot").empty();
			$('#mytable > tfoot').append(
					'<TR> <TH ALIGN=LEFT COLSPAN=5>Total a Pagar</TH> <TH>'
							+ vdetotal + '</TH> </TR>');					
	
			/* agregar json curso */
			generaJson();

		},
		error : function() {
			alert('error ');
		}

	});

}

function Borrar() {

	$("#mytable").on(
			'click',
			'.btnDelete',
			function() {
				$(this).closest('tr').remove();

				$("#mytable > tfoot").empty();

				calculaTotal();

				if (vdetotal > 0) {
					$('#mytable > tfoot').append(
							'<TR> <TH ALIGN=LEFT COLSPAN=5>Total a Pagar</TH> <TH>'
									+ vdetotal + '</TH> </TR>');
				}

				/* agregar json curso */
				generaJson();

			});
}

var generaJson = function() {

	var dataArray = new Array();

	function dataRow(id_Grupo, cNombre_Grupo, id_Curso, cNombre_Curso,
			cHorario, dePrecio) {
		this.id_Grupo = id_Grupo;
		this.cNombre_Grupo = cNombre_Grupo;
		this.cNombre_Curso = cNombre_Curso;
		this.cHorario = cHorario;
		this.dePrecio = dePrecio;
		this.id_Curso = id_Curso;
	}

	$.each($("#mytable tbody").find("tr"), function() {
		dataArray.push(new dataRow(
				$(this).closest("tr").find(".id_Grupo").text(), 
				$(this).closest("tr").find(".cNombre_Grupo").text(),
				$(this).closest("tr").find(".id_Curso").text(), 
				$(this).closest("tr").find(".cNombre_Curso").text(), 
				$(this).closest("tr").find(".cHorario").text(), 
				$(this).closest("tr").find(".dePrecio").text()));

	});
	var sJson = JSON.stringify(dataArray);
	$("#cGrupo").val(sJson);
};

var calculaTotal = function() {

	vdetotal = 0;
	$.each($("#mytable tbody").find("tr"), function() {
		vdetotal = vdetotal + parseInt($(this).closest("tr").find(".dePrecio").text());

	});
};




function get_localidad(){
	
	vcCp = $('#Form_ctAlumno input#cCP').val();
	
	
	$.ajax({
		type : "GET",
		url : "ctAlumno/getLocalidad",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		data : {
			cCP : vcCp
		},
		success : function(data, textStatus, jqXHR) {			
			
			if (data.length == 1) {	
				for ( var item in data) {
					$('#Form_ctAlumno input#cColonia').val( data[item].cLocalidad );
					 $('#Form_ctAlumno input#cMunicipio').val( data[item].cNomMunicipio );				    
					 $('#Form_ctAlumno input#cEstado').val( data[item].cNomEstado );
				}				
				
			} else if (data.length > 1)  {
				
			
				
				$("#tableLoc > tbody").empty();
				
				for ( var item in data) {
				
				$('#tableLoc > tbody').append(
						'<tr>' + '<td class ="cLocalidad">'     + data[item].cLocalidad    + '</td>' 
						       + '<td class = "cNomMunicipio">' + data[item].cNomMunicipio + '</td>' 
						       + '<td class = "cNomEstado">'    + data[item].cNomEstado    + '</td>' 
						       + '<td>'	 + '</tr>');
				}
				
				
				
				$('#Localidades_Dialog').dialog("option", "title", 'Localidades (Doble clic Seleccionar)');
				$('#Localidades_Dialog').dialog('open');
			}
			
			
			
			

		},
		error : function() {
			alert("erro al ejecutar el BuscaMenu" + textStatus);
		}

	});	
}

$(document).ready(function() {

	$('#Localidades_Dialog').dialog({

		autoOpen : false,
		position : 'center',
		modal : true,
		resizable : false,
		width : 800,
		buttons : {			
			"Cancel" : function() {
				$(this).dialog('close');
			}
		},
		close : function() {
			
			$(this).dialog('close');
		}

	});
	

	$('#tableLoc').on('dblclick','tr',function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		//var value = $(this).find('td:fisrt').html();
		
		$('#Form_ctAlumno input#cColonia').val($(this).closest("tr").find(".cLocalidad").text() );
	    $('#Form_ctAlumno input#cMunicipio').val($(this).closest("tr").find(".cNomMunicipio").text() );				    
		$('#Form_ctAlumno input#cEstado').val($(this).closest("tr").find(".cNomEstado").text() );	
		$("#Localidades_Dialog").dialog('close');
		
		
	});
	
});

function validaCampos(){
	
	
	if($('#Form_ctAlumno input#cNombre').val() == ""){
		alert("Nombre Debe De Tener Un Valor");
		return false; 
		
	}if($('#Form_ctAlumno input#cApellido').val() == ""){
		alert("Apellido Debe De Tener Un Valor");
		return false; 
		
	}if($('#Form_ctAlumno input#dtFechaNac').val() == ""){
		alert("Fecha De Nacimiento Debe De Tener Un Valor");
		return false; 
		
	}	
	
	return true;
}



