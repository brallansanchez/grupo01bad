package ues.fia.eisi.bad.grupo4.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ues.fia.eisi.bad.grupo4.models.entities.Empresa;
import ues.fia.eisi.bad.grupo4.models.entities.Persona;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/curriculo")
public class CurriculoController {
	
	
	
	private GenericDao<Persona> service;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
	
	@Autowired
	public void setDao(GenericDao<Persona> daoToSet) {
		service = daoToSet;
		service.setClazz(Persona.class);
	}
	
	@GetMapping("/create")
	public String index() {
		return "/curriculo/index";
	}
	
	@PostMapping("/create")
	public @ResponseBody String onCreate(@RequestParam Map<String,String> params) throws ParseException {
		Persona persona = new Persona();
		persona.setNombrePersona(params.get("nombre"));
		persona.setApellidoPersona(params.get("apellido"));
		persona.setEmailPersona(params.get("email"));
		persona.setDireccionPersona(params.get("direccion"));
		persona.setDuiPersona(params.get("dui"));
		persona.setFechaNacimientoPersona(this.simpleDateFormat.parse(params.get("fechaNac")));
		persona.setNitPersona(params.get("nit"));
		persona.setNupPersona(params.get("nup"));
		persona.setPasaportePersona(params.get("pasaporte"));
		persona.setTelefonoPersona(params.get("telefono"));
		
		this.service.create(persona);
		return "exito";
	}
	
}
