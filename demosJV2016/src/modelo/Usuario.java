package modelo;

import modelo.Usuario.RolUsuario;
import util.Fecha;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario de un sistema según el modelo1.2 
 *  @since: prototipo1.0
 *  @source: Usuario.java 
 *  @version: 1.2 - 2017/02/16 
 *  @author: ajp
 */

public class Usuario {

	public enum RolUsuario {
		INVITADO, 
		NORMAL, 
		ADMINISTRADOR
	}
	
	private Nif nif;
	private String nombre;
	private String apellidos;
	private String idUsr;
	private DireccionPostal domicilio;
	private Correo correo;
	private Fecha fechaNacimiento;
	private Fecha fechaAlta;
	private ClaveAcceso claveAcceso;
	private RolUsuario rol;

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param nif
	 * @param nombre
	 * @param apellidos
	 * @param domicilio
	 * @param correo
	 * @param fechaNacimiento
	 * @param fechaAlta
	 * @param claveAcceso
	 * @param rol
	 */
	public Usuario(Nif nif, String nombre, String apellidos,
			DireccionPostal domicilio, Correo correo, Fecha fechaNacimiento,
			Fecha fechaAlta, ClaveAcceso claveAcceso, RolUsuario rol) {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		generarIdUsr();
		setDomicilio(domicilio);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
	}

	/**
	 * Genera el idUsr con las letras iniciales del nombre, 
	 * primer y segundo apellido; seguido del el último dígito 
	 * del dni y la letra del nif. 
	 */
	private void generarIdUsr() {	
		if (nombre != null && apellidos != null && nif != null) {
			String idUsr = "" + nombre.charAt(0) + apellidos.charAt(0) +
					apellidos.charAt(apellidos.indexOf(" ")+1) +
					nif.getTexto().substring(7);
			this.idUsr = idUsr;
		}
	}

	/**
	 * Genera una variante cambiando la última letra del idUsr 
	 * por la siguiente en el alfabeto.
	 */
	public void generarVarianteIdUsr() {
		String alfabetoNif = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		String letraNueva;
		if (idUsr != null) {
			String letraAnterior = "" + idUsr.charAt(4);
			if (idUsr.charAt(4) == 'Z') {
				letraNueva = "" + 'A';
			}
			else {
				letraNueva = "" + alfabetoNif.charAt(alfabetoNif.indexOf(letraAnterior) + 1);
			}
			idUsr = idUsr.substring(0, 4) + letraNueva;
		}
	}

	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 */
	public Usuario() {
		this(new Nif(), "Nombre", "Apellidos1 Apellido2", new DireccionPostal(), new Correo(), 
				new Fecha(), new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);
	}

	/**
	 * Constructor copia. Utiliza constructor convencional.
	 * @param usr
	 */
	public Usuario(Usuario usr) {
		this(new Nif(usr.nif), usr.nombre, usr.apellidos, usr.domicilio, usr.correo,
				usr.fechaNacimiento, usr.fechaAlta, new ClaveAcceso(usr.claveAcceso), usr.rol);
	}

	public ClaveAcceso getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(ClaveAcceso claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {	
		if (NombreValido(nombre)) {
			this.nombre = nombre;
		}
	}

	private boolean NombreValido(String nombre) {
		if (nombre != null) {
			return	nombre.matches("^[A-ZÑÁÉÍÓÚ][a-zñáéíóú]+");
		}
		return false;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Nif getNif() {
		return nif;
	}

	public void setNif(Nif nif) {
		this.nif = nif;
	}

	public String getIdUsr() {
		return idUsr;
	}

	public DireccionPostal getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DireccionPostal domicilio) {
		this.domicilio = domicilio;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Comprueba validez de una fecha de alta.
	 * @param fechaAlta.
	 * @return true si cumple.
	 */
	private boolean fechaNacimientoValida(Fecha fechaAlta) {
		if (fechaAlta != null
				&& fechaAltaCoherente(fechaAlta)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba coherencia de una fecha de alta.
	 * @param fechaAlta.
	 * @return true si cumple.
	 */
	private boolean fechaNacimientoCoherente(Fecha fechaAlta) {
		// Comprueba que fechaAlta no es, por ejemplo, del futuro
		// --Pendiente--
		return true;
	}

	public Correo getCorreo() {
		return correo;
	}

	public void setCorreo(Correo correo) {	
			this.correo = correo;
	}


	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Fecha fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Comprueba validez de una fecha de alta.
	 * @param fechaAlta.
	 * @return true si cumple.
	 */
	private boolean fechaAltaValida(Fecha fechaAlta) {
		if (fechaAlta != null
				&& fechaAltaCoherente(fechaAlta)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba coherencia de una fecha de alta.
	 * @param fechaAlta.
	 * @return true si cumple.
	 */
	private boolean fechaAltaCoherente(Fecha fechaAlta) {
		// Comprueba que fechaAlta no es, por ejemplo, del futuro
		// --Pendiente--
		return true;
	}

	public RolUsuario getRol() {
		return rol;
	}


	public void setRol(RolUsuario rol) {
		assert rol != null;
		this.rol = rol;
	}
	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado -valores de atributos- de objeto de la clase Usuario.  
	 */
	@Override
	public String toString() {
		return String.format(
				"%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n"
						+ "%-16s %s\n", 
						"nif:", nif, "nombre:", nombre, "apellidos:", apellidos, "idUsr:", idUsr, 
						"domicilio:", domicilio, "correo:", correo, "fechaNacimiento:", fechaNacimiento, 
						"fechaAlta:", fechaAlta, "claveAcceso:", claveAcceso, "rol:", rol);		
	}
	
	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * primero invoca al método hashcode y luego el equals.
	 * @return falso si no cumple las condiciones.
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (nif.equals(((Usuario)obj).nif) &&
					nombre.equals(((Usuario)obj).nombre) &&
					apellidos.equals(((Usuario)obj).apellidos) &&
					idUsr.equals(((Usuario)obj).idUsr) &&
					domicilio.equals(((Usuario)obj).domicilio) &&
					correo.equals(((Usuario)obj).correo) &&
					fechaNacimiento.equals(((Usuario)obj).fechaNacimiento) &&
					fechaAlta.equals(((Usuario)obj).fechaAlta) &&
					claveAcceso.equals(((Usuario)obj).claveAcceso) &&
					rol.equals(((Usuario)obj).rol) 
					) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result
				+ ((claveAcceso == null) ? 0 : claveAcceso.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result
				+ ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result
				+ ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result
				+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((idUsr == null) ? 0 : idUsr.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}

	/**
	 * Genera un clon del propio objeto realizando una copia profunda.
	 * @return el objeto clonado.
	*/
	public Usuario clone() {
		// Utiliza el constructor copia.
		return new Usuario(this);
	}
	
} // class

