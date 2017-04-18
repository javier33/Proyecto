/** 
 * Proyecto: Juego de la vida.
 * Representa el espacio y las leyes que determinan un mundo de simulación del según el modelo 2.
 * Se hace validación de datos pero no se gestionan todavía los errores correspondientes. 
 * @since: prototipo2.0
 * @source: Mundo.java 
 * @version: 2.0 - 2017.03.11
 * @author: ajp
 */

package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Mundo implements Leyes, Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<Integer> constantes;
	private Map<Patron, Posicion> distribucion;
	private byte[][] espacio;	

	/**
	 * Constructor convencional.
	 * Establece el valor inicial de cada uno de los atributos.
	 * Recibe parámetros que se corresponden con los atributos.
	 * Utiliza métodos set... para la posible verificación.
	 * @param nombre
	 * @param constantes
	 * @param distribucion
	 * @param espacio
	 */
	public Mundo(String nombre, List<Integer> constantes, 
			Map<Patron, Posicion> distribucion, byte[][] espacio) {
		setNombre(nombre);
		setConstantes(constantes);
		setDistribucion(distribucion);
		setEspacio(espacio);
	}

	/**
	 * Constructor por defecto.
	 * Establece el valor inicial, por defecto, de cada uno de los atributos.
	 * Llama al constructor convencional de la propia clase.
	 */
	public Mundo() {
		this("MundoDefecto", new ArrayList<Integer>(), 
				new Hashtable<Patron, Posicion>(), null);
	}

	/**
	 * Constructor copia.
	 * Establece el valor inicial de cada uno de los atributos a partir de
	 * los valores obtenidos de un objeto de su misma clase.
	 * Llama al constructor convencional utilizando objetos obtenidos
	 * con los contructores copia de los atributos.
	 * El atributo espacio es clonado utilizando utilidades de clonación de arrays.
	 * @param m - ea Mundo a clonar
	 */
	public Mundo(Mundo m) {
		this(m.nombre, new ArrayList<Integer>(m.constantes), 
				new Hashtable<Patron,Posicion>(m.distribucion), m.espacio);

		this.espacio = new byte[m.espacio.length][m.espacio.length];

		for (int i=0; i <m.espacio.length; i++)
			this.espacio[i] = Arrays.copyOf(m.espacio[i], m.espacio[i].length);
		//System.arraycopy(m.espacio[i], 0, this.espacio[i], 0, m.espacio[i].length);	
	}

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getConstantes() {
		return constantes;
	}

	public Map<Patron, Posicion> getDistribucion() {
		return distribucion;
	}


	public byte[][] getEspacio() {
		return espacio;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			this.nombre = "MundoDefecto";
		}
		else {
			this.nombre = nombre; 
		}
	}

	public void setConstantes(List<Integer> parametros) {

		if (parametros == null) {
			this.constantes = new ArrayList<Integer>();
		}
		else {
			this.constantes = parametros;
		}
	}

	public void setDistribucion(Map<Patron, Posicion> distribucion) {
		this.distribucion = distribucion;
	}

	public void setEspacio(byte[][] espacio) {
		int tamaño = 12;
		if (espacio == null || espacio.length == 0) {
			this.espacio = new byte[tamaño][tamaño];
			for (int i=0; i < this.espacio.length; i++) {
				for (int j=0; j < this.espacio.length; j++) {
					this.espacio[i][j] = 0; 
				}
			}
			return;
		}
		this.espacio = espacio;
	}

	//Métodos de la interface Leyes
	/**
	 * Establece la manera en que actualiza el estado de un mundo. 
	 * Responde a la regla: El tiempo transcurre y se producen cambios...
	 */
	@Override
	public void actualizarMundo()  {     					
		int tam = espacio.length; 
		byte[][] nuevaRealidad = new byte[tam][tam];

		for (int i = 0; i < tam; i++) {
			for (int j = 0; j <= 11; j++) {
				int vecinas = 0;					//células adyacentes
				// las celdas situadas fuera del mundo, con índices fuera de rango, darán
				// ArrayIndexOutOfBoundsException y son equivalentes a células vacías a efectos de 
				// vecindad.
				try {
					vecinas += espacio[i-1][j];	    //celda N			NO | N | NE
					vecinas += espacio[i-1][j+1];	//celda NE			----------- 
					vecinas += espacio[i][j+1];	    //celda E			 O |   | E
					vecinas += espacio[i+1][j+1];	//celda SE          -----------
					vecinas += espacio[i+1][j]; 	//celda S           SO | S | SE
					vecinas += espacio[i+1][j-1];    //celda SO 
					vecinas += espacio[i][j-1];	    //celda O           			                                     	
					vecinas += espacio[i-1][j-1]; 	//celda NO
				} 
				catch (java.lang.ArrayIndexOutOfBoundsException e) { } 

				if (vecinas < 2) {
					nuevaRealidad[i][j] = 0; 				// subpoblación, muere
				}
				if (vecinas > 3) {
					nuevaRealidad[i][j] = 0; 				// sobrepoblación, muere
				}
				if (vecinas == 3) {
					nuevaRealidad[i][j] = 1; 				// pasa a estar viva o se mantiene
				}
				if (vecinas == 2 && espacio[i][j] == 1) {						
					nuevaRealidad[i][j] = 1; 				// se mantiene viva
				}
			}
		}
		espacio = nuevaRealidad;
	}

	/**
	 * @return el texto formateado del estado -valores de atributos- deL objeto de la clase Mundo.  
	 */
	@Override
	public String toString() {
		return String
				.format("Mundo [nombre=%s, parametros=%s, distribucion=%s, espacio=%s]",
						nombre, constantes, distribucion,
						Arrays.toString(espacio));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constantes == null) ? 0 : constantes.hashCode());
		result = prime * result + ((distribucion == null) ? 0 : distribucion.hashCode());
		result = prime * result + Arrays.deepHashCode(espacio);
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
			if (nombre.equals(((Mundo)obj).nombre) &&
					constantes.equals(((Mundo)obj).constantes) &&
					distribucion.equals(((Mundo)obj).distribucion) &&
					espacio.equals(((Mundo)obj).espacio) 
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
		return new Mundo(this);
	}

} //class
