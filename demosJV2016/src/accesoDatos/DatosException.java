/** 
 * Proyecto: Juego de la vida.
 * Maneja los errores de acceso a datos.
 *  @since: prototipo2.1
 *  @source: DatosException.java 
 *  @version: 2.1 - 2017.04.5
 *  @author: ajp
 */

package accesoDatos;

public class DatosException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Excepción por defecto sin mensaje
	 */
	public DatosException() {
		super();
	}
	
	/**
	 * Excepción con mensaje
	 * @param msg - el mensaje de error asociado
	 */
	public DatosException(String msg) {
		super(msg);
	}
}
