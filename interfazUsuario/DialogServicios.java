package interfazUsuario;

import control.ControlServicios;
import objetosNegocio.Servicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DialogServicios extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtIdServicio;
    private JTextField txtNombreServicio;
    private JTextField txtPrecio;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private ControlServicios controlServicios;

    public DialogServicios() {

        controlServicios = new ControlServicios();

        initComponents();

        cargarServicios();
    }

    private void initComponents() {

        setTitle("Gestión de Servicios");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Nombre Servicio",
                        "Precio"
                }, 0);

        tabla = new JTable(modelo);

        add(new JScrollPane(tabla),
                BorderLayout.CENTER);

        JPanel panelFormulario =
                new JPanel(
                        new GridLayout(
                                3, 2, 5, 5));

        txtIdServicio = new JTextField();
        txtIdServicio.setEditable(false);

        txtNombreServicio = new JTextField();
        txtPrecio = new JTextField();

        panelFormulario.add(new JLabel("ID Servicio"));
        panelFormulario.add(txtIdServicio);

        panelFormulario.add(
                new JLabel("Nombre Servicio"));
        panelFormulario.add(
                txtNombreServicio);

        panelFormulario.add(
                new JLabel("Precio"));
        panelFormulario.add(
                txtPrecio);

        btnAgregar =
                new JButton("Agregar");

        btnActualizar =
                new JButton("Actualizar");

        btnEliminar =
                new JButton("Eliminar");

        btnLimpiar =
                new JButton("Limpiar");

        JPanel panelBotones =
                new JPanel();

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelSur =
                new JPanel(
                        new BorderLayout());

        panelSur.add(
                panelFormulario,
                BorderLayout.CENTER);

        panelSur.add(
                panelBotones,
                BorderLayout.SOUTH);

        add(
                panelSur,
                BorderLayout.SOUTH);

        btnAgregar.addActionListener(
                e -> agregar());

        btnActualizar.addActionListener(
                e -> actualizar());

        btnEliminar.addActionListener(
                e -> eliminar());

        btnLimpiar.addActionListener(
                e -> limpiar());

        tabla.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                seleccionar();
                            }
                        });
    }

    private void cargarServicios() {

        try {

            modelo.setRowCount(0);

            for (Servicio servicio :
                    controlServicios.consultar()) {

                modelo.addRow(
                        new Object[]{
                                servicio.getIdServicio(),
                                servicio.getNombreServicio(),
                                servicio.getPrecio()
                        });
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    private void agregar() {

        try {

            Servicio servicio =
                    new Servicio();

            servicio.setNombreServicio(
                    txtNombreServicio.getText());

            servicio.setPrecio(
                    Double.parseDouble(
                            txtPrecio.getText()));

            controlServicios.agregar(
                    servicio);

            cargarServicios();

            limpiar();

            JOptionPane.showMessageDialog(
                    this,
                    "Servicio agregado correctamente");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    private void actualizar() {

        try {

            int fila = tabla.getSelectedRow();
            if (fila == -1) return;

            Servicio servicio = new Servicio();

            servicio.setIdServicio(
                    (int) modelo.getValueAt(fila, 0)
            );

            servicio.setNombreServicio(txtNombreServicio.getText());
            servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));

            controlServicios.actualizar(servicio);

            cargarServicios();
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void eliminar() {

        try {

            int fila = tabla.getSelectedRow();
            if (fila == -1) return;

            Servicio servicio = new Servicio();
            servicio.setIdServicio((int) modelo.getValueAt(fila, 0));

            controlServicios.eliminar(servicio);

            cargarServicios();
            limpiar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void seleccionar() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        txtIdServicio.setText(
                modelo.getValueAt(fila, 0).toString()
        );

        txtNombreServicio.setText(
                modelo.getValueAt(fila, 1).toString()
        );

        txtPrecio.setText(
                modelo.getValueAt(fila, 2).toString()
        );
    }

    private void limpiar() {

        txtNombreServicio.setText("");
        txtPrecio.setText("");

        tabla.clearSelection();
    }
}