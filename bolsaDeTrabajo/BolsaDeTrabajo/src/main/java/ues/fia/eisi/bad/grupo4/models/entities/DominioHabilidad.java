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
@Table(name = "DOMINIO_HABILIDAD")
public class DominioHabilidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_dominio_hab_seq")
	@SequenceGenerator(name = "id_dominio_hab_seq", sequenceName = "SEQ_DOMINIO_HABILIDAD", allocationSize = 1)
	@Column(name = "ID_DOMINIO_HABILIDAD")
	@Basic(optional = false)
	private Long idDominioHabilidad;
	
	@Column(name = "DOMINIO_HABILIDAD")
	@Basic(optional = false)
	private String dominioHabilidad;
	
	public DominioHabilidad() {
		
	}

	public Long getIdDominioHabilidad() {
		return idDominioHabilidad;
	}

	public void setIdDominioHabilidad(Long idDominioHabilidad) {
		this.idDominioHabilidad = idDominioHabilidad;
	}

	public String getDominioHabilidad() {
		return dominioHabilidad;
	}

	public void setDominioHabilidad(String dominioHabilidad) {
		this.dominioHabilidad = dominioHabilidad;
	}
}
