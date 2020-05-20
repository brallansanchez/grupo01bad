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

@Entity
@Table(name="RANGO_EXPERIENCIA")
public class RangoExperiencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_rango_exp_seq")
	@SequenceGenerator(name = "id_rango_exp_seq", sequenceName = "SEQ_RANGO_EXPERIENCIA", allocationSize = 1)
	@Column(name = "ID_RANGO_EXPERIENCA")
	@Basic(optional = false)
	private Long idRangoExperiencia;
	
	@Column(name="RANGO_EXPERIENCIA")
	@Basic(optional = false)
	private String rangoExperiencia;
	
	public RangoExperiencia() {
		
	}

	public Long getIdRangoExperiencia() {
		return idRangoExperiencia;
	}

	public void setIdRangoExperiencia(Long idRangoExperiencia) {
		this.idRangoExperiencia = idRangoExperiencia;
	}

	public String getRangoExperiencia() {
		return rangoExperiencia;
	}

	public void setRangoExperiencia(String rangoExperiencia) {
		this.rangoExperiencia = rangoExperiencia;
	}
}
