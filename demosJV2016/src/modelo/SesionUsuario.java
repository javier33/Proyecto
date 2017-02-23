package modelo;

/** 
 * Proyecto: Juego de la vida.
 *  Implementa el concepto de sesion que mantiene un usuario en el sistema según el modelo1.2
 *  @since: prototipo1.0
 *  @source: SesionUsuario.java 
 *  @version: 1.2 - 2017/02/16
 *  @author: ajp
 */

import util.Fecha;

public class SesionUsuario {
	
	// Atributos	
	private Usuario usr;   
	private Fecha fecha; 
	
	// Constructores
	
	/**
	 * @param usr
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, Fecha fecha) {
		setUsr(usr);
		setFecha(fecha);
	}
	
	public SesionUsuario(){
		this(new Usuario(), new Fecha());
	}

	public SesionUsuario(SesionUsuario su){
		this(new Usuario(su.usr), new Fecha(su.fecha));
	}
	
	
	// Métodos de acceso
	
	public Usuario getUsr() {
		return usr;
	}
	
	public Fecha getFecha() {
		return fecha;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}
	
	public void setFecha(Fecha fecha) {
		assert fechaSesionValida(fecha);
		this.fecha = fecha;
	}

	/**
	 * Comprueba validez de una fecha.
	 * @param fecha.
	 * @return true si cumple.
	 */
	private boolean fechaSesionValida(Fecha fecha) {
		if (fecha != null
				&& fechaSesionCoherente(fecha)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba coherencia de una fecha de sesión.
	 * @param fecha.
	 * @return true si cumple.
	 */
	private boolean fechaSesionCoherente(Fecha fecha) {
		// Comprueba que fechaSesion no es, por ejemplo, del futuro
		// --Pendiente--
		return true;
	}
	
	// Métodos redefinidos
	
	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado (valores de atributos) 
	 * del objeto de la clase SesionUsuario  
	 */
	@Override
	public String toString() {
		return  "\n" + usr
				+ String.format("\n fecha: \t\t%s", fecha);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((usr == null) ? 0 : usr.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * primero invoca al método hashcode y luego el equals.
	 * @return falso si no cumple las condiciones.
	*/
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (usr.equals(((SesionUsuario)obj).usr) &&
					fecha.equals(((SesionUsuario)obj).fecha) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Genera un clon del propio objeto realizando una copia profunda.
	 * @return el objeto clonado.
	*/
	public SesionUsuario clone() {
		// Utiliza el constructor copia.
		return new SesionUsuario(this);
	}
	
} // class
