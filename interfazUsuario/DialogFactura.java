package interfazUsuario;

import control.*;
import objetosNegocio.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class DialogFactura extends JDialog {

    private Consulta consulta;

    private ControlFacturas controlFacturas = new ControlFacturas();
    private ControlDetalleFacturas controlDetalleFacturas = new ControlDetalleFacturas();
    private ControlConsultaServicio controlConsultaServicios = new ControlConsultaServicio();
    private ControlServicios controlServicios = new ControlServicios();
    private ControlDetalleTratamientos controlDetalleTratamientos = new ControlDetalleTratamientos();
    private ControlMedicamentos controlMedicamentos = new ControlMedicamentos();
    private ControlMascotas controlMascotas = new ControlMascotas();
    private ControlClientes controlClientes = new ControlClientes();

    private Cliente clienteDuenio;
    private Factura factura;
    private ArrayList<DetalleFactura> detalles = new ArrayList<>();

    private JRadioButton rbDuenioSi;
    private JRadioButton rbDuenioNo;
    private JTextField txtNombrePagador;
    private JTextField txtCedulaPagador;
    private JTable tablaResumen;
    private DefaultTableModel modeloResumen;
    private JTextArea txtDetalle;
    private JComboBox<ItemCombo> cbServicios;
    private JSpinner spCantidadServicio;
    private JButton btnAgregarServicio;
    private JButton btnQuitarDetalle;
    private JButton btnGuardar;

    public DialogFactura(Window owner, Consulta consulta) {

        super(owner, "Factura", ModalityType.APPLICATION_MODAL);
        this.consulta = consulta;

        setSize(900, 650);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        cargarFactura();
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelPagador = new JPanel(new GridLayout(3, 2, 8, 8));
        panelPagador.setBorder(new TitledBorder("Pagador"));

        rbDuenioSi = new JRadioButton("Si", true);
        rbDuenioNo = new JRadioButton("No");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbDuenioSi);
        grupo.add(rbDuenioNo);

        txtNombrePagador = new JTextField();
        txtCedulaPagador = new JTextField();

        panelPagador.add(new JLabel("El pagador es el dueno de la mascota?"));
        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radios.add(rbDuenioSi);
        radios.add(rbDuenioNo);
        panelPagador.add(radios);
        panelPagador.add(new JLabel("Nombre pagador:"));
        panelPagador.add(txtNombrePagador);
        panelPagador.add(new JLabel("Cedula pagador:"));
        panelPagador.add(txtCedulaPagador);

        add(panelPagador, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(8, 8));

        JPanel panelServicios = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panelServicios.setBorder(new TitledBorder("Agregar servicio a la factura"));

        cbServicios = new JComboBox<>();
        cbServicios.setPreferredSize(new Dimension(340, 26));

        spCantidadServicio = new JSpinner(
                new SpinnerNumberModel(1, 1, 999, 1));

        btnAgregarServicio = new JButton("Agregar");
        btnQuitarDetalle = new JButton("Quitar seleccionado");

        panelServicios.add(new JLabel("Servicio:"));
        panelServicios.add(cbServicios);
        panelServicios.add(new JLabel("Cantidad:"));
        panelServicios.add(spCantidadServicio);
        panelServicios.add(btnAgregarServicio);
        panelServicios.add(btnQuitarDetalle);

        panelCentro.add(panelServicios, BorderLayout.NORTH);

        modeloResumen = new DefaultTableModel(
                new Object[]{"Tipo", "ID", "Descripcion", "Cantidad", "Subtotal", "IVA", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaResumen = new JTable(modeloResumen);
        tablaResumen.setRowHeight(25);
        tablaResumen.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        txtDetalle = new JTextArea();
        txtDetalle.setEditable(false);
        txtDetalle.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JSplitPane split = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tablaResumen),
                new JScrollPane(txtDetalle));

        split.setResizeWeight(0.62);

        panelCentro.add(split, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        btnGuardar = new JButton("Guardar Factura");

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.add(btnGuardar);
        add(acciones, BorderLayout.SOUTH);

        rbDuenioSi.addActionListener(e -> actualizarCamposPagador());
        rbDuenioNo.addActionListener(e -> actualizarCamposPagador());
        btnAgregarServicio.addActionListener(e -> agregarServicioManual());
        btnQuitarDetalle.addActionListener(e -> quitarDetalleSeleccionado());
        btnGuardar.addActionListener(e -> guardarFactura());

        actualizarCamposPagador();
    }

    private void actualizarCamposPagador() {

        boolean pagadorManual =
                rbDuenioNo.isSelected();

        txtNombrePagador.setEnabled(
                pagadorManual);
        txtCedulaPagador.setEnabled(
                pagadorManual);

        if (!pagadorManual) {
            txtNombrePagador.setText("");
            txtCedulaPagador.setText("");
        }
    }

    private void cargarFactura() {

        try {

            Mascota mascota =
                    controlMascotas.obtener(
                            new Mascota(
                                    consulta.getIdMascota(),
                                    null,
                                    null,
                                    0,
                                    0,
                                    0,
                                    null,
                                    null));

            if (mascota == null) {
                throw new Exception("No se encontro la mascota de la consulta");
            }

            clienteDuenio =
                    controlClientes.obtener(
                            new Cliente(
                                    mascota.getIdCliente(),
                                    null,
                                    null,
                                    null,
                                    null));

            if (clienteDuenio == null) {
                throw new Exception("No se encontro el cliente duenio de la mascota");
            }

            factura =
                    new Factura(
                            0,
                            LocalDate.now(),
                            clienteDuenio.getIdCliente(),
                            "",
                            "",
                            consulta.getIdConsulta(),
                            0,
                            0);

            cargarComboServicios();
            cargarServicios();
            cargarMedicamentos();

            factura.calcularTotal();
            refrescarVista();

            btnGuardar.setEnabled(
                    !detalles.isEmpty());

            if (detalles.isEmpty()) {
                txtDetalle.setText(
                        "La consulta seleccionada no tiene servicios ni medicamentos asociados.");
            }

        } catch (Exception e) {
            btnGuardar.setEnabled(false);
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarComboServicios()
            throws Exception {

        cbServicios.removeAllItems();

        for (Servicio servicio : controlServicios.consultar()) {
            cbServicios.addItem(
                    new ItemCombo(
                            servicio.getIdServicio(),
                            servicio.getNombreServicio()
                                    + " - "
                                    + moneda(servicio.getPrecio())));
        }
    }

    private void cargarServicios()
            throws Exception {

        for (ConsultaServicio consultaServicio :
                controlConsultaServicios.consultarPorConsulta(
                        consulta.getIdConsulta())) {

            Servicio servicio =
                    controlServicios.obtener(
                            new Servicio(
                                    consultaServicio.getIdServicio(),
                                    null,
                                    0));

            if (servicio != null) {

                int cantidad =
                        consultaServicio.getCantidad();

                DetalleFactura detalle =
                        crearDetalleServicio(
                                servicio,
                                cantidad);

                agregarDetalle(
                        detalle,
                        "Servicio",
                        servicio.getNombreServicio(),
                        detalle.getSubtotal());
            }
        }
    }

    private void cargarMedicamentos()
            throws Exception {

        if (consulta.getIdTratamiento() <= 0) {
            return;
        }

        for (DetalleTratamiento detalleTratamiento :
                controlDetalleTratamientos.consultarPorTratamiento(
                        consulta.getIdTratamiento())) {

            Medicamento medicamento =
                    controlMedicamentos.obtenerPorId(
                            detalleTratamiento.getIdMedicamento());

            if (medicamento != null) {

                int cantidad = 1;
                double subtotal =
                        medicamento.getPrecio() * cantidad;

                DetalleFactura detalle =
                        new DetalleFactura(
                                0,
                                0,
                                0,
                                medicamento.getIdMedicamento(),
                                cantidad,
                                subtotal,
                                calcularTotalDetalle(subtotal));

                agregarDetalle(
                        detalle,
                        "Medicamento",
                        medicamento.getNombreMedicamento(),
                        subtotal);
            }
        }
    }

    private DetalleFactura crearDetalleServicio(Servicio servicio,
                                                int cantidad) {

        double subtotal =
                servicio.getPrecio() * cantidad;

        return new DetalleFactura(
                0,
                0,
                servicio.getIdServicio(),
                0,
                cantidad,
                subtotal,
                calcularTotalDetalle(subtotal));
    }

    private void agregarServicioManual() {

        try {

            ItemCombo item =
                    (ItemCombo) cbServicios.getSelectedItem();

            if (item == null) {
                throw new Exception("No hay servicios disponibles para agregar");
            }

            int cantidad =
                    (Integer) spCantidadServicio.getValue();

            Servicio servicio =
                    controlServicios.obtener(
                            new Servicio(
                                    item.getId(),
                                    null,
                                    0));

            if (servicio == null) {
                throw new Exception("No se encontro el servicio seleccionado");
            }

            DetalleFactura detalle =
                    crearDetalleServicio(
                            servicio,
                            cantidad);

            agregarDetalle(
                    detalle,
                    "Servicio",
                    servicio.getNombreServicio(),
                    detalle.getSubtotal());

            factura.calcularTotal();
            refrescarVista();
            btnGuardar.setEnabled(!detalles.isEmpty());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void quitarDetalleSeleccionado() {

        int fila =
                tablaResumen.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un detalle para quitar");
            return;
        }

        int modeloFila =
                tablaResumen.convertRowIndexToModel(fila);

        detalles.remove(modeloFila);
        factura.getDetalleFacturas().remove(modeloFila);
        modeloResumen.removeRow(modeloFila);

        factura.calcularTotal();
        refrescarVista();
        btnGuardar.setEnabled(!detalles.isEmpty());
    }

    private void agregarDetalle(DetalleFactura detalle,
                                String tipo,
                                String descripcion,
                                double subtotal) {

        detalles.add(detalle);
        factura.agregarDetalleFactura(detalle);

        agregarFilaResumen(
                tipo,
                detalle.getIdServicio() > 0
                        ? detalle.getIdServicio()
                        : detalle.getIdMedicamento(),
                descripcion,
                detalle.getCantidad(),
                subtotal);
    }

    private void agregarFilaResumen(String tipo,
                                    int id,
                                    String descripcion,
                                    int cantidad,
                                    double subtotal) {

        modeloResumen.addRow(new Object[]{
                tipo,
                id,
                descripcion,
                cantidad,
                moneda(subtotal),
                moneda(subtotal * 0.15),
                moneda(calcularTotalDetalle(subtotal))
        });
    }

    private double calcularTotalDetalle(double subtotal) {
        return subtotal + (subtotal * 0.15);
    }

    private void refrescarVista() {

        StringBuilder sb =
                new StringBuilder();

        sb.append("Factura generada desde consulta #")
                .append(consulta.getIdConsulta())
                .append('\n');

        sb.append("Fecha: ")
                .append(factura.getFecha())
                .append('\n');

        sb.append("Cliente duenio ID: ")
                .append(factura.getIdCliente())
                .append(" - ")
                .append(clienteDuenio.getNombre())
                .append('\n');

        sb.append('\n');

        for (int i = 0; i < modeloResumen.getRowCount(); i++) {

            sb.append(modeloResumen.getValueAt(i, 0))
                    .append(" | ")
                    .append(modeloResumen.getValueAt(i, 2))
                    .append(" | Cant: ")
                    .append(modeloResumen.getValueAt(i, 3))
                    .append(" | Total: ")
                    .append(modeloResumen.getValueAt(i, 6))
                    .append('\n');
        }

        sb.append('\n');

        sb.append("Subtotal: ")
                .append(moneda(factura.getTotal() - factura.getIvaTotal()))
                .append('\n');

        sb.append("IVA 15%: ")
                .append(moneda(factura.getIvaTotal()))
                .append('\n');

        sb.append("Total: ")
                .append(moneda(factura.getTotal()));

        txtDetalle.setText(sb.toString());
    }

    private void guardarFactura() {

        try {

            if (rbDuenioSi.isSelected()) {
                factura.setNombrePagador("");
                factura.setCedulaPagador("");
            } else {

                String nombre =
                        txtNombrePagador.getText().trim();

                String cedula =
                        txtCedulaPagador.getText().trim();

                if (nombre.isEmpty()) {
                    throw new Exception("Ingrese el nombre del pagador");
                }

                if (cedula.isEmpty()) {
                    throw new Exception("Ingrese la cedula del pagador");
                }

                factura.setNombrePagador(nombre);
                factura.setCedulaPagador(cedula);
            }

            factura.calcularTotal();
            controlFacturas.agregar(factura);

            for (DetalleFactura detalle : detalles) {

                detalle.setIdFactura(
                        factura.getIdFactura());

                controlDetalleFacturas.agregar(
                        detalle);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Factura guardada correctamente. ID: " + factura.getIdFactura());

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private String moneda(double valor) {

        return String.format(
                Locale.US,
                "$%.2f",
                valor);
    }
}