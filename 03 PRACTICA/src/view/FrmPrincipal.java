package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import controller.CtrlCuentas;
import model.Cuenta;
import model.exceptions.ESaldoNoValido;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private String ruta = "prueba.dat";
	private CtrlCuentas ctrl = new CtrlCuentas(ruta);
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	

	/**
	 * Crea el frame
	 */
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(null);	
		setTitle("Práctica 03 - Ema y Paula");
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar mnPrincipal = new JMenuBar();
		contentPane.add(mnPrincipal, BorderLayout.NORTH);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnPrincipal.add(mnOpciones);
		
		JMenuItem mntmVaciarLista = new JMenuItem("Vaciar Lista");
		mnOpciones.add(mntmVaciarLista);
		mntmVaciarLista.setHorizontalAlignment(SwingConstants.LEFT);
		
		JMenuItem mntmCargarTest = new JMenuItem("Cargar Test");
		mntmCargarTest.setHorizontalAlignment(SwingConstants.LEFT);
		mnOpciones.add(mntmCargarTest);
		
		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);
		
		JMenuItem mntmCargarDatos = new JMenuItem("Cargar Datos");
		mntmCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = ctrl.cargarDesdeFichero();
				
				for (Cuenta cuenta : cuentas) {
					System.out.println(cuenta);
				}
			}
		});
		mnOpciones.add(mntmCargarDatos);
		mntmCargarDatos.setHorizontalAlignment(SwingConstants.LEFT);
		
		JMenuItem mntmGuardarDatos = new JMenuItem("Guardar Datos");
		mntmGuardarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cuenta c;
				try {
					c = new Cuenta(1, "Prueba", 200.0, 100.0, LocalDate.now());
					cuentas.add(c);
					ctrl.guardarEnFichero(cuentas);
					
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		mnOpciones.add(mntmGuardarDatos);
		mntmGuardarDatos.setHorizontalAlignment(SwingConstants.LEFT);
		
		JMenu mnVer = new JMenu("Ver");
		mnVer.setHorizontalAlignment(SwingConstants.CENTER);
		mnPrincipal.add(mnVer);
		
		JMenuItem mntmListar = new JMenuItem("Listar todos");
		mnVer.add(mntmListar);
		
		JMenuItem mntmUnoXUno = new JMenuItem("Visualizar uno a uno");
		mnVer.add(mntmUnoXUno);
		
		JMenu mnInsertar = new JMenu("Insertar");
		mnPrincipal.add(mnInsertar);
		
		JMenuItem mntmNewCtaAhorro = new JMenuItem("Nueva cuenta de ahorro");
		mnInsertar.add(mntmNewCtaAhorro);
		
		JMenuItem mntmNewCtaCorriente = new JMenuItem("Nueva cuenta corriente");
		mnInsertar.add(mntmNewCtaCorriente);
	}
}
