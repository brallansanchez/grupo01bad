package ues.fia.eisi.bad.grupo4.models.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="OFERTA_LABORAL")
public class OfertaLaboral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="id_oferta_laboral_sequence")
	@SequenceGenerator(name="id_oferta_laboral_sequence", sequenceName="SEQ_OFERTA_LABORAL", allocationSize = 1)
	@Column(name="ID_OFERTAL")
	@Basic(optional=false)
	private Long idOferta;
	
	@Column(name="TITULO_OFERTA")
	@Basic(optional=false)
	private String tituloOferta;
	
	@Column(name="DESCRIPCION_OFERTA")
	@Basic(optional=false)
	private String descripcionOferta;
	
	@Column(name="FECHA_EXPIRACION_OFERTA")
	@Basic(optional=false)
	private Date fechaExp;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_CATEGORIA_PUESTO")
	private CategoriaPuesto categoria;
	
	public OfertaLaboral() {
		
	}

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getDescripcionOferta() {
		return descripcionOferta;
	}

	public void setDescripcionOferta(String descripcionOferta) {
		this.descripcionOferta = descripcionOferta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(Date fechaExp) {
		this.fechaExp = fechaExp;
	}

	public CategoriaPuesto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPuesto categoria) {
		this.categoria = categoria;
	}
	
}

