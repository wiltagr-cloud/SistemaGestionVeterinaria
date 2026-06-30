package interfazUsuario;

import control.ControlTipoMascotas;
import objetosNegocio.TipoMascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogTipoMascota extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtNombreTipo;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnAtras;

    private ControlTipoMascotas controlTipoMascota;

    public DialogTipoMascota() {

        controlTipoMascota = new ControlTipoMascotas();

        setTitle("Gestión de Tipos de Mascota");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarTipos();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Tipo Mascota"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombreTipo = new JTextField();

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Tipo Mascota:"));
        form.add(txtNombreTipo);

        JPanel botones = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnAtras = new JButton("Atrás");

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnAtras);

        JPanel sur = new JPanel(new BorderLayout());
        sur.add(form, BorderLayout.CENTER);
        sur.add(botones, BorderLayout.SOUTH);

        add(sur, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnAtras.addActionListener(e -> dispose());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionar();
            }
        });
    }


    private void cargarTipos() {

        try {

            modelo.setRowCount(0);

            ArrayList<TipoMascota> lista =
                    controlTipoMascota.consultar();

            for (TipoMascota t : lista) {

                modelo.addRow(new Object[]{
                        t.getIdTipoMascota(),
                        t.getNombreTipoMascota()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void agregar() {

        try {

            String nombre = txtNombreTipo.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un tipo de mascota");
                return;
            }

            // evitar duplicados
            for (TipoMascota t : controlTipoMascota.consultar()) {
                if (t.getNombreTipoMascota().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(this, "Este tipo ya existe");
                    return;
                }
            }

            TipoMascota t = new TipoMascota();
            t.setNombreTipoMascota(nombre);

            controlTipoMascota.agregar(t);

            cargarTipos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actualizar() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro");
                return;
            }

            TipoMascota t = new TipoMascota();

            t.setIdTipoMascota(Integer.parseInt(txtId.getText()));
            t.setNombreTipoMascota(txtNombreTipo.getText().trim());

            controlTipoMascota.actualizar(t);

            cargarTipos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void eliminar() {

        try {

            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro");
                return;
            }

            TipoMascota t = new TipoMascota();
            t.setIdTipoMascota(Integer.parseInt(txtId.getText()));

            controlTipoMascota.eliminar(t);

            cargarTipos();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void seleccionar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) return;

        txtId.setText(modelo.getValueAt(fila, 0).toString());
        txtNombreTipo.setText(modelo.getValueAt(fila, 1).toString());
    }

    private void limpiar() {

        txtId.setText("");
        txtNombreTipo.setText("");
        tabla.clearSelection();
    }
}