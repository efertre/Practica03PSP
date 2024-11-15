package model;

import java.time.LocalDate;


public class CuentaAhorro extends Cuenta implements FechaCalculable {
	
	private Double interesAnual;
	private Double ahorros;
	

	public CuentaAhorro(Integer numero, String titular, Double saldo, Double saldoMinimo,
			LocalDate fechaApertura, Double interesAnual, Double ahorros) {
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


	@Override
	public int cumplirMes() {
		
		
		int dias = ((FechaCalculable.ANYO - super.getFechaApertura().getYear()) * 365 +
				(FechaCalculable.MES - super.getFechaApertura().getMonthValue()) * 30 +
				(FechaCalculable.DIA_DEL_MES - super.getFechaApertura().getDayOfMonth()));
		
		
		return dias / 30;
	}


	@Override
	public int cumplirAnyo() {
		int dias = ((FechaCalculable.ANYO - super.getFechaApertura().getYear()) * 365 +
				(FechaCalculable.MES - super.getFechaApertura().getMonthValue()) * 30 +
				(FechaCalculable.DIA_DEL_MES - super.getFechaApertura().getDayOfMonth()));
		
		
		return dias / 365;
	}

	
	
}
