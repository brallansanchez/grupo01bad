package ues.fia.eisi.bad.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sesion_empresa")
public class SesionEmpresaController {


	@GetMapping("/empresa")
	public String empresaSesion() {
		return "sesiones_empresa/empresa";
	}

    
}