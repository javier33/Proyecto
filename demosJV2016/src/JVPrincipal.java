/** 
 * Proyecto: Juego de la vida.
 * Secuencia principal de arraque del programa.
 * Implementación del control de inicio de sesión y ejecución de la simulación por defecto.  
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 2.0 - 2017/03/11 
 * @author: ajp
 */

import accesoDatos.Datos;
import accesoDatos.test.DatosPrueba;
import accesoUsr.Presentacion;

public class JVPrincipal {

	public static void main(String[] args) {	
		
		Datos fachada = new Datos();
		Presentacion presentacion = new Presentacion();
		
		//DatosPrueba.cargarUsuariosPrueba();	
		System.out.println(fachada.toStringDatosUsuarios());
		
		if (presentacion.iniciarSesionCorrecta()) {
			presentacion.arrancarSimulacion();
		}
		else {		
			System.out.println("\nDemasiados intentos fallidos...");
		}	
		System.out.println("Fin del programa.");
	}
	
} //class
