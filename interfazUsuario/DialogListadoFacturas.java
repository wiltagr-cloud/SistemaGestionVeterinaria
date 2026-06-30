package interfazUsuario;

import control.ControlClientes;
import control.ControlFacturas;
import objetosNegocio.Cliente;
import objetosNegocio.Factura;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class DialogListadoFacturas extends JFrame {

    private ControlFacturas controlFacturas = new ControlFacturas();
    private ControlClientes controlClientes = new ControlClientes();

    private JComboBox<ItemCombo> cbClientes;
    private JTextField txtBuscarCliente;
    private JButton btnBuscar;
    private JButton btnMostrarTodas;
    private JTable tablaFacturas;
    private DefaultTableModel modeloFacturas;
    private JTextArea txtResumen;

    public DialogListadoFacturas() {

        setTitle("Listado de Facturas");
        setSize(950, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        cargarClientes();
        cargarFacturas();
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelBusqueda = new JPanel(new GridLayout(2, 1, 5, 5));
        panelBusqueda.setBorder(new TitledBorder("Buscar facturas por cliente"));

        JPanel filaCliente = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));

        cbClientes = new JComboBox<>();
        cbClientes.setPreferredSize(new Dimension(350, 26));

        btnBuscar = new JButton("Buscar");
        btnMostrarTodas = new JButton("Mostrar todas");

        filaCliente.add(new JLabel("Cliente:"));
        filaCliente.add(cbClientes);
        filaCliente.add(btnBuscar);
        filaCliente.add(btnMostrarTodas);

        JPanel filaTexto = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));

        txtBuscarCliente = new JTextField();
        txtBuscarCliente.setPreferredSize(new Dimension(350, 26));

        filaTexto.add(new JLabel("Nombre o cedula:"));
        filaTexto.add(txtBuscarCliente);

        panelBusqueda.add(filaCliente);
        panelBusqueda.add(filaTexto);

        add(panelBusqueda, BorderLayout.NORTH);

        modeloFacturas = new DefaultTableModel(
                new Object[]{
                        "ID Factura",
                        "Fecha",
                        "ID Cliente",
                        "Cliente",
                        "Cedula",
                        "ID Consulta",
                        "Pagador",
                        "Cedula Pagador",
                        "IVA",
                        "Total"
                }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaFacturas = new JTable(modeloFacturas);
        tablaFacturas.setRowHeight(25);
        tablaFacturas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JSplitPane split = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tablaFacturas),
                new JScrollPane(txtResumen));

        split.setResizeWeight(0.72);

        add(split, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarFacturas());
        btnMostrarTodas.addActionListener(e -> cargarFacturas());

        tablaFacturas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarResumenSeleccionado();
            }
        });
    }

    private void cargarClientes() {

        try {

            cbClientes.removeAllItems();
            cbClientes.addItem(
                    new ItemCombo(
                            0,
                            "Todos los clientes"));

            for (Cliente cliente : controlClientes.consultar()) {
                cbClientes.addItem(
                        new ItemCombo(
                                cliente.getIdCliente(),
                                cliente.getNombre()
                                        + " - "
                                        + cliente.getCedula()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarFacturas() {

        try {

            txtBuscarCliente.setText("");
            cbClientes.setSelectedIndex(0);

            cargarTabla(
                    controlFacturas.consultar());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void buscarFacturas() {

        try {

            String texto =
                    txtBuscarCliente.getText().trim().toLowerCase();

            ItemCombo item =
                    (ItemCombo) cbClientes.getSelectedItem();

            if (texto.isEmpty()
                    && item != null
                    && item.getId() > 0) {

                cargarTabla(
                        controlFacturas.consultarPorCliente(
                                item.getId()));
                return;
            }

            if (texto.isEmpty()) {
                cargarFacturas();
                return;
            }

            ArrayList<Factura> resultado =
                    new ArrayList<>();

            for (Cliente cliente : controlClientes.consultar()) {

                String nombre =
                        cliente.getNombre() == null
                                ? ""
                                : cliente.getNombre().toLowerCase();

                String cedula =
                        cliente.getCedula() == null
                                ? ""
                                : cliente.getCedula().toLowerCase();

                if (nombre.contains(texto)
                        || cedula.contains(texto)) {

                    resultado.addAll(
                            controlFacturas.consultarPorCliente(
                                    cliente.getIdCliente()));
                }
            }

            cargarTabla(resultado);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarTabla(ArrayList<Factura> facturas) {

        modeloFacturas.setRowCount(0);

        for (Factura factura : facturas) {

            Cliente cliente =
                    obtenerClienteSeguro(
                            factura.getIdCliente());

            String nombreCliente =
                    cliente == null
                            ? ""
                            : cliente.getNombre();

            String cedulaCliente =
                    cliente == null
                            ? ""
                            : cliente.getCedula();

            modeloFacturas.addRow(new Object[]{
                    factura.getIdFactura(),
                    factura.getFecha(),
                    factura.getIdCliente(),
                    nombreCliente,
                    cedulaCliente,
                    factura.getIdConsulta(),
                    textoSeguro(factura.getNombrePagador()),
                    textoSeguro(factura.getCedulaPagador()),
                    moneda(factura.getIvaTotal()),
                    moneda(factura.getTotal())
            });
        }

        txtResumen.setText(
                "Facturas encontradas: " + facturas.size());
    }

    private Cliente obtenerClienteSeguro(int idCliente) {

        try {
            return controlClientes.obtenerPorId(idCliente);
        } catch (Exception e) {
            return null;
        }
    }

    private void mostrarResumenSeleccionado() {

        int fila =
                tablaFacturas.getSelectedRow();

        if (fila < 0) {
            return;
        }

        int modeloFila =
                tablaFacturas.convertRowIndexToModel(fila);

        StringBuilder sb =
                new StringBuilder();

        sb.append("Factura #")
                .append(modeloFacturas.getValueAt(modeloFila, 0))
                .append('\n');

        sb.append("Fecha: ")
                .append(modeloFacturas.getValueAt(modeloFila, 1))
                .append('\n');

        sb.append("Cliente: ")
                .append(modeloFacturas.getValueAt(modeloFila, 3))
                .append(" - ")
                .append(modeloFacturas.getValueAt(modeloFila, 4))
                .append('\n');

        sb.append("Consulta: ")
                .append(modeloFacturas.getValueAt(modeloFila, 5))
                .append('\n');

        sb.append("Pagador: ")
                .append(modeloFacturas.getValueAt(modeloFila, 6))
                .append('\n');

        sb.append("Cedula pagador: ")
                .append(modeloFacturas.getValueAt(modeloFila, 7))
                .append('\n');

        sb.append("IVA: ")
                .append(modeloFacturas.getValueAt(modeloFila, 8))
                .append('\n');

        sb.append("Total: ")
                .append(modeloFacturas.getValueAt(modeloFila, 9));

        txtResumen.setText(sb.toString());
    }

    private String textoSeguro(String texto) {

        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        return texto;
    }

    private String moneda(double valor) {

        return String.format(
                Locale.US,
                "$%.2f",
                valor);
    }
}