/** Proyecto: Juego de la vida.
 *  Implementa el concepto de direccion postal según el modelo 2.
 *  Se hace validación de datos pero no se gestionan todavía los errores correspondientes.
 *  @since: prototipo1.2
 *  @source: DireccionPostal.java 
 *  @version: 2.0 - 2017.02.20
 *  @author: ajp
 */

package modelo;

import java.io.Serializable;
import util.Formato;

public class DireccionPostal implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String calle;
	private String numero;
	private String cp;
	private String poblacion;

	public DireccionPostal(String calle, String numero, String cp, String poblacion) {
		setCalle(calle);
		setNumero(numero);
		setCP(cp);
		setPoblacion(poblacion);
	}

	public DireccionPostal() {
		this("Calle", "00", "99999", "Población");
	}

	public DireccionPostal(DireccionPostal direccion) {
		this(direccion.calle, direccion.numero, direccion.cp, 
				direccion.poblacion);
	}

	public void setCalle(String calle) {
		assert(calleValida(calle));
		this.calle = calle;
	}

	/**
	 * Comprueba validez de la calle.
	 * @param via.
	 * @return true si cumple.
	 */
	private boolean calleValida(String calle) {
		if (calle != null
				&& util.Formato.validar(calle, Formato.PATRON_NOMBRE_VIA)
				&& calleAutentica(calle)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba que existe la calle.
	 * @param calle.
	 * @return true si cumple.
	 */
	private boolean calleAutentica(String calle) {
		// Comprueba que la calle no es falsa.
		//--Pendiente--
		return true;
	}

	public void setNumero(String numero) {
		if (numeroValido(numero)) {
			this.numero = numero;
		}
		else {
			// ERROR -mejor con assert-
		}
	}

	/**
	 * Comprueba validez de la vía pública.
	 * @param via.
	 * @return true si cumple.
	 */
	private boolean numeroValido(String numero) {
		if (numero != null
				&& util.Formato.validar(numero, Formato.PATRON_NUMERO_POSTAL)) {
			return true;
		}
		return false;
	}

	public void setCP(String cp) {
		assert(codigoPostalValido(cp));
		this.cp = cp;

	}

	/**
	 * Comprueba validez de un código Postal.
	 * @param codigoPostal.
	 * @return true si cumple.
	 */
	private boolean codigoPostalValido(String codigoPostal) {
		if (codigoPostal != null
				&& util.Formato.validar(codigoPostal, Formato.PATRON_CP) 
				&& codigoPostalAutentico(codigoPostal)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba que existe el código postal.
	 * @param codigoPostal.
	 * @return true si cumple.
	 */
	private boolean codigoPostalAutentico(String codigoPostal) {
		// Comprueba que el codigo postal no es falso. 
		//--Pendiente--
		return true;
	}

	public void setPoblacion(String poblacion) {
		assert poblacionValida(poblacion);
		this.poblacion = poblacion;
	}

	/**
	 * Comprueba validez de la poblacion.
	 * @param poblacion.
	 * @return true si cumple.
	 */
	private boolean poblacionValida(String poblacion) {
		if (poblacion != null
				&& util.Formato.validar(poblacion, Formato.PATRON_TOPONIMO)
				&& poblacionAutentica(poblacion)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba que existe la población.
	 * @param poblacion.
	 * @return true si cumple.
	 */
	private boolean poblacionAutentica(String poblacion) {
		// Comprueba que la población no es falsa.
		//--Pendiente--
		return true;
	}

	public String getCodigoPostal() {
		return calle;
	}

	public String getCalle() {
		return calle;
	}


	public String getNumero() {
		return numero;
	}

	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado -valores de atributos- de objeto de la clase Usuario.  
	 */
	@Override
	public String toString() {
		return String.format(
				"calle: %s\t"
						+ "numero: %s\t"
						+ "cp: %s\t"
						+ "poblacion: %s\n",
						calle, numero, cp, poblacion);		
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
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
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
			if (calle.equals(((DireccionPostal)obj).calle) &&
					cp.equals(((DireccionPostal)obj).cp) &&
					numero.equals(((DireccionPostal)obj).numero) &&
					poblacion.equals(((DireccionPostal)obj).poblacion)
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
		return new DireccionPostal(this);
	}

} // class
