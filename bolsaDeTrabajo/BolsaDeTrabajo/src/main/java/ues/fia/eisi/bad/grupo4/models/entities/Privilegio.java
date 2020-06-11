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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PRIVILEGIO")
public class Privilegio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_privilegio_seq")
	@SequenceGenerator(name="id_privilegio_seq", sequenceName ="SEQ_PRIVILEGIO", allocationSize=1)
	@Column(name="ID_PRIVILEGIO")
	@Basic(optional = false)
	private Long idPrivilegio;
	
	@Column(name="PRIVILEGIO")
	@Basic(optional = false)
	private String privilegio;
	
	@Column(name="P_INSERT")
	@Basic(optional = true)
	private short insertP;
	
	@Column(name="P_EDIT")
	@Basic(optional = true)
	private short editP;
	
	@Column(name="P_SELECT")
	@Basic(optional=true)
	private short selectP;
	
	@Column(name="P_DELETE")
	@Basic(optional=true)
	private short deleteP;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_ROL")
	private Rol rol;
	
	public Privilegio() {
		
	}

	public Long getIdPrivilegio() {
		return idPrivilegio;
	}

	public void setIdPrivilegio(Long idPrivilegio) {
		this.idPrivilegio = idPrivilegio;
	}

	public String getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(String privilegio) {
		this.privilegio = privilegio;
	}

	public short getInsertP() {
		return insertP;
	}

	public void setInsertP(short insertP) {
		this.insertP = insertP;
	}

	public short getEditP() {
		return editP;
	}

	public void setEditP(short editP) {
		this.editP = editP;
	}

	public short getSelectP() {
		return selectP;
	}

	public void setSelectP(short selectP) {
		this.selectP = selectP;
	}

	public short getDeleteP() {
		return deleteP;
	}

	public void setDeleteP(short deleteP) {
		this.deleteP = deleteP;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
