package ues.fia.eisi.bad.grupo4.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ues.fia.eisi.bad.grupo4.models.entities.Pais;

import ues.fia.eisi.bad.grupo4.services.DAO.PaisDao;

@RequestMapping("/sesion/admin/pais")
@Controller
public class PaisController {

	@Autowired
	private PaisDao paisDao;
	
	private List<Pais> paises;

	@GetMapping({"/",""})
	public String index(Model model) {
		paises = (List<Pais>) paisDao.findAll();
		model.addAttribute("paises",paises);
		return "pais/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("pais", new Pais());
		return "pais/create";
	}
	
	@PostMapping("/create")
	public String onCreatePais(@Valid Pais pais, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "pais/create";
		}
		
		try {
			paisDao.save(pais);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/pais/";
	}
	
	@GetMapping("/ver/{idPais}")
	public String onViewPais(@PathVariable Long idPais, Model model, RedirectAttributes flash) {
		Pais pais = (paisDao.findById(idPais)).orElse(null);
		if(pais == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/pais/";
		}
		model.addAttribute("pais",pais);
		model.addAttribute("readOnly", true);
		return "pais/view";
	}
	
	@GetMapping("/editar/{idPais}")
	public String getEditView(@PathVariable Long idPais, Model model, RedirectAttributes flash) {
		Pais pais = (paisDao.findById(idPais)).orElse(null);
		if(pais == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/pais/";
		}
		
		model.addAttribute("pais",pais);
		return "pais/edit";
	}
	
	@PostMapping("/editar")
	public String onEditPais(@Valid Pais pais, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "pais/edit";
		}
		
		try {
			paisDao.save(pais);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/pais/";
	}
	
	@RequestMapping(value = "/eliminar/{idPais}")
	public String onDeletePais(@PathVariable Long idPais, RedirectAttributes flash) {
		Pais pais = paisDao.findById(idPais).orElse(null);
		
		if(pais == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/pais/";
		}
		
		try {
			paisDao.delete(pais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/pais/";
	}
}
