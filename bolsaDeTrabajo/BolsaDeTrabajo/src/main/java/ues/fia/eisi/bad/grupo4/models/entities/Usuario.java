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
@Table(name="USUARIO")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario_seq")
	@SequenceGenerator(name = "id_usuario_seq", sequenceName = "SEQ_USUARIO", allocationSize = 1)
	@Column(name="ID_USUARIO")
	@Basic(optional = false)
	private Long idUsuario;
	
	@Column(name = "NOMBRE_USUARIO")
	@Basic(optional=false)
	private String nombreUsuario;
	
	@Column(name = "CONTRASENIA_USUARIO")
	@Basic(optional = false)
	private String password;
	
	@Column(name = "ACTIVO")
	@Basic(optional = false)
	private short activo;
	
	@Column(name = "FECHA_CREACION")
	@Basic(optional = true)
	private Date fechaCreacion;
	
	@Column(name = "BLOQUEADO")
	@Basic(optional = false)
	private short bloqueado;
	
	@Column(name = "FECHA_BLOQUEO")
	@Basic(optional = true)
	private Date fechaBloqueo;
	
	@Column(name = "INTENTOS_FALLIDOS")
	@Basic(optional = true)
	private short intentosFallidos;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ID_ROL")
	private Rol rol;
	
	public Usuario() {
		
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getActivo() {
		return activo;
	}

	public void setActivo(short activo) {
		this.activo = activo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public short getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(short bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Date getFechaBloqueo() {
		return fechaBloqueo;
	}

	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	public short getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(short intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
