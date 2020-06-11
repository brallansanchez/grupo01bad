var empresa = {};
var ban = false;
var formaciones = [];
var logros = [];
var documentos = [];
var badges = [];
var persona = {};
var redesSociales = {};

var idGenerator = 0;
var idLogro = 0;
var idDoc = 0;
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
 * metodo para mostrar los campos para agregar isbn y edicion
 * @returns
 */
function onChangeTipoDoc(){
	var value = $("#tipo_doc").val();
	if(value == 1){
		$("#auxLibro").after('<div id="removableDiv">'
						+	'<div class="col-md-6">'
						+	'<div class="form-group">'
						+	'<label>ISBN</label>'
						+	'<input class="form-control" type="text" id="isbn" />'
						+	'</div></div>'
						+	'<div class="col-md-6">'
						+	'<div class="form-group">'
						+	'<label>Edición</label>'
						+	'<input class="form-control" type="text" id="edicionLibro"/>'
						+	'</div></div></div>');
		
	} else {
		$("#removableDiv").remove();
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
	
	objeto = {
			formaciones: formaciones
	}
	
	$.ajax({
		url: "/curriculo/array",
		type: "POST",
		contentType: 'application/json;charset=UTF-8',
		data: JSON.stringify(objeto),
		success:function(res){
			console.log(res);
		}
	});
}

/**
 * funcion para eliminar una fila de la tabla de formación academica.
 * @param event
 * @returns
 */
function onRemove(event) {
	event.preventDefault();
	$(event.target).parent().parent().remove();
	var id = $(event.target).parent().parent().data();
	var nuevoArray = formaciones.filter(formacion => formacion.id != id.id);
	formaciones = nuevoArray;
}

/**
 * funcion para agregar un logro.
 * @param event
 * @returns
 */
function addLogro(event){
	event.preventDefault();

	idLogro++;
	logro = {
		id:idLogro,
		logro : $("#logro").val(),
		fecha_logro : $("#fechaLogro").val(),
		descripcion : $("#descripcionLogro").val()
	};

	logros.push(logro);
	$("#auxBodyLogro")
			.append(
					'<tr id="filaLogro'
							+ idLogro
							+ '" data-idLogro="'+idLogro+'"><td>'
							+ logro.logro
							+ '</td>'
							+ '<td>'
							+ logro.fecha_logro
							+ '</td>'
							+ '<td><button class="btn btn-warning" id="button_logro" onclick="onRemoveLogro(event)">Remover</button></td></tr>');
	$("#tabla_logros").removeAttr("style");
	if (idLogro == 1) {
		$("#tabla_logros").after('<hr/>');
	}

	logro = {};
	// Limpiando campos
	$("#logro").val(""),
	$("#fechaLogro").val(""),
	$("#descripcionLogro").val("")
}

/**
 * funcion para eliminar un logro del arreglo de logros.
 * @param event
 * @returns
 */
function onRemoveLogro(event){
	event.preventDefault();
	$(event.target).parent().parent().remove();
	var id = $(event.target).parent().parent().data();
	console.log(id);
	var nuevoArray = logros.filter(logro => logro.id != id.idlogro);
	logros = nuevoArray;
}

/**
 * funcion para agregar un libro al perfil del profesional.
 * @param event
 * @returns
 */
function addDocument(event){
	event.preventDefault();

	idDoc++;
	documento = {
		id:idDoc,
		tituloDoc : $("#titulo_libro").val(),
		fecha_doc : $("#fechaLibro").val(),
		lugar_doc : $("#lugarLibro").val(),
		ibsn	  : $("#isbn").val(),
		edicion	  : $("#edicionLibro").val()
	};

	documentos.push(documento);
	$("#auxBodyLibros")
			.append(
					'<tr id="filaLibro'
							+ idDoc
							+ '" data-idDoc="'+idDoc+'"><td>'
							+ documento.tituloDoc
							+ '</td>'
							+ '<td>'
							+ documento.fecha_doc
							+ '</td>'
							+ '<td><button class="btn btn-warning" id="button_doc" onclick="onRemoveDoc(event)">Remover</button></td></tr>');
	$("#tabla_libros").removeAttr("style");
	if (idDoc == 1) {
		$("#tabla_libros").after('<hr/>');
	}

	documento = {};
	// Limpiando campos
	$("#titulo_libro").val(""),
	$("#fechaLibro").val(""),
	$("#lugarLibro").val("")
}

/**
 * metodo para remover un elemento de la tabla de documentos
 * @param event
 * @returns
 */
function onRemoveDoc(event){
	event.preventDefault();
	$(event.target).parent().parent().remove();
	var id = $(event.target).parent().parent().data();
	console.log(id);
	console.log(documentos)
	var nuevoArray = documentos.filter(doc => doc.id != id.iddoc);
	documentos = nuevoArray;
	console.log(documentos)
}

/**
 * funcion para enviar los datos del perfil del personal.
 * @returns
 */
function onCreateProfesionalProfile(){
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
	
	redesSociales = {
			facebook: $("#Facebook").val(),
			twitter: $("$Twitter").val(),
			instagram: $("#Instagram").val(),
			linkedln: $("#Linkedln").val
	};
	
	
}

/**
 * metodo para obtener las habilidades de una categoria seleccionada
 * @returns
 */
function onSelectCategory(event){
	event.preventDefault();
	var idCategoria = $("#categoria_puesto").val();
	
	$.ajax({
		url: "/curriculo/getSkills",
		type: "POST",
		data: {id:idCategoria},
		success: function(res){
			console.log(res);
			$("#habilities_select").empty();
			$("#habilities_select").append('<option value="0">Seleccione...</option>');
			for(var i = 0; i < res.length; i++){
				$("#habilities_select").append('<option value="'+res[i]['idHabilidad']+'">' + res[i]['habilidad'] 
											+  '</option>');
			}
		}
	})
}

/**
 * funcion para agregar una clase a los radio button de estilo botton.
 * @param event
 * @returns
 */
function addActiveClass(event){
	event.preventDefault();
	$($(".back-color-checked")[0]).removeClass("back-color-checked");
	//$($("input[name='options']:checked").parent()[0]).addClass("back-color-checked");
	$(event.target).parent().addClass("back-color-checked");
}

/**
 * funcion para agregar una habilidad al perfil profesional.
 * @param event
 * @returns
 */
function onAddHability(event){
	event.preventDefault();
	var hability = $("#habilities_select option:selected").text();
	var dominio = $($("input[name='options']:checked")).parent().text();
	
	if(hability != "" && dominio != ""){
		$("#habilities_box").removeAttr("style");
		$("#badges").append('<button type="button" data-id="'+$("#habilities_select").val()+'" onclick="removeBadge(event)" class="btn btn-success">'+hability+' | '+dominio+' <span class="badge">&times;</span></button>');
		var badge = {
				habilityId: $("#habilities_select").val(),
				habilityName: hability,
				dominioId: $($("input[name='options']:checked")).val(),
				domninioName: dominio
		};
		
		badges.push(badge);
	}
}

/**
 * funcion para remover una habilidad del perfil profesional.
 * @param event
 * @returns
 */
function removeBadge(event){
	event.preventDefault();
	$(event.target).remove();
	console.log(typeof($(event.target)))
	var id = $(event.target).data();
	var nuevoArray = badges.filter(badges => badges.habilityId != id.id);
	badges = nuevoArray;
}

function onAddSpeakingHability(event){
	event.preventDefault();
	var hability = $("#speakingHabilities_select option:selected").text();
	var dominio = $($("input[name='speakingOptions']:checked")).parent().text();
	
	if(hability != "" && dominio != ""){
		$("#speakingHabilities_box").removeAttr("style");
		$("#speakingBadges").append('<button type="button" data-id="'+$("#speakingHabilities_select").val()+'" onclick="removeSpeakingBadge(event)" class="btn btn-success">'+hability+' | '+dominio+' <span class="badge">&times;</span></button>');
		var badge = {
				habilityId: $("#speakingHabilities_select").val(),
				habilityName: hability,
				dominioId: $($("input[name='speakingOptions']:checked")).val(),
				domninioName: dominio
		};
		
		badges.push(badge);
	}
}

function removeSpeakingBadge(event){
	event.preventDefault();
	$(event.target).remove();
	console.log(typeof($(event.target)))
	var id = $(event.target).data();
	var nuevoArray = badges.filter(badges => badges.habilityId != id.id);
	badges = nuevoArray;
}