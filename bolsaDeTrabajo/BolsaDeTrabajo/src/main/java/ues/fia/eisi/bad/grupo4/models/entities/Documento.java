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
@Table(name="DOCUMENTO")
public class Documento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_doc_seq")
	@SequenceGenerator(name="id_doc_seq", sequenceName="SEQ_DOCUMENTO",	allocationSize=1)
	@Column(name="ID_DOCUMENTO")
	@Basic(optional=false)
	private Long idDocumento;
	
	@Column(name="TITULO_DOCUMENTO")
	@Basic(optional=false)
	private String tituloDocumento;
	
	@Column(name="FECHA_PUBLICACION")
	@Basic(optional=false)
	private Date fechaPublicacion;
	
	@Column(name="LUGAR_PUBLICACION")
	@Basic(optional=false)
	private String lugarPublicacion;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;
	
	@OneToOne(mappedBy = "documento")
	private DetalleDocumento detDocumento;
	
	public Documento() {
		
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getTituloDocumento() {
		return tituloDocumento;
	}

	public void setTituloDocumento(String tituloDocumento) {
		this.tituloDocumento = tituloDocumento;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getLugarPublicacion() {
		return lugarPublicacion;
	}

	public void setLugarPublicacion(String lugarPublicacion) {
		this.lugarPublicacion = lugarPublicacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public DetalleDocumento getDetDocumento() {
		return detDocumento;
	}

	public void setDetDocumento(DetalleDocumento detDocumento) {
		this.detDocumento = detDocumento;
	}
}
