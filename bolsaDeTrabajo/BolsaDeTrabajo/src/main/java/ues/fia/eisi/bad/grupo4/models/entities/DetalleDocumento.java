package ues.fia.eisi.bad.grupo4.models.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DETALLE_DOCUMENTO")
public class DetalleDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_det_doc_seq")
	@SequenceGenerator(name = "id_det_doc_seq", sequenceName = "SEQ_DETALLE_DOCUMENTO", allocationSize = 1)
	@Column(name = "ID_DETALLE_DOC")
	@Basic(optional  = false)
	private Long idDetDocumento;
	
	@Column(name = "ISBN")
	@Basic(optional = false)
	private String isbn;
	
	@Column(name = "EDICION_DOC")
	@Basic(optional = false)
	private String edicionDoc;
	
	@OneToOne
	@JoinColumn(name="ID_DOCUMENTO")
	private Documento documento;
	
	public DetalleDocumento(){
		
	}

	public Long getIdDetDocumento() {
		return idDetDocumento;
	}

	public void setIdDetDocumento(Long idDetDocumento) {
		this.idDetDocumento = idDetDocumento;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEdicionDoc() {
		return edicionDoc;
	}

	public void setEdicionDoc(String edicionDoc) {
		this.edicionDoc = edicionDoc;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
}
