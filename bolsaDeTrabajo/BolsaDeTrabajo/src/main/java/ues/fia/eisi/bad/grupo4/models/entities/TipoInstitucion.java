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
@Table(name="TIPO_INSTITUCION")
public class TipoInstitucion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_institucion_seq")
	@SequenceGenerator(name="id_tipo_institucion_seq", sequenceName="SEQ_TIPO_INSTITUCION", allocationSize=1)
	@Column(name="ID_TIPO_INSTITUCION")
	@Basic(optional=false)
	private Long idTipoInstitucion;
	
	@Column(name="TIPO_INSTITUCION")
	@Basic(optional=false)
	private String tipoInstitucion;
	
	public TipoInstitucion() {
		
	}

	public Long getIdTipoInstitucion() {
		return idTipoInstitucion;
	}

	public void setIdTipoInstitucion(Long idTipoInstitucion) {
		this.idTipoInstitucion = idTipoInstitucion;
	}

	public String getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(String tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}
}
