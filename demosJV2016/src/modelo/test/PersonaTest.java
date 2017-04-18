/** 
 * Proyecto: Juego de la vida.
 * Clase JUnit de prueba automatizada de las características de la clase Persona según el modelo 2.
 * @since: prototipo2
 * @source: TestPersona.java 
 * @version: 2.0 - 2017.03.21
 * @author: ajp
 */

package modelo.test;

import static org.junit.Assert.*;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.Nif;
import modelo.Persona;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Fecha;

public class PersonaTest {
	private Persona persona1; 
	private Persona persona2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@Before
	public void crearDatosPrueba() {
		// Objetos para la prueba.
		persona1 = new Usuario(); 
		persona2 = new Usuario(new Nif(), "Luis", "Pérez Ruiz",
				new DireccionPostal(), new Correo(), new Fecha(2000, 03, 21),
				new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@After	
	public void borrarDatosPrueba() {
		persona1 = null;
	}

	// Test CON DATOS VALIDOS
	@Test
	public void testPersona() {
		assertNotNull(persona1);
	}

	@Test
	public void testGetNif() {
		assertEquals(persona2.getNif(), new Nif());
	}

	@Test
	public void testGetNombre() {
		assertEquals(persona2.getNombre(), "Luis");
	}

	@Test
	public void testGetApellidos() {
		assertEquals(persona2.getApellidos(), "Pérez Ruiz");
	}

	@Test
	public void testGetDomicilio() {
		assertEquals(persona2.getDomicilio(), new DireccionPostal());
	}

	@Test
	public void testGetFechaNacimiento() {
		assertEquals(persona2.getFechaNacimiento(), new Fecha(2000, 03, 21));
	}

	@Test
	public void testGetCorreo() {
		assertEquals(persona2.getCorreo(), new Correo());
	}

	@Test
	public void testSetNif() {
		persona1.setNif(new Nif("00000001R"));
		assertEquals(persona1.getNif(), new Nif("00000001R"));
	}

	@Test
	public void testSetNombre() {
		persona1.setNombre("Luis");
		assertEquals(persona1.getNombre(), "Luis");
	}

	@Test
	public void testSetCorreo() {
		persona1.setCorreo(new Correo("luis@gmail.com"));
		assertEquals(persona1.getCorreo(), new Correo("luis@gmail.com"));
	}

	@Test
	public void testSetApellidos() {
		persona1.setApellidos("Sánchez Azul");
		assertEquals(persona1.getApellidos(), "Sánchez Azul");
	}

	@Test
	public void testSetDomicilio() {
		persona1.setDomicilio(new DireccionPostal());
		assertEquals(persona1.getDomicilio(), persona2.getDomicilio());
	}

	@Test
	public void testSetFechaNacimiento() {
		persona1.setFechaNacimiento(new Fecha(2000, 03, 21));
		assertEquals(persona1.getFechaNacimiento(), persona2.getFechaNacimiento());
	}

	@Test
	public void testToString() {
		assertEquals(persona2.toString(), new Usuario(new Nif(), "Luis", "Pérez Ruiz",
				new DireccionPostal(), new Correo(), new Fecha(2000, 03, 21),
				new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL).toString());	
	}

	@Test
	public void testEqualsObject() {
		persona1 = new Usuario(new Nif(), "Luis", "Pérez Ruiz",
				new DireccionPostal(), new Correo(), new Fecha(2000, 03, 21),
				new Fecha(), new ClaveAcceso(), RolUsuario.NORMAL);
		assertTrue(persona1.equals(persona2));
	}

	public void testHashCode() {
		assertEquals(persona2.hashCode(), -2032408461);
	}

	// Test CON DATOS NO VALIDOS
	@Test
	public void testSetNifNull() {
		try {
			persona1.setNif(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor por defecto.
		assertEquals(persona1.getNif(), new Nif());
	}

	@Test
	public void testSetNombreNull() {
		try {
			persona1.setNombre(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor por defecto.
		assertEquals(persona1.getNombre(), "Nombre");
	}

	@Test
	public void testSetApellidosNull() {
		try {
			persona1.setApellidos(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe ser null.
		assertNotNull(persona1.getApellidos());
	}

	@Test
	public void testSetDomicilioNull() {
		try {
			persona1.setDomicilio(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor por defecto.
		assertEquals(persona1.getDomicilio(), new DireccionPostal());
	}

	@Test
	public void testSetCorreoNull() {
		try {
			persona1.setCorreo(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor por defecto.
		assertEquals(persona1.getCorreo(), new Correo());
	}

	@Test
	public void testSetFechaNacimientoNull() {
		try {
			persona2.setFechaNacimiento(null);
			fail("No debe llegar aquí...");
		} catch (AssertionError e) { }

		// Si funciona bien no debe cambiar el valor previo.
		assertEquals(persona2.getFechaNacimiento(), new Fecha(2000, 03, 21));
	}

} //class
