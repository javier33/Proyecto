package accesoDatos;
/** Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos relacionados con el almacenamineto del programa.
 *  @since: prototipo1.2
 *  @source: Datos.java 
 *  @version: 1.2 - 2017/02/22
 *  @author: ajp
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	private ArrayList<Usuario> datosUsuarios;
	private ArrayList<SesionUsuario> datosSesiones;
	private Hashtable<String, String> equivalencias;

	public Datos() {
		datosUsuarios = new ArrayList<Usuario>();
		datosSesiones = new ArrayList<SesionUsuario>();
		equivalencias = new Hashtable<String, String>();
		cargarDatosPrueba();
	}

	public ArrayList<Usuario> getDatosUsuarios() {
		return datosUsuarios;
	}

	public ArrayList<SesionUsuario> getDatosSesiones() {
		return datosSesiones;
	}
	
	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}
	
	/**
	 * Obtiene todos los usuarios almacenados en texto formateado.
	 */
	public String textoDatosUsuarios() {
		StringBuilder aux = new StringBuilder();
		for (Usuario u: datosUsuarios) {
			aux.append("\n" + u);
		}
		return aux.toString();
	}

	/**
	 * Volcado de todos los usuarios almacenados.
	 */
	public String volcarDatosUsuarios() {
		StringBuilder aux = new StringBuilder();
		for (Usuario u: datosUsuarios) {
			aux.append("<usr>"  
						+"<attrib>"+u.getNif()+"</attrib>"
						+"<attrib>"+u.getNombre()+"</attrib>"
						+"<attrib>"+u.getApellidos()+"</attrib>"
						+"<attrib>"+u.getIdUsr()+"</attrib>"
						+"<attrib>"+u.getDomicilio()+"</attrib>"
						+"<attrib>"+u.getCorreo()+"</attrib>"
						+"<attrib>"+u.getFechaNacimiento()+"</attrib>"
						+"<attrib>"+u.getFechaAlta()+"</attrib>"
						+"<attrib>"+u.getClaveAcceso()+"</attrib>"
						+"<attrib>"+u.getRol()+"</attrib>"
					+"</usr>");
		}
		return aux.toString();
	}

	/**
	 * Volcado de todos las sesiones almacenadas.
	 */
	public String volcarDatosSesiones() {
		StringBuilder aux = new StringBuilder();
		for (SesionUsuario s: datosSesiones) {
			aux.append("<sesion>"
					+"<usr>"  
						+"<attrib>"+s.getUsr().getNif()+"</attrib>"
						+"<attrib>"+s.getUsr().getNombre()+"</attrib>"
						+"<attrib>"+s.getUsr().getApellidos()+"</attrib>"
						+"<attrib>"+s.getUsr().getIdUsr()+"</attrib>"
						+"<attrib>"+s.getUsr().getDomicilio()+"</attrib>"
						+"<attrib>"+s.getUsr().getCorreo()+"</attrib>"
						+"<attrib>"+s.getUsr().getFechaNacimiento()+"</attrib>"
						+"<attrib>"+s.getUsr().getFechaAlta()+"</attrib>"
						+"<attrib>"+s.getUsr().getClaveAcceso()+"</attrib>"
						+"<attrib>"+s.getUsr().getRol()+"</attrib>"
					+"</usr>"
					+"<attrib>"+s.getFecha()+"</attrib>"
					+"<sesion>");
		}
		return aux.toString();
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	private void cargarDatosPrueba() {
		final int  MAX_USUARIOS_PRUEBA = 10;
		String[] NifValidos = { "00000000T", "00000001R", "00000002W", "00000003A", "00000004G",
				"00000005M", "00000006Y", "00000007F", "00000008P", "00000009D" };
		for (int i = 0; i < MAX_USUARIOS_PRUEBA ; i++) {
			Usuario usr =  new Usuario(new Nif(NifValidos[i]), "Pepe",
					"López Pérez", new DireccionPostal("Alta", "10", "30012", "Murcia"), 
					new Correo("pepe" + i + "@gmail.com"), new Fecha(2000, 11, 30), 
					new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);
			
			datosUsuarios.add(usr);
			
			//Añade equivalencias para idUsr 
			equivalencias.put(usr.getIdUsr(), usr.getIdUsr());
			equivalencias.put(usr.getNif().getTexto(), usr.getIdUsr());
			equivalencias.put(usr.getCorreo().getTexto().toUpperCase(), usr.getIdUsr());
		}
	}

	/**
	 * Obtiene el idUsr que equivale a la credencial recibida.
	 * @param credencialUsr, puede ser nif o correo.
	 */
	public String equivalenciaId(String credencialUsr) {	
		return equivalencias.get(credencialUsr);
	}
	
	/**
	 * Búsqueda binaria de usuario dado su idUsr.
	 * @param idUsr - el idUsr de Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String idUsr) {
		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio;
		while (inicio <= fin && idUsr != null) {
			medio = (inicio + fin) / 2;
			comparacion = datosUsuarios.get(medio).getIdUsr()
								.compareToIgnoreCase(idUsr);
			if (comparacion == 0) {
				return datosUsuarios.get(medio);
			}
			if (comparacion < 0) {
				inicio = medio + 1;
			}
			else {
				fin = medio - 1;
			}
		}
		return null;				
	}
	
	/**
	 * Búsqueda binaria recursiva de usuario dado su idUsr.
	 * @param idUsr - el idUsr a buscar.
	 * @param inicio - del bloque.
	 * @param fin - del bloque.
	 * @return - el Usuario encontrado o null si no existe.
	 */		
	private Usuario buscarUsuarioRec(String idUsr, int inicio, int fin) {
		Usuario usrRetorno = null;
		int comparacion; 
		int medio;
		if (inicio <= fin && idUsr != null) {
			medio = (inicio + fin) / 2;
			comparacion = datosUsuarios.get(medio).getIdUsr()
										.compareTo(idUsr);
			if (comparacion == 0) {					// Encontrado
				usrRetorno = datosUsuarios.get(medio);
			}
			else {
				if(comparacion < 0) {
					usrRetorno = buscarUsuarioRec(idUsr, medio+1, fin);
				}
				if (comparacion > 0) {
					usrRetorno = buscarUsuarioRec(idUsr, inicio, medio-1);
				}
			}
		}
		return usrRetorno;
	} 
	
	/**
	 * Añade una nueva sesión en el almacén de datos.
	 * @param sesionUsuario a guardar.
	 */
	public void registrarSesion(SesionUsuario sesionUsuario) {
		datosSesiones.add(sesionUsuario);	
	}

} //class
