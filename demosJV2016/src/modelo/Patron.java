/** 
 *  Proyecto: Juego de la vida.
 *  Organiza aspectos de gestión de la simulación según el modelo 2.
 *  @since: prototipo2.0
 *  @source: Patron.java 
 *  @version: 2.0 - 2017.03.20
 *  @author: ajp
 */

package modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Patron implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private byte[][] esquema;

	/**
	 * Constructor convencional.
	 * Establece el valor inicial de cada uno de los atributos.
	 * Recibe parámetros que se corresponden con los atributos.
	 * Utiliza métodos set... para la posible verificación.
	 * @param nombre
	 * @param esquema
	 */
	public Patron(String nombre, byte[][] esquema) {
		setNombre(nombre);
		setEsquema(esquema);
	}

	/**
	 * Constructor por defecto.
	 * Establece el valor inicial, por defecto, de cada uno de los atributos.
	 * Llama al constructor convencional de la propia clase.
	 */
	public Patron() {
		this("NombrePatron", new byte[1][1]);
	}

	/**
	 * Constructor copia.
	 * Establece el valor inicial de cada uno de los atributos a partir de
	 * los valores obtenidos de un objeto de su misma clase.
	 * El atributo esquema es clonado utilizando utilidades de clonación de arrays.
	 * @param p
	 */
	public Patron(Patron p) {
		this(p.nombre, p.esquema);
		this.esquema = new byte[p.esquema.length][p.esquema.length];	
		for (int i=0; i <p.esquema.length; i++) {
			this.esquema[i] = Arrays.copyOf(p.esquema[i], p.esquema[i].length);
		}                                
	}

	/** Constructor especial.
	 * Establece el valor inicial de cada uno de los atributos.
	 * Recibe parámetros para inicializa los atributos.
	 * Utiliza métodos set... para la posible verificación.
	 * @param nombre
	 * @param filas
	 * @param columnas
	 * @param imagenPatron
	 */
	public Patron(String nombre, int filas, int columnas, String imagenPatron) {
		setNombre(nombre);

		byte [][] esquema = new byte [filas][columnas];
		//...
		setEsquema(esquema);
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the esquema
	 */
	public byte[][] getEsquema() {
		return esquema;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		if (nombre != null) {
			this.nombre = nombre;
		}
		else {
			this.nombre = "NombrePatron";
		}
	}

	/**
	 * @param esquema the esquema to set
	 */
	public void setEsquema(byte[][] esquema) {

		if (esquema == null || esquema.length == 0) {
			byte[][] aux = {{0}};
			this.esquema  = aux;
		}		
		else {
			this.esquema = esquema;
		}
	}

	@Override
	public String toString() {
		return String.format("Patron [nombre=%s, esquema=%s]", nombre,
				Arrays.toString(esquema));
	}

	/**
	 * hashCode() complementa al método equals y sirve para comparar objetos de forma 
	 * rápida en estructuras Hash. 
	 * Cuando Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet etc)
	 * primero invoca al método hashcode y luego el equals.
	 * @return un número entero de 32 bit.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(esquema);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * @return falso si no cumple las condiciones.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (nombre.equals(((Patron)obj).nombre) &&
					esquema.equals(((Patron)obj).esquema)
					) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Genera un clon del propio objeto realizando una copia profunda.
	 * @return el objeto clonado.
	 */
	@Override
	public Object clone() {
		// Utiliza el constructor copia.
		return new Patron(this);
	}

} // class
