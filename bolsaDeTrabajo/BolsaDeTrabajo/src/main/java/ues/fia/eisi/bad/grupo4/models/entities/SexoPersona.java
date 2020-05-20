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
@Table(name = "SEXO_PERSONA")
public class SexoPersona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sexo_sequence")
	@SequenceGenerator(name = "id_sexo_sequence", sequenceName = "SEX_SEXO_PERSONA", allocationSize = 1)
	@Column(name = "ID_SEXO_PERSONA")
	@Basic(optional = false)
	private Long idSexoPersona;
	
	@Column(name="SEXO_PERSONA")
	@Basic(optional = false)
	private String sexoPersona;
	
	public SexoPersona() {
		
	}

	public Long getIdSexoPersona() {
		return idSexoPersona;
	}

	public void setIdSexoPersona(Long idSexoPersona) {
		this.idSexoPersona = idSexoPersona;
	}

	public String getSexoPersona() {
		return sexoPersona;
	}

	public void setSexoPersona(String sexoPersona) {
		this.sexoPersona = sexoPersona;
	}
}
