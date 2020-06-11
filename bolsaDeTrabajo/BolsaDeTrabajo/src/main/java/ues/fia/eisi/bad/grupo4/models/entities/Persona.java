package ues.fia.eisi.bad.grupo4.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PERSONA")
public class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_persona")
	@SequenceGenerator(name = "id_sequence_persona", sequenceName="SEQ_PERSONA",allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "ID_PERSONA")
	private Long idPersona;
	
	@Basic(optional = false)
	@Column(name = "NOMBRE_PERSONA")
	private String nombrePersona;
	
	@Basic(optional = false)
	@Column(name = "APELLIDO_PERSONA")
	private String apellidoPersona;
	

	@Basic(optional = false)
	@Column(name = "EMAIL_PERSONA")
	private String emailPersona;
	

	@Basic(optional = false)
	@Column(name = "FECHA_NACIMIENTO_PERSONA")
	private Date fechaNacimientoPersona;
	

	@Basic(optional = true)
	@Column(name = "DUI_PERSONA")
	private String duiPersona;
	
	@Basic(optional = true)
	@Column(name = "NIT_PERSONA")
	private String nitPersona;
	

	@Basic(optional = true)
	@Column(name = "PASAPORTE_PERSONA")
	private String pasaportePersona;
	

	@Basic(optional = true)
	@Column(name = "NUP_PERSONA")
	private String nupPersona;
	

	@Basic(optional = false)
	@Column(name = "DIRECCION_PERSONA")
	private String direccionPersona;
	

	@Basic(optional = false)
	@Column(name = "TELEFONO_PERSONA")
	private String telefonoPersona;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_SEXO_PERSONA")
	private SexoPersona sexoPersona;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_DIVISION")
	private DivisionGeografica paisResidencia;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_RANGO_EXPERIENCA")
	private RangoExperiencia rangoExperiencia;
	
	@OneToOne(optional = false)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	public Persona() {
		
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getApellidoPersona() {
		return apellidoPersona;
	}

	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}

	public String getEmailPersona() {
		return emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}

	public Date getFechaNacimientoPersona() {
		return fechaNacimientoPersona;
	}

	public void setFechaNacimientoPersona(Date fechaNacimientoPersona) {
		this.fechaNacimientoPersona = fechaNacimientoPersona;
	}

	public String getDuiPersona() {
		return duiPersona;
	}

	public void setDuiPersona(String duiPersona) {
		this.duiPersona = duiPersona;
	}

	public String getNitPersona() {
		return nitPersona;
	}

	public void setNitPersona(String nitPersona) {
		this.nitPersona = nitPersona;
	}

	public String getPasaportePersona() {
		return pasaportePersona;
	}

	public void setPasaportePersona(String pasaportePersona) {
		this.pasaportePersona = pasaportePersona;
	}

	public String getNupPersona() {
		return nupPersona;
	}

	public void setNupPersona(String nupPersona) {
		this.nupPersona = nupPersona;
	}

	public String getDireccionPersona() {
		return direccionPersona;
	}

	public void setDireccionPersona(String direccionPersona) {
		this.direccionPersona = direccionPersona;
	}

	public String getTelefonoPersona() {
		return telefonoPersona;
	}

	public void setTelefonoPersona(String telefonoPersona) {
		this.telefonoPersona = telefonoPersona;
	}

	public SexoPersona getSexoPersona() {
		return sexoPersona;
	}

	public void setSexoPersona(SexoPersona sexoPersona) {
		this.sexoPersona = sexoPersona;
	}

	public DivisionGeografica getPaisResidencia() {
		return paisResidencia;
	}

	public void setPaisResidencia(DivisionGeografica paisResidencia) {
		this.paisResidencia = paisResidencia;
	}

	public RangoExperiencia getRangoExperiencia() {
		return rangoExperiencia;
	}

	public void setRangoExperiencia(RangoExperiencia rangoExperiencia) {
		this.rangoExperiencia = rangoExperiencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

