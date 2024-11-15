package model;

import java.util.GregorianCalendar;

public class CuentaCorriente extends Cuenta {

	private Double comisionMantenimiento;
	private TipoComision tipo;
	
	public CuentaCorriente(Integer numero, String titular, Double saldo, Double saldoMinimo,
			GregorianCalendar fechaApertura, Double comisionMantenimiento, TipoComision tipo) {
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

	
}
