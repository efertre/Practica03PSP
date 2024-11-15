package model;

import java.util.GregorianCalendar;

public class Cuenta {

	private Integer numero;
	private transient String titular;
	private Double saldo;
	private Double saldoMinimo;
	private GregorianCalendar fechaApertura;
	
	
	
	
	
	public Cuenta(Integer numero, String titular, Double saldo, Double saldoMinimo, GregorianCalendar fechaApertura) {
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
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getSaldoMinimo() {
		return saldoMinimo;
	}
	public void setSaldoMinimo(Double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}
	public GregorianCalendar getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(GregorianCalendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	
	
}
