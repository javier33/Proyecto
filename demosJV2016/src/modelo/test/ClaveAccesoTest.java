/** 
 * Proyecto: Juego de la vida.
 * Prueba Junit4 de la clase Claveacceso según el modelo 2
 * @since: prototipo2
 * @source: ClaveAccesoTest.java 
 * @version: 2 - 2017.03.22
 * @author: ajp
 */

package modelo.test;

import static org.junit.Assert.*;
import modelo.ClaveAcceso;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClaveAccesoTest {
	private ClaveAcceso clave1;
	private ClaveAcceso clave2 ;

	public ClaveAccesoTest () {
		clave2 = new ClaveAcceso("Miau#0");
	}

	@Before
	public void InicializaDatosPrueba() {	
		clave1 = new ClaveAcceso();
	}

	@After
	public void borrarDatosPrueba() {	
		clave1 = null;
	}

	// Test con DATOS VALIDOS
	@Test
	public void testClaveAccesoConvencional() {	
		assertNotNull(clave2);
	}

	@Test
	public void testClaveAcceso() {
		assertNotNull(clave1);
	}

	@Test
	public void testClaveAccesoCopia() {
		clave1 = new ClaveAcceso(clave2);
		assertNotSame(clave2, clave1);
	}

	@Test
	public void testGetTexto() {
		assertEquals(clave2.getTexto(), "Pmezd8");
	}

	@Test
	public void testSetTexto() {
		clave1.setTexto("Miau#0");
		assertEquals(clave1.getTexto(), "Pmezd8");
	}

	@Test
	public void testToString() {
		assertEquals(clave2.toString(), "Pmezd8");
	}

	@Test
	public void testEquals() {
		ClaveAcceso clave = null;
		clave = new ClaveAcceso();
		assertEquals(clave, clave1);
	}

	@Test
	public void testClone() {
		ClaveAcceso clave = (ClaveAcceso) clave2.clone();
		assertNotSame(clave, clave2);
	}

	@Test
	public void testHashCode() {
		assertEquals(clave2.hashCode(), -1900842107);
	}

	// Test con DATOS NO VALIDOS
	@Test
	public void testClaveAccesoConvencionalTextoNull() {	
		ClaveAcceso clave = null;
		try {
			String texto = null;
			clave = new ClaveAcceso(texto);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
		assertNull(clave);
	}

	@Test
	public void testClaveAccesoConvencionalTextoMalFormato() {	
		ClaveAcceso clave = null;
		try {
			clave = new ClaveAcceso("hola");
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
		assertNull(clave);
	}

	@Test
	public void testSetTextoNull() {
		try {
			clave1.setTexto(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
		assertEquals(clave1.getTexto(), "Pmezd8");
	}

	@Test
	public void testSetTextoMalFormato() {
		try {
			clave1.setTexto("hola");
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { }
		assertEquals(clave1.getTexto(), "Pmezd8");
	}

} //class
