package ues.fia.eisi.bad.grupo4.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ues.fia.eisi.bad.grupo4.models.entities.CategoriaPuesto;
import ues.fia.eisi.bad.grupo4.services.DAO.CategoriaPuestoDao;

@Controller
@RequestMapping("/sesion/admin/categoriapuesto")
public class CategoriaPuestoController {
	
	@Autowired
	private CategoriaPuestoDao categoriaPuestoDao;
	
	private List<CategoriaPuesto> categorias;

	@GetMapping({"/",""})
	public String index(Model model) {
		categorias = (List<CategoriaPuesto>) categoriaPuestoDao.findAll();
		model.addAttribute("categorias",categorias);
		return "categoria_puesto/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("categoria", new CategoriaPuesto());
		return "categoria_puesto/create";
	}
	
	@PostMapping("/create")
	public String onCreateCategoriaPuesto(@Valid @ModelAttribute("categoria") CategoriaPuesto categoria, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "categoria_puesto/create";
		}
		
		try {
			categoriaPuestoDao.save(categoria);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/categoriapuesto/";
	}
	
	@GetMapping("/ver/{idCategoria}")
	public String onViewCategoriaPuesto(@PathVariable Long idCategoria, Model model, RedirectAttributes flash) {
		CategoriaPuesto categoriaPuesto = (categoriaPuestoDao.findById(idCategoria)).orElse(null);
		if(categoriaPuesto == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/categoriapuesto/";
		}
		model.addAttribute("categoria",categoriaPuesto);
		model.addAttribute("readOnly", true);
		return "categoria_puesto/view";
	}
	
	@GetMapping("/editar/{idCategoria}")
	public String getEditView(@PathVariable Long idCategoria, Model model, RedirectAttributes flash) {
		CategoriaPuesto categoriaPuesto = (categoriaPuestoDao.findById(idCategoria)).orElse(null);
		if(categoriaPuesto == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/categoriapuesto/";
		}
		
		model.addAttribute("categoria",categoriaPuesto);
		return "categoria_puesto/edit";
	}
	
	@PostMapping("/editar")
	public String onEditCategoriaPuesto(@Valid @ModelAttribute("categoria") CategoriaPuesto categoria, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "categoria_puesto/edit";
		}
		
		try {
			categoriaPuestoDao.save(categoria);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/categoriapuesto/";
	}
	
	@RequestMapping(value = "/eliminar/{idCategoria}")
	public String onDeleteCategoriaPuesto(@PathVariable Long idCategoria, RedirectAttributes flash) {
		CategoriaPuesto categoriaPuesto = categoriaPuestoDao.findById(idCategoria).orElse(null);
		
		if(categoriaPuesto == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/categoriapuesto/";
		}
		
		try {
			categoriaPuestoDao.delete(categoriaPuesto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/categoriapuesto/";
	}
}
