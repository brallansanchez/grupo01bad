function getEmpresaById(id){
	$.ajax({
		  url: '/empresas/ver/'+id,
		  success: function(res){
			  $("#nombre_empresa").attr("readonly","true");
			  $("#telefono_empresa").attr("readonly","true");
			  $("#email_empresa").attr("readonly","true");
			  $("#direccion_empresa").attr("readonly","true");
			  $("#descripcion_empresa").attr("readonly","true");
			  
			  $("#nombre_empresa").val(res.nombreEmpresa);
			  $("#telefono_empresa").val(res.telefonoEmpresa);
			  $("#email_empresa").val(res.emailEmpresa);
			  $("#direccion_empresa").val(res.direccionEmpresa);
			  $("#descripcion_empresa").val(res.descripcionEmpresa == null ? "---":res.descripcionEmpresa);
		  }
	});
}