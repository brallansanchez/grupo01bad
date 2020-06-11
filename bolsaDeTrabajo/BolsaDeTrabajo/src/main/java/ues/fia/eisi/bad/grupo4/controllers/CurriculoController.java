package ues.fia.eisi.bad.grupo4.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import ues.fia.eisi.bad.grupo4.models.entities.CategoriaPuesto;
import ues.fia.eisi.bad.grupo4.models.entities.DominioHabilidad;
import ues.fia.eisi.bad.grupo4.models.entities.Empresa;
import ues.fia.eisi.bad.grupo4.models.entities.FormacionAcademica;
import ues.fia.eisi.bad.grupo4.models.entities.Habilidad;
import ues.fia.eisi.bad.grupo4.models.entities.Persona;
import ues.fia.eisi.bad.grupo4.models.entities.RedSocial;
import ues.fia.eisi.bad.grupo4.models.entities.TipoDocumento;
import ues.fia.eisi.bad.grupo4.models.entities.TipoFormacion;
import ues.fia.eisi.bad.grupo4.models.entities.TipoInstitucion;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/curriculo")
public class CurriculoController {
	
	
	
	private GenericDao<FormacionAcademica> service;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private List<TipoFormacion> tiposDeFormacion;
	private List<TipoInstitucion> tiposDeInstitucion;
	private List<RedSocial> redesSociales;
	private List<TipoDocumento> tipoDocumentos;
	private List<CategoriaPuesto> categorias;
	private List<Habilidad> skills;
	private List<DominioHabilidad> dominioHabilidades;
	private List<Habilidad> speakingSkills;
	
	@Autowired
	public void setDao(GenericDao<FormacionAcademica> daoToSet) {
		service = daoToSet;
		service.setClazz(FormacionAcademica.class);
	}
	
	@GetMapping("/create")
	public String index(Model model) {
		String jpqlTipoFormacion = "from TipoFormacion";
		tiposDeFormacion = (List<TipoFormacion>) service.jpqlQuery(jpqlTipoFormacion);
		model.addAttribute("tipos", tiposDeFormacion);
		
		String jpqlTipoInstitucion = "from TipoInstitucion ti where ti.idTipoInstitucion in(1,2,7)";
		tiposDeInstitucion = (List<TipoInstitucion>) service.jpqlQuery(jpqlTipoInstitucion);
		model.addAttribute("instituciones",tiposDeInstitucion);
		
		String jpqlRedSocial = "from RedSocial";
		redesSociales = (List<RedSocial>) service.jpqlQuery(jpqlRedSocial);
		model.addAttribute("redes", redesSociales);
		
		String jpqlTipoDocumento = "from TipoDocumento";
		tipoDocumentos = (List<TipoDocumento>) service.jpqlQuery(jpqlTipoDocumento);
		model.addAttribute("tipoDocs", tipoDocumentos);
		
		String jqplCategoriasPuesto = "from CategoriaPuesto";
		categorias = (List<CategoriaPuesto>) service.jpqlQuery(jqplCategoriasPuesto);
		model.addAttribute("categories", categorias);
		
		String jpqlDominioHabilidad = "from DominioHabilidad dom order by dom.idDominioHabilidad";
		dominioHabilidades = service.jpqlQuery(jpqlDominioHabilidad);
		model.addAttribute("dominios", dominioHabilidades);
		
		String jpqlSpeakingSkills = "from Habilidad h join fetch h.tipoHabilidad t where t.idTipoHabilidad = 2";
		speakingSkills = service.jpqlQuery(jpqlSpeakingSkills);
		model.addAttribute("languages", speakingSkills);
		return "/curriculo/index";
	}
	
	@PostMapping("/array")
	public @ResponseBody String onArray(@RequestBody Map<String,List<Map<String,String>>> params) {
		List<Map<String,String>> auxList = params.get("formaciones");
		Map objeto = auxList.get(0);
		String titulo = (String) objeto.get("titulo");
		System.out.println(titulo);
		return "exito";
	}
	
	@PostMapping("/create")
	public @ResponseBody String onCreate(@RequestParam Map<String,String> params) throws ParseException {
		Persona persona = new Persona();
		persona.setNombrePersona(params.get("nombre"));
		persona.setApellidoPersona(params.get("apellido"));
		persona.setEmailPersona(params.get("email"));
		persona.setDireccionPersona(params.get("direccion"));
		persona.setDuiPersona(params.get("dui"));
		persona.setFechaNacimientoPersona(this.simpleDateFormat.parse(params.get("fechaNac")));
		persona.setNitPersona(params.get("nit"));
		persona.setNupPersona(params.get("nup"));
		persona.setPasaportePersona(params.get("pasaporte"));
		persona.setTelefonoPersona(params.get("telefono"));
		
		this.service.createAny(persona);
		return "exito";
	}
	
	@PostMapping("/getSkills")
	public @ResponseBody List<Habilidad> getSkills(@RequestParam Long id){
		String jpqlSkill = "from Habilidad skill join fetch skill.categoriaPuesto category where skill.categoriaPuesto.idCategoriaPuesto = :id";
		Map<String,Object> params = new HashMap();
		params.put("id", id);
		skills = (List<Habilidad>) service.jpqlParamsQuery(jpqlSkill, params);
		return skills;
	}
	
}
