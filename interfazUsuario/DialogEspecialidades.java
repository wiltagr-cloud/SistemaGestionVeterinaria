package interfazUsuario;

import control.ControlEspecialidades;
import objetosNegocio.Especialidad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogEspecialidades extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtId;
    private JTextField txtNombre;
    private ControlEspecialidades controlEspecialidades;
    public DialogEspecialidades() {

        controlEspecialidades = new ControlEspecialidades();

        setTitle("Gestión de Especialidades");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarEspecialidades();
    }

    // ================= UI =================
    private void initComponents() {

        setLayout(new BorderLayout());

        // ===== TABLA =====
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tabla = new JTable(modelo);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // ===== FORMULARIO =====
        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombre = new JTextField();

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);

        // ===== BOTONES =====
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

        // ===== EVENTOS =====
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
    // ================= LISTAR =================
    private void cargarEspecialidades() {

        try {
            modelo.setRowCount(0);

            ArrayList<Especialidad> lista =
                    controlEspecialidades.consultar();

            for (Especialidad esp : lista) {


                modelo.addRow(new Object[]{

                        esp.getIdEspecialidad(),
                        esp.getNombreEspecialidad()

                });


            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        }

    }
    // ================= AGREGAR =================
    private void agregar() {

        try {

            Especialidad esp = new Especialidad();

            esp.setNombreEspecialidad(
                    txtNombre.getText().trim()
            );

            controlEspecialidades.agregar(esp);

            cargarEspecialidades();

            limpiar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
    // ================= ACTUALIZAR =================
    private void actualizar() {


        try {

            Especialidad esp = new Especialidad();

            esp.setIdEspecialidad(
                    Integer.parseInt(txtId.getText())
            );

            esp.setNombreEspecialidad(
                    txtNombre.getText().trim()
            );

            controlEspecialidades.actualizar(esp);

            cargarEspecialidades();

            limpiar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        }
    }
    // ================= ELIMINAR =================
    private void eliminar() {

        try {

            Especialidad esp = new Especialidad();

            esp.setIdEspecialidad(
                    Integer.parseInt(txtId.getText())
            );

            controlEspecialidades.eliminar(esp);

            cargarEspecialidades();

            limpiar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        }

    }

    // ================= SELECCION =================
    private void seleccionar() {

        int fila = tabla.getSelectedRow();
        if (fila == -1)
            return;

        txtId.setText(
                modelo.getValueAt(fila,0).toString()
        );

        txtNombre.setText(
                modelo.getValueAt(fila,1).toString()
        );

    }

    // ================= UTIL =================
    private void limpiar() {

        txtId.setText("");

        txtNombre.setText("");

        tabla.clearSelection();

    }
}