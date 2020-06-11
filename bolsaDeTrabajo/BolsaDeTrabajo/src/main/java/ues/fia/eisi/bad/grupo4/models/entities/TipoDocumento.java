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
@Table(name="TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_doc_seq")
	@SequenceGenerator(name = "id_tipo_doc_seq", sequenceName = "SEQ_TIPO_DOCUMENTO", allocationSize = 1)
	@Column(name = "ID_TIPO_DOCUMENTO")
	@Basic(optional = false)
	private Long idTipoDocumento;
	
	@Column(name = "TIPO_DOCUMENTO")
	@Basic(optional = false)
	private String tipoDocumento;
	
	public TipoDocumento() {
		
	}

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
