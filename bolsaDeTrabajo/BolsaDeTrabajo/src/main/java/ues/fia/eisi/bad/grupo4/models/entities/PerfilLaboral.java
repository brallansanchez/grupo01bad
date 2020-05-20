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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PERFIL_LABORAL")
public class PerfilLaboral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_perfil_sequence")
	@SequenceGenerator(name="id_perfil_sequence", sequenceName="SEQ_PERFIL_LABORAL", allocationSize = 1)
	@Column(name="ID_PERFIL")
	@Basic(optional = false)
	private Long idPerfilLaboral;
	
	@Column(name="AREA_EMPRESA_PERFIL")
	@Basic(optional = false)
	private String areaEmpresarial;
	
	@Column(name="CARGO_SOLICITADO")
	@Basic(optional = false)
	private String cargoSolicitado;
	
	@Column(name="RANGO_SALARIAL")
	@Basic(optional=true)
	private String rangoSalarial;
	
	@ManyToOne
	@JoinColumn(name="ID_DIVISION")
	private DivisionGeografica divisionGeografica;
	
	@ManyToOne
	@JoinColumn(name="ID_RANGO_EXPERIENCA")
	private RangoExperiencia rangoExperiencia;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_CONTRATO")
	private TipoContrato tipoContrato;
	
	@ManyToOne
	@JoinColumn(name="ID_SEXO_PERSONA")
	private SexoPersona sexoPersona;
	
	@ManyToOne
	@JoinColumn(name="ID_RANGO_EDAD")
	private RangoEdad rangoEdad;
	
	@OneToOne
	@JoinColumn(name="ID_OFERTAL")
	private OfertaLaboral ofertaLaboral;
	
	public PerfilLaboral() {
		
	}

	public Long getIdPerfilLaboral() {
		return idPerfilLaboral;
	}

	public void setIdPerfilLaboral(Long idPerfilLaboral) {
		this.idPerfilLaboral = idPerfilLaboral;
	}

	public String getAreaEmpresarial() {
		return areaEmpresarial;
	}

	public void setAreaEmpresarial(String areaEmpresarial) {
		this.areaEmpresarial = areaEmpresarial;
	}

	public String getCargoSolicitado() {
		return cargoSolicitado;
	}

	public void setCargoSolicitado(String cargoSolicitado) {
		this.cargoSolicitado = cargoSolicitado;
	}

	public String getRangoSalarial() {
		return rangoSalarial;
	}

	public void setRangoSalarial(String rangoSalarial) {
		this.rangoSalarial = rangoSalarial;
	}

	public DivisionGeografica getDivisionGeografica() {
		return divisionGeografica;
	}

	public void setDivisionGeografica(DivisionGeografica divisionGeografica) {
		this.divisionGeografica = divisionGeografica;
	}

	public RangoExperiencia getRangoExperiencia() {
		return rangoExperiencia;
	}

	public void setRangoExperiencia(RangoExperiencia rangoExperiencia) {
		this.rangoExperiencia = rangoExperiencia;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public SexoPersona getSexoPersona() {
		return sexoPersona;
	}

	public void setSexoPersona(SexoPersona sexoPersona) {
		this.sexoPersona = sexoPersona;
	}

	public RangoEdad getRangoEdad() {
		return rangoEdad;
	}

	public void setRangoEdad(RangoEdad rangoEdad) {
		this.rangoEdad = rangoEdad;
	}

	public OfertaLaboral getOfertaLaboral() {
		return ofertaLaboral;
	}

	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}
	
}
