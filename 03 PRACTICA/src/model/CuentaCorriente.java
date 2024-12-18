package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.exceptions.ESaldoNoValido;

public class CuentaCorriente extends Cuenta implements FechaCalculable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double comisionMantenimiento;
	private TipoComision tipo;

	public CuentaCorriente(Integer numero, String titular, Double saldo, LocalDate fechaApertura,
			Double comisionMantenimiento, TipoComision tipo) throws ESaldoNoValido {
		super(numero, titular, saldo, fechaApertura);

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
	public String toString() {
	    return String.format("Cuenta Corriente: " +
	                         "Comisión de Mantenimiento: %.2f " +
	                         "Tipo de Comisión: %s " +
	                         "%s", 
	                         comisionMantenimiento, 
	                         tipo, 
	                         super.toString());
	}


}
