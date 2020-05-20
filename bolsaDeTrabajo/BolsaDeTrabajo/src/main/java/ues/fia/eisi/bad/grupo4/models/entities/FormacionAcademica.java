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
@Table(name="FORMACION_ACADEMICA")
public class FormacionAcademica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_formacion_seq")
	@SequenceGenerator(name="id_formacion_seq", sequenceName="SEQ_FORMACION_ACADEMICA", allocationSize=1)
	@Column(name="ID_FORMACION")
	@Basic(optional=false)
	private Long idFormacion;
	
	@Column(name="TITULO_FORMACION")
	@Basic(optional=false)
	private String tituloFormacion;
	
	@Column(name="ANIO_FORMACION")
	@Basic(optional=false)
	private Date anioFormacion;
	
	@Column(name="CODIGO_CERTIFICACION")
	@Basic(optional=true)
	private String codigoCertificacion;
	
	@ManyToOne
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_FORMACION")
	private TipoFormacion tipoFormacion;
	
	@ManyToOne
	@JoinColumn(name="ID_DETALLE_EXPERIENCIA")
	private Institucion institucion;
	
	public FormacionAcademica() {
		
	}

	public Long getIdFormacion() {
		return idFormacion;
	}

	public void setIdFormacion(Long idFormacion) {
		this.idFormacion = idFormacion;
	}

	public String getTituloFormacion() {
		return tituloFormacion;
	}

	public void setTituloFormacion(String tituloFormacion) {
		this.tituloFormacion = tituloFormacion;
	}

	public Date getAnioFormacion() {
		return anioFormacion;
	}

	public void setAnioFormacion(Date anioFormacion) {
		this.anioFormacion = anioFormacion;
	}

	public String getCodigoCertificacion() {
		return codigoCertificacion;
	}

	public void setCodigoCertificacion(String codigoCertificacion) {
		this.codigoCertificacion = codigoCertificacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoFormacion getTipoFormacion() {
		return tipoFormacion;
	}

	public void setTipoFormacion(TipoFormacion tipoFormacion) {
		this.tipoFormacion = tipoFormacion;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
}
