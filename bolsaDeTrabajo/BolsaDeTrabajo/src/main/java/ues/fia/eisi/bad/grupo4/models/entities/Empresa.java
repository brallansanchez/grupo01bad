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
import javax.validation.constraints.Size;

@Entity
@Table(name="EMPRESA")
public class Empresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_EMPRESA")
	private Integer idEmpresa;
	
	@Size(max = 150)
	@Basic(optional = false)
	@Column(name = "NOMBRE_EMPRESA")
	private String nombreEmpresa;
	
	@Size(max = 25)
	@Basic(optional = false)
	@Column(name = "TELEFONO_EMPRESA")
	private String telefonoEmpresa;
	
	@Size(max = 255)
	@Basic(optional = false)
	@Column(name = "DIRECCION_EMPRESA")
	private String direccionEmpresa;
	
	@Size(max = 150)
	@Basic(optional = false)
	@Column(name = "EMAIL_EMPRESA")
	private String emailEmpresa;
	
	@Size(max = 500)
	@Column(name = "DESCRIPCION_EMPRESA")
	private String descripcionEmpresa;
	
	public Empresa() {
		
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public String getDescripcionEmpresa() {
		return descripcionEmpresa;
	}

	public void setDescripcionEmpresa(String descripcionEmpresa) {
		this.descripcionEmpresa = descripcionEmpresa;
	}
	
	
	
}
