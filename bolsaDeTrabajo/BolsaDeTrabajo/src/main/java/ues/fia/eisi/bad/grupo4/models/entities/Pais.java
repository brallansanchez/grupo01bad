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
@Table(name="PAIS")
public class Pais implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pais_sequence")
	@SequenceGenerator(name = "id_pais_sequence", sequenceName="SEQ_PAIS", allocationSize = 1)
	@Column(name="ID_PAIS")
	@Basic(optional=false)
	private Long idPais;
	
	@Column(name="NOMBRE_PAIS")
	@Basic(optional=false)
	@NotEmpty
	private String nombrePais;
	
	public Pais() {
		
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
}
