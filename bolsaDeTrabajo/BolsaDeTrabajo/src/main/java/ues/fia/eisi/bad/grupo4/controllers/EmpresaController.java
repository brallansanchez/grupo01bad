package ues.fia.eisi.bad.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import ues.fia.eisi.bad.grupo4.models.entities.Empresa;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	private List<Empresa> empresas;
	private GenericDao<Empresa> service;
	
	@Autowired
	public void setDao(GenericDao<Empresa> daoToSet) {
		service = daoToSet;
		service.setClazz(Empresa.class);
	}
	
	@PostConstruct
	public void init() {
		empresas = new ArrayList<Empresa>();
	}
	
	@GetMapping("/crear")
	public String onCreateEmpresa(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "empresas/createEmpresa";
	}
	
	@PostMapping("/create")
	public String onSaveRecord(@ModelAttribute Empresa empresa,Model model){
		
		if(
				empresa.getNombreEmpresa()    == null || empresa.getNombreEmpresa().equals("") 		||
				empresa.getDireccionEmpresa() == null || empresa.getDireccionEmpresa().equals("")   ||
				empresa.getEmailEmpresa()     == null || empresa.getEmailEmpresa().equals("")		||
				empresa.getTelefonoEmpresa()  == null || empresa.getTelefonoEmpresa().equals("")
				
		  )
		{
			model.addAttribute("messages","Tiene campos vacíos");
			return "empresas/create";
		} else {
			service.create(empresa);
			return "redirect:/empresas/";
		}
		
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("empresas", this.getAllRecords());
		return "empresas/empresas";
	}
	
	@GetMapping(value="/ver/{id}")
	public @ResponseBody Empresa onViewEmpresa(@PathVariable Long id, Model model) {
		return this.getOneEmpresa(id);
	}
	
	@PostMapping(value="/actualizar")
	public @ResponseBody String onUpdateEmpresa(@RequestParam Map<String,String> empresa) {
		Empresa e = service.findOne(Long.valueOf(empresa.get("id")));
		String response = "Error";
		
		e.setNombreEmpresa(empresa.get("nombre"));
		e.setEmailEmpresa(empresa.get("email"));
		e.setTelefonoEmpresa(empresa.get("telefono"));
		e.setDireccionEmpresa(empresa.get("direccion"));
		e.setDescripcionEmpresa(empresa.get("descripcion"));
		try {
			service.update(e);
			this.empresas=this.getAllRecords();
			response = "Success";
		}catch(Exception ex) {
			response = "Something went wrong!";
		}
		return response;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	private List<Empresa> getAllRecords(){
		return service.findAll();
	}
	
	private Empresa getOneEmpresa(Long id) {
		return service.findOne(id);
	}
}
