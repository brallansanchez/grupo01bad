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
@Table(name="DETALLE_RED_SOCIAL")
public class DetalleRedSocial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_det_red_seq")
	@SequenceGenerator(name="id_det_red_seq", sequenceName="SEQ_DETALLE_RED_SOCIAL", allocationSize=1)
	@Column(name="ID_DETALLE_RED")
	@Basic(optional=true)
	private Long idDetalleRed;
	
	@Column(name="URL_RED")
	@Basic(optional=false)
	private String urlRed;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_PERSONA")
	private Persona persona;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ID_RED_SOCIAL")
	private RedSocial redSocial;
	
	public DetalleRedSocial() {
		
	}

	public Long getIdDetalleRed() {
		return idDetalleRed;
	}

	public void setIdDetalleRed(Long idDetalleRed) {
		this.idDetalleRed = idDetalleRed;
	}

	public String getUrlRed() {
		return urlRed;
	}

	public void setUrlRed(String urlRed) {
		this.urlRed = urlRed;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public RedSocial getRedSocial() {
		return redSocial;
	}

	public void setRedSocial(RedSocial redSocial) {
		this.redSocial = redSocial;
	}
}
