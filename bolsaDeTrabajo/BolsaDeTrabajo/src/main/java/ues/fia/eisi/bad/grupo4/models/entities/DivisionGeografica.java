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
@Table(name="DIVISION_GEOGRAFICA")
public class DivisionGeografica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_division_geo_seq")
	@SequenceGenerator(name="id_division_geo_seq", sequenceName="SEQ_DIVISION_GEOGRAFICA", allocationSize = 1)
	@Column(name="ID_DIVISION")
	@Basic(optional=false)
	private Long idDivision;
	
	@Column(name="NOMBRE_DIVISION")
	@Basic(optional=false)
	private String nombreDivision;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_PAIS")
	private Pais pais;
	
	public DivisionGeografica() {
		
	}

	public Long getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(Long idDivision) {
		this.idDivision = idDivision;
	}

	public String getNombreDivision() {
		return nombreDivision;
	}

	public void setNombreDivision(String nombreDivision) {
		this.nombreDivision = nombreDivision;
	}

	public Pais getIdPais() {
		return pais;
	}

	public void setIdPais(Pais idPais) {
		this.pais = idPais;
	}
	
}
