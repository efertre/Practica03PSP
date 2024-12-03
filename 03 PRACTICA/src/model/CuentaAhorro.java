package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.exceptions.ESaldoNoValido;

public class CuentaAhorro extends Cuenta implements FechaCalculable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double interesAnual;
	private Double ahorros;

	public CuentaAhorro(Integer numero, String titular, Double saldo, LocalDate fechaApertura, Double interesAnual,
			Double ahorros) throws ESaldoNoValido {
		super(numero, titular, saldo, fechaApertura);

		setInteresAnual(interesAnual);
		setAhorros(ahorros);

	}

	public Double getInteresAnual() {
		return interesAnual;
	}

	public void setInteresAnual(Double interesAnual) {
		this.interesAnual = interesAnual;
	}

	public Double getAhorros() {
		return ahorros;
	}

	public void setAhorros(Double ahorros) {
		this.ahorros = ahorros;
	}

	@Override
	public String toString() {
	    return String.format("Cuenta de Ahorro: " +
	                         "Interés anual: %.2f%% " +  // Mostrar el interés como porcentaje
	                         "Ahorros: %.2f " +
	                         "%s", 
	                         interesAnual * 100,  // Convertir el valor decimal a porcentaje
	                         ahorros, 
	                         super.toString());
	}



}
