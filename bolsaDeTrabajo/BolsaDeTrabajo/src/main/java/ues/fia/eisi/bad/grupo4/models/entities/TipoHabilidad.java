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
@Table(name="TIPO_HABILIDAD")
public class TipoHabilidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_hab_seq")
	@SequenceGenerator(name = "id_tipo_hab_seq", sequenceName = "SEQ_TIPO_HABILIDAD", allocationSize = 1)
	@Column(name = "ID_TIPO_HABILIDAD")
	@Basic(optional = false)
	private Long idTipoHabilidad;
	
	@Column(name = "TIPO_HABILIDAD")
	@Basic(optional = false)
	private String TipoHabilidad;
	
	public TipoHabilidad() {
		
	}

	public Long getIdTipoHabilidad() {
		return idTipoHabilidad;
	}

	public void setIdTipoHabilidad(Long idTipoHabilidad) {
		this.idTipoHabilidad = idTipoHabilidad;
	}

	public String getTipoHabilidad() {
		return TipoHabilidad;
	}

	public void setTipoHabilidad(String tipoHabilidad) {
		TipoHabilidad = tipoHabilidad;
	}
}
