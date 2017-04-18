/** 
 * Proyecto: Juego de la vida.
 * Clase JUnit de prueba automatizada de las características de la clase Usuario según el modelo 2.
 * @since: prototipo2
 * @source: TestUsuario.java 
 * @version: 2.0 - 2017.03.21
 * @author: ajp
 */

package modelo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.Nif;
import modelo.Persona;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class UsuarioTest {
	private Usuario usuario1; 
	private Usuario usuario2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 * @throws UtilException 
	 */
	@Before
	public void crearDatosPrueba() {
		// Objetos para la prueba.
		usuario1 = new Usuario(); 
		usuario2 = new Usuario(new Nif(), "Luis", "Pérez Ruiz",
				new DireccionPostal("C/Luna", "27", "30132", "Murcia"), new Correo(), 
				new Fecha(), new Fecha(2000, 03, 21), new ClaveAcceso(), RolUsuario.INVITADO);
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@After	
	public void borrarDatosPrueba() {
		usuario1 = null;
	}

	// Test CON DATOS VALIDOS
	@Test
	public void testGetContraseña() {
		assertNotNull(((Usuario)usuario2).getClaveAcceso());
	}

	@Test
	public void testGetRol() {
		assertNotNull(((Usuario)usuario2).getRol());
	}

	@Test
	public void testSetFechaAlta() {
		Fecha fecha = new Fecha(2012, 2, 9);
		usuario1.setFechaAlta(fecha);
		assertSame( usuario1.getFechaAlta(), fecha);
	}

	@Test
	public void testSetClaveAcceso() {
		ClaveAcceso clave = null;
		clave = new ClaveAcceso("Hola#12");
		usuario1.setClaveAcceso(clave);

		assertSame(usuario1.getClaveAcceso(), clave);
	}

	@Test
	public void testSetRol() {
		usuario1.setRol(RolUsuario.INVITADO);
		assertSame(usuario1.getRol(), RolUsuario.INVITADO);
	}

	@Test
	public void testToString() {
		assertNotNull(usuario2.toString());	
	}

	@Test
	public void testEqualsObject() {
		usuario1 = new Usuario(new Nif(), "Luis", "Pérez Ruiz",
				new DireccionPostal("C/Luna", "27", "30132", "Murcia"), new Correo(), 
				new Fecha(), new Fecha(2000, 03, 21), new ClaveAcceso(), RolUsuario.INVITADO);
		assertTrue(usuario1.equals(usuario2));
	}

	public void testHashCode() {
		System.out.println(usuario2.hashCode());
		assertEquals(usuario2.hashCode(), -2032408461);
	}

	// Test CON DATOS NO VALIDOS
	@Test
	public void testSetClaveAccesoNull() {
		try {
			usuario1.setNif(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor por defecto.
		assertNotNull(usuario1.getClaveAcceso());
	}

	@Test
	public void testSetFechaAltaNull() {
		try {
			usuario2.setFechaAlta(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor previo.
		assertEquals(usuario2.getFechaAlta(), new Fecha(2000, 03, 21));
	}

	@Test
	public void testSetRolNull() {
		try {
			usuario1.setRol(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor previo por defecto.
		assertEquals(usuario1.getRol(), RolUsuario.NORMAL);
	}
} // class
