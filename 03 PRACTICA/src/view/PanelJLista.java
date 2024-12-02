package view;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Lista;
import model.Cuenta;


public class PanelJLista extends JPanel {

    private JScrollPane scrollPane;
    private JList<String> jList1;

    public PanelJLista(Lista<Cuenta> lista) {
        setLayout(null);
        initComponents(lista);
    }

    private void initComponents(Lista<Cuenta> lista) {
        scrollPane = new JScrollPane();
        jList1 = new JList<>();

        // Configurar el JScrollPane y JList
        actualizarLista(lista);
        scrollPane.setViewportView(jList1);
        scrollPane.setBounds(54, 53, 224, 175);

        add(scrollPane); // Añadir JScrollPane al panel

        
    }

	public void actualizarLista(Lista<Cuenta> lista) {
		jList1.setModel(modeloLista(lista));
	}

	private DefaultListModel<String> modeloLista(Lista<Cuenta> lista) {
	    DefaultListModel<String> modelo = new DefaultListModel<>();
	    if(lista != null) {
	    // Nodo auxiliar para recorrer la lista personalizada
	    Lista<Cuenta>.Node<Cuenta> nodoActual = lista.inicio;
	    while (nodoActual != null) {
	        Cuenta cuenta = nodoActual.getPrincipal(); // Obtiene el objeto Cuenta del nodo
	        String infoObjeto = "Información de la cuenta: " + cuenta.toString(); // Formatea la información
	        modelo.addElement(infoObjeto); // Añade la información al modelo
	        nodoActual = nodoActual.getSiguiente(); // Avanza al siguiente nodo
	    }
	    }
	    return modelo;
	}

}
