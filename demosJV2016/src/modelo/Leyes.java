/** Proyecto: Juego de la vida.
 *  Establece los métodos que debe implementar la clase Mundo según el modelo 2.
 *  @since: prototipo2.0
 *  @source: Leyes.java 
 *  @version: 2.0 - 2017.03.11
 *  @author: ajp
 */

package modelo;

public interface Leyes {
	/**
	 * Establece la manera en que actualiza el estado de un mundo. 
	 * Responde a la regla: El tiempo transcurre y se producen cambios...
	 */
	void actualizarMundo();
}
