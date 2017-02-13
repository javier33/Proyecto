import java.util.regex.Pattern;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.
 *  En esta versión no se han aplicado la mayoría de los estándares 
 *  de diseño OO dirigidos a conseguir un "código limpio". 
 *  La implementación es básica con el fin ilustrar 
 *  cómo se evoluciona desde un "código malo".
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.  
 *  @since: prototipo1.0
 *  @source: Usuario.java 
 *  @version: 1.1 - 2017/01/21 
 *  @author: ajp
 */

public class Usuario {

	public static final String[] ROLES = {"NORMAL", "INVITADO", "ADMINISTRADOR"};
	// Apartado 2:	
	private String nif;
	private String nombre;
	private String apellidos;
	private String idUsr;
	private String domicilio;
	private String correo;
	private String fechaNacimiento;
	private String fechaAlta;
	private String claveAcceso;
	private String rol;

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
	public Usuario(String nif, String nombre, String apellidos,
			String domicilio, String correo, String fechaNacimiento,
			String fechaAlta, String claveAcceso, String rol) {
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

	// Apartado 4:
	/**
	 * Genera el idUsr con las letras iniciales del nombre, 
	 * primer y segundo apellido; seguido del el último dígito 
	 * del dni y la letra del nif. 
	 */
	private void generarIdUsr() {	
		if (nombre != null && apellidos != null && nif != null) {
			String idUsr = "" + nombre.charAt(0) + apellidos.charAt(0) +
					apellidos.charAt(apellidos.indexOf(" ")+1) +
					nif.substring(7);
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

	// Apartado 2:
	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 */
	public Usuario() {
		this("12345678Z", "Nombre", "Apellidos1 Apellido2", "Domicilio", "correo@correo.es", 
				"2016.01.27", "2016.01.27", "Miau#0", ROLES[0]);
	}

	/**
	 * Constructor copia. Utiliza constructor convencional.
	 * @param usr
	 */
	public Usuario(Usuario usr) {
		this(usr.nif, usr.nombre, usr.apellidos, usr.domicilio, usr.correo,
				usr.fechaNacimiento, usr.fechaAlta, usr.claveAcceso, usr.rol);
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		if (ClaveAccesoValida(claveAcceso)) {
			this.claveAcceso = encriptarCesar(claveAcceso);
		}
	}

	// Apartado 6:
	private boolean ClaveAccesoValida(String claveAcceso) {
		if (claveAcceso != null) {
			return	claveAcceso.matches("([\\wñÑ$*-+&!?#]){5,}");
		}
		return false;
	}

	// Apartado 5:
	private String encriptarCesar(String claveEnClaro) {
		String alfabetoNormal =     "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789?$%&#";
		String alfabetoDesplazado = "FGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789?$%&#ABCDE";
		StringBuilder claveEncriptada = new StringBuilder();
		for (int i=0; i < claveEnClaro.length(); i++) {
			int posicion = alfabetoNormal.indexOf(claveEnClaro.charAt(i));
			claveEncriptada.append(alfabetoDesplazado.charAt(posicion));
		}	
		return claveEncriptada.toString();
	}

	// Apartado 2:
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {	
		if (NombreValido(nombre)) {
			this.nombre = nombre;
		}
	}

	// Apartado 6:
	private boolean NombreValido(String nombre) {
		if (nombre != null) {
			return	nombre.matches("^[A-ZÑÁÉÍÓÚ][a-zñáéíóú]+");
		}
		return false;
	}

	// Apartado 2:
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getIdUsr() {
		return idUsr;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (CorreoValido(correo)) {
			this.correo = correo;
		}
	}

	// Apartado 6:
	private boolean CorreoValido(String correo) {
		if (correo != null) {
			return	correo.matches("^[\\w-\\+]+(\\.[\\w-\\+]+)*@"
					+ "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		}
		return false;
	}

	// Apartado 2:
	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol.toUpperCase();
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

} // class

