var user = {
	
	getObject: function(){
		return {
			username: $("#username").val(),
			password: $("#password").val()
		}
	},
	
	validateFields: function(object){
		let errorCounter = 0;
		if(object.username == "" || object.username == " "){
			errorCounter++;
			$("#username").addClass("error-field");
		}
		
		if(object.password == "" || object.password == " "){
			errorCounter++;
			$("#password").addClass("error-field");
		}
		
		return errorCounter > 0 ? false:true;
	},
		
	onSendData: function(event){
		event.preventDefault();
		var data = user.getObject();
		
		if(user.validateFields(data)){
			$.ajax({
				url: '/checkUserAndPass',
				type: 'POST',
				data: data,
				success: function(res){
					if(res == 'exito'){
						window.location.href = "/sesion/";
					} else {
						$("#error-message").css("display","flex");
					}
				}
			});
		}
	}
};