package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CtrlCuentas;
import controller.Lista;
import model.Cuenta;
import model.CuentaCorriente;
import model.TipoComision;
import model.exceptions.EFechaNoValida;
import model.exceptions.ESaldoNoValido;

public class PanInsertarCuentaCorriente<E> extends JPanel {

	private static final long serialVersionUID = 1L;

	// Campos de entrada
	private JTextField tfNumero, tfTitular, tfSaldo, tfFecha, tfComMant;
	private JLabel lblTitular, lblNumero, lblSaldo, lblFecha, lblComMant, lblTipo;
	private JButton btnInsertar;
	private JComboBox<TipoComision> cbTipo;
	private Lista<E> lista; // Lista personalizada
	private CtrlCuentas ctrl;

	public PanInsertarCuentaCorriente(Lista<E> lista) {
		ctrl = new CtrlCuentas("prueba.dat");
		this.lista = lista;
		setLayout(null);
		addComponents();
		addListeners();
	}

	// Añadir componentes (campos de texto y etiquetas)
	private void addComponents() {
		lblTitular = new JLabel("Titular:");
		lblTitular.setBounds(220, 38, 51, 16);
		add(lblTitular);

		lblNumero = new JLabel("Num de cta:");
		lblNumero.setBounds(49, 38, 82, 16);
		add(lblNumero);

		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBounds(123, 96, 76, 16);
		add(lblSaldo);

		lblFecha = new JLabel("Fecha de apertura (yyyy-mm-dd):");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(-15, 129, 214, 16);
		add(lblFecha);

		lblComMant = new JLabel("Comisión de Mantenimiento:");
		lblComMant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComMant.setBounds(36, 167, 163, 16);
		add(lblComMant);

		lblTipo = new JLabel("Tipo de comisión:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(86, 205, 113, 16);
		add(lblTipo);

		// Crear los campos de texto
		tfNumero = new JTextField();
		tfNumero.setBounds(131, 33, 51, 26);
		add(tfNumero);

		tfTitular = new JTextField();
		tfTitular.setBounds(269, 33, 79, 26);
		add(tfTitular);

		tfSaldo = new JTextField();
		tfSaldo.setBounds(209, 92, 139, 26);
		add(tfSaldo);

		tfFecha = new JTextField();

		tfFecha.setBounds(209, 125, 139, 26);
		add(tfFecha);

		tfComMant = new JTextField();
		tfComMant.setBounds(209, 163, 139, 26);
		add(tfComMant);

		// Botón Insertar
		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(183, 261, 87, 29);
		add(btnInsertar);

		cbTipo = new JComboBox<>(TipoComision.values());
		cbTipo.setBounds(211, 201, 137, 27);
		add(cbTipo);

	}

	// Añadir el listener al botón para insertar la cuenta
	private void addListeners() {
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtener los datos ingresados
					Integer numero = Integer.parseInt(tfNumero.getText());
					String titular = tfTitular.getText();
					Double saldo = Double.parseDouble(tfSaldo.getText());
					LocalDate fecha = LocalDate.parse(tfFecha.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
					Double comisionMantenimiento = Double.parseDouble(tfComMant.getText());

					// Lanzar exepcion de saldo si es negativo
					if (saldo < 0) {
						throw new ESaldoNoValido("El saldo no debe ser negativo");
					} else if (saldo < Cuenta.saldoMinimo) {
						throw new ESaldoNoValido("El saldo no debe ser menor a " + Cuenta.saldoMinimo);

					}

					// Lanzar exepcion de fecha si es futura
					if (CtrlCuentas.comprobarFechaFutura(fecha))
						throw new EFechaNoValida();

					TipoComision tipoComision = (TipoComision) cbTipo.getSelectedItem();

					// Lanzar exepcion de saldo si es negativo
					if (saldo < 0) {
						throw new ESaldoNoValido("El saldo no debe ser negativo");
					} else if (saldo < Cuenta.saldoMinimo) {
						throw new ESaldoNoValido("El saldo no debe ser menor a " + Cuenta.saldoMinimo);

					}

					// Lanzar exepcion de fecha si es futura
					if (CtrlCuentas.comprobarFechaFutura(fecha))
						throw new EFechaNoValida();

					// Crear una nueva cuenta corriente
					CuentaCorriente nuevaCuenta = new CuentaCorriente(numero, titular, saldo, fecha,
							comisionMantenimiento, tipoComision);

					// Insertar la cuenta en la lista
					lista.insertarNodo((E) nuevaCuenta);

					// Guardar la lista de cuentas en el archivo

					ctrl.guardarEnFichero((Lista<Cuenta>) lista);

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this, "Cuenta insertada correctamente.",
							"Éxito", JOptionPane.INFORMATION_MESSAGE);

					// Limpiar los campos después de la inserción
					limpiarCampos();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this,
							"Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ESaldoNoValido ex) {
					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this, ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);

				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this,
							"El formato de la fecha es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);

				} catch (EFechaNoValida ex) {

					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this, "La fecha no debe ser futura.",
							"Error", JOptionPane.ERROR_MESSAGE);

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(PanInsertarCuentaCorriente.this, "Error al insertar la cuenta.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

	}

	// Limpiar los campos después de la inserción
	private void limpiarCampos() {
		tfNumero.setText("");
		tfTitular.setText("");
		tfSaldo.setText("");
		tfFecha.setText("");
		tfComMant.setText("");
	}
}
