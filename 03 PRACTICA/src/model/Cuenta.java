package model;

import java.time.LocalDate;

import model.exceptions.ESaldoNoValido;

public class Cuenta implements FechaCalculable {

	private Integer numero;
	private transient String titular;
	private Double saldo;
	private Double saldoMinimo;
	private LocalDate fechaApertura;

	public Cuenta(Integer numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura) throws ESaldoNoValido {
		super();
		setNumero(numero);
		setTitular(titular);
		setSaldo(saldo);
		setSaldoMinimo(saldoMinimo);
		setFechaApertura(fechaApertura);

	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		if (numero >= 1 && numero <= 1000)
			this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) throws ESaldoNoValido {
		if (saldo < saldoMinimo)
			throw new ESaldoNoValido("Saldo no válido [SALDO INFERIOR QUE EL SALDO MÍNIMO]");
		this.saldo = saldo;
	}

	public Double getSaldoMinimo() {
		return saldoMinimo;
	}

	public void setSaldoMinimo(Double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Override
	public int cumplirMes() {

		int dias = ((FechaCalculable.ANYO - getFechaApertura().getYear()) * 365 +
				(FechaCalculable.MES - getFechaApertura().getMonthValue()) * 30 +
				(FechaCalculable.DIA_DEL_MES - getFechaApertura().getDayOfMonth()));
		
		
		return dias / 30;
	}

	@Override
	public int cumplirAnyo() {
		int dias = ((FechaCalculable.ANYO - getFechaApertura().getYear()) * 365 +
				(FechaCalculable.MES - getFechaApertura().getMonthValue()) * 30 +
				(FechaCalculable.DIA_DEL_MES - getFechaApertura().getDayOfMonth()));
		
		
		return dias / 365;
	}

}
