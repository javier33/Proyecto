/** 
 * Proyecto: Juego de la vida.
 * Pruebas iniciales de las clases Usuario y SesionUsuario del modelo1.
 * Implementación del control de inicio de sesión y ejecución de la simulación por defecto. 
 * En esta versión no se han aplicado la mayoría de los estándares 
 * de diseño OO dirigidos a conseguir un "código limpio".
 * Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Clase demasiado grande. 
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.1 - 2017/01/18 
 * @author: ajp
 */

import java.util.Date;
import java.util.Scanner;

public class JVPrincipal {

	static final int MAX_USUARIOS = 10;
	static final int MAX_SESIONES = 10;
	static Usuario[] datosUsuarios = new Usuario[MAX_USUARIOS];
	static SesionUsuario[] datosSesiones = new SesionUsuario[MAX_SESIONES];
	static int sesionesRegistradas = 0;				// Control de sesiones registradas.

	// En este array los 0 indican celdas con células muertas y los 1 vivas.
	static byte[][] mundo = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0 }, //
			{ 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 }, // Given:
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Planeador
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Flip-Flop
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  // 1x Still Life
	};

	static final int TAMAÑO = 12;
	static final int CICLOS = 120;

	public static void main(String[] args) {	
		cargarDatosPrueba();
		datosUsuarios[9].generarVarianteIdUsr();
		mostrarTodosDatosUsuarios();

		if (iniciarSesionCorrecta()) {
			arrancarSimulacion();
		}
		else {
			
			System.out.println("\nDemasiados intentos fallidos...");
		}
	
		System.out.println("Fin del programa.");
	}

	/**
	 * Controla el acceso de usuario 
	 * y registro de la sesión correspondiente. 
	 * @return true si la sesión de usuario es válida.
	 */
	private static boolean iniciarSesionCorrecta() {
		Scanner teclado = new Scanner(System.in);	// Entrada por consola.
		boolean todoCorrecto = false;				// Control de credenciales de usuario.
		Usuario usrSesion = null;					// Usuario en sesión.
		int intentos = 3;							// Contandor de intentos.
	
		do {
			// Pide usuario y contraseña.
			System.out.print("Introduce el idUsr: ");
			String idUsr = teclado.nextLine();
			System.out.print("Introduce clave acceso: ");
			String clave = teclado.nextLine();
			
			// Busca usuario coincidente con las credenciales.
			System.out.println(idUsr);
			usrSesion = buscarUsuario(idUsr);
			if ( usrSesion != null) {	
				// Usuario temporal para encriptar la clave introducida
				Usuario usrTmp = new Usuario();
				usrTmp.setClaveAcceso(clave);
				if (usrSesion.getClaveAcceso().equals(usrTmp.getClaveAcceso())) {
					todoCorrecto = true;
				}
			}
			if (todoCorrecto == false) {
				intentos--;
				System.out.println("Credenciales incorrectas...");
				System.out.println("Quedan " + intentos + " intentos... ");
			}
		}
		while (!todoCorrecto && intentos > 0);

		if (todoCorrecto) {
			// Apartado 5:
			// Registra sesion de usuario.
			SesionUsuario sesion = new SesionUsuario();
			sesion.setUsr(usrSesion);
			Date hoy = new Date();							// Fecha del sistema.
			sesion.setFecha(hoy.toString());			    
			datosSesiones[sesionesRegistradas] = sesion;  	// Añade sesión a partir de la última posición ocupada.
			sesionesRegistradas++; 							// Actualiza contador sesiones.
			
			System.out.println("Sesión: " + sesionesRegistradas + '\n' + "Iniciada por: " + usrSesion.getNombre() + " "
					+ usrSesion.getApellidos());
			return true;
		}
		return false;
	}

	/**
	 * Busca usuario dado su idUsr.
	 * @param idUsr - el idUsr del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public static Usuario buscarUsuario(String idUsr) {
		// Busca usuario coincidente con la credencial.
		for (int i = 0; i < MAX_USUARIOS; i++) {
			if (datosUsuarios[i] != null && datosUsuarios[i].getIdUsr().equals(idUsr)) {
				return datosUsuarios[i];	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}
	
	/**
	 * Ejecuta una simulación del juego de la vida en la consola.
	 * Utiliza la configuración por defecto.
	 */
	static void arrancarSimulacion() {
		int generacion = 0; 
		do {
			System.out.println("\nGeneración: " + generacion);
			mostrarMundo();
			mundo = actualizarMundo();
			generacion++;
		}
		while (generacion <= CICLOS);
	}

	/**
	 * Despliega en la consola el estado almacenado, corresponde
	 * a una generación del Juego de la vida.
	 */
	private static void mostrarMundo() {

		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j < TAMAÑO; j++) {
				System.out.print((mundo[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}

	/**
	 * Actualiza el estado almacenado del Juego de la Vida.
	 * @return nuevoEstado, el array con los cambios de la siguiente generación.
	 */
	private static byte[][] actualizarMundo()  {     					
		byte[][] nuevoEstado = new byte[TAMAÑO][TAMAÑO];

		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j <= 11; j++) {
				int vecinas = 0;						// Celdas adyacentes.

				// Las celdas situadas fuera del mundo, con índices fuera de rango, hay que controlarlas
				if (i-1 >= 0)	
					vecinas += mundo[i-1][j];			// Celda N			NO | N | NE
														//					-----------
				if (i-1 >= 0 && j+1 < TAMAÑO)			// 					 O | * | E
					vecinas += mundo[i-1][j+1];			// Celda NE			----------- 
														//					SO | S | SE
				if (j+1 < TAMAÑO)
					vecinas += mundo[i][j+1];			// Celda E			 

				if (i+1 < TAMAÑO && j+1 < TAMAÑO)
					vecinas += mundo[i+1][j+1];			// Celda SE          

				if (i+1 < TAMAÑO)
					vecinas += mundo[i+1][j]; 			// Celda S           

				if (i+1 < TAMAÑO && j-1 >= 0)
					vecinas += mundo[i+1][j-1]; 		// Celda SO 

				if (j-1 >= 0)
					vecinas += mundo[i][j-1];			// Celda O           			                                     	

				if (i-1 >= 0 && j-1 >= 0)
					vecinas += mundo[i-1][j-1]; 		// Celda NO

				if (vecinas < 2) 
					nuevoEstado[i][j] = 0; 				// Subpoblación, muere...

				if (vecinas > 3) 
					nuevoEstado[i][j] = 0; 				// Sobrepoblación, muere...

				if (vecinas == 3) 
					nuevoEstado[i][j] = 1; 				// Pasa a estar viva o se mantiene.

				if (vecinas == 2 && mundo[i][j] == 1) 						
					nuevoEstado[i][j] = 1; 				// Se mantiene viva...
			}
		}
		return nuevoEstado;
	}

	/**
	 * Muestra por consola todos los usuarios almacenados.
	 */
	private static void mostrarTodosDatosUsuarios() {
		for (Usuario u: datosUsuarios) {
			System.out.println("\n" + u);
		}
	}

	/**
	 *
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	private static void cargarDatosPrueba() {
		for (int i = 0; i < MAX_USUARIOS; i++) {
			Usuario usuarioAux = new Usuario(i + "2344556K", "Pepe", "López Pérez",
					"C/Luna, 27 30132 Murcia", "pepe" + "@gmail.com", "1990.11.12", "2014.12.3",
					"Miau#" + i, Usuario.ROLES[0]);
			int variantes = 1;
			while (buscarUsuario(usuarioAux.getIdUsr()) != null
					&& variantes < "ABCDEFGHJKLMNPQRSTUVWXYZ".length()) {
				usuarioAux.generarVarianteIdUsr();
				variantes++;
			}
			datosUsuarios[i] = usuarioAux;
		}
	}
	
} //class
