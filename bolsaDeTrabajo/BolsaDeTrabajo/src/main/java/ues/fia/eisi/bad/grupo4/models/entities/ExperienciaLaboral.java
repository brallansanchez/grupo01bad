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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EXPERIENCIA_LABORAL")
public class ExperienciaLaboral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_exp_seq")
	@SequenceGenerator(name="id_exp_seq",sequenceName="SEQ_EXPERIENCIA_LABORAL", allocationSize = 1)
	@Column(name="ID_EXPERIENCIA_LABORAL")
	@Basic(optional = false)
	private Long idExpLaboral;
	
	@Column(name = "PUESTO_TRABAJO_EXPERIENCIA")
	@Basic(optional = false)
	private String puestoTrabajo;
	
	@Column(name = "FECHA_INICIO")
	@Basic(optional = false)
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	@Basic(optional = false)
	private Date fechaFin;
	
	@Column(name="DESCRIPCION_FUNCIONES")
	@Basic(optional = false)
	private String funciones;
	
	@Column(name="TRABAJO_ACTUALMENTE")
	@Basic(optional = false)
	private Long actualTrabajo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_CATEGORIA_PUESTO")
	private CategoriaPuesto categoriaPuesto;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_DETALLE_EXPERIENCIA")
	private Institucion institucion;
	
	public ExperienciaLaboral() {
		
	}

	public Long getIdExpLaboral() {
		return idExpLaboral;
	}

	public void setIdExpLaboral(Long idExpLaboral) {
		this.idExpLaboral = idExpLaboral;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public Long getActualTrabajo() {
		return actualTrabajo;
	}

	public void setActualTrabajo(Long actualTrabajo) {
		this.actualTrabajo = actualTrabajo;
	}

	public CategoriaPuesto getCategoriaPuesto() {
		return categoriaPuesto;
	}

	public void setCategoriaPuesto(CategoriaPuesto categoriaPuesto) {
		this.categoriaPuesto = categoriaPuesto;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
}
