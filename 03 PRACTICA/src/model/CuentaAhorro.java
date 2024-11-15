package model;

import java.util.GregorianCalendar;

public class CuentaAhorro extends Cuenta {
	
	private Double interesAnual;
	private Double ahorros;
	

	public CuentaAhorro(Integer numero, String titular, Double saldo, Double saldoMinimo,
			GregorianCalendar fechaApertura, Double interesAnual, Double ahorros) {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);

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

	
	
}
