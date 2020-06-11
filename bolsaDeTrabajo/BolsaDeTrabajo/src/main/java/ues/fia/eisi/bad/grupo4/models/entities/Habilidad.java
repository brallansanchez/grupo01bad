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
@Table(name="HABILIDAD")
public class Habilidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_habilidad_seq")
	@SequenceGenerator(name = "id_habilidad_seq", sequenceName = "SEQ_HABILIDAD", allocationSize = 1)
	@Column(name = "ID_HABILIDAD")
	@Basic(optional = false)
	private Long idHabilidad;
	
	@Column(name = "NOMBRE_HABILIDAD")
	@Basic(optional = false)
	private String habilidad;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_TIPO_HABILIDAD")
	private TipoHabilidad tipoHabilidad;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_CATEGORIA_PUESTO")
	private CategoriaPuesto categoriaPuesto;
	
	public Habilidad() {
		
	}

	public Long getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(Long idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public TipoHabilidad getTipoHabilidad() {
		return tipoHabilidad;
	}

	public void setTipoHabilidad(TipoHabilidad tipoHabilidad) {
		this.tipoHabilidad = tipoHabilidad;
	}

	public CategoriaPuesto getCategoriaPuesto() {
		return categoriaPuesto;
	}

	public void setCategoriaPuesto(CategoriaPuesto categoriaPuesto) {
		this.categoriaPuesto = categoriaPuesto;
	}
}
