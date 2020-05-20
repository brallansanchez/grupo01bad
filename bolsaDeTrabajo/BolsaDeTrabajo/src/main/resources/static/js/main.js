var empresa = {};
var ban = false;
var formaciones = [];

var idGenerator = 0;
/**
 * Función para obtener los datos de una empresa seleccionada, recibe el id de
 * la empresa y un parametro opcion para determinar si es consulta o edicion.
 * 
 * @param id
 * @param opcion
 * @returns
 */
function getEmpresaById(id, opcion) {
	$.ajax({
		url : '/empresas/ver/' + id,
		success : function(res) {
			if (opcion == 1) {
				$("#nombre_empresa_modal_view").attr("readonly", "true");
				$("#telefono_empresa_modal_view").attr("readonly", "true");
				$("#email_empresa_modal_view").attr("readonly", "true");
				$("#direccion_empresa_modal_view").attr("readonly", "true");
				$("#descripcion_empresa_modal_view").attr("readonly", "true");

				$("#nombre_empresa_modal_view").val(res.nombreEmpresa);
				$("#telefono_empresa_modal_view").val(res.telefonoEmpresa);
				$("#email_empresa_modal_view").val(res.emailEmpresa);
				$("#direccion_empresa_modal_view").val(res.direccionEmpresa);
				$("#descripcion_empresa_modal_view").val(
						res.descripcionEmpresa == null ? "---"
								: res.descripcionEmpresa);
			} else {
				$("#nombre_empresa_modal_edit").val(res.nombreEmpresa);
				$("#telefono_empresa_modal_edit").val(res.telefonoEmpresa);
				$("#email_empresa_modal_edit").val(res.emailEmpresa);
				$("#direccion_empresa_modal_edit").val(res.direccionEmpresa);
				$("#descripcion_empresa_modal_edit").val(
						res.descripcionEmpresa == null ? "---"
								: res.descripcionEmpresa);

				empresa.id = id;
			}

		}
	});
}

/**
 * metodo para actualizar una empresa que se haya seleccionado
 * 
 * @returns
 */
function onUpdateEmpresa() {

	empresa.nombre = $("#nombre_empresa_modal_edit").val();
	empresa.telefono = $("#telefono_empresa_modal_edit").val();
	empresa.email = $("#email_empresa_modal_edit").val();
	empresa.direccion = $("#direccion_empresa_modal_edit").val();
	empresa.descripcion = $("#descripcion_empresa_modal_edit").val();

	if (empresa.nombre != null && empresa.nombre != ''
			&& empresa.telefono != null && empresa.telefono != ''
			&& empresa.email != null && empresa.email != ''
			&& empresa.direccion != null && empresa.direccion != '') {
		$.ajax({
			url : '/empresas/actualizar',
			type : 'POST',
			data : empresa,
			success : function(res) {
				if (res == 'Success') {
					$.notify({
						icon : "nc-icon nc-check-2",
						title : "¡Información!",
						message : "Registros guardados éxitosamente."

					}, {
						type : "success",
						timer : 3000,
						placement : {
							from : 'top',
							align : 'right'
						},
						onShown : function() {
							window.location.href = "/empresas/";
						}
					});
				} else if (res == 'Error') {
					$.notify({
						icon : "nc-icon nc-simple-remove",
						title : "¡Error!",
						message : "Algo salió mal."

					}, {
						type : "danger",
						timer : 3000,
						placement : {
							from : 'top',
							align : 'right'
						}
					});
				} else {
					$.notify({
						icon : "nc-icon nc-simple-remove",
						title : "¡Información!",
						message : "Error desconocido."

					}, {
						type : "warning",
						timer : 3000,
						placement : {
							from : 'top',
							align : 'right'
						}
					});
				}
			}
		})
	} else {
		$(".pos-demo").notify("Llene los campos con '*'", {
			position : "left"
		});
	}
}

/**
 * Metodo para crear el curriculo de una persona.
 * 
 * @returns
 */
function onSendCurriculo() {
	persona = {
		nombre : $("#firstname").val(),
		apellido : $("#lastname").val(),
		email : $("#email").val(),
		telefono : $("#telefono").val(),
		direccion : $("#direccion").val(),
		fechaNac : $("#date_of_birth").val(),
		dui : $("#dui").val(),
		nit : $("#nit").val(),
		pasaporte : $("#pasaporte").val(),
		nup : $("#nup").val()
	};

	$.ajax({
		url : '/curriculo/create',
		type : 'POST',
		data : persona,
		success : function(res) {
			console.log(res);
		}
	});
}

/**
 * metodo para obtener el listado de regiones del pais seleccionado
 */
