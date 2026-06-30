package interfazUsuario;

import control.ControlRoles;
import control.ControlUsuarios;
import objetosNegocio.Rol;
import objetosNegocio.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogUsuarios extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtId;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JComboBox<Rol> cmbRoles;

    private ControlUsuarios controlUsuarios;
    private ControlRoles controlRoles;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;

    public DialogUsuarios() {

        controlUsuarios = new ControlUsuarios();
        controlRoles = new ControlRoles();

        setTitle("Gestión de Usuarios");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarRoles();
        cargarUsuarios();

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        // ===== TABLA =====
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Usuario", "Nombre", "Apellido", "Rol"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // ===== FORMULARIO =====
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        cmbRoles = new JComboBox<>();

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Usuario:"));
        form.add(txtUsuario);

        form.add(new JLabel("Contraseña:"));
        form.add(txtPassword);

        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);

        form.add(new JLabel("Apellido:"));
        form.add(txtApellido);

        form.add(new JLabel("Rol:"));
        form.add(cmbRoles);

        JPanel botones = new JPanel(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
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

                btnAgregar.setEnabled(false);
                btnActualizar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });
    }
    private void cargarUsuarios() {

        try {

            modelo.setRowCount(0);

            ArrayList<Usuario> lista = controlUsuarios.consultar();

            for (Usuario u : lista) {

                modelo.addRow(new Object[]{
                        u.getIdUsuario(),
                        u.getNombreUsuario(),
                        u.getNombre(),
                        u.getApellido(),
                        obtenerNombreRol(u.getIdRol())
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void cargarRoles() {

        try {

            cmbRoles.removeAllItems();

            ArrayList<Rol> roles = controlRoles.consultar();

            for (Rol r : roles) {
                cmbRoles.addItem(r);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void agregar() {

        try {

            Usuario u = new Usuario();

            u.setNombreUsuario(txtUsuario.getText().trim());
            u.setContrasenia(new String(txtPassword.getPassword()).trim());
            u.setNombre(txtNombre.getText().trim());
            u.setApellido(txtApellido.getText().trim());
            u.setIdRol(((Rol) cmbRoles.getSelectedItem()).getIdRol());

            controlUsuarios.agregar(u);

            cargarUsuarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void actualizar() {

        try {

            Usuario u = new Usuario();

            u.setIdUsuario(Integer.parseInt(txtId.getText()));
            u.setNombreUsuario(txtUsuario.getText().trim());
            u.setContrasenia(new String(txtPassword.getPassword()).trim());
            u.setNombre(txtNombre.getText().trim());
            u.setApellido(txtApellido.getText().trim());
            u.setIdRol(((Rol) cmbRoles.getSelectedItem()).getIdRol());

            controlUsuarios.actualizar(u);

            cargarUsuarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void eliminar() {

        try {

            Usuario u = new Usuario();
            u.setIdUsuario(Integer.parseInt(txtId.getText()));

            controlUsuarios.eliminar(u);

            cargarUsuarios();
            limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void seleccionar() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        txtId.setText(modelo.getValueAt(fila, 0).toString());
        txtUsuario.setText(modelo.getValueAt(fila, 1).toString());
        txtNombre.setText(modelo.getValueAt(fila, 2).toString());
        txtApellido.setText(modelo.getValueAt(fila, 3).toString());

        seleccionarRol(modelo.getValueAt(fila, 4).toString());
    }

    private void seleccionarRol(String nombreRol) {

        for (int i = 0; i < cmbRoles.getItemCount(); i++) {

            Rol r = cmbRoles.getItemAt(i);

            if (r.getNombreRol().equalsIgnoreCase(nombreRol)) {
                cmbRoles.setSelectedIndex(i);
                break;
            }
        }
    }
    private String obtenerNombreRol(int idRol) {

        try {

            for (Rol r : controlRoles.consultar()) {
                if (r.getIdRol() == idRol) {
                    return r.getNombreRol();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Sin rol";
    }

    private void limpiar() {

        txtId.setText("");
        txtUsuario.setText("");
        txtPassword.setText("");
        txtNombre.setText("");
        txtApellido.setText("");

        if (cmbRoles.getItemCount() > 0) {
            cmbRoles.setSelectedIndex(0);
        }

        tabla.clearSelection();
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
}