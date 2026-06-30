package interfazUsuario;

import control.ControlClientes;
import objetosNegocio.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogClientes extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JTextField txtDireccion;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnAtras;

    private ControlClientes controlClientes;

    public DialogClientes() {

        controlClientes = new ControlClientes();

        setTitle("Gestión de Clientes");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarClientes();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ===== TABLA =====

        modelo = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Nombre",
                        "Cédula",
                        "Teléfono",
                        "Dirección"
                }, 0) {

            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna) {

                return false;
            }
        };

        tabla = new JTable(modelo);

        add(
                new JScrollPane(tabla),
                BorderLayout.CENTER
        );

        // ===== FORMULARIO =====

        JPanel form = new JPanel(
                new GridLayout(5, 2, 5, 5)
        );

        txtId = new JTextField();
        txtId.setEditable(false);

        txtNombre = new JTextField();
        txtCedula = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);

        form.add(new JLabel("Cédula:"));
        form.add(txtCedula);

        form.add(new JLabel("Teléfono:"));
        form.add(txtTelefono);

        form.add(new JLabel("Dirección:"));
        form.add(txtDireccion);

        // ===== BOTONES =====

        JPanel botones = new JPanel(
                new FlowLayout()
        );

        btnAgregar =
                new JButton("Agregar");

        btnActualizar =
                new JButton("Actualizar");

        btnEliminar =
                new JButton("Eliminar");

        btnLimpiar =
                new JButton("Limpiar");

        btnAtras =
                new JButton("Atrás");

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnAtras);

        JPanel sur = new JPanel(
                new BorderLayout()
        );

        sur.add(
                form,
                BorderLayout.CENTER
        );

        sur.add(
                botones,
                BorderLayout.SOUTH
        );

        add(
                sur,
                BorderLayout.SOUTH
        );

        // ===== EVENTOS =====

        btnAgregar.addActionListener(
                e -> agregar());

        btnActualizar.addActionListener(
                e -> actualizar());

        btnEliminar.addActionListener(
                e -> eliminar());

        btnLimpiar.addActionListener(
                e -> limpiar());

        btnAtras.addActionListener(
                e -> volverMenu());

        tabla.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                seleccionar();
                            }
                        });
    }

    private void cargarClientes() {

        try {

            modelo.setRowCount(0);

            ArrayList<Cliente> lista =
                    controlClientes.consultar();

            for (Cliente c : lista) {

                modelo.addRow(
                        new Object[]{
                                c.getIdCliente(),
                                c.getNombre(),
                                c.getCedula(),
                                c.getTelefono(),
                                c.getDireccion()
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void validarCampos()
            throws Exception {

        if (txtNombre.getText()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Ingrese el nombre"
            );
        }

        if (txtCedula.getText()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Ingrese la cédula"
            );
        }

        if (txtTelefono.getText()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Ingrese el teléfono"
            );
        }

        if (txtDireccion.getText()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Ingrese la dirección"
            );
        }
    }

    private void agregar() {

        try {

            validarCampos();

            Cliente cliente =
                    new Cliente();

            cliente.setNombre(
                    txtNombre.getText().trim());

            cliente.setCedula(
                    txtCedula.getText().trim());

            cliente.setTelefono(
                    txtTelefono.getText().trim());

            cliente.setDireccion(
                    txtDireccion.getText().trim());

            controlClientes.agregar(
                    cliente);

            cargarClientes();
            limpiar();

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente agregado correctamente"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void actualizar() {

        try {

            validarCampos();

            Cliente cliente =
                    new Cliente();

            cliente.setIdCliente(
                    Integer.parseInt(
                            txtId.getText()
                    ));

            cliente.setNombre(
                    txtNombre.getText().trim());

            cliente.setCedula(
                    txtCedula.getText().trim());

            cliente.setTelefono(
                    txtTelefono.getText().trim());

            cliente.setDireccion(
                    txtDireccion.getText().trim());

            controlClientes.actualizar(
                    cliente);

            cargarClientes();
            limpiar();

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente actualizado correctamente"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void eliminar() {

        try {

            if (txtId.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cliente"
                );

                return;
            }

            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Desea eliminar este cliente?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            Cliente cliente =
                    new Cliente();

            cliente.setIdCliente(
                    Integer.parseInt(
                            txtId.getText()
                    ));

            controlClientes.eliminar(
                    cliente);

            cargarClientes();
            limpiar();

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente eliminado correctamente"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void seleccionar() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {
            return;
        }

        txtId.setText(
                modelo.getValueAt(
                        fila,
                        0
                ).toString());

        txtNombre.setText(
                modelo.getValueAt(
                        fila,
                        1
                ).toString());

        txtCedula.setText(
                modelo.getValueAt(
                        fila,
                        2
                ).toString());

        txtTelefono.setText(
                modelo.getValueAt(
                        fila,
                        3
                ).toString());

        txtDireccion.setText(
                modelo.getValueAt(
                        fila,
                        4
                ).toString());

        btnAgregar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    private void limpiar() {

        txtId.setText("");
        txtNombre.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");

        tabla.clearSelection();

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void volverMenu() {

        new VentanaMenuRecepcionista()
                .setVisible(true);

        dispose();
    }
}