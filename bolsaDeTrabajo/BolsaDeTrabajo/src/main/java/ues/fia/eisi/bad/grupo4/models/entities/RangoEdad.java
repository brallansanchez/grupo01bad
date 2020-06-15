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
@Table(name="RANGO_EDAD")
public class RangoEdad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_rango_sequence")
	@SequenceGenerator(name = "id_rango_sequence", sequenceName="SEQ_RANGO_EDAD", allocationSize = 1)
	@Column(name="ID_RANGO_EDAD")
	@Basic(optional=false)
	private Long idRangoEdad;
	
	@Column(name="RANGO_EDAD")
	@Basic(optional = false)
	@NotEmpty
	private String rangoEdad;
	
	public RangoEdad() {
		
	}

	public Long getIdRangoEdad() {
		return idRangoEdad;
	}

	public void setIdRangoEdad(Long idRangoEdad) {
		this.idRangoEdad = idRangoEdad;
	}

	public String getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(String rangoEdad) {
		this.rangoEdad = rangoEdad;
	}
}
