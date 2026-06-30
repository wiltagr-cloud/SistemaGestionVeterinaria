package interfazUsuario;

import Enums.DiaSemana;
import control.ControlHorarios;
import objetosNegocio.Horario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class DialogHorarios extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JComboBox<DiaSemana> cmbDia;

    // 🔥 NUEVO: spinners de hora
    private JSpinner spHoraInicio;
    private JSpinner spHoraFinal;

    private ControlHorarios controlHorarios;

    public DialogHorarios() {

        controlHorarios = new ControlHorarios();

        setTitle("Gestión de Horarios");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarHorarios();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Día", "Hora Inicio", "Hora Final"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        cmbDia = new JComboBox<>(DiaSemana.values());

        // =========================
        // 🔥 SPINNER HORA INICIO
        // =========================
        spHoraInicio = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorInicio =
                new JSpinner.DateEditor(spHoraInicio, "HH:mm");
        spHoraInicio.setEditor(editorInicio);

        // =========================
        // 🔥 SPINNER HORA FINAL
        // =========================
        spHoraFinal = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorFinal =
                new JSpinner.DateEditor(spHoraFinal, "HH:mm");
        spHoraFinal.setEditor(editorFinal);

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Día:"));
        form.add(cmbDia);

        form.add(new JLabel("Hora inicio:"));
        form.add(spHoraInicio);

        form.add(new JLabel("Hora final:"));
        form.add(spHoraFinal);

        JPanel botones = new JPanel(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

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
            if (!e.getValueIsAdjusting()) {
                seleccionar();
            }
        });
    }

    // =========================
    // CONVERTIR SPINNER → LocalTime
    // =========================
    private LocalTime obtenerHora(JSpinner spinner) {

        Date date = (Date) spinner.getValue();

        return date.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalTime();
    }

    private void cargarHorarios() {

        try {
            modelo.setRowCount(0);

            ArrayList<Horario> lista = controlHorarios.consultar();

            for (Horario h : lista) {

                modelo.addRow(new Object[]{
                        h.getIdHorario(),
                        h.getDiaSemana(),
                        h.getHoraInicio(),
                        h.getHoraFinal()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void agregar() {

        try {

            Horario h = new Horario();

            h.setDiaSemana((DiaSemana) cmbDia.getSelectedItem());
            h.setHoraInicio(obtenerHora(spHoraInicio));
            h.setHoraFinal(obtenerHora(spHoraFinal));

            controlHorarios.agregar(h);

            cargarHorarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actualizar() {

        try {

            Horario h = new Horario();

            h.setIdHorario(Integer.parseInt(txtId.getText()));
            h.setDiaSemana((DiaSemana) cmbDia.getSelectedItem());
            h.setHoraInicio(obtenerHora(spHoraInicio));
            h.setHoraFinal(obtenerHora(spHoraFinal));

            controlHorarios.actualizar(h);

            cargarHorarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void eliminar() {

        try {

            Horario h = new Horario();
            h.setIdHorario(Integer.parseInt(txtId.getText()));

            controlHorarios.eliminar(h);

            cargarHorarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void seleccionar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) return;

        txtId.setText(modelo.getValueAt(fila, 0).toString());

        cmbDia.setSelectedItem(
                DiaSemana.valueOf(modelo.getValueAt(fila, 1).toString())
        );

        // convertir string a hora
        spHoraInicio.setValue(java.sql.Time.valueOf(
                LocalTime.parse(modelo.getValueAt(fila, 2).toString())
        ));

        spHoraFinal.setValue(java.sql.Time.valueOf(
                LocalTime.parse(modelo.getValueAt(fila, 3).toString())
        ));
    }

    private void limpiar() {

        txtId.setText("");

        if (cmbDia.getItemCount() > 0) {
            cmbDia.setSelectedIndex(0);
        }

        spHoraInicio.setValue(new Date());
        spHoraFinal.setValue(new Date());

        tabla.clearSelection();
    }
}