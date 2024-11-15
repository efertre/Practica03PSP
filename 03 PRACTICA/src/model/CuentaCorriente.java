package model;

import java.time.LocalDate;

public class CuentaCorriente extends Cuenta implements FechaCalculable {

	private Double comisionMantenimiento;
	private TipoComision tipo;
	
	public CuentaCorriente(Integer numero, String titular, Double saldo, Double saldoMinimo,
			LocalDate fechaApertura, Double comisionMantenimiento, TipoComision tipo) {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);

		setComisionMantenimiento(comisionMantenimiento);
		setTipo(tipo);
	}

	public Double getComisionMantenimiento() {
		return comisionMantenimiento;
	}

	public void setComisionMantenimiento(Double comisionMantenimiento) {
		this.comisionMantenimiento = comisionMantenimiento;
	}

	public TipoComision getTipo() {
		return tipo;
	}

	public void setTipo(TipoComision tipo) {
		this.tipo = tipo;
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
