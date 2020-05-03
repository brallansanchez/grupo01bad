package ues.fia.eisi.bad.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		empresas = this.getAllRecords();
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("empresas", empresas);
		return "empresas/index";
	}
	
	@GetMapping(value="/ver/{id}")
	public String onViewEmpresa(@PathVariable Long id, Model model) {
		return this.getOneEmpresa(id).toString();
	}	
	
	//-------------------------------------------------------------------------------------------------------------------
	private List<Empresa> getAllRecords(){
		return service.findAll();
	}
	
	private Empresa getOneEmpresa(Long id) {
		return service.findOne(id);
	}
}
