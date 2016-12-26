/** 
 * Proyecto: Juego de la vida.
 * Pruebas iniciales de las clases Usuario y SesionUsuario del modelo1.
 * Implementación del control de inicio de sesión y ejecución de la simulación por defecto. 
 * En esta versión no se han aplicado la mayoría de los estándares 
 * de diseño OO dirigidos a conseguir un "código limpio".
 * Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Ausencia de encapsulación.
 *  	- Clase demasiado grande. 
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.0 - 2016/12/8 
 * @author: ajp
 */

import java.util.Date;
import java.util.Scanner;

public class JVPrincipal {

	// Apartado 6:
	static final int MAX_USUARIOS = 10;
	static final int MAX_SESIONES = 10;
	static Usuario[] datosUsuarios = new Usuario[MAX_USUARIOS];
	static SesionUsuario[] datosSesiones = new SesionUsuario[MAX_SESIONES];
	static int sesionesRegistradas = 0;				// Control de sesiones registradas.

	// Apartado 8:
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
		// Apartados 4 y 5: Pruebas previas
		//probarUsuario();
		//probarSesionUsuario();

		// Apartado 7: 
		cargarDatosPrueba();		
		mostrarTodosDatosUsuarios();

		// Apartado 8:
		if (iniciarSesionCorrecta()) {
			arrancarSimulacion();
		}
		else {
			System.out.println("\nDemasiados intentos fallidos...");
		}
			
