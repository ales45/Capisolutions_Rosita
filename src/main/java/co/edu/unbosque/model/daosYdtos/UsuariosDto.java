/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unbosque.model.daosYdtos;

/**
 *
 * @author algam
 */
public class UsuariosDto {
	int idUsuario;
	String usuario;
	String contraseña;
	String acceso;
	
	
	public UsuariosDto() {
		
	}
	
	public UsuariosDto(String usuario,String contraseña,String acceso) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.acceso = acceso;
	}
	
	//setters y getters

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
    
}
    