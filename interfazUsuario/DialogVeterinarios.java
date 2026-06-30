package interfazUsuario;

import control.*;
import objetosNegocio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogVeterinarios extends JFrame {

    private JTable tablaVeterinarios;
    private DefaultTableModel modeloVeterinarios;

    private JTable tablaDetalle;
    private DefaultTableModel modeloDetalle;

    private JTextField txtId;

    private JComboBox<Usuario> cmbUsuarios;
    private JComboBox<Especialidad> cmbEspecialidades;
    private JComboBox<Horario> cmbHorarios;

    private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    private JButton btnAgregarHorario, btnQuitarHorario;

    private ControlVeterinarios controlVeterinarios = new ControlVeterinarios();
    private ControlUsuarios controlUsuarios = new ControlUsuarios();
    private ControlEspecialidades controlEspecialidades = new ControlEspecialidades();
    private ControlHorarios controlHorarios = new ControlHorarios();
    private ControlDetalleHorarios controlDetalle = new ControlDetalleHorarios();

    private ArrayList<DetalleHorario> detalleTemporal = new ArrayList<>();

    public DialogVeterinarios() {

        setTitle("Gestión de Veterinarios");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarCombos();
        cargarVeterinarios();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modeloVeterinarios = new DefaultTableModel(
                new Object[]{"ID", "Usuario", "Especialidad"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tablaVeterinarios = new JTable(modeloVeterinarios);

        modeloDetalle = new DefaultTableModel(
                new Object[]{"ID Horario", "Día", "Hora"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tablaDetalle = new JTable(modeloDetalle);

        JSplitPane split = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tablaVeterinarios),
                new JScrollPane(tablaDetalle)
        );

        split.setDividerLocation(300);
        add(split, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        cmbUsuarios = new JComboBox<>();
        cmbEspecialidades = new JComboBox<>();
        cmbHorarios = new JComboBox<>();

        form.add(new JLabel("ID"));
        form.add(txtId);

        form.add(new JLabel("Usuario"));
        form.add(cmbUsuarios);

        form.add(new JLabel("Especialidad"));
        form.add(cmbEspecialidades);

        form.add(new JLabel("Horario"));
        form.add(cmbHorarios);

        JPanel botones = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        btnAgregarHorario = new JButton("Agregar Horario");
        btnQuitarHorario = new JButton("Quitar Horario");

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnAgregarHorario);
        botones.add(btnQuitarHorario);

        JPanel sur = new JPanel(new BorderLayout());
        sur.add(form, BorderLayout.CENTER);
        sur.add(botones, BorderLayout.SOUTH);
        add(sur, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarVeterinario());
        btnActualizar.addActionListener(e -> actualizarVeterinario());
        btnEliminar.addActionListener(e -> eliminarVeterinario());
        btnLimpiar.addActionListener(e -> limpiar());

        btnAgregarHorario.addActionListener(e -> agregarHorarioDetalle());
        btnQuitarHorario.addActionListener(e -> quitarHorario());

        tablaVeterinarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarVeterinarioSeleccionado();
            }
        });
    }

    private void cargarCombos() {
        try {

            cmbUsuarios.removeAllItems();
            for (Usuario u : controlUsuarios.consultarVeterinarios()) {
                cmbUsuarios.addItem(u);
            }

            cmbEspecialidades.removeAllItems();
            for (Especialidad e : controlEspecialidades.consultar()) {
                cmbEspecialidades.addItem(e);
            }

            cmbHorarios.removeAllItems();
            for (Horario h : controlHorarios.consultar()) {
                cmbHorarios.addItem(h);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void agregarHorarioDetalle() {

        Horario h = (Horario) cmbHorarios.getSelectedItem();
        if (h == null) return;

        for (DetalleHorario d : detalleTemporal) {
            if (d.getIdHorario() == h.getIdHorario()) {
                JOptionPane.showMessageDialog(this, "Horario ya agregado");
                return;
            }
        }

        DetalleHorario d = new DetalleHorario();
        d.setIdHorario(h.getIdHorario());

        detalleTemporal.add(d);

        modeloDetalle.addRow(new Object[]{
                h.getIdHorario(),
                h.getDiaSemana(),
                h.getHoraInicio() + " - " + h.getHoraFinal()
        });
    }

    private void quitarHorario() {

        int fila = tablaDetalle.getSelectedRow();
        if (fila == -1) return;

        detalleTemporal.remove(fila);
        modeloDetalle.removeRow(fila);
    }
    private void agregarVeterinario() {

        try {

            Veterinario v = new Veterinario();

            Usuario u = (Usuario) cmbUsuarios.getSelectedItem();
            Especialidad e = (Especialidad) cmbEspecialidades.getSelectedItem();

            v.setIdUsuario(u.getIdUsuario());
            v.setIdEspecialidad(e.getIdEspecialidad());

            controlVeterinarios.agregar(v);

            int idVet = v.getIdVeterinario();

            for (DetalleHorario d : detalleTemporal) {
                d.setIdVeterinario(idVet);
                controlDetalle.agregar(d);
            }

            cargarVeterinarios();
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void actualizarVeterinario() {

        try {

            if (txtId.getText().isEmpty()) return;

            int idVet = Integer.parseInt(txtId.getText());

            Veterinario v = new Veterinario();
            v.setIdVeterinario(idVet);

            Usuario u = (Usuario) cmbUsuarios.getSelectedItem();
            Especialidad e = (Especialidad) cmbEspecialidades.getSelectedItem();

            v.setIdUsuario(u.getIdUsuario());
            v.setIdEspecialidad(e.getIdEspecialidad());

            controlVeterinarios.actualizar(v);

            for (DetalleHorario d : controlDetalle.consultarPorVeterinario(idVet)) {
                controlDetalle.eliminar(d);
            }
            for (DetalleHorario d : detalleTemporal) {
                d.setIdVeterinario(idVet);
                controlDetalle.agregar(d);
            }

            cargarVeterinarios();
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    private void eliminarVeterinario() {

        try {

            int idVet = Integer.parseInt(txtId.getText());

            for (DetalleHorario d : controlDetalle.consultarPorVeterinario(idVet)) {
                controlDetalle.eliminar(d);
            }

            Veterinario v = new Veterinario();
            v.setIdVeterinario(idVet);

            controlVeterinarios.eliminar(v);

            cargarVeterinarios();
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    private void cargarVeterinarios() {

        try {

            modeloVeterinarios.setRowCount(0);

            for (Veterinario v : controlVeterinarios.consultar()) {

                Usuario u = controlUsuarios.obtenerPorId(v.getIdUsuario());
                Especialidad e = controlEspecialidades.obtenerPorId(v.getIdEspecialidad());

                modeloVeterinarios.addRow(new Object[]{
                        v.getIdVeterinario(),
                        u.getNombre() + " " + u.getApellido(),
                        e.getNombreEspecialidad()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================= SELECCION =================
    private void cargarVeterinarioSeleccionado() {

        int fila = tablaVeterinarios.getSelectedRow();
        if (fila == -1) return;

        try {

            int idVet = (int) modeloVeterinarios.getValueAt(fila, 0);

            txtId.setText(String.valueOf(idVet));

            detalleTemporal.clear();
            modeloDetalle.setRowCount(0);

            for (DetalleHorario d : controlDetalle.consultarPorVeterinario(idVet)) {

                detalleTemporal.add(d);

                Horario h = controlHorarios.obtenerPorId(d.getIdHorario());

                modeloDetalle.addRow(new Object[]{
                        h.getIdHorario(),
                        h.getDiaSemana(),
                        h.getHoraInicio() + " - " + h.getHoraFinal()
                });
            }

            // combos
            Veterinario v = controlVeterinarios.obtenerPorId(idVet);

            for (int i = 0; i < cmbUsuarios.getItemCount(); i++) {
                if (cmbUsuarios.getItemAt(i).getIdUsuario() == v.getIdUsuario()) {
                    cmbUsuarios.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < cmbEspecialidades.getItemCount(); i++) {
                if (cmbEspecialidades.getItemAt(i).getIdEspecialidad() == v.getIdEspecialidad()) {
                    cmbEspecialidades.setSelectedIndex(i);
                    break;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void limpiar() {
        txtId.setText("");
        detalleTemporal.clear();
        modeloDetalle.setRowCount(0);
    }
}