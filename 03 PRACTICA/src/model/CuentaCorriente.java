package model;

import java.time.LocalDate;

import exceptions.ESaldoNoValido;

public class CuentaCorriente extends Cuenta implements FechaCalculable {

	private Double comisionMantenimiento;
	private TipoComision tipo;
	
	public CuentaCorriente(Integer numero, String titular, Double saldo, Double saldoMinimo,
			LocalDate fechaApertura, Double comisionMantenimiento, TipoComision tipo) throws ESaldoNoValido {
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
