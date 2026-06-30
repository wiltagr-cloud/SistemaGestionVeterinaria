package interfazUsuario;

import control.*;
import objetosNegocio.*;
import Enums.EstadoCita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DialogCitas extends JFrame {

    private JTextField txtBuscarCliente;

    private JComboBox<ItemCombo> cmbClientes;
    private JComboBox<ItemCombo> cmbMascotas;
    private JComboBox<ItemCombo> cmbVeterinarios;

    private JComboBox<Integer> cmbDia;
    private JComboBox<Integer> cmbMes;
    private JComboBox<Integer> cmbAnio;

    private JComboBox<EstadoCita> cmbEstado;
    JComboBox<String> cmbHora;

    private JTable tabla;
    private DefaultTableModel modelo;

    private ControlClientes controlClientes = new ControlClientes();
    private ControlMascotas controlMascotas = new ControlMascotas();
    private ControlVeterinarios controlVeterinarios = new ControlVeterinarios();
    private ControlCitas controlCitas = new ControlCitas();
    private ControlUsuarios controlUsuarios = new ControlUsuarios();
    private ControlEspecialidades controlEspecialidades = new ControlEspecialidades();

    public DialogCitas() {

        setTitle("Gestión de Citas");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarClientes();
        cargarVeterinarios();
        cargarCitas();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Mascota", "Veterinario", "Fecha","Hora", "Estado"}, 0
        );

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(7, 2, 5, 5));

        txtBuscarCliente = new JTextField();

        cmbClientes = new JComboBox<>();
        cmbMascotas = new JComboBox<>();
        cmbVeterinarios = new JComboBox<>();
        cmbEstado = new JComboBox<>(EstadoCita.values());

        cmbDia = new JComboBox<>();
        cmbMes = new JComboBox<>();
        cmbAnio = new JComboBox<>();

        for (int i = 1; i <= 31; i++) cmbDia.addItem(i);
        for (int i = 1; i <= 12; i++) cmbMes.addItem(i);
        for (int i = 2026; i <= 2035; i++) cmbAnio.addItem(i);

        cmbHora = new JComboBox<>();

        for (int h = 8; h <= 18; h++) {
            cmbHora.addItem(String.format("%02d:00", h));
        }

        form.add(new JLabel("Buscar Cliente:"));
        form.add(txtBuscarCliente);

        form.add(new JLabel("Cliente:"));
        form.add(cmbClientes);

        form.add(new JLabel("Mascota:"));
        form.add(cmbMascotas);

        form.add(new JLabel("Veterinario:"));
        form.add(cmbVeterinarios);

        form.add(new JLabel("Fecha:"));

        JPanel fechaPanel = new JPanel(new FlowLayout());
        fechaPanel.add(cmbDia);
        fechaPanel.add(cmbMes);
        fechaPanel.add(cmbAnio);

        form.add(fechaPanel);
        form.add(new JLabel("Hora:"));
        form.add(cmbHora);

        form.add(new JLabel("Estado:"));
        form.add(cmbEstado);

        JPanel botones = new JPanel(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);

        JPanel sur = new JPanel(new BorderLayout());
        sur.add(form, BorderLayout.CENTER);
        sur.add(botones, BorderLayout.SOUTH);

        add(sur, BorderLayout.SOUTH);

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarClientes();
            }
        });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccion();
            }
        });

        cmbClientes.addActionListener(e -> cargarMascotas());

        btnAgregar.addActionListener(e -> agregar());
        btnEliminar.addActionListener(e -> eliminar());
        btnActualizar.addActionListener(e -> actualizar());
    }
    private void cargarSeleccion() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        try {

            int idCita = (int) modelo.getValueAt(fila, 0);

            Cita c = controlCitas.obtenerCitaPorId(idCita);

            if (c == null) return;

            Mascota m = controlMascotas.obtener(
                    new Mascota(c.getIdMascota(), null, null, 0, 0, 0, null, null)
            );

            Cliente cl = controlClientes.obtener(
                    new Cliente(m.getIdCliente(), null, null, null, null)
            );

            cmbDia.setSelectedItem(c.getFecha().getDayOfMonth());
            cmbMes.setSelectedItem(c.getFecha().getMonthValue());
            cmbAnio.setSelectedItem(c.getFecha().getYear());

            cmbHora.setSelectedItem(c.getHora().toString());

            cmbEstado.setSelectedItem(c.getEstadoCita());

            seleccionarCombo(cmbClientes, cl.getIdCliente());
            cargarMascotas();

            seleccionarCombo(cmbMascotas, m.getIdMascota());
            seleccionarCombo(cmbVeterinarios, c.getIdVeterinario());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void seleccionarCombo(JComboBox<ItemCombo> combo, int id) {

        for (int i = 0; i < combo.getItemCount(); i++) {

            if (combo.getItemAt(i).getId() == id) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    private void cargarClientes() {

        try {
            cmbClientes.removeAllItems();

            for (Cliente c : controlClientes.consultar()) {
                cmbClientes.addItem(new ItemCombo(
                        c.getIdCliente(),
                        c.getNombre() + " " + c.getCedula()
                ));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void filtrarClientes() {

        try {

            String texto = txtBuscarCliente.getText().toLowerCase();

            cmbClientes.removeAllItems();

            for (Cliente c : controlClientes.consultar()) {

                if (c.getNombre().toLowerCase().contains(texto)) {

                    cmbClientes.addItem(new ItemCombo(
                            c.getIdCliente(),
                            c.getNombre() + " " + c.getCedula()
                    ));
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void cargarMascotas() {

        try {

            cmbMascotas.removeAllItems();

            ItemCombo cliente = (ItemCombo) cmbClientes.getSelectedItem();
            if (cliente == null) return;

            for (Mascota m : controlMascotas.consultarPorCliente(cliente.getId())) {

                cmbMascotas.addItem(new ItemCombo(
                        m.getIdMascota(),
                        m.getNombreMascota()
                ));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void cargarVeterinarios() {

        try {

            cmbVeterinarios.removeAllItems();

            for (Veterinario v : controlVeterinarios.consultar()) {

                Usuario u = controlUsuarios.obtenerPorId(v.getIdUsuario());

                Especialidad e = controlEspecialidades.obtener(
                        new Especialidad(v.getIdEspecialidad(), null)
                );

                String texto = u.getNombre() + " " + u.getApellido()
                        + " - " + e.getNombreEspecialidad();

                cmbVeterinarios.addItem(new ItemCombo(
                        v.getIdVeterinario(),
                        texto
                ));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void agregar() {

        try {

            ItemCombo mascota = (ItemCombo) cmbMascotas.getSelectedItem();
            ItemCombo vet = (ItemCombo) cmbVeterinarios.getSelectedItem();

            LocalDate fecha = LocalDate.of(
                    (int) cmbAnio.getSelectedItem(),
                    (int) cmbMes.getSelectedItem(),
                    (int) cmbDia.getSelectedItem()
            );


            String horaStr = (String) cmbHora.getSelectedItem();
            LocalTime hora = LocalTime.parse(horaStr);

            for (Cita c : controlCitas.consultarPorVeterinario(vet.getId())) {

                if (c.getFecha().equals(fecha) &&
                        c.getHora().equals(hora)) {

                    JOptionPane.showMessageDialog(this,
                            "Veterinario ocupado en esa fecha y hora");
                    return;
                }
            }

            Cita cita = new Cita();

            cita.setIdMascota(mascota.getId());
            cita.setIdVeterinario(vet.getId());
            cita.setFecha(fecha);
            cita.setHora(hora);
            cita.setEstadoCita((EstadoCita) cmbEstado.getSelectedItem());

            controlCitas.agregar(cita);

            cargarCitas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void cargarCitas() {

        try {

            modelo.setRowCount(0);

            for (Cita c : controlCitas.consultar()) {

                Mascota m = controlMascotas.obtener(
                        new Mascota(c.getIdMascota(), null, null, 0, 0, 0, null, null)
                );

                Cliente cl = controlClientes.obtener(
                        new Cliente(m.getIdCliente(), null, null, null, null)
                );


                Veterinario v = controlVeterinarios.obtener(
                        new Veterinario(c.getIdVeterinario(), 0, 0)
                );

                String nombreCliente = cl.getNombre();
                String nombreMascota = m.getNombreMascota();

                Usuario u = controlUsuarios.obtenerPorId(v.getIdUsuario());

                String nombreVet = u.getNombre() + " " + u.getApellido();

                modelo.addRow(new Object[]{
                        c.getIdCita(),
                        nombreCliente,
                        nombreMascota,
                        nombreVet,
                        c.getFecha(),
                        c.getHora(),
                        c.getEstadoCita()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actualizar() {

        try {

            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una cita");
                return;
            }

            int idCita = (int) modelo.getValueAt(fila, 0);

            ItemCombo mascota = (ItemCombo) cmbMascotas.getSelectedItem();
            ItemCombo vet = (ItemCombo) cmbVeterinarios.getSelectedItem();

            LocalDate fecha = LocalDate.of(
                    (int) cmbAnio.getSelectedItem(),
                    (int) cmbMes.getSelectedItem(),
                    (int) cmbDia.getSelectedItem()
            );

            LocalTime hora = LocalTime.parse((String) cmbHora.getSelectedItem());

            Cita c = new Cita();

            c.setIdCita(idCita);
            c.setIdMascota(mascota.getId());
            c.setIdVeterinario(vet.getId());
            c.setFecha(fecha);
            c.setHora(hora);
            c.setEstadoCita((EstadoCita) cmbEstado.getSelectedItem());

            controlCitas.actualizar(c);

            cargarCitas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void eliminar() {

        try {

            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una cita");
                return;
            }

            int idCita = (int) modelo.getValueAt(fila, 0);

            Cita c = new Cita();
            c.setIdCita(idCita);

            controlCitas.eliminar(c);

            cargarCitas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}