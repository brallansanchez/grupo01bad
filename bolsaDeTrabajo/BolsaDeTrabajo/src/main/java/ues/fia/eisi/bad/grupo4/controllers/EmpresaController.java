package ues.fia.eisi.bad.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/")
	public String index(Model model) {
		empresas = new ArrayList<Empresa>();
		empresas = service.findAll();
		model.addAttribute("empresas", empresas);
		return "empresas/index";
	}
}
