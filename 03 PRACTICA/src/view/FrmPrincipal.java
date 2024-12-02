package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

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
	private JPanel panPrincipal;
	
	private String ruta = "prueba.dat";

	private CtrlCuentas ctrl = new CtrlCuentas(ruta);
	private Lista<Cuenta> cuentas = new Lista<>() ;
	
	private PanelJLista panJList;
	private JMenu mnOpciones, mnVer, mnInsertar;
	private JMenuBar mnPrincipal;
	private JMenuItem mntmVaciarLista, mntmCargarTest, mntmCargarDatos, mntmGuardarDatos, mntmListar, mntmUnoXUno, mntmNewCtaAhorro,  mntmNewCtaCorriente;
	
	

	/**
	 * Crea el frame
	 */
	public FrmPrincipal() {
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		setResizable(false);
		setLocationRelativeTo(null);	
		setTitle("Práctica 03 - Ema y Paula");
		

		addComponents();
		addListeners();
		

		
		
	}




	private void addComponents() {
		
		if(cuentas == null) {
		cuentas = ctrl.cargarDesdeFichero();
		}
		
		panPrincipal = new JPanel();
		panPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panPrincipal);
		panPrincipal.setLayout(new BorderLayout(0, 0));

		panJList = new PanelJLista(cuentas);
		
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

		

		panPrincipal.add(panJList, BorderLayout.CENTER);
		
		

	}
	
	private void addListeners() {
		
		mntmVaciarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = null;
				panJList.actualizarLista(cuentas);
			}
		});
		

		mntmCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentas = ctrl.cargarDesdeFichero();
				
				
				
				
				panJList.actualizarLista(cuentas);
				
			}
		});
		
		
		
		mntmGuardarDatos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (cuentas == null) { // Verificar si la lista está vacía
		            JOptionPane.showMessageDialog(
		                FrmPrincipal.this,
		                "No hay cuentas en la lista para guardar.",
		                "Error",
		                JOptionPane.ERROR_MESSAGE
		            );
		            return;
		        }
		        
		        try {
					cuentas.insertarNodo(new Cuenta(1, "Prueba", 200.0, 100.0, LocalDate.now()));
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        // Intentar guardar la lista en el archivo
				ctrl.guardarEnFichero(cuentas);

				JOptionPane.showMessageDialog(
				    FrmPrincipal.this,
				    "Los datos se han guardado correctamente en el archivo.",
				    "Éxito",
				    JOptionPane.INFORMATION_MESSAGE
				);
		    }
		});
		
		
		
		mntmListar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        panJList = new PanelJLista(cuentas);


		        // Añadir el nuevo panel
		        panPrincipal.add(panJList, BorderLayout.CENTER);

		        // Actualizar la interfaz
		        panPrincipal.revalidate();
		        panPrincipal.repaint();
		    }
		});
		
		mntmCargarTest.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        try {
					
		        	cuentas = ctrl.cargarTest();
					panJList.actualizarLista(cuentas);
					
				} catch (ESaldoNoValido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		mntmNewCtaAhorro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		mntmNewCtaCorriente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
			
	}

}
