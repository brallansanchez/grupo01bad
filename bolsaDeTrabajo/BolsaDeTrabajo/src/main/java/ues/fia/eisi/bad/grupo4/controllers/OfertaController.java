package ues.fia.eisi.bad.grupo4.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ues.fia.eisi.bad.grupo4.models.entities.CategoriaPuesto;
import ues.fia.eisi.bad.grupo4.models.entities.DivisionGeografica;
import ues.fia.eisi.bad.grupo4.models.entities.Empresa;
import ues.fia.eisi.bad.grupo4.models.entities.OfertaLaboral;
import ues.fia.eisi.bad.grupo4.models.entities.Pais;
import ues.fia.eisi.bad.grupo4.models.entities.PerfilLaboral;
import ues.fia.eisi.bad.grupo4.models.entities.RangoEdad;
import ues.fia.eisi.bad.grupo4.models.entities.RangoExperiencia;
import ues.fia.eisi.bad.grupo4.models.entities.SexoPersona;
import ues.fia.eisi.bad.grupo4.models.entities.TipoContrato;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {
	
	private GenericDao<OfertaLaboral> service;
	private List<CategoriaPuesto> categorias;
	private List<Pais> paises;
	private List<RangoExperiencia> rangosDeExperiencia;
	private List<TipoContrato> tipoContratos;
	private List<SexoPersona> generos;
	private List<RangoEdad> rangoEdades;
	private List<OfertaLaboral> ofertas;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public void setDao(GenericDao<OfertaLaboral> daoToSet) {
		service = daoToSet;
		service.setClazz(OfertaLaboral.class);
	}
	
	@PostConstruct
	public void init() {
		ofertas = new ArrayList<OfertaLaboral>();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public String index(Model model) {
		this.ofertas = (List<OfertaLaboral>) service.jpqlQuery("from OfertaLaboral ol join fetch ol.categoria where ol.empresa.idEmpresa = 1");
		//model.addAttribute("ofertas",ofertas);
		model.addAttribute("ofertas",this.getAllRecords());
		
		return "ofertas/ofertas";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/create")
	public String onCreate(Model model) {
		
		onInitLists();
		
		//Definicion de modelos para la vista
		model.addAttribute("categorias", this.categorias);
		model.addAttribute("paises", this.paises);
		model.addAttribute("rangoExp", this.rangosDeExperiencia);
		model.addAttribute("contratos", this.tipoContratos);
		model.addAttribute("generos", this.generos);
		model.addAttribute("rangoEdades", this.rangoEdades);
		
		return "ofertas/create";
	}
	
	@GetMapping("/ver/{id}")
	public @ResponseBody OfertaLaboral onViewOffer(@PathVariable Long id){
		return service.findOne(id);
	}
	
	@GetMapping("/ver/perfil/{id}")
	public @ResponseBody PerfilLaboral onViewPerfil(@PathVariable Long id) {
		String jqpl = "from PerfilLaboral pl join fetch pl.ofertaLaboral ol where ol.idOferta = :id";
		return (PerfilLaboral) service.jpqlUniqueResult(jqpl, id);
	}
	
	@PostMapping("/create")
	public @ResponseBody String onCreateOffer(@RequestParam Map<String,String> params) throws ParseException {
		CategoriaPuesto categoriaPuesto = null;
		SexoPersona sexoPersona = null;
		DivisionGeografica divGeogragica = null;
		RangoEdad rangoEdad = null;
		TipoContrato tipoContrato = null;
		RangoExperiencia rangoExp = null;
		Empresa empresa = null;
		
		if(
			(
					this.categorias.isEmpty() 		||
					this.generos.isEmpty() 	  		||
					this.paises.isEmpty() 	  		||
					this.rangoEdades.isEmpty()		||
					this.tipoContratos.isEmpty()	||
					this.rangosDeExperiencia.isEmpty()
			)
		  ) 
		{
			this.onInitLists();
			System.out.println("entro");
		}
		
		for (CategoriaPuesto category : categorias) {
			if(category.getIdCategoriaPuesto().equals(Long.valueOf(params.get("categoria")))) {
				categoriaPuesto = category;
				System.out.println("categoria");
				break;
			}
		}
		
		for (SexoPersona genero : generos) {
			if(genero.getIdSexoPersona().equals(Long.valueOf(params.get("sexoPersona")))) {
				sexoPersona = genero;
				System.out.println("genero");
				break;
			}
		}
		
		for (RangoEdad ageRange : rangoEdades) {
			if(ageRange.getIdRangoEdad().equals(Long.valueOf(params.get("rangoEdad")))) {
				rangoEdad = ageRange;
				System.out.println("Rango edad");
				break;
			}
		}
		
		for (TipoContrato contrato: tipoContratos) {
			if(contrato.getIdTipoContrato().equals(Long.valueOf(params.get("tipoContrato")))) {
				tipoContrato = contrato;
				System.out.println("Contrato");
				break;
			}
		}
		
		for (RangoExperiencia expRange : rangosDeExperiencia) {
			if(expRange.getIdRangoExperiencia().equals(Long.valueOf(params.get("rangoExp")))) {
				rangoExp = expRange;
				System.out.println("rango experiencia");
				break;
			}
		}
		
		String jpql = "from DivisionGeografica dg where dg.idDivision = :id";
		divGeogragica = (DivisionGeografica) service.jpqlUniqueResult(jpql, Long.valueOf(params.get("divisionGeo")));
				
		String jpqlEmpresa = "from Empresa e where e.idEmpresa = :id";
		empresa = (Empresa) service.jpqlUniqueResult(jpqlEmpresa, 1L);
				
		OfertaLaboral ofertaLaboral = new OfertaLaboral();
		Date fechaExpiracion = simpleDateFormat.parse(params.get("fechaExp"));
		ofertaLaboral.setFechaExp(fechaExpiracion);
		ofertaLaboral.setTituloOferta(params.get("titulo"));
		ofertaLaboral.setDescripcionOferta(params.get("descripcion"));
		ofertaLaboral.setCategoria(categoriaPuesto);
		ofertaLaboral.setEmpresa(empresa);
		service.create(ofertaLaboral);
		
		PerfilLaboral perfilLaboral = new PerfilLaboral();
		perfilLaboral.setAreaEmpresarial(params.get("areaCargo"));
		perfilLaboral.setCargoSolicitado(params.get("cargo"));
		perfilLaboral.setDivisionGeografica(divGeogragica);
		perfilLaboral.setRangoEdad(rangoEdad);
		perfilLaboral.setRangoExperiencia(rangoExp);
		perfilLaboral.setRangoSalarial(params.get("rangoSalarial"));
		perfilLaboral.setSexoPersona(sexoPersona);
		perfilLaboral.setTipoContrato(tipoContrato);
		perfilLaboral.setOfertaLaboral(ofertaLaboral);
		
		service.createAny(perfilLaboral);
		return "exito";
	}
	
	@PostMapping("/regiones")
	public @ResponseBody List<DivisionGeografica> onGetRegions(@RequestBody Long id){
		//Definicion de jpql
		String jpqlRegiones = "from DivisionGeografica dg join fetch dg.pais p where p.idPais = :param";
		
		//Definicion de parametros
		Map params = new HashMap();
		params.put("param", id);
		
		return service.jpqlParamsQuery(jpqlRegiones, params);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Metodo para inicializar listas de la vista
	 */
	private void onInitLists() {

		//jpql de catalogos
		String jpqlCategorias = "from CategoriaPuesto";
		String jpqlPaises = "from Pais";
		String jpqlRangoExp = "from RangoExperiencia";
		String jpqlTipoContrato = "from TipoContrato";
		String jpqlSexoPersona = "from SexoPersona";
		String jpqlRangoEdad = "from RangoEdad order by rangoEdad";
		
		//Llenando listas
		this.categorias = (List<CategoriaPuesto>) service.jpqlQuery(jpqlCategorias);
		this.paises = (List<Pais>) service.jpqlQuery(jpqlPaises);
		this.rangosDeExperiencia = (List<RangoExperiencia>) service.jpqlQuery(jpqlRangoExp);
		this.tipoContratos = (List<TipoContrato>) service.jpqlQuery(jpqlTipoContrato);
		this.generos = (List<SexoPersona>) service.jpqlQuery(jpqlSexoPersona);
		this.rangoEdades = (List<RangoEdad>) service.jpqlQuery(jpqlRangoEdad);
	}
	
	private List<OfertaLaboral> getAllRecords(){
		return service.findAll();
	}
	
}
