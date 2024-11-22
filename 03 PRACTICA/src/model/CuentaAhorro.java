package model;

import java.time.LocalDate;

import exceptions.ESaldoNoValido;


public class CuentaAhorro extends Cuenta implements FechaCalculable {
	
	private Double interesAnual;
	private Double ahorros;
	

	public CuentaAhorro(Integer numero, String titular, Double saldo, Double saldoMinimo,
			LocalDate fechaApertura, Double interesAnual, Double ahorros) throws ESaldoNoValido {
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