function onGetRegiones() {
	var pais = $("#selectedCountry").val();
	$
			.ajax({
				url : '/ofertas/regiones',
				type : 'POST',
				dataType : 'json',
				contentType : "application/json;charset=UTF-8",
				data : JSON.stringify(pais),
				success : function(res) {
					var options = '';
					ban = true;

					// Eliminando tag select anterior si la hubiese
					if ($("#location").next() != null) {
						$($("#location").next()).remove();
					}

					// Creando tags options
					for (var i = 0; i < res.length; i++) {
						options = options + '<option value="'
								+ res[i]['idDivision'] + '"' + ' >'
								+ res[i]['nombreDivision'] + '</option>';
					}

					// Insertando tag select y options
					$("#location")
							.after(
									'<select id="divisionGeo" class="form-control">'
											+ '<option id="firstOption" value="0" >Seleccione...</option>'
											+ options + '</select>');
				}
			});
}

/**
 * metodo para guardar una oferta laboral.
 * 
 * @returns
 */
function onCreateOfertaLaboral() {
	var errors = 0;
	var formData = {
		fechaExp : $("#fechaExp").val(),
		titulo : $("#titulo").val(),
		categoria : $("#categoriaPuesto").val(),
		descripcion : $("#descripcion").val(),
		areaCargo : $("#areaCargo").val(),
		cargo : $("#cargo").val(),
		rangoSalarial : $("#rangoSalarial").val(),
		divisionGeo : ban ? $("#divisionGeo").val() : null,
		rangoExp : $("#rangoExp").val(),
		tipoContrato : $("#tipoContrato").val(),
		sexoPersona : $("#sexoPersona").val(),
		rangoEdad : $("#rangoEdad").val()
	}

	if (formData['fechaExp'] == null || formData['fechaExp'] == "") {
		$("#fechaExp").css("border", "1px solid red");
		errors++;
	}

	if (formData['titulo'] == null || formData['titulo'] == 'undefined'
			|| formData['titulo'] == "") {
		$("#titulo").css("border", "1px solid red");
		errors++;
	}

	if (formData['categoria'] == null || formData['categoria'] == 'undefined'
			|| formData['categoria'] == '0') {
		$("#categoriaPuesto").css("border", "1px solid red");
		errors++;
	}

	if (formData['descripcion'] == null
			|| formData['descripcion'] == 'undefined'
			|| formData['descripcion'] == "") {
		$("#descripcion").css("border", "1px solid red");
		errors++;
	}

	if (formData['areaCargo'] == null || formData['areaCargo'] == 'undefined'
			|| formData['areaCargo'] == "") {
		$("#areaCargo").css("border", "1px solid red");
		errors++;
	}

	if (formData['cargo'] == null || formData['cargo'] == 'undefined'
			|| formData['cargo'] == "") {
		$("#cargo").css("border", "1px solid red");
		errors++;
	}

	if (formData['rangoSalarial'] == null
			|| formData['rangoSalarial'] == 'undefined'
			|| formData['rangoSalarial'] == "") {
		$("#rangoSalarial").css("border", "1px solid red");
		errors++;
	}

	if (formData['divisionGeo'] == null
			|| formData['divisionGeo'] == 'undefined'
			|| formData['divisionGeo'] == '0') {
		$("#divisionGeo").css("border", "1px solid red");
		errors++;
	}

	if (formData['rangoExp'] == null || formData['rangoExp'] == '0') {
		$("#rangoExp").css("border", "1px solid red");
		errors++;
	}

	if (formData['tipoContrato'] == null || formData['tipoContrato'] == '0') {
		$("#tipoContrato").css("border", "1px solid red");
		errors++;
	}

	if (formData['sexoPersona'] == null || formData['sexoPersona'] == '0') {
		$("#sexoPersona").css("border", "1px solid red");
		errors++;
	}

	if (formData['rangoEdad'] == null || formData['rangoEdad'] == '0') {
		$("#rangoEdad").css("border", "1px solid red");
		errors++;
	}

	if (errors === 0) {
		$.ajax({
			url : '/ofertas/create',
			type : 'POST',
			data : formData,
			success : function(res) {
				$.notify({
					icon : "nc-icon nc-simple-remove",
					title : "¡Información!",
					message : "Registros guardados éxitosamente."

				}, {
					type : "success",
					timer : 3000,
					placement : {
						from : 'top',
						align : 'right'
					},
					onShown : function() {
						window.location.href = "/ofertas/";
					}
				});
			}
		});
	} else {
		$.notify({
			icon : "nc-icon nc-simple-remove",
			title : "¡Error!",
			message : "Tiene " + errors + " campos vacíos"

		}, {
			type : "danger",
			timer : 4000,
			placement : {
				from : 'top',
				align : 'right'
			}
		});
	}

}

