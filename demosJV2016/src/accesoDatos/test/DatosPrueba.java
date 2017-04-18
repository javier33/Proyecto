/** 
 * Proyecto: Juego de la vida.
 * Clase auxiliar para generar datos de prueba.
 * @since: prototipo2.1
 * @source: DatosPrueba.java 
 * @version: 2.1 - 2017/04/9 
 * @author: ajp
 */

package accesoDatos.test;

import java.util.ArrayList;
import java.util.Hashtable;
import util.Fecha;
import accesoDatos.Datos;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.Mundo;
import modelo.Nif;
import modelo.Patron;
import modelo.Posicion;
import modelo.SesionUsuario;
import modelo.SesionUsuario.EstadoSesion;
import modelo.Simulacion;
import modelo.Simulacion.EstadoSimulacion;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;

public class DatosPrueba {

	private static Datos fachada = new Datos();

	/**
	 * Genera datos de prueba en el almacén de datos.
	 */
	public static void cargarDatosPrueba() {
		cargarUsuariosPrueba();
		cargarSesionesPrueba();
		cargarMundosPrueba();
		cargarSimulacionesPrueba();
		cargarPatronesPrueba();	
	}

	/**
	 * Elimina datos de prueba y los predeterminados del almacén de datos.
	 */
	public static void borrarDatosPrueba() {
		fachada.borrarTodosUsuarios();
		fachada.borrarTodasSesiones();
		fachada.borrarTodasSimulaciones();
		fachada.borrarTodosMundos();
		fachada.borrarTodosPatrones();
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	private static void cargarUsuariosPrueba() {
		String[] NifValidos = { "00000002W", "00000003A", "00000004G", "00000005M", "00000006Y", "00000007F"};
		for (int i = 0; i < NifValidos.length ; i++) {
			Usuario usr =  new Usuario(new Nif(NifValidos[i]), "Pepe",
					"López Pérez", new DireccionPostal("Alta", "10", "30012", "Murcia"), 
					new Correo("pepe" + i + "@gmail.com"), new Fecha(2000, 11, 30), 
					new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);

			fachada.altaUsuario(usr);
		}
	}

	/**
	 * Genera dos sesiones de prueba válidas, asociadas los usuarios predeterminados.
	 */
	private static void cargarSesionesPrueba() {
		Usuario usrPrueba1 = fachada.obtenerUsuario("AAA0T");
		Usuario usrPrueba2 = fachada.obtenerUsuario("III1R");
		SesionUsuario sesionPrueba1 = new SesionUsuario(usrPrueba1, new Fecha(), EstadoSesion.CERRADA);
		SesionUsuario sesionPrueba2 = new SesionUsuario(usrPrueba2, new Fecha(), EstadoSesion.CERRADA);
		fachada.altaSesion(sesionPrueba1);
		fachada.altaSesion(sesionPrueba2);
	}

	/**
	 * Genera dos Simulaciones de prueba válidas, asociada al Usuario invitado predeterminado 
	 * con una configuración de Mundo predetermindo.
	 */
	private static void cargarSimulacionesPrueba() {
		Usuario usrPrueba = fachada.obtenerUsuario("III1R");
		Mundo mundoPrueba = fachada.obtenerMundo("MundoDemo");
		Simulacion simulacionPrueba1 = new Simulacion(usrPrueba, new Fecha(), mundoPrueba, EstadoSimulacion.PREPARADA);
		Simulacion simulacionPrueba2 = new Simulacion(usrPrueba, new Fecha(), mundoPrueba, EstadoSimulacion.PREPARADA);
		fachada.altaSimulacion(simulacionPrueba1);
		fachada.altaSimulacion(simulacionPrueba2);
	}

	/**
	 * Genera Mundo de prueba válidos dentro 
	 * de los almacenes de datos.
	 */
	private static void cargarMundosPrueba() {		
		// En este array los 0 indican celdas con célula muerta y los 1 vivas
		byte[][] espacioPrueba =  new byte[][] { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  
		};
		Mundo mundoPrueba = new Mundo("MundoPrueba", new ArrayList<Integer>(), new Hashtable<Patron,Posicion>(), espacioPrueba);
		fachada.altaMundo(mundoPrueba);
	}

	/**
	 * Genera Patron de prueba válidos dentro 
	 * del almacén de datos.
	 */
	private static void cargarPatronesPrueba() {
		byte[][] esquemaPrueba =  new byte[][]{ 
			{ 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0 }, 
			{ 1, 0, 0, 1 }, 
			{ 1, 1, 1, 1 }, 
			{ 1, 1, 0, 0 }
		};
		Patron patronPrueba = new Patron("PatronPrueba", esquemaPrueba);
		fachada.altaPatron(patronPrueba);
	}

} //class
