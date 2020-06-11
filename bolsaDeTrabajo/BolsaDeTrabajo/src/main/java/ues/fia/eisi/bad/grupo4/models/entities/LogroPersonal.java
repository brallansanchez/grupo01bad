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
@Table(name="LOGRO_PERSONAL")
public class LogroPersonal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_logro_personal_seq")
	@SequenceGenerator(name = "id_logro_personal_seq", sequenceName = "SEQ_LOGRO", allocationSize = 1)
	@Column(name="ID_LOGRO")
	@Basic(optional = false)
	private Long idLogro;
	
	@Column(name = "LOGRO_PERSONAL")
	@Basic(optional=false)
	private String logro;
	
	@Column(name = "DESCRIPCION_LOGRO")
	@Basic(optional = false)
	private String descripcion;
	
	@Column(name = "FECHA_LOGRO")
	@Basic(optional = false)
	private Date fecha;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	public LogroPersonal() {
		
	}

	public Long getIdLogro() {
		return idLogro;
	}

	public void setIdLogro(Long idLogro) {
		this.idLogro = idLogro;
	}

	public String getLogro() {
		return logro;
	}

	public void setLogro(String logro) {
		this.logro = logro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
