package view;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Cuenta;


public class PanelJLista extends JPanel {

    private JScrollPane scrollPane;
    private JList<String> jList1;

    public PanelJLista(List<Cuenta> lista) {
        setLayout(null);

        System.out.println("Se ha creado el panel");
        initComponents(lista);
    }

    private void initComponents(List<Cuenta> lista) {
        scrollPane = new JScrollPane();
        jList1 = new JList<>();

        // Configurar el JScrollPane y JList
        jList1.setModel(modeloLista(lista));
        scrollPane.setViewportView(jList1);
        scrollPane.setBounds(0, 19, 450, 281);

        add(scrollPane); // Añadir JScrollPane al panel

        System.out.println("Se ha configurado el JList y JScrollPane");
    }

    private DefaultListModel<String> modeloLista(List<Cuenta> lista) {
        DefaultListModel<String> modelo = new DefaultListModel<>();

        System.out.println("Se están metiendo los datos");

        if (lista == null || lista.isEmpty()) {
            modelo.addElement("No hay elementos");
        } else {
            for (Cuenta cuenta : lista) {
                String infoObjeto = "Información de la cuenta: " + cuenta.toString();
                modelo.addElement(infoObjeto);
            }
        }

        return modelo;
    }
}
