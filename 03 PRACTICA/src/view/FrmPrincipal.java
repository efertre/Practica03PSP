package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CtrlCuentas;
import controller.Lista;
import model.Cuenta;
import model.exceptions.ESaldoNoValido;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panPrincipal, panJList, panVerUnoxUno;

	private String ruta = "prueba.dat";

	private CtrlCuentas ctrl = new CtrlCuentas(ruta);
	private Lista<Cuenta> cuentas;

	private JMenu mnOpciones, mnVer, mnInsertar;
	private JMenuBar mnPrincipal;
	private JMenuItem mntmVaciarLista, mntmCargarTest, mntmCargarDatos, mntmGuardarDatos, mntmListar, mntmUnoXUno,
			mntmNewCtaAhorro, mntmNewCtaCorriente;

	private PanInsertarCuentaCorriente<Cuenta> panInsertarCuentaCorriente;
	private PanInsertarCuentaAhorro<Cuenta> panInsertarCuentaAhorro;

	/**
	 * Crea el frame
	 */
	public FrmPrincipal() {
		cuentas = ctrl.cargarDesdeFichero();
		if (cuentas == null) {
			cuentas = new Lista<>();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 380);

		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Práctica 03 - Ema y Paula");

		addComponents();
		addListeners();

	}

	private void addComponents() {

		panPrincipal = new JPanel();
		panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panPrincipal);
		panPrincipal.setLayout(new BorderLayout(0, 0));

		panVerUnoxUno = new PanVerUnoxUno(cuentas);

		mntmVaciarLista = new JMenuItem("Vaciar Lista");
		mntmVaciarLista.setHorizontalAlignment(SwingConstants.LEFT);

		mntmCargarTest = new JMenuItem("Cargar Test");
		mntmCargarTest.setHorizontalAlignment(SwingConstants.LEFT);

		mntmCargarDatos = new JMenuItem("Cargar Datos");
		mntmCargarDatos.setHorizontalAlignment(SwingConstants.LEFT);

		mntmGuardarDatos = new JMenuItem("Guardar Datos");
		mntmGuardarDatos.setHorizontalAlignment(SwingConstants.LEFT);

		mnOpciones = new JMenu("Opciones");

		mnOpciones.add(mntmVaciarLista);
		mnOpciones.add(mntmCargarTest);

		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);

		mnOpciones.add(mntmCargarDatos);
		mnOpciones.add(mntmGuardarDatos);

		mntmListar = new JMenuItem("Listar todos");

		mntmUnoXUno = new JMenuItem("Visualizar uno a uno");

		mnVer = new JMenu("Ver");
		mnVer.setHorizontalAlignment(SwingConstants.CENTER);

		mnVer.add(mntmListar);
		mnVer.add(mntmUnoXUno);

		mntmNewCtaAhorro = new JMenuItem("Nueva cuenta de ahorro");

		mntmNewCtaCorriente = new JMenuItem("Nueva cuenta corriente");

		mnInsertar = new JMenu("Insertar");

		mnInsertar.add(mntmNewCtaAhorro);
		mnInsertar.add(mntmNewCtaCorriente);

		mnPrincipal = new JMenuBar();
		mnPrincipal.add(mnOpciones);
		mnPrincipal.add(mnVer);
		mnPrincipal.add(mnInsertar);

		panPrincipal.add(mnPrincipal, BorderLayout.NORTH);

		panPrincipal.add(panVerUnoxUno, BorderLayout.CENTER);

	}

	private void addListeners() {

		mntmVaciarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = new Lista<>(); // Lista vacía
				((PanVerUnoxUno) panVerUnoxUno).actualizarLista(cuentas);

				// Si el panel de listado ya existe, actualízalo con la nueva lista
				if (panJList instanceof PanelJLista) {
					((PanelJLista) panJList).actualizarLista(cuentas);
				}
			}
		});

		mntmCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = ctrl.cargarDesdeFichero();

				((PanVerUnoxUno) panVerUnoxUno).actualizarLista(cuentas);

				// Si el panel de listado ya existe, actualízalo con la nueva lista
				if (panJList instanceof PanelJLista) {
					((PanelJLista) panJList).actualizarLista(cuentas);
				}

			}
		});

		mntmGuardarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cuentas == null) {
					JOptionPane.showMessageDialog(FrmPrincipal.this, "No hay cuentas en la lista para guardar.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ctrl.guardarEnFichero(cuentas);
				JOptionPane.showMessageDialog(FrmPrincipal.this,
						"Los datos se han guardado correctamente en el archivo.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		mntmCargarTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					cuentas = ctrl.cargarTest();
					((PanVerUnoxUno) panVerUnoxUno).actualizarLista(cuentas);

					// Si el panel de uno por uno ya existe, actualízalo con la nueva lista
					if (panJList instanceof PanelJLista) {
						((PanelJLista) panJList).actualizarLista(cuentas);
					}
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mntmUnoXUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panVerUnoxUno = new PanVerUnoxUno(cuentas);
				cambiarPanel(panVerUnoxUno);
			}
		});

		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panJList = new PanelJLista(cuentas);
				cambiarPanel(panJList);
			}
		});

		mntmNewCtaAhorro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Crear una nueva instancia del panel de insertar cuenta corriente
				panInsertarCuentaAhorro = new PanInsertarCuentaAhorro<>(cuentas);
				// Cambiar el panel en el centro del JFrame
				cambiarPanel(panInsertarCuentaAhorro);
			}
		});

		mntmNewCtaCorriente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Crear una nueva instancia del panel de insertar cuenta corriente
				panInsertarCuentaCorriente = new PanInsertarCuentaCorriente<>(cuentas);
				// Cambiar el panel en el centro del JFrame
				cambiarPanel(panInsertarCuentaCorriente);
			}
		});

	}

	private void cambiarPanel(JPanel nuevoPanel) {
		panPrincipal.removeAll(); // Remueve todos los componentes actuales
		panPrincipal.add(mnPrincipal, BorderLayout.NORTH); // Vuelve a añadir la barra de menú
		panPrincipal.add(nuevoPanel, BorderLayout.CENTER); // Añade el nuevo panel
		panPrincipal.revalidate();
		panPrincipal.repaint();
	}

}
