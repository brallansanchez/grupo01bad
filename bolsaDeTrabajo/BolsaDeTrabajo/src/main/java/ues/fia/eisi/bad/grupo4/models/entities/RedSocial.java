package ues.fia.eisi.bad.grupo4.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="RED_SOCIAL")
public class RedSocial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_red_social_seq")
	@SequenceGenerator(name="id_red_social_seq", sequenceName="SEQ_RED_SOCIAL", allocationSize=1)
	@Column(name="ID_RED_SOCIAL")
	@Basic(optional = false)
	private Long idRedSocial;
	
	@Column(name="NOMBRE_RED_SOCIAL")
	@Basic(optional=false)
	@NotEmpty
	private String nombreRedSocial;
	
	public RedSocial() {
		
	}

	public Long getIdRedSocial() {
		return idRedSocial;
	}

	public void setIdRedSocial(Long idRedSocial) {
		this.idRedSocial = idRedSocial;
	}

	public String getNombreRedSocial() {
		return nombreRedSocial;
	}

	public void setNombreRedSocial(String nombreRedSocial) {
		this.nombreRedSocial = nombreRedSocial;
	}
}
