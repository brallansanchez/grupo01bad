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

import ues.fia.eisi.bad.grupo4.models.entities.RangoExperiencia;
import ues.fia.eisi.bad.grupo4.models.entities.RedSocial;
import ues.fia.eisi.bad.grupo4.services.DAO.RangoExperienciaDao;
import ues.fia.eisi.bad.grupo4.services.DAO.RedSocialDao;

@RequestMapping("/sesion/admin/redsocial")
@Controller
public class RedSocialController {
	@Autowired
	private RedSocialDao redSocialDao;
	
	private List<RedSocial> redesSociales;

	@GetMapping({"/",""})
	public String index(Model model) {
		redesSociales = (List<RedSocial>) redSocialDao.findAll();
		model.addAttribute("redes",redesSociales);
		return "red_social/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("red", new RedSocial());
		return "red_social/create";
	}
	
	@PostMapping("/create")
	public String onCreateRedSocial(@Valid @ModelAttribute("red") RedSocial redSocial, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "red_social/create";
		}
		
		try {
			redSocialDao.save(redSocial);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/redsocial/";
	}
	
	@GetMapping("/ver/{idRedSocial}")
	public String onViewRedSocial(@PathVariable Long idRedSocial, Model model, RedirectAttributes flash) {
		RedSocial redSocial = (redSocialDao.findById(idRedSocial)).orElse(null);
		if(redSocial == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/redsocial/";
		}
		model.addAttribute("red",redSocial);
		model.addAttribute("readOnly", true);
		return "red_social/view";
	}
	
	@GetMapping("/editar/{idRedSocial}")
	public String getEditView(@PathVariable Long idRedSocial, Model model, RedirectAttributes flash) {
		RedSocial redSocial = (redSocialDao.findById(idRedSocial)).orElse(null);
		if(redSocial == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/redsocial/";
		}
		
		model.addAttribute("red",redSocial);
		return "red_social/edit";
	}
	
	@PostMapping("/editar")
	public String onEditRedSocial(@Valid @ModelAttribute("red") RedSocial redSocial, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "red_social/edit";
		}
		
		try {
			redSocialDao.save(redSocial);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/redsocial/";
	}
	
	@RequestMapping(value = "/eliminar/{idRedSocial}")
	public String onDeleteRedSocial(@PathVariable Long idRedSocial, RedirectAttributes flash) {
		RedSocial redSocial = redSocialDao.findById(idRedSocial).orElse(null);
		
		if(redSocial == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/redsocial/";
		}
		
		try {
			redSocialDao.delete(redSocial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/redsocial/";
	}
}
