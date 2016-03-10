package modelo;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
	private long id; //Identificador numerico del usuario
	private String nombre; //nombre del usuario
	private String apellido;//appellidos del usuario
	private String email;//su direccion de correo electronico
	
	public Usuario() {}//Para construir un usuario vacio
	public Usuario(Usuario us) {
	   this.setNombre(us.getNombre());
	   this.setApellido(us.getApellido());
	   this.setEmail(us.getEmail());
	}
//	public Usuario(String nombre, String apellido, String email){
//		this.nombre = nombre;
//		this.apellido = apellido;
//		this.email = email;
//	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellidos) {
		this.apellido = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return nombre + " " + apellido + " - " + email;
	}
}
