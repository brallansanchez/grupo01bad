
package ues.fia.eisi.bad.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import ues.fia.eisi.bad.grupo4.models.entities.Persona;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	private List<Persona> personas;
	private GenericDao<Persona> service;

	@Autowired
	public void setDao(GenericDao<Persona> daoToSet) {
		service = daoToSet;
		service.setClazz(Persona.class);
	}

	@PostConstruct
	public void init() {
		personas = new ArrayList<Persona>();
	}
	
	@GetMapping("/crear")
	public String onCreatePersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "personas/create";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		System.out.println("entro al metodo de personas");
		model.addAttribute("personas", this.getAllRecords());
		return "personas/index";
	}
	
	@GetMapping("/ver_perfil")
	public String ver_perfil(Model model){
	model.addAttribute("personas",this.getAllRecords()); 
		return "personas/ver_perfil";
	}

	private List<Persona> getAllRecords(){
		return service.findAll();
	}
	
	private Persona getOnePersona(Long id) {
		return service.findOne(id);
	}

	
	
}
