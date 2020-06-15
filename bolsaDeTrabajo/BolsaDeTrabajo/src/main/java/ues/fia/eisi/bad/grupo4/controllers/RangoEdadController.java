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

import ues.fia.eisi.bad.grupo4.models.entities.Pais;
import ues.fia.eisi.bad.grupo4.models.entities.RangoEdad;
import ues.fia.eisi.bad.grupo4.services.DAO.PaisDao;
import ues.fia.eisi.bad.grupo4.services.DAO.RangoEdadDao;

@RequestMapping("/sesion/admin/rangoedad")
@Controller
public class RangoEdadController {
	@Autowired
	private RangoEdadDao rangoEdadDao;
	
	private List<RangoEdad> rangoEdades;

	@GetMapping({"/",""})
	public String index(Model model) {
		rangoEdades = (List<RangoEdad>) rangoEdadDao.findAll();
		model.addAttribute("rangoEdades",rangoEdades);
		return "rango_edad/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("rango", new RangoEdad());
		return "rango_edad/create";
	}
	
	@PostMapping("/create")
	public String onCreateRangoEdad(@Valid @ModelAttribute("rango") RangoEdad rangoEdad, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "rango_edad/create";
		}
		
		try {
			rangoEdadDao.save(rangoEdad);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/rangoedad/";
	}
	
	@GetMapping("/ver/{idRandoEdad}")
	public String onViewRangoEdad(@PathVariable Long idRandoEdad, Model model, RedirectAttributes flash) {
		RangoEdad rangoEdad = (rangoEdadDao.findById(idRandoEdad)).orElse(null);
		if(rangoEdad == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/rangoedad/";
		}
		model.addAttribute("rango",rangoEdad);
		model.addAttribute("readOnly", true);
		return "rango_edad/view";
	}
	
	@GetMapping("/editar/{idRandoEdad}")
	public String getEditView(@PathVariable Long idRandoEdad, Model model, RedirectAttributes flash) {
		RangoEdad rangoEdad = (rangoEdadDao.findById(idRandoEdad)).orElse(null);
		if(rangoEdad == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/rangoedad/";
		}
		
		model.addAttribute("rango",rangoEdad);
		return "rango_edad/edit";
	}
	
	@PostMapping("/editar")
	public String onEditRangoEdad(@Valid @ModelAttribute("rango") RangoEdad rangoEdad, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "rango_edad/edit";
		}
		
		try {
			rangoEdadDao.save(rangoEdad);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/rangoedad/";
	}
	
	@RequestMapping(value = "/eliminar/{idRandoEdad}")
	public String onDeleteRangoEdad(@PathVariable Long idRandoEdad, RedirectAttributes flash) {
		RangoEdad rangoEdad = rangoEdadDao.findById(idRandoEdad).orElse(null);
		
		if(rangoEdad == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/rangoedad/";
		}
		
		try {
			rangoEdadDao.delete(rangoEdad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/rangoedad/";
	}
}
