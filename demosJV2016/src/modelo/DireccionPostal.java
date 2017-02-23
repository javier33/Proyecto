package modelo;

import util.Formato;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de direccion postal según el modelo 1.2
 *  @since: prototipo1.2
 *  @source: DireccionPostal.java 
 *  @version: 1.2 - 2017/02/20
 *  @author: ajp
 */

public class DireccionPostal {
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
		this("Calle", "numero", "00000", "Población");
	}
	
	public DireccionPostal(DireccionPostal direccion) {
		this(direccion.calle, direccion.numero, direccion.cp, 
				direccion.poblacion);
	}
	


	public void setCalle(String calle) {
		assert calleValida(calle);
		this.calle = calle;	
	}

	/**
	 * Comprueba validez de la calle.
	 * @param via.
	 * @return true si cumple.
	 */
	private boolean calleValida(String calle) {
		if (calle != null
				&& util.Formato.validar(calle, Formato.PATRON_TOPONIMO)
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
		assert numeroValido(numero);
		this.numero = numero;
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
		assert codigoPostalValido(cp);
		this.calle = cp;
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


	@Override
	public String toString() {
		return calle + ", " + numero + ", " + cp
				+ ", " + poblacion;
	}
	
} // class
