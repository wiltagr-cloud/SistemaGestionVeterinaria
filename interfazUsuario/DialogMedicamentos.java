package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import control.ControlMedicamentos;

import objetosNegocio.Medicamento;
import objetosNegocio.MedicamentoComprimidos;
import objetosNegocio.MedicamentoDosisFija;
import objetosNegocio.MedicamentoPorPeso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DialogMedicamentos extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextField txtViaAdministracion;
    private JTextField txtDosisPorKg;
    private JTextField txtCantidadFija;
    private JTextField txtStock;
    private JTextField txtPrecio;

    private JComboBox<String> cmbTipoMedicamento;

    private ControlMedicamentos controlMedicamentos;

    public DialogMedicamentos() {

        controlMedicamentos = new ControlMedicamentos();

        initComponents();
        cargarMedicamentos();
        configurarEventos();
    }

    private void initComponents() {

        setTitle("Gestión de Medicamentos");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{
                "ID", "Nombre", "Categoria", "Via",
                "Tipo", "Dosis/Kg", "Cantidad Fija",
                "Stock", "Precio"
        }, 0);

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombre = new JTextField();
        txtCategoria = new JTextField();
        txtViaAdministracion = new JTextField();
        txtDosisPorKg = new JTextField();
        txtCantidadFija = new JTextField();
        txtStock = new JTextField();
        txtPrecio = new JTextField();

        cmbTipoMedicamento = new JComboBox<>();
        cmbTipoMedicamento.addItem("Por Peso");
        cmbTipoMedicamento.addItem("Dosis Fija");
        cmbTipoMedicamento.addItem("Comprimidos");

        JPanel form = new JPanel(new GridLayout(9, 2, 5, 5));

        form.add(new JLabel("ID")); form.add(txtId);
        form.add(new JLabel("Nombre")); form.add(txtNombre);
        form.add(new JLabel("Categoria")); form.add(txtCategoria);
        form.add(new JLabel("Via")); form.add(txtViaAdministracion);
        form.add(new JLabel("Tipo")); form.add(cmbTipoMedicamento);
        form.add(new JLabel("Dosis/Kg")); form.add(txtDosisPorKg);
        form.add(new JLabel("Cantidad Fija")); form.add(txtCantidadFija);
        form.add(new JLabel("Stock")); form.add(txtStock);
        form.add(new JLabel("Precio")); form.add(txtPrecio);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        JPanel sur = new JPanel(new BorderLayout());
        sur.add(form, BorderLayout.CENTER);
        sur.add(botones, BorderLayout.SOUTH);

        add(sur, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionar();
        });
    }
    private void configurarEventos() {

        cmbTipoMedicamento.addActionListener(e -> ajustarCampos());
        ajustarCampos();
    }

    private void ajustarCampos() {

        String tipo = cmbTipoMedicamento.getSelectedItem().toString();

        if (tipo.equals("Por Peso")) {

            txtDosisPorKg.setEnabled(true);
            txtCantidadFija.setEnabled(false);
            txtCantidadFija.setText("");

        } else {

            txtDosisPorKg.setEnabled(false);
            txtDosisPorKg.setText("");
            txtCantidadFija.setEnabled(true);
        }
    }
    private String formatearTipo(String tipo) {

        if (tipo == null) return "";

        switch (tipo) {
            case "POR_PESO":
                return "Por Peso";
            case "DOSIS_FIJA":
                return "Dosis Fija";
            case "COMPRIMIDOS":
                return "Comprimidos";
            default:
                return tipo;
        }
    }
    private void cargarMedicamentos() {

        try {

            modelo.setRowCount(0);

            for (Medicamento m : controlMedicamentos.consultar()) {

                modelo.addRow(new Object[]{
                        m.getIdMedicamento(),
                        m.getNombreMedicamento(),
                        m.getCategoria(),
                        m.getViaAdministracion(),
                        formatearTipo(m.getTipoMedicamento()),
                        m.getDosisPorKg(),
                        m.getCantidadFija(),
                        m.getStock(),
                        m.getPrecio()
                });
                System.out.println("Tipo: " + m.getTipoMedicamento());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void agregar() {

        try {

            Medicamento m;
            String tipo = cmbTipoMedicamento.getSelectedItem().toString();

            if (tipo.equals("Por Peso")) {

                if (txtDosisPorKg.getText().trim().isEmpty())
                    throw new Exception("Ingrese dosis por kg");

                m = new MedicamentoPorPeso();
                m.setDosisPorKg(Double.parseDouble(txtDosisPorKg.getText()));
                m.setCantidadFija(0);

            } else if (tipo.equals("Dosis Fija")) {

                if (txtCantidadFija.getText().trim().isEmpty())
                    throw new Exception("Ingrese dosis fija");

                m = new MedicamentoDosisFija();
                m.setCantidadFija(Double.parseDouble(txtCantidadFija.getText()));
                m.setDosisPorKg(0);

            } else {

                if (txtCantidadFija.getText().trim().isEmpty())
                    throw new Exception("Ingrese cantidad de comprimidos");

                m = new MedicamentoComprimidos();
                m.setCantidadFija(Double.parseDouble(txtCantidadFija.getText()));
                m.setDosisPorKg(0);
            }

            m.setNombreMedicamento(txtNombre.getText().trim());
            m.setCategoria(txtCategoria.getText().trim());
            m.setViaAdministracion(txtViaAdministracion.getText().trim());
            m.setTipoMedicamento(tipo);

            m.setStock(Integer.parseInt(txtStock.getText()));
            m.setPrecio(Double.parseDouble(txtPrecio.getText()));

            controlMedicamentos.agregar(m);

            cargarMedicamentos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void actualizar() {

        try {

            Medicamento m;
            String tipo = cmbTipoMedicamento.getSelectedItem().toString();

            if (tipo.equals("Por Peso")) {

                m = new MedicamentoPorPeso();
                m.setDosisPorKg(Double.parseDouble(txtDosisPorKg.getText()));
                m.setCantidadFija(0);

            } else if (tipo.equals("Dosis Fija")) {

                m = new MedicamentoDosisFija();
                m.setCantidadFija(Double.parseDouble(txtCantidadFija.getText()));
                m.setDosisPorKg(0);

            } else {

                m = new MedicamentoComprimidos();
                m.setCantidadFija(Double.parseDouble(txtCantidadFija.getText()));
                m.setDosisPorKg(0);
            }

            m.setIdMedicamento(Integer.parseInt(txtId.getText()));
            m.setNombreMedicamento(txtNombre.getText().trim());
            m.setCategoria(txtCategoria.getText().trim());
            m.setViaAdministracion(txtViaAdministracion.getText().trim());
            m.setTipoMedicamento(tipo);

            m.setStock(Integer.parseInt(txtStock.getText()));
            m.setPrecio(Double.parseDouble(txtPrecio.getText()));

            controlMedicamentos.actualizar(m);

            cargarMedicamentos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void eliminar() {

        try {

            Medicamento m = new MedicamentoComprimidos();
            m.setIdMedicamento(Integer.parseInt(txtId.getText()));

            controlMedicamentos.eliminar(m);

            cargarMedicamentos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void seleccionar() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        txtId.setText(modelo.getValueAt(fila, 0).toString());
        txtNombre.setText(modelo.getValueAt(fila, 1).toString());
        txtCategoria.setText(modelo.getValueAt(fila, 2).toString());
        txtViaAdministracion.setText(modelo.getValueAt(fila, 3).toString());

        cmbTipoMedicamento.setSelectedItem(modelo.getValueAt(fila, 4).toString());
        txtDosisPorKg.setText(modelo.getValueAt(fila, 5).toString());
        txtCantidadFija.setText(modelo.getValueAt(fila, 6).toString());
        txtStock.setText(modelo.getValueAt(fila, 7).toString());
        txtPrecio.setText(modelo.getValueAt(fila, 8).toString());
    }

    private void limpiar() {

        txtId.setText("");
        txtNombre.setText("");
        txtCategoria.setText("");
        txtViaAdministracion.setText("");
        txtDosisPorKg.setText("");
        txtCantidadFija.setText("");
        txtStock.setText("");
        txtPrecio.setText("");

        cmbTipoMedicamento.setSelectedIndex(0);
        ajustarCampos();

        tabla.clearSelection();
    }
}