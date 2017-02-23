package modelo;

/** Proyecto: Juego de la vida.
 *  Implementa el concepto de contraseña de seguridad según el modelo 1.2
 *  @since: prototipo1.2
 *  @source: ClaveAcceso.java 
 *  @version: 1.2 - 2017/02/14
 *  @author: ajp
 */

public class ClaveAcceso {

	private String texto;

	public ClaveAcceso(String texto) {
		setTexto(texto);
	}

	public ClaveAcceso() {
		this("Miau#0");
	}

	public ClaveAcceso(ClaveAcceso claveAcceso) {
		this(claveAcceso.texto);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		if (ClaveAccesoValida(texto)) {
			this.texto = encriptarCesar(texto);
		}
	}

	private boolean ClaveAccesoValida(String texto) {
		if (texto != null) {
			return	texto.matches("([\\wñÑ$*-+&!?#]){5,}");
		}
		return false;
	}

	private String encriptarCesar(String texto) {
		String alfabetoNormal =     "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789?$%&#";
		String alfabetoDesplazado = "FGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789?$%&#ABCDE";
		StringBuilder claveEncriptada = new StringBuilder();
		for (int i=0; i < texto.length(); i++) {
			int posicion = alfabetoNormal.indexOf(texto.charAt(i));
			claveEncriptada.append(alfabetoDesplazado.charAt(posicion));
		}	
		return claveEncriptada.toString();
	}

	@Override
	public String toString() {
		return texto;
	}

	/**
	 * hashcode() complementa al método equals y sirve para comparar objetos de forma 
	 * rápida en estructuras Hash. 
	 * Cuando Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet etc)
	 * primero invoca al método hashcode y luego el equals.
	 * @return un número entero de 32 bit.
	*/
	@Override
	public int hashCode() {
		final int primo = 31;
		int result = 1;
		result = primo * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * primero invoca al método hashcode y luego el equals.
	 * @return falso si no cumple las condiciones.
	*/
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (texto.equals(((ClaveAcceso) obj).texto)) {
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
	public ClaveAcceso clone() {
		// Utiliza el constructor copia.
		return new ClaveAcceso(this);
	}

} //class
