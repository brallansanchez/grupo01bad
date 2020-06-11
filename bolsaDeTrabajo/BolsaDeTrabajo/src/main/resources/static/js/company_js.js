var ban;
var empresa = {
	
	/**
	 * metodo para validar los campos de la compañia.
	 */
	validateCompanyFields: function(object){
		let errorCounter = 0;
		if(object.nombre == "" || object.nombre == " "){
			$("#companyName").addClass("error-field");
			errorCounter++;
		}
		
		if(object.email == "" || object.email == " "){
			$("#emailCompany").addClass("error-field");
			errorCounter++;
		}
		
		if(object.tel == "" || object.tel == " "){
			$("#telCompany").addClass("error-field");
			errorCounter++;
		}
		
		if(object.dir == "" || object.dir == " "){
			$("#direccion").addClass("error-field");
			errorCounter++;
		}
		
		if(object.desc == "" || object.desc == " "){
			$("#descripcion").addClass("error-field");
			errorCounter++;
		}
		
		return errorCounter > 0 ? false:true;
	},
	
	/**
	 * metodo para validar los campos de usuario.
	 */
	validateUserFields: function(object){
		let errorCounter=0;
		if(object.username == "" || object.username == " "){
			$("#username").addClass("error-field");
			errorCounter++;
		}
		
		if(object.password == "" || !empresa.validatePassword()){
			empresa.onCheckUniqueValue();
			if(!ban){
				$("#username").addClass("error-field");
				$("#password").addClass("error-field");
				$("#passwordRepeat").addClass("error-field");
				errorCounter++;
			}
		}
		
		return errorCounter > 0 ? false:true;
	},
	
	onCheckUniqueValue: function(event){
		ban = false;
		var field= {
			id: "username",
			value: $("#username").val() 
		};
		
		if(field.value != "" && field.value != " "){
			$.ajax({
				url: '/checkUniqueConstraints',
				type: 'POST',
				data: field,
				success: function(res){
					if(res == 0){
						$("#username").addClass("error-border");
						ban = false;
					} else {
						$("#username").removeClass("error-border");
						ban = true;
					}
				}
			});
		}
	},
	
	/**
	 * metodo para validar la clave ingresada.
	 */
	validatePassword: function(){
		const regex = RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
		if($("#password").val() === $("#passwordRepeat").val()){
			if(regex.test($("#password").val())){
				return true;
			} else{
				return false;
			}
		} else {
			return false;
		}
	},

	/**
	 * crear objeto de datos a enviar.
	 */
	getObject: function(){
		return {
			
			companyData:{
				name  : $("#companyName").val(),
				email : $("#emailCompany").val(),
				tel   : $("#telCompany").val(),
				dir   : $("#direccion").val(),
				desc  : $("#descripcion").val()
			},
			
			userData:{
				username: $("#username").val(),
				password: $("#password").val(),
				passRep : $("#passwordRepeat").val()
			}
		};
	},
	
	/**
	 * metodo para enviar los datos para crear la empresa y su usuario.
	 */
	sendObject: function(event){
		event.preventDefault();
		var data = empresa.getObject();
		if(empresa.validateCompanyFields(data.companyData) && empresa.validateUserFields(data.userData)){
			$.ajax({
				url: '/create/company',
				contentType: 'application/json;charset=UTF-8',
				type: 'POST',
				data: JSON.stringify(data),
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
					} else {
						$.notify({
							icon : "nc-icon nc-check-2",
							title : "¡Error!",
							message : "Algo salió mal."

						}, {
							type : "danger",
							timer : 3500,
							placement : {
								from : 'top',
								align : 'right'
							}
						});
					}
				}
			});
		}
	}
};

