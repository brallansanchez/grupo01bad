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

import ues.fia.eisi.bad.grupo4.models.entities.RangoEdad;
import ues.fia.eisi.bad.grupo4.models.entities.RangoExperiencia;
import ues.fia.eisi.bad.grupo4.services.DAO.RangoEdadDao;
import ues.fia.eisi.bad.grupo4.services.DAO.RangoExperienciaDao;

@RequestMapping("/sesion/admin/rangoexp")
@Controller
public class RangoExperienciaController {
	@Autowired
	private RangoExperienciaDao rangoExpDao;
	
	private List<RangoExperiencia> rangoExperiencias;

	@GetMapping({"/",""})
	public String index(Model model) {
		rangoExperiencias = (List<RangoExperiencia>) rangoExpDao.findAll();
		model.addAttribute("rangoExp",rangoExperiencias);
		return "rango_exp/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("rango", new RangoExperiencia());
		return "rango_exp/create";
	}
	
	@PostMapping("/create")
	public String onCreateRangoExperiencia(@Valid @ModelAttribute("rango") RangoExperiencia rangoExperiencia, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "rango_exp/create";
		}
		
		try {
			rangoExpDao.save(rangoExperiencia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/rangoexp/";
	}
	
	@GetMapping("/ver/{idRandoExp}")
	public String onViewRangoExperiencia(@PathVariable Long idRandoExp, Model model, RedirectAttributes flash) {
		RangoExperiencia rangoExperiencia = (rangoExpDao.findById(idRandoExp)).orElse(null);
		if(rangoExperiencia == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/rangoexp/";
		}
		model.addAttribute("rango",rangoExperiencia);
		model.addAttribute("readOnly", true);
		return "rango_exp/view";
	}
	
	@GetMapping("/editar/{idRangoExp}")
	public String getEditView(@PathVariable Long idRangoExp, Model model, RedirectAttributes flash) {
		RangoExperiencia rangoExperiencia = (rangoExpDao.findById(idRangoExp)).orElse(null);
		if(rangoExperiencia == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/rangoexp/";
		}
		
		model.addAttribute("rango",rangoExperiencia);
		return "rango_exp/edit";
	}
	
	@PostMapping("/editar")
	public String onEditRangoExperiencia(@Valid @ModelAttribute("rango") RangoExperiencia rangoExperiencia, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "rango_exp/edit";
		}
		
		try {
			rangoExpDao.save(rangoExperiencia);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/rangoexp/";
	}
	
	@RequestMapping(value = "/eliminar/{idRandoExperiencia}")
	public String onDeleteRangoExperiencia(@PathVariable Long idRandoExperiencia, RedirectAttributes flash) {
		RangoExperiencia rangoExperiencia = rangoExpDao.findById(idRandoExperiencia).orElse(null);
		
		if(rangoExperiencia == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/rangoexp/";
		}
		
		try {
			rangoExpDao.delete(rangoExperiencia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/rangoexp/";
	}

}