/**
 * Metodo para encontrar la oferta que se desea consultar.
 * 
 * @param id
 * @returns
 */
function getOfertaById(id, opcion) {
	$.ajax({
		url : '/ofertas/ver/' + id,
		success : function(res) {
			if (opcion == 1) {
				$("#fechaExp").val(res.fechaExp);
				$("#categoria").val(res.categoria.nombreCategoriaPuesto);
				$("#titulo").val(res.tituloOferta);
				$("#descripcion").val(res.descripcionOferta);
			}
		}
	});
}

/**
 * funcion para recuperar el perfil de la oferta seleccionada
 * 
 * @param id
 * @param opcion
 * @returns
 */
function getPerfilById(id, opcion) {
	$.ajax({
		url : '/ofertas/ver/perfil/' + id,
		success : function(res) {
			console.log(res);
			$("#encabezado").html(
					"Titulo de la oferta: " + res.ofertaLaboral.tituloOferta);
			$("#area").val(res.areaEmpresarial);
			$("#cargo").val(res.cargoSolicitado);
			$("#rango").val(res.rangoSalarial);
			$("#ubicacion").val(
					res.divisionGeografica.nombreDivision + ", "
							+ res.divisionGeografica.idPais.nombrePais);
			$("#rangoExp").val(res.rangoExperiencia.rangoExperiencia);
			$("#tipoContrato").val(res.tipoContrato.nombreTipoContrato);
			$("#rangoEdad").val(res.rangoEdad.rangoEdad);
			switch (res.sexoPersona.sexoPersona) {
			case 'I':
				$("#genero").val("Indiferente");
				break;
			case 'M':
				$("#genero").val("Masculino");
				break;
			case 'F':
				$("#genero").val("Femenino");
				break;
			}
		}
	});
}

/**
 * metodo para mostrar o ocultar el div para introducir el codigo de
 * certificacion
 * 
 * @returns
 */
function onChangeTipoFormacion() {
	var value = $("#tipo_titulo").val();
	if (value == 2) {
		$("#aux")
				.after(
						'<div id="removable" class="row">'
								+ '<div class="col-md-12"> '
								+ '<div class="form-group">'
								+ '<label>Código Certificación</label>'
								+ '<input type="text" class="form-control" id="codigo">'
								+ '</div></div></div>');

	} else {
		$("#removable").remove();
	}
}

/**
 * funcion para agregar eduacion al cv
 * 
 * @returns
 */
function addEducation(event) {
	event.preventDefault();

	idGenerator++;
	formacion = {
		id:idGenerator,
		titulo : $("#titulo_form").val(),
		anio : $("#anio_form").val(),
		tipo_titulo : $("#tipo_titulo").val(),
		telefono_form : $("#telefono_form").val(),
		institucion : $("#institucion").val(),
		tipo_institucion : $("#tipo_institucion").val(),
		email_form : $("#email_form").val(),
		codigo_certificacion : $("#tipo_titulo").val() == '2' ? $("#codigo")
				.val() : null
	}

	formaciones.push(formacion);
	$("#auxBody")
			.append(
					'<tr id="fila'
							+ idGenerator
							+ '" data-id="'+idGenerator+'"><td>'
							+ formacion.titulo
							+ '</td>'
							+ '<td>'
							+ formacion.anio
							+ '</td>'
							+ '<td><button class="btn btn-warning" id="button" onclick="onRemove(event)">Remover</button></td></tr>');
	$("#tabla_formacion").removeAttr("style");
	if (idGenerator == 1) {
		$("#tabla_formacion").after('<hr/>');
	}

	formacion = {};
	// Limpiando campos
	$("#titulo_form").val("");
	$("#anio_form").val("");
	$("#tipo_titulo").val("0");
	$("#telefono_form").val("");
	$("#institucion").val("");
	$("#tipo_institucion").val("0");
	$("#email_form").val("");
	$("#codigo").val("");
	
	$.ajax({
		url: "/curriculo/array",
		type: "POST",
		contentType: 'application/json;charset=UTF-8',
		data: JSON.stringify(formaciones),
		success:function(res){
			console.log(res);
		}
	})
}

function onRemove(event,f) {
	event.preventDefault();
	$(event.target).parent().parent().remove();
	var id = $(event.target).parent().parent().data();
	var nuevoArray = formaciones.filter(formacion => formacion.id != id.id);
	formaciones = nuevoArray;
}