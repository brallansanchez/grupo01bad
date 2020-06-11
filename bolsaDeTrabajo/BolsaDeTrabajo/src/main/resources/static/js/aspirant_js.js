var usuario = {};
var persona = {};
var validUniques = {
		username:0,
		nup:0,
		nit:0,
		pasaporte:0,
		dui:0,
		email:0
};

$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();
    
    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        var $target = $(e.target);
        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {
        var $active = $('.nav-tabs li>.active');
        var auxOp = $($active[0]).attr("href");
        var ban;
        if(auxOp == '#step1'){
        	ban = checkMandatoryFields(1);
        	if(ban && onCheckValidUniques()){
        		$active.parent().next().find('.nav-link').removeClass('disabled');
                nextTab($active);
        	}
        } else if(auxOp == '#step2'){
        	ban = checkMandatoryFields(2);
        	console.log(ban);
        	if(ban && onCheckValidUniques()){
        		$active.parent().next().find('.nav-link').removeClass('disabled');
                nextTab($active);
        	}
        } else if(auxOp == '#step3'){
        	$active.parent().next().find('.nav-link').removeClass('disabled');
            nextTab($active);
        }
    });
    
    $(".prev-step").click(function (e) {
        var $active = $('.nav-tabs li>a.active');
        prevTab($active);
    });
});

function nextTab(elem) {
    $(elem).parent().next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).parent().prev().find('a[data-toggle="tab"]').click();
}

/**
 * Funcion para obtener las regiones del país seleccionado.
 * @param event
 * @returns
 */
function onSelectCountry(event){
	var country_id = $("#countries").val();
	
	if(country_id != 0){
		$.ajax({
			url: '/countries/regions',
			type: 'POST',
			data: {idPais:country_id},
			success: function(res){
				$("#ubicaciones").empty();
				$("#ubicaciones").append('<option value="0">Seleccione...</option>');
				for(var i=0;i<res.length;i++){
					$("#ubicaciones").append('<option value="'+res[i]['idDivision']+'">' +res[i]['nombreDivision']+ '</option>')
				}
			}
		});

	}
}

/**
 * metodo para validar campos obligatorios del form
 * @param op
 * @returns
 */
function checkMandatoryFields(op){
	errorCounter = 0;
	switch(op){
		case 1:
			persona = {
				names: $("#names").val(),
				lastnames: $("#lastname").val(),
				genero: $("#genero").val(),
				rangoExp: $("#rangoExp").val(),
				email: $("#email").val(),
				dateOfBirth: $("#date_of_birth").val(),
				location: $("#ubicaciones").val(),
				dui: $("#dui").val(),
				nit: $("#nit").val(),
				nup: $("#nup").val(),
				pasaporte: $("#pasaporte").val(),
				telefono: $("#telefono").val(),
				direccion: $("#direccion").val()
			};
			
			if(persona.names == ""){
				$("#names").addClass("error-field");
				errorCounter++;
			}else{
				$("#names").removeClass("error-field");
			}
			
			if(persona.lastnames == ""){
				$("#lastname").addClass("error-field");
				errorCounter++;
			}else{
				$("#lastname").removeClass("error-field");
			}
			
			if(persona.genero == '0'){
				$("#genero").addClass("error-field");
				errorCounter++;
			}else{
				$("#genero").removeClass("error-field");
			}
			
			if(persona.rangoExp == '0'){
				$("#rangoExp").addClass("error-field");
				errorCounter++;
			}else{
				$("#rangoExp").removeClass("error-field");
			}
			
			if(persona.email == ""){
				$("#email").addClass("error-field");
				errorCounter++;
			}else{
				$("#email").removeClass("error-field");
			}
			
			if(persona.dateOfBirth == ""){
				$("#date_of_birth").addClass("error-field");
				errorCounter++;
			}else{
				$("#date_of_birth").removeClass("error-field");
			}
			
			if(persona.location == '0'){
				$("#ubicaciones").addClass("error-field");
				errorCounter++;
			}else{
				$("#ubicaciones").removeClass("error-field");
			}
			
			if(persona.telefono == ""){
				$("#telefono").addClass("error-field");
				errorCounter++;
			}else{
				$("#telefono").removeClass("error-field");
			}
			
			if(persona.direccion == ""){
				$("#direccion").addClass("error-field");
				errorCounter++;
			}else{
				$("#direccion").removeClass("error-field");
			}
			
			if(errorCounter > 0){
				return false;
			}else{
				return true;
			}
			break;
		case 2:
			usuario = {
				username: $("#username").val(),
				password: $("#password").val(),
				repeatedPassword: $("#repeatPassword").val()
			};
			
			if(usuario.username == ""){
				$("#username").addClass("error-field");
				errorCounter++;
			} else {
				$("#username").removeClass("error-field");
			}
			
			if(usuario.password == ""){
				$("#password").addClass("error-field");
				errorCounter++;
			} else {
				$("#password").removeClass("error-field");
			}
			
			if(usuario.repeatedPassword == ""){
				$("#repeatPassword").removeClass("error-field");
				errorCounter++;
			} else {
				$("#repeatPassword").removeClass("error-field");
			}
			
			if(errorCounter > 0 || !isPasswordValid()){
				$("#indicaciones").css("display","flex");
				return false;
			}else{
				$("#indicaciones").css("display","none");
				return true;
			}
			break;
	}
}

/**
 * funcion para validar la contraseña ingresada por el usuario.
 * @returns true si es valida, false si no lo es.
 */
function isPasswordValid(){
	const regex = RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
	if($("#password").val() === $("#repeatPassword").val()){
		if(regex.test($("#password").val())){
			return true;
		} else{
			return false;
		}
	} else {
		return false;
	}
}

/**
 * funcion para crear la cuenta del aspirante o candidato
 * @param event
 * @returns
 */
function onCreateAccount(event){
	event.preventDefault();
	objeto = {
			datosPersonales: persona,
			datosUsuario: usuario
	};
	$.ajax({
		url: '/create/candidato',
		contentType: 'application/json;charset=UTF-8',
		type: 'POST',
		data: JSON.stringify(objeto),
		success: function(res){
			if(res == 'exito'){
				$.notify({
					icon : "nc-icon nc-check-2",
					title : "¡Información!",
					message : "Usuario creado con éxito."

				}, {
					type : "success",
					timer : 3500,
					placement : {
						from : 'top',
						align : 'right'
					},
					onShown : function() {
						window.location.href = "/login/";
					}
				});
			}
		}
	});
}

/**
 * funcion para validar si el valor del campo es unico en la base.
 * @param event
 * @returns
 */
function onCheckUnique(event){
	var field = {
			id: event.target.id,
			value: event.target.value
	};
	
	if(field.value != "" && field.value != " "){
		$.ajax({
			url: '/checkUniqueConstraints',
			type: 'POST',
			data: field,
			success: function(res){
				if(res == 0){
					$(event.target).addClass("error-border");
					$("#"+$(event.target).attr("aria-describedby")).removeClass("uniqueMessage");
					validUniques[field.id] = 1;
				} else {
					$(event.target).removeClass("error-border");
					$("#"+$(event.target).attr("aria-describedby")).addClass("uniqueMessage");
					validUniques[field.id] = 0;
				}
			}
		});
	}
}

/**
 * funcion para verificar si los campos unicos tienen valores unicos.
 * @returns true si todos los valores son unicos y false de lo contrario
 */
function onCheckValidUniques(){
	if(validUniques.username == 1){
		return false;
	}
	
	if(validUniques.nup == 1){
		return false;
	}
	
	if(validUniques.nit == 1){
		return false;
	}
	
	if(validUniques.pasaporte == 1){
		return false;
	}
	
	if(validUniques.email){
		return false;
	}
	
	return true;
}