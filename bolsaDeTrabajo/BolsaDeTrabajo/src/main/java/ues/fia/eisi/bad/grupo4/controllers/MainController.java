package ues.fia.eisi.bad.grupo4.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ues.fia.eisi.bad.grupo4.models.entities.DivisionGeografica;
import ues.fia.eisi.bad.grupo4.models.entities.Empresa;
import ues.fia.eisi.bad.grupo4.models.entities.Pais;
import ues.fia.eisi.bad.grupo4.models.entities.Persona;
import ues.fia.eisi.bad.grupo4.models.entities.RangoExperiencia;
import ues.fia.eisi.bad.grupo4.models.entities.Rol;
import ues.fia.eisi.bad.grupo4.models.entities.SexoPersona;
import ues.fia.eisi.bad.grupo4.models.entities.Usuario;
import ues.fia.eisi.bad.grupo4.services.DAO.GenericDao;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private GenericDao<Persona> service;

	private List<Pais> paises;
	private List<DivisionGeografica> divisiones;
	private List<RangoExperiencia> rangoExperiencia;
	private List<SexoPersona> sexoPersona;
	private List<Rol> roles;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@GetMapping("")
	public String index() {
		return "index";
	}

	@GetMapping({ "login", "login/" })
	public String loginIn() {
		return "security/login";
	}

	@GetMapping("password-recovery")
	public String forgorPassword() {
		return "security/passwordRecovery";
	}

	@GetMapping("signup/candidato")
	public String signupAspirants(Model model) {
		String jpqlPais = "from Pais";
		String jpqlSexoPersona = "from SexoPersona";
		String jpqlRangoExp = "from RangoExperiencia";

		paises = (List<Pais>) service.jpqlQuery(jpqlPais);
		sexoPersona = (List<SexoPersona>) service.jpqlQuery(jpqlSexoPersona);
		rangoExperiencia = (List<RangoExperiencia>) service.jpqlQuery(jpqlRangoExp);

		model.addAttribute("paises", paises);
		model.addAttribute("generos", sexoPersona);
		model.addAttribute("rangosExp", rangoExperiencia);
		return "security/sign_aspirant";
	}

	@GetMapping("signup/company")
	public String signupCompany() {
		return "security/sign_company";
	}

	@PostMapping("countries/regions")
	public @ResponseBody List<DivisionGeografica> cargarRegiones(@RequestParam Long idPais) {
		String jpqlRegions = "from DivisionGeografica dg join fetch dg.pais country where dg.pais.idPais = :id";
		Map params = new HashMap();
		params.put("id", idPais);
		divisiones = service.jpqlParamsQuery(jpqlRegions, params);
		return divisiones;
	}

	@PostMapping("create/candidato")
	public @ResponseBody String onCreateAspiratnte(@RequestBody Map<String, Map<String, String>> params)
			throws ParseException {
		// objetos persona, usuario
		Map<String, String> persona = params.get("datosPersonales");
		Map<String, String> usuario = params.get("datosUsuario");

		// Respuesta de la peticion
		String resultado = "exito";

		// Obteniendo roles
		String jpqlRol = "from Rol rol order by rol.idRol";
		roles = service.jpqlQuery(jpqlRol);

		// Obteniendo sexo, division geografica y rango experiencia escogida
		String jpqlSexoPersona = "from SexoPersona s where s.idSexoPersona = :id";
		String jpqlDivGeo = "from DivisionGeografica dv where dv.idDivision = :id";
		String jpqlRangoExp = "from RangoExperiencia re where re.idRangoExperiencia = :id";

		SexoPersona sexoPersona = (SexoPersona) service.jpqlUniqueResult(jpqlSexoPersona,
				Long.valueOf(persona.get("genero")));
		DivisionGeografica dv = (DivisionGeografica) service.jpqlUniqueResult(jpqlDivGeo,
				Long.valueOf(persona.get("location")));
		RangoExperiencia re = (RangoExperiencia) service.jpqlUniqueResult(jpqlRangoExp,
				Long.valueOf(persona.get("rangoExp")));

		// ******************* Creando usuario ************************
		Usuario u = new Usuario();
		u.setActivo((short) 1);
		u.setBloqueado((short) 0);
		u.setFechaCreacion(new Date());
		u.setIntentosFallidos((short) 0);
		u.setNombreUsuario(usuario.get("username"));
		u.setPassword(DigestUtils.sha1Hex(usuario.get("password")));
		u.setRol(roles.get(1));
		// ********************** Usuario creado

		// ********************** Creando persona ****************************
		Persona p = new Persona();
		p.setNombrePersona(persona.get("names"));
		p.setApellidoPersona(persona.get("lastnames"));
		p.setDireccionPersona(persona.get("direccion"));
		p.setDuiPersona(persona.get("dui"));
		p.setEmailPersona(persona.get("email"));
		p.setFechaNacimientoPersona(simpleDateFormat.parse(persona.get("dateOfBirth")));
		p.setNitPersona(persona.get("nit"));
		p.setNupPersona(persona.get("nup"));
		p.setPasaportePersona(persona.get("pasaporte"));
		p.setTelefonoPersona(persona.get("telefono"));
		p.setPaisResidencia(dv);
		p.setSexoPersona(sexoPersona);
		p.setRangoExperiencia(re);
		p.setUsuario(u);
		// ************************* Persona creada **********************************

		try {
			service.createAny(u);
			service.createAny(p);
		} catch (Exception ex) {
			if (ex.getMessage().contains("UNIQUE KEY"))
				resultado = "fracaso";
			else if (ex.getMessage().contains("FOREIGN KEY"))
				resultado = "fracaso";
			else
				resultado = "fracaso";

			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
		}

		return resultado;
	}
	
	@PostMapping("create/company")
	public @ResponseBody String onCreateCompany(@RequestBody Map<String, Map<String, String>> params) {
		String response = "exito";
		
		Map<String, String> companyValues = params.get("companyData");
		Map<String, String> userValues = params.get("userData");
		
		// Obteniendo roles
		String jpqlRol = "from Rol rol order by rol.idRol";
		roles = service.jpqlQuery(jpqlRol);
		
		Empresa empresa = new Empresa();
		
		empresa.setNombreEmpresa(companyValues.get("name"));
		empresa.setTelefonoEmpresa(companyValues.get("tel"));
		empresa.setEmailEmpresa(companyValues.get("email"));
		empresa.setDireccionEmpresa(companyValues.get("dir"));
		empresa.setDescripcionEmpresa(companyValues.get("desc"));
		
		Usuario u = new Usuario();
		
		u.setActivo((short) 1);
		u.setBloqueado((short) 0);
		u.setFechaCreacion(new Date());
		u.setIntentosFallidos((short) 0);
		u.setNombreUsuario(userValues.get("username"));
		u.setRol(roles.get(0));
		u.setPassword(DigestUtils.sha1Hex(userValues.get("password")));
		
		try {
			empresa.setUsuario(u);
			service.createAny(u);
			service.createAny(empresa);
		} catch (Exception ex) {
			if (ex.getMessage().contains("UNIQUE KEY"))
				response = "fracaso";
			else if (ex.getMessage().contains("FOREIGN KEY"))
				response = "fracaso";
			else
				response = "fracaso";

			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
		}
		
		return response;
	}

	@PostMapping("checkUniqueConstraints")
	public @ResponseBody Integer doesExist(@RequestParam Map<String, String> values) {
		Integer res = null;
		Map<String,Object> param = new HashMap<String,Object>();
		
		String sql = "select checkuniqueconstraint('"+values.get("id")+"','"+values.get("value")+"') from dual";
		res = (Integer) service.getUniqueValue(sql, param);
		System.out.println(res);
		return res;
	}

}