		System.out.println("Fin del programa.");
	}

	/**
	 * Apartado 8:
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
				if (usrSesion.claveAcceso.equals(clave)) {
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
			// Registra sesion de usuario.
			SesionUsuario sesion = new SesionUsuario();
			sesion.usr = usrSesion;
			sesion.fecha = new Date().toString();
			datosSesiones[sesionesRegistradas] = sesion;  	// Añade sesión a partir de la última posición ocupada.
			sesionesRegistradas++; 							// Actualiza contador sesiones.
			
			System.out.println("Sesión: " + sesionesRegistradas + '\n' + "Iniciada por: " + usrSesion.nombre + " "
					+ usrSesion.apellidos);
			return true;
		}
		return false;
	}

	/**
	 * Apartado 8:
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public static Usuario buscarUsuario(String idUsr) {
		// Busca usuario coincidente con la credencial.
		for (int i = 0; i < MAX_USUARIOS; i++) {
			if (datosUsuarios[i].nif.equals(idUsr)) {
				return datosUsuarios[i];	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}
	
	/**
	 * Apartado 8:
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
	 * Apartado 8:
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
	 * Apartado 8:
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
	 * Apartado 7:
	 * Muestra por consola todos los usuarios almacenados.
	 */
	private static void mostrarTodosDatosUsuarios() {
		for (Usuario u: datosUsuarios) {
			System.out.println("\n" + u);
		}
	}

	/**
	 * Apartado 7:
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	private static void cargarDatosPrueba() {
		Usuario usuarioAux = new Usuario();
		usuarioAux.nif = "2344556K";
		usuarioAux.nombre = "Pepe"; 
		usuarioAux.apellidos = "López Pérez";
		usuarioAux.domicilio = "C/Luna, 27 30132 Murcia";
		usuarioAux.correo = "pepe@gmail.com";
		usuarioAux.fechaNacimiento = "1990.11.12";
		usuarioAux.fechaAlta = "2014.12.3";
		usuarioAux.claveAcceso = "Miau#0";
		usuarioAux.rol = "usuario normal";
		datosUsuarios[0] = usuarioAux;

		for (int i = 1; i < MAX_USUARIOS; i++) {
			usuarioAux = new Usuario();
			usuarioAux.nif = i + "344556K";
			usuarioAux.nombre = "Pepe" + i; 
			usuarioAux.apellidos = "López" + " Pérez" +i ;
			usuarioAux.domicilio = "C/Luna, 27 30132 Murcia";
			usuarioAux.correo = "pepe" + i + "@gmail.com";
			usuarioAux.fechaNacimiento = "1990.11.12";
			usuarioAux.fechaAlta = "2014.12.3";
			usuarioAux.claveAcceso = "Miau#" + i;
			usuarioAux.rol = "usuario normal";
			datosUsuarios[i] = usuarioAux;
		}
	}

	/**
	 * Apartado 5: 
	 * Pruebas de la clase SesionUsuario
	 */
	private static void probarSesionUsuario() {

		// Datos para la prueba...
		Usuario usr = new Usuario();
		usr.nif = "23456790K";
		usr.nombre = "Pepe";
		usr.apellidos = "López Pérez";
		usr.domicilio = "C/Luna, 27 30132 Murcia";
		usr.correo = "pepe@gmail.com";
		usr.fechaNacimiento = "1990.11.12";
		usr.fechaAlta = "2016.12.3";
		usr.claveAcceso = "Miau#0";
		usr.rol = "usuario normal";
		
		// Prueba de la clase SesionUsuario
		SesionUsuario sesion1 = new SesionUsuario();
		sesion1.usr = usr;
		sesion1.fecha = "2016.12.3";
		System.out.println(sesion1);	
	}
	
	/**
	 * Apartado 4: 
	 * Pruebas de la clase Usuario
	 */
	private static void probarUsuario() {
		Scanner teclado = new Scanner(System.in);

		// Prueba de la clase Usuario

		Usuario usr1;
		//Equivalencia
		//usr1.nombre = "Luis";
		//null.nombre = "Luis";	

		// Asignación entre referencias -no duplica objeto-
		Usuario usr2 = new Usuario();
		usr1 = usr2;

		usr2.nif = "23456790K";
		usr2.nombre = "Pepe";
		usr2.apellidos = "López Pérez";
		usr2.domicilio = "C/Luna, 27 30132 Murcia";
		usr2.correo = "pepe@gmail.com";
		usr2.fechaNacimiento = "1990.11.12";
		usr2.fechaAlta = "2016.12.3";
		usr2.claveAcceso = "miau";
		usr2.rol = "usuario normal";

		// Modifica también usr2, son el mismo objeto
		usr1.nombre = "Luis";
		System.out.println("usr1: " + usr1.nombre);
		System.out.println("usr2: " + usr2.nombre);

		// Así si duplica
		Usuario usr4 = new Usuario();
		usr4 .nif = usr2.nif;
		usr4.nombre = usr2.nombre;
		usr4.apellidos = usr2.apellidos;
		usr4.domicilio =  usr2.domicilio;
		usr4.correo = usr2.correo;
		usr4.fechaNacimiento = usr2.fechaNacimiento;
		usr4.fechaAlta = usr2.fechaAlta;
		usr4.fechaAlta = usr2.fechaAlta;
		usr4.rol = usr2.rol;

		// Son diferentes objetos.
		usr4.nombre = "Pedro";
		System.out.println(usr2.nombre);

		// Desde teclado...
		Usuario usr3 = new Usuario();
		System.out.println("Entrada de datos de usuario... ");
		System.out.print("nif: ");	
		usr3.nif = teclado.next();
		System.out.print("nombre: ");	
		usr3.nombre = teclado.next();
		System.out.print("apellidos: ");
		usr3.apellidos = teclado.next();
		System.out.print("domicilio: ");
		usr3.domicilio =  teclado.next();
		System.out.print("correo: ");
		usr3.correo = teclado.next();
		System.out.print("fechaNacimiento: ");
		usr3.fechaNacimiento = teclado.next();
		System.out.print("fechaAlta: ");
		usr3.fechaAlta = teclado.next();
		System.out.print("claveAcceso: ");
		usr3.claveAcceso = teclado.next();
		System.out.print("rol: ");
		usr3.rol = "NORMAL";

		// Si toString() de Usuario no está redefinido...
		System.out.println(usr1); 		// Muestra identificador único de objeto
		System.out.println(usr2);
		System.out.println(usr3);
		System.out.println(usr4);
	}

} //class
