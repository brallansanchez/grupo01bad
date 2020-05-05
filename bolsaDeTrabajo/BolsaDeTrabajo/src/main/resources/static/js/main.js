var empresa = {};

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
								: res.descripcionEmpresa
								);
			} else {
				$("#nombre_empresa_modal_edit").val(res.nombreEmpresa);
				$("#telefono_empresa_modal_edit").val(res.telefonoEmpresa);
				$("#email_empresa_modal_edit").val(res.emailEmpresa);
				$("#direccion_empresa_modal_edit").val(res.direccionEmpresa);
				$("#descripcion_empresa_modal_edit").val(
						res.descripcionEmpresa == null ? "---"
								: res.descripcionEmpresa
								);
				
				empresa.id = id;
			}
			
			
		}
	});
}

function onUpdateEmpresa(){
	
	empresa.nombre = $("#nombre_empresa_modal_edit").val();
	empresa.telefono = $("#telefono_empresa_modal_edit").val();
	empresa.email = $("#email_empresa_modal_edit").val();
	empresa.direccion = $("#direccion_empresa_modal_edit").val();
	empresa.descripcion = $("#descripcion_empresa_modal_edit").val();
	
	if(
			empresa.nombre != null && empresa.nombre != ''			&&
			empresa.telefono != null && empresa.telefono != ''		&&
			empresa.email != null && empresa.email != ''			&&
			empresa.direccion != null && empresa.direccion != ''	
      )
	{
		$.ajax({
			url: '/empresas/actualizar',
			type: 'POST',
			data: empresa,
			success: function(res){
				if(res == 'Success'){
					toastr.success('Registro actualizado con éxito', 'Información');
				} else if(res == 'Error'){
					toastr.error('Algo salió mal!', '¡Error!')
				}else{
					toastr.warning('Error desconocido', 'Información');
				}
			}
		})
	} else {
		$(".pos-demo").notify(
				"Llene los campos con '*'", 
				{ position:"left" }
		);
	}
}

function onSendCurriculo(){
	persona = {
			nombre: 	$("#firstname").val(),
			apellido: 	$("#lastname").val(),
			email: 		$("#email").val(),
			telefono: 	$("#telefono").val(),
			direccion: 	$("#direccion").val(),
			fechaNac:	$("#date_of_birth").val(),
			dui:		$("#dui").val(),
			nit:		$("#nit").val(),
			pasaporte:	$("#pasaporte").val(),
			nup:		$("#nup").val()
	};
	
	$.ajax({
		url: '/curriculo/create',
		type: 'POST',
		data: persona,
		success: function(res){
			console.log(res);
		}
	});
}