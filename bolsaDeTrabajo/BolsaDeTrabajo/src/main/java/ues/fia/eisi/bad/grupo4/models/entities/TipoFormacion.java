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
@Table(name="TIPO_FORMACION_ACADEMICA")
public class TipoFormacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_form_seq")
	@SequenceGenerator(name="id_tipo_form_seq", sequenceName="SEQ_TIPO_FORMACION_ACADEMICA", allocationSize=1)
	@Column(name="ID_TIPO_FORMACION")
	@Basic(optional=false)
	private Long idTipoFormacion;
	
	@Column(name="TIPO_FORMACION")
	@Basic(optional=false)
	private String tipoFormacion;
	
	public TipoFormacion() {
		
	}

	public Long getIdTipoFormacion() {
		return idTipoFormacion;
	}

	public void setIdTipoFormacion(Long idTipoFormacion) {
		this.idTipoFormacion = idTipoFormacion;
	}

	public String getTipoFormacion() {
		return tipoFormacion;
	}

	public void setTipoFormacion(String tipoFormacion) {
		this.tipoFormacion = tipoFormacion;
	}
}
