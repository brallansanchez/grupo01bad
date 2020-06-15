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

import ues.fia.eisi.bad.grupo4.models.entities.TipoContrato;
import ues.fia.eisi.bad.grupo4.models.entities.TipoDocumento;
import ues.fia.eisi.bad.grupo4.services.DAO.TipoContratacionDao;
import ues.fia.eisi.bad.grupo4.services.DAO.TipoDocumentoDao;

@RequestMapping("/sesion/admin/tipodoc")
@Controller
public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoDao tipoDocDao;
	
	private List<TipoDocumento> documentos;

	@GetMapping({"/",""})
	public String index(Model model) {
		documentos = (List<TipoDocumento>) tipoDocDao.findAll();
		model.addAttribute("documentos",documentos);
		return "tipo_documento/index";
	}
	
	@GetMapping({"/create","/create/"})
	public String createView(Model model) {
		model.addAttribute("doc", new TipoDocumento());
		return "tipo_documento/create";
	}
	
	@PostMapping("/create")
	public String onCreateTipoDocumento(@Valid @ModelAttribute("doc") TipoDocumento tipoDocumento, BindingResult result, RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			return  "tipo_documento/create";
		}
		
		try {
			tipoDocDao.save(tipoDocumento);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success", "Registro creado con éxito");
		return "redirect:/sesion/admin/tipodoc/";
	}
	
	@GetMapping("/ver/{idTipoDocumento}")
	public String onViewTipoDocumento(@PathVariable Long idTipoDocumento, Model model, RedirectAttributes flash) {
		TipoDocumento tipoDocumento = (tipoDocDao.findById(idTipoDocumento)).orElse(null);
		if(tipoDocumento == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:sesion/admin/tipodoc/";
		}
		model.addAttribute("doc",tipoDocumento);
		model.addAttribute("readOnly", true);
		return "tipo_documento/view";
	}
	
	@GetMapping("/editar/{idTipoDocumento}")
	public String getEditView(@PathVariable Long idTipoDocumento, Model model, RedirectAttributes flash) {
		TipoDocumento tipoDocumento = (tipoDocDao.findById(idTipoDocumento)).orElse(null);
		if(tipoDocumento == null) {
			flash.addFlashAttribute("error", "El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/tipodoc/";
		}
		
		model.addAttribute("doc",tipoDocumento);
		return "tipo_documento/edit";
	}
	
	@PostMapping("/editar")
	public String onEditTipoDocumento(@Valid @ModelAttribute("doc") TipoDocumento tipoDocumento, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			return "tipo_documento/edit";
		}
		
		try {
			tipoDocDao.save(tipoDocumento);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro editado con éxito");
		return "redirect:/sesion/admin/tipodoc/";
	}
	
	@RequestMapping(value = "/eliminar/{idTipoDocumento}")
	public String onDeleteTipoDocumento(@PathVariable Long idTipoDocumento, RedirectAttributes flash) {
		TipoDocumento tipoDocumento = tipoDocDao.findById(idTipoDocumento).orElse(null);
		
		if(tipoDocumento == null) {
			flash.addFlashAttribute("error","El registro no pudo ser encontrado");
			return "redirect:/sesion/admin/tipodoc/";
		}
		
		try {
			tipoDocDao.delete(tipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		flash.addFlashAttribute("success","Registro eliminado con éxito");
		return "redirect:/sesion/admin/tipodoc/";
	}
}
