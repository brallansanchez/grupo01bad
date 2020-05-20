package ues.fia.eisi.bad.grupo4.models.entities;

import java.io.Serializable;

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
@Table(name="INSTITUCION")
public class Institucion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_institucion_seq")
	@SequenceGenerator(name="id_institucion_seq", sequenceName="SEQ_INSTITUCION", allocationSize=1)
	@Column(name="ID_DETALLE_EXPERIENCIA")
	@Basic(optional=false)
	private Long idInstitucion;
	
	@Column(name="NOMBRE_ORGANIZACION")
	@Basic(optional=false)
	private String nombreInstitucion;
	
	@Column(name="TEL_ORGANIZACION")
	@Basic(optional=false)
	private String telefono;
	
	@Column(name="EMAIL_ORGANIZACION")
	@Basic(optional=false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_INSTITUCION")
	private TipoInstitucion tipoInstitucion;
	
	public Institucion() {
		
	}

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoInstitucion getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(TipoInstitucion tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}
}
