package interfazUsuario;

import control.*;
import objetosNegocio.*;
import Enums.SexoMascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DialogMascota extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtPeso;
    private JTextField txtBuscarCliente;

    private JFormattedTextField txtFechaNacimiento;

    private JComboBox<TipoMascota> cmbTipo;
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<SexoMascota> cmbSexo;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnBuscarCliente;

    private ControlMascotas controlMascotas = new ControlMascotas();
    private ControlClientes controlClientes = new ControlClientes();
    private ControlTipoMascotas controlTipo = new ControlTipoMascotas();

    public DialogMascota() {

        setTitle("Gestión de Mascotas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarCombos();
        cargarMascotas();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Cliente", "Tipo", "Sexo", "Raza", "Peso"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombre = new JTextField();
        txtRaza = new JTextField();
        txtPeso = new JTextField();

        txtBuscarCliente = new JTextField();

        txtFechaNacimiento = new JFormattedTextField();
        txtFechaNacimiento.setToolTipText("yyyy-MM-dd");

        cmbTipo = new JComboBox<>();
        cmbCliente = new JComboBox<>();
        cmbSexo = new JComboBox<>(SexoMascota.values());

        form.add(new JLabel("ID"));
        form.add(txtId);

        form.add(new JLabel("Nombre"));
        form.add(txtNombre);

        form.add(new JLabel("Raza"));
        form.add(txtRaza);

        form.add(new JLabel("Peso"));
        form.add(txtPeso);

        form.add(new JLabel("Tipo Mascota"));
        form.add(cmbTipo);

        form.add(new JLabel("Cliente"));
        form.add(cmbCliente);

        form.add(new JLabel("Buscar cliente:"));
        form.add(txtBuscarCliente);

        form.add(new JLabel("Sexo"));
        form.add(cmbSexo);

        JPanel botones = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnBuscarCliente = new JButton("Buscar Cliente");

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

        btnBuscarCliente.addActionListener(e -> buscarCliente());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionar();
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarClientes();
            }
        });
    }

    private void cargarCombos() {

        try {

            cmbTipo.removeAllItems();
            for (TipoMascota t : controlTipo.consultar()) {
                cmbTipo.addItem(t);
            }

            cmbCliente.removeAllItems();
            for (Cliente c : controlClientes.consultar()) {
                cmbCliente.addItem(c);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarMascotas() {

        try {

            modelo.setRowCount(0);

            for (Mascota m : controlMascotas.consultar()) {

                Cliente c = controlClientes.obtener(
                        new Cliente(m.getIdCliente(), null, null, null, null)
                );

                TipoMascota t = controlTipo.obtener(
                        new TipoMascota(m.getIdTipoMascota(), null)
                );

                modelo.addRow(new Object[]{
                        m.getIdMascota(),
                        m.getNombreMascota(),
                        c.getNombre(),
                        t.getNombreTipoMascota(),
                        m.getSexoMascota(),
                        m.getRaza(),
                        m.getPeso()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void agregar() {

        try {

            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre requerido");
                return;
            }

            Mascota m = new Mascota();

            m.setNombreMascota(txtNombre.getText().trim());
            m.setRaza(txtRaza.getText().trim());
            m.setPeso(Double.parseDouble(txtPeso.getText()));

            m.setIdCliente(((Cliente) cmbCliente.getSelectedItem()).getIdCliente());
            m.setIdTipoMascota(((TipoMascota) cmbTipo.getSelectedItem()).getIdTipoMascota());
            m.setSexo((SexoMascota) cmbSexo.getSelectedItem());

            controlMascotas.agregar(m);

            cargarMascotas();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actualizar() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione una mascota");
                return;
            }

            Mascota m = new Mascota();

            m.setIdMascota(Integer.parseInt(txtId.getText()));

            m.setNombreMascota(txtNombre.getText().trim());
            m.setRaza(txtRaza.getText().trim());
            m.setPeso(Double.parseDouble(txtPeso.getText()));

            m.setIdCliente(((Cliente) cmbCliente.getSelectedItem()).getIdCliente());
            m.setIdTipoMascota(((TipoMascota) cmbTipo.getSelectedItem()).getIdTipoMascota());
            m.setSexo((SexoMascota) cmbSexo.getSelectedItem());

            controlMascotas.actualizar(m);

            cargarMascotas();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void eliminar() {

        try {

            if (txtId.getText().trim().isEmpty()) return;

            Mascota m = new Mascota();
            m.setIdMascota(Integer.parseInt(txtId.getText()));

            controlMascotas.eliminar(m);

            cargarMascotas();
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
        txtRaza.setText(modelo.getValueAt(fila, 5).toString());
        txtPeso.setText(modelo.getValueAt(fila, 6).toString());
    }

    private void buscarCliente() {

        String nombre = txtBuscarCliente.getText().trim();

        for (int i = 0; i < cmbCliente.getItemCount(); i++) {

            Cliente c = cmbCliente.getItemAt(i);

            if (c.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                cmbCliente.setSelectedIndex(i);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Cliente no encontrado");
    }

    private void limpiar() {

        txtId.setText("");
        txtNombre.setText("");
        txtRaza.setText("");
        txtPeso.setText("");

        tabla.clearSelection();
    }
    private void filtrarClientes() {

        try {

            String texto = txtBuscarCliente.getText().trim().toLowerCase();

            cmbCliente.removeAllItems();

            for (Cliente c : controlClientes.consultar()) {

                String nombreCompleto =
                        (c.getNombre() + " " + c.getCedula()).toLowerCase();

                if (nombreCompleto.contains(texto)) {
                    cmbCliente.addItem(c);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}