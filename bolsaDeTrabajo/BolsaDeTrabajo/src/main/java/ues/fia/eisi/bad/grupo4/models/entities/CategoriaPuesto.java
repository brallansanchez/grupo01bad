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
import javax.validation.constraints.Size;

@Entity
@Table(name="CATEGORIA_PUESTO")
public class CategoriaPuesto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_categoria_puesto_sequence")
	@SequenceGenerator(name="id_categoria_puesto_sequence", sequenceName="SEQ_CATEGORIA_PUESTO", allocationSize=1)
	@Column(name="ID_CATEGORIA_PUESTO")
	@Basic(optional=false)
	private Long idCategoriaPuesto;
	
	@Column(name="NOMBRE_CATEGORIA_PUESTO")
	@Basic(optional=false)
	@NotEmpty
	@Size(min=3, max=35)
	private String nombreCategoriaPuesto;
	
	public CategoriaPuesto() {
		
	}

	public Long getIdCategoriaPuesto() {
		return idCategoriaPuesto;
	}

	public void setIdCategoriaPuesto(Long idCategoriaPuesto) {
		this.idCategoriaPuesto = idCategoriaPuesto;
	}

	public String getNombreCategoriaPuesto() {
		return nombreCategoriaPuesto;
	}

	public void setNombreCategoriaPuesto(String nombreCategoriaPuesto) {
		this.nombreCategoriaPuesto = nombreCategoriaPuesto;
	}

}
