import java.util.Date;

/** 
 * Proyecto: Juego de la vida.
 *  Implementa el concepto de SesionUsuario según el modelo1
 *  En esta versión no se han aplicado la mayoría de los estándares 
 *  de diseño OO dirigidos a conseguir un "código limpio". 
 *  La implementación es básica con el fin ilustrar 
 *  cómo se evoluciona desde un "código malo".
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Clase sólo de datos.
 *  @since: prototipo1.0
 *  @source: SesionUsuario.java 
 *  @version: 1.1 - 2017/01/21 
 *  @author: ajp
 */

public class SesionUsuario {
	
	// Apartado 2:	
	private Usuario usr;   //materializa la relación de composición del modelo UML
	private String fecha; 

	
	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param usr
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, String fecha) {
		setUsr(usr);
		setFecha(fecha);
	}

	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 */
	public SesionUsuario() {
		this(new Usuario(), new Date().toString());
	}
	
	/**
	 * Constructor copia. Utiliza constructor convencional.
	 * @param sesion
	 */
	public SesionUsuario(SesionUsuario sesion) {
		this(sesion.usr, new String(sesion.fecha));
	}
	
	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
			this.fecha = fecha;	
	}

	// Apartado 3:
	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado (valores de atributos) 
	 * de objeto de la clase SesionUsuario  
	 */
	@Override
	public String toString() {
		return usr.toString() 
				+ String.format("%-16s %s\n", "fecha:", fecha);	
	}
	
} // class
