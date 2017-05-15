/** 
 * Proyecto: Juego de la vida.
 * Maneja los errores de las clases de utilidad.
 *  @since: prototipo2.1
 *  @source: UtilException.java 
 * @version: 2.1 - 2017.04.5
 *  @author: ajp
 */

package util;

public class UtilException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Excepción por defecto sin mensaje
	 */
	public UtilException() {
		super();
	}
	
	/**
	 * Excepción con mensaje
	 * @param msg - el mensaje de error asociado
	 */
	public UtilException(String msg) {
		super(msg);
	}
}
