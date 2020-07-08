package ues.fia.eisi.bad.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sesion_aspirante")
public class SesionAspiranteController {


	@GetMapping("/aspirante")
	public String empresaSesion() {
		return "sesiones_aspirante/aspirante";
	}

    
}