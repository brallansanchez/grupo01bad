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

import ues.fia.eisi.bad.grupo4.models.entities.RedSocial;
import ues.fia.eisi.bad.grupo4.models.entities.TipoContrato;
import ues.fia.eisi.bad.grupo4.services.DAO.RedSocialDao;
import ues.fia.eisi.bad.grupo4.services.DAO.TipoContratacionDao;

@RequestMapping("/sesion/admin/tipocontrato")
@Controller
public class TipoContratoController {
	@Autowired
	private TipoContratacionDao tipoContratoDao;
	
	private List<TipoContrato> contratos;

	@GetMapping({"/",""})
	public String index(Model model) {
		contratos = (List<TipoContrato>) tipoContratoDao.findAll();
		model.addAttribute("contratos",contratos);
		return "tipo_contrato/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("contrato", new TipoContrato());
		return "tipo_contrato/create";
	}
	
	@PostMapping("/create")
	public String onCreateTipoContrato(@Valid @ModelAttribute("contrato") TipoContrato tipoContrato, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "tipo_contrato/create";
		}
		
		try {
			tipoContratoDao.save(tipoContrato);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/tipocontrato/";
	}
	
	@GetMapping("/ver/{idTipoContrato}")
	public String onViewTipoContrato(@PathVariable Long idTipoContrato, Model model, RedirectAttributes flash) {
		TipoContrato tipoContrato = (tipoContratoDao.findById(idTipoContrato)).orElse(null);
		if(tipoContrato == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/tipocontrato/";
		}
		model.addAttribute("contrato",tipoContrato);
		model.addAttribute("readOnly", true);
		return "tipo_contrato/view";
	}
	
	@GetMapping("/editar/{idTipoContrato}")
	public String getEditView(@PathVariable Long idTipoContrato, Model model, RedirectAttributes flash) {
		TipoContrato tipoContrato = (tipoContratoDao.findById(idTipoContrato)).orElse(null);
		if(tipoContrato == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/tipocontrato/";
		}
		
		model.addAttribute("contrato",tipoContrato);
		return "tipo_contrato/edit";
	}
	
	@PostMapping("/editar")
	public String onEditTipoContrato(@Valid @ModelAttribute("contrato") TipoContrato tipoContrato, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "tipo_contrato/edit";
		}
		
		try {
			tipoContratoDao.save(tipoContrato);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/tipocontrato/";
	}
	
	@RequestMapping(value = "/eliminar/{idTipoContrato}")
	public String onDeleteRedSocial(@PathVariable Long idTipoContrato, RedirectAttributes flash) {
		TipoContrato tipoContrato = tipoContratoDao.findById(idTipoContrato).orElse(null);
		
		if(tipoContrato == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/tipocontrato/";
		}
		
		try {
			tipoContratoDao.delete(tipoContrato);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/tipocontrato/";
	}
}
