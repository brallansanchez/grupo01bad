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
@Table(name = "TIPO_CONTRATACION")
public class TipoContrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_contrato_seq")
	@SequenceGenerator(name = "id_tipo_contrato_seq", sequenceName = "SEQ_TIPO_CONTRATACION", allocationSize = 1)
	@Column(name = "ID_TIPO_CONTRATO")
	@Basic(optional = false)
	private Long idTipoContrato;
	
	@Column(name="NOMBRE_TIPO_CONTRATO")
	@Basic(optional = false)
	@NotEmpty
	private String nombreTipoContrato;
	
	public TipoContrato() {
		
	}

	public Long getIdTipoContrato() {
		return idTipoContrato;
	}

	public void setIdTipoContrato(Long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	public String getNombreTipoContrato() {
		return nombreTipoContrato;
	}

	public void setNombreTipoContrato(String nombreTipoContrato) {
		this.nombreTipoContrato = nombreTipoContrato;
	}
}
