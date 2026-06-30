package interfazUsuario;

import control.ControlRoles;
import control.ControlUsuarios;
import objetosNegocio.Rol;
import objetosNegocio.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogRoles extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtNombreRol;

    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private ControlRoles controlRoles;

    public DialogRoles() {

        controlRoles = new ControlRoles();

        setTitle("Gestión de Roles");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarRoles();

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Nombre del Rol");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        JPanel panelFormulario =
                new JPanel(new GridLayout(2, 2, 5, 5));

        txtId =
                new JTextField();

        txtId.setEditable(false);

        txtNombreRol =
                new JTextField();

        panelFormulario.add(
                new JLabel("ID:"));

        panelFormulario.add(
                txtId);

        panelFormulario.add(
                new JLabel("Nombre del Rol:"));

        panelFormulario.add(
                txtNombreRol);

        JPanel panelBotones =
                new JPanel(new FlowLayout());

        btnAgregar =
                new JButton("Agregar");

        btnActualizar =
                new JButton("Actualizar");

        btnEliminar =
                new JButton("Eliminar");

        btnLimpiar =
                new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelSur =
                new JPanel(new BorderLayout());

        panelSur.add(
                panelFormulario,
                BorderLayout.CENTER);

        panelSur.add(
                panelBotones,
                BorderLayout.SOUTH);

        add(panelSur, BorderLayout.SOUTH);

        btnAgregar.addActionListener(
                e -> agregarRol());

        btnActualizar.addActionListener(
                e -> actualizarRol());

        btnEliminar.addActionListener(
                e -> eliminarRol());

        btnLimpiar.addActionListener(
                e -> limpiarCampos());

        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                cargarRolSeleccionado();

                btnAgregar.setEnabled(false);
                btnActualizar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });
    }

    private void cargarRoles() {

        try {

            modelo.setRowCount(0);

            ArrayList<Rol> roles =
                    controlRoles.consultar();

            for (Rol rol : roles) {

                modelo.addRow(
                        new Object[]{
                                rol.getIdRol(),
                                rol.getNombreRol()
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean existeRol(String nombreRol)
            throws Exception {

        for (Rol rol : controlRoles.consultar()) {

            if (rol.getNombreRol()
                    .equalsIgnoreCase(
                            nombreRol.trim())) {

                return true;
            }
        }

        return false;
    }

    private boolean existeRol(
            String nombreRol,
            int idActual)
            throws Exception {

        for (Rol rol : controlRoles.consultar()) {

            if (rol.getNombreRol()
                    .equalsIgnoreCase(
                            nombreRol.trim())
                    &&
                    rol.getIdRol() != idActual) {

                return true;
            }
        }

        return false;
    }

    private boolean rolEnUso(int idRol)
            throws Exception {

        ControlUsuarios controlUsuarios =
                new ControlUsuarios();

        for (Usuario usuario :
                controlUsuarios.consultar()) {

            if (usuario.getIdRol()
                    == idRol) {

                return true;
            }
        }

        return false;
    }

    private boolean esRolProtegido(
            String nombreRol) {

        return nombreRol.equalsIgnoreCase(
                "Administrador")
                ||
                nombreRol.equalsIgnoreCase(
                        "Veterinario")
                ||
                nombreRol.equalsIgnoreCase(
                        "Recepcionista");
    }

    private void agregarRol() {

        try {

            String nombreRol =
                    txtNombreRol.getText().trim();

            if (nombreRol.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el nombre del rol");

                return;
            }

            if (existeRol(nombreRol)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un rol con ese nombre");

                return;
            }

            Rol rol =
                    new Rol();

            rol.setNombreRol(nombreRol);

            controlRoles.agregar(rol);

            JOptionPane.showMessageDialog(
                    this,
                    "Rol agregado correctamente");

            cargarRoles();
            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarRol() {

        try {

            if (txtId.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un rol");

                return;
            }

            String nombreRol =
                    txtNombreRol.getText().trim();

            if (nombreRol.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el nombre del rol");

                return;
            }

            if (esRolProtegido(nombreRol)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Los roles del sistema no pueden modificarse");

                return;
            }

            if (existeRol(
                    nombreRol,
                    Integer.parseInt(
                            txtId.getText()))) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un rol con ese nombre");

                return;
            }

            Rol rol =
                    new Rol();

            rol.setIdRol(
                    Integer.parseInt(
                            txtId.getText()));

            rol.setNombreRol(
                    nombreRol);

            controlRoles.actualizar(
                    rol);

            JOptionPane.showMessageDialog(
                    this,
                    "Rol actualizado correctamente");

            cargarRoles();
            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarRol() {

        try {

            if (txtId.getText()
                    .trim()
                    .isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un rol");

                return;
            }

            int idRol =
                    Integer.parseInt(
                            txtId.getText());

            String nombreRol =
                    txtNombreRol.getText().trim();

            if (esRolProtegido(nombreRol)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Este rol es del sistema y no puede eliminarse");

                return;
            }

            if (rolEnUso(idRol)) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se puede eliminar el rol porque está asignado a usuarios");

                return;
            }

            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Desea eliminar este rol?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION);

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            Rol rol =
                    new Rol();

            rol.setIdRol(idRol);

            controlRoles.eliminar(
                    rol);

            JOptionPane.showMessageDialog(
                    this,
                    "Rol eliminado correctamente");

            cargarRoles();
            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarRolSeleccionado() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {
            return;
        }

        txtId.setText(
                modelo.getValueAt(
                        fila,
                        0).toString());

        txtNombreRol.setText(
                modelo.getValueAt(
                        fila,
                        1).toString());
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtNombreRol.setText("");

        tabla.clearSelection();

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
}
