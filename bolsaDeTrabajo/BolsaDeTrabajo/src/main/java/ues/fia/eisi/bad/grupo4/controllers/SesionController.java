package ues.fia.eisi.bad.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sesion")
public class SesionController {

	@GetMapping("/admin")
	public String adminSesion() {
		return "sesiones/admin";
	}
	@GetMapping("/aspirant")
	public String aspiranteSesion() {
		return "sesiones/aspirant";
	}
	@GetMapping("/company")
	public String empresaSesion() {
		return "sesiones/company";
	}
}
