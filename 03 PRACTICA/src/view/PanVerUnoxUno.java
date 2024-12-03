package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CtrlCuentas;
import controller.Lista;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;

public class PanVerUnoxUno<E> extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfNumero, tfTitular, tfSaldo, tfFecha, tfComMant, tfTipo;
	private JLabel lblTitular, lblNumero, lblSaldo, lblFecha, lblComMant, lblTipo;
	private JButton btnAnterior, btnSiguiente, btnCalcular;

	private Lista<E> lista; // Lista personalizada
	private Lista<E>.Node<E> nodoActual; // Nodo actualmente seleccionado

	public PanVerUnoxUno(Lista<E> lista) {
		this.lista = lista;
		this.nodoActual = lista.getInicio(); // Comenzamos en el inicio de la lista
		setLayout(null);

		addComponents();
		addListeners();

		actualizarVista(); // Muestra el contenido del nodo actual al inicio
	}

	private void addComponents() {

		lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(220, 38, 51, 16);
		add(lblTitular);

		lblNumero = new JLabel("Num de cta:");
		lblNumero.setBounds(49, 38, 82, 16);
		add(lblNumero);

		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBounds(106, 96, 76, 16);
		add(lblSaldo);

		lblFecha = new JLabel("Fecha de apertura:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(67, 129, 115, 16);
		add(lblFecha);

		tfNumero = new JTextField();
		tfNumero.setEditable(false);
		tfNumero.setBounds(131, 33, 51, 26);
		add(tfNumero);
		tfNumero.setColumns(10);

		tfTitular = new JTextField();
		tfTitular.setEditable(false);
		tfTitular.setColumns(10);
		tfTitular.setBounds(269, 33, 139, 26);
		add(tfTitular);

		tfSaldo = new JTextField();
		tfSaldo.setEditable(false);
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(209, 92, 139, 26);
		add(tfSaldo);

		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setColumns(10);
		tfFecha.setBounds(209, 125, 139, 26);
		add(tfFecha);

		lblComMant = new JLabel("Comisión de Mantemiento:");
		lblComMant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComMant.setBounds(21, 167, 163, 16);
		add(lblComMant);

		tfComMant = new JTextField();
		tfComMant.setEditable(false);
		tfComMant.setColumns(10);
		tfComMant.setBounds(209, 163, 139, 26);
		add(tfComMant);

		lblTipo = new JLabel("Tipo de comisión:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(69, 205, 113, 16);
		add(lblTipo);

		tfTipo = new JTextField();
		tfTipo.setEditable(false);
		tfTipo.setColumns(10);
		tfTipo.setBounds(209, 201, 139, 26);
		add(tfTipo);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(34, 271, 87, 29);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(312, 271, 102, 29);
		add(btnSiguiente);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(182, 271, 87, 29);
		add(btnCalcular);
	}

	private void addListeners() {

		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nodoActual != null && nodoActual != lista.getInicio()) {
					Lista<E>.Node<E> anterior = lista.getInicio();
					while (anterior.getSiguiente() != nodoActual) {
						anterior = anterior.getSiguiente();
					}
					nodoActual = anterior;
					actualizarVista();
				}
			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nodoActual != null && nodoActual.getSiguiente() != null) {
					nodoActual = nodoActual.getSiguiente();
					actualizarVista();
				}
			}
		});

		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nodoActual != null) {
					Cuenta cuenta = (Cuenta) nodoActual.getPrincipal(); // Obtener la cuenta actual
					// Comprobar si ha pasado un mes o un año
					if (debeCalcular(cuenta)) {
						// Llamar al método que realiza el cálculo de saldo
						cuenta.calcularSaldo(cuenta);

						// Si el saldo es válido, actualizar el saldo en la UI o la lista
						actualizarSaldo(cuenta);

						CtrlCuentas ctrl = new CtrlCuentas("prueba.dat");
						ctrl.guardarEnFichero((Lista<Cuenta>) lista);

					}
				}
			}
		});

	}

	private boolean debeCalcular(Cuenta cuenta) {
		LocalDate hoy = LocalDate.now();
		boolean esDiaDeMes = cuenta.getFechaApertura().getDayOfMonth() == hoy.getDayOfMonth();
		boolean esDiaDeAnio = esDiaDeMes && cuenta.getFechaApertura().getMonthValue() == hoy.getMonthValue();

		return esDiaDeMes || esDiaDeAnio;
	}

	public void actualizarSaldo(Cuenta cuenta) {
		tfSaldo.setText(String.valueOf(cuenta.getSaldo()));
	}

	private void actualizarVista() {
		if (nodoActual == null || nodoActual.getPrincipal() == null) {
			// Si no hay cuentas, muestra mensaje de error y deshabilita navegación
			limpiarCampos();
			JOptionPane.showMessageDialog(this, "No hay cuentas para mostrar.", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			btnAnterior.setEnabled(false);
			btnSiguiente.setEnabled(false);
			return;
		}

		// Obtiene la cuenta actual y actualiza los campos
		Cuenta cuenta = (Cuenta) nodoActual.getPrincipal();
		tfNumero.setText(cuenta.getNumero().toString());
		tfTitular.setText(cuenta.getTitular());
		tfSaldo.setText(String.valueOf(cuenta.getSaldo()));
		tfFecha.setText(cuenta.getFechaApertura().toString());

		if (cuenta instanceof CuentaCorriente) {
			lblComMant.setText("Comisión de Mantenimiento:");
			lblTipo.setText("Tipo de Comisión:");

			tfComMant.setText(((CuentaCorriente) cuenta).getComisionMantenimiento().toString());
			tfTipo.setText(((CuentaCorriente) cuenta).getTipo().toString());
		}

		if (cuenta instanceof CuentaAhorro) {
			lblComMant.setText("Ahorros:");
			lblTipo.setText("Interés anual:");

			tfComMant.setText(((CuentaAhorro) cuenta).getAhorros().toString());
			tfTipo.setText(((CuentaAhorro) cuenta).getInteresAnual().toString());
		}

		// Habilita/Deshabilita botones según sea necesario
		btnAnterior.setEnabled(lista.getInicio() != nodoActual);
		btnSiguiente.setEnabled(nodoActual.getSiguiente() != null);
	}

	private void limpiarCampos() {
		tfNumero.setText("");
		tfTitular.setText("");
		tfSaldo.setText("");
		tfFecha.setText("");
		tfComMant.setText("");
		tfTipo.setText("");
	}

	public void actualizarLista(Lista<Cuenta> nuevaLista) {
		this.lista = (Lista<E>) nuevaLista;
		this.nodoActual = (nuevaLista != null) ? (Lista<E>.Node<E>) nuevaLista.getInicio() : null;
		actualizarVista(); // Muestra la primera cuenta de la nueva lista
	}
}
