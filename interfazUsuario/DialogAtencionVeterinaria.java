package interfazUsuario;

import control.*;
import objetosNegocio.*;
import Enums.EstadoCita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogAtencionVeterinaria extends JFrame {

    private Cita cita;

    private ControlConsultas controlConsulta = new ControlConsultas();
    private ControlTratamientos controlTratamiento = new ControlTratamientos();
    private ControlDetalleTratamientos controlDetalle = new ControlDetalleTratamientos();
    private ControlMedicamentos controlMedicamentos = new ControlMedicamentos();
    private ControlMascotas controlMascotas = new ControlMascotas();
    private ControlVeterinarios controlVeterinarios = new ControlVeterinarios();
    private ControlUsuarios controlUsuarios = new ControlUsuarios();
    private ControlCitas controlCitas = new ControlCitas();

    private JLabel lblMascota;
    private JLabel lblVeterinario;
    private JLabel lblFecha;

    private JTextField txtMotivo;
    private JTextField txtDiagnostico;
    private JTextField txtDescripcion;
    private JTextField txtDuracion;

    private JComboBox<ItemCombo> cmbMedicamento;
    private JTextField txtDosis;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnAgregarMedicamento;
    private JButton btnGuardarTodo;

    private ArrayList<DetalleTratamiento> listaDetalles = new ArrayList<>();

    public DialogAtencionVeterinaria(Cita cita) {

        this.cita = cita;

        setTitle("Atención Veterinaria");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        cargarDatos();
        cargarMedicamentos();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JPanel pConsulta = new JPanel(new GridLayout(5, 2, 5, 5));
        pConsulta.setBorder(BorderFactory.createTitledBorder("CONSULTA"));

        lblMascota = new JLabel();
        lblVeterinario = new JLabel();
        lblFecha = new JLabel();

        txtMotivo = new JTextField();
        txtDiagnostico = new JTextField();

        pConsulta.add(new JLabel("Mascota:"));
        pConsulta.add(lblMascota);

        pConsulta.add(new JLabel("Veterinario:"));
        pConsulta.add(lblVeterinario);

        pConsulta.add(new JLabel("Fecha:"));
        pConsulta.add(lblFecha);

        pConsulta.add(new JLabel("Motivo:"));
        pConsulta.add(txtMotivo);

        pConsulta.add(new JLabel("Diagnóstico:"));
        pConsulta.add(txtDiagnostico);

        JPanel pTratamiento = new JPanel(new GridLayout(2, 2, 5, 5));
        pTratamiento.setBorder(BorderFactory.createTitledBorder("TRATAMIENTO"));

        txtDescripcion = new JTextField();
        txtDuracion = new JTextField();

        pTratamiento.add(new JLabel("Descripción:"));
        pTratamiento.add(txtDescripcion);

        pTratamiento.add(new JLabel("Duración (días):"));
        pTratamiento.add(txtDuracion);

        JPanel pMed = new JPanel(new BorderLayout());
        pMed.setBorder(BorderFactory.createTitledBorder("MEDICAMENTOS"));

        JPanel topMed = new JPanel(new GridLayout(3, 2, 5, 5));

        cmbMedicamento = new JComboBox<>();
        txtDosis = new JTextField();

        btnAgregarMedicamento = new JButton("Agregar");

        topMed.add(new JLabel("Medicamento:"));
        topMed.add(cmbMedicamento);

        topMed.add(new JLabel("Dosis:"));
        topMed.add(txtDosis);

        topMed.add(btnAgregarMedicamento);

        modelo = new DefaultTableModel(new Object[]{"Medicamento", "Dosis"}, 0);
        tabla = new JTable(modelo);

        tabla.setRowHeight(25);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(900, 200));

        pMed.add(topMed, BorderLayout.NORTH);
        pMed.add(scroll, BorderLayout.CENTER);
        btnGuardarTodo = new JButton("GUARDAR TODO / FINALIZAR");
        btnGuardarTodo.setPreferredSize(new Dimension(200, 40));

        panel.add(pConsulta);
        panel.add(pTratamiento);
        panel.add(pMed);

        add(panel, BorderLayout.CENTER);
        add(btnGuardarTodo, BorderLayout.SOUTH);
        btnAgregarMedicamento.addActionListener(e -> agregarMedicamento());
        btnGuardarTodo.addActionListener(e -> guardarTodo());
        cmbMedicamento.addActionListener(e -> calcularDosisAutomatica());
    }
    private void cargarDatos() {

        try {

            Mascota m = controlMascotas.obtener(
                    new Mascota(cita.getIdMascota(), null, null, 0, 0, 0, null, null)
            );

            Veterinario v = controlVeterinarios.obtener(
                    new Veterinario(cita.getIdVeterinario(), 0, 0)
            );

            Usuario u = controlUsuarios.obtenerPorId(v.getIdUsuario());

            lblMascota.setText(m.getNombreMascota());
            lblVeterinario.setText(u.getNombre() + " " + u.getApellido());
            lblFecha.setText(cita.getFecha().toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarMedicamentos() {

        try {

            cmbMedicamento.removeAllItems();

            for (Medicamento m : controlMedicamentos.consultar()) {

                cmbMedicamento.addItem(
                        new ItemCombo(m.getIdMedicamento(), m.getNombreMedicamento())
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void agregarMedicamento() {

        try {

            ItemCombo item = (ItemCombo) cmbMedicamento.getSelectedItem();
            Medicamento med = controlMedicamentos.obtenerPorId(item.getId());

            Mascota m = controlMascotas.obtener(
                    new Mascota(cita.getIdMascota(), null, null, 0, 0, 0, null, null)
            );

            double dosis = med.calcularDosis(m.getPeso());

            modelo.addRow(new Object[]{
                    med.getNombreMedicamento(),
                    dosis
            });

            DetalleTratamiento d = new DetalleTratamiento();
            d.setIdMedicamento(med.getIdMedicamento());
            d.setDosis(dosis);

            listaDetalles.add(d);

            txtDosis.setText(String.valueOf(dosis));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void calcularDosisAutomatica() {

        try {

            ItemCombo item = (ItemCombo) cmbMedicamento.getSelectedItem();
            if (item == null) return;

            Medicamento med = controlMedicamentos.obtenerPorId(item.getId());

            Mascota m = controlMascotas.obtener(
                    new Mascota(cita.getIdMascota(), null, null, 0, 0, 0, null, null)
            );

            txtDosis.setText(String.valueOf(med.calcularDosis(m.getPeso())));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void guardarTodo() {

        try {

            Tratamiento t = new Tratamiento();
            t.setDescripcion(txtDescripcion.getText());
            t.setDuracionDias(Integer.parseInt(txtDuracion.getText()));

            controlTratamiento.agregar(t);

            int idTratamiento = t.getIdTratamiento();

            for (DetalleTratamiento d : listaDetalles) {

                d.setIdTratamiento(idTratamiento);
                controlDetalle.agregar(d);
            }

            Consulta c = new Consulta();
            c.setFecha(cita.getFecha());
            c.setMotivo(txtMotivo.getText());
            c.setDiagnostico(txtDiagnostico.getText());
            c.setIdMascota(cita.getIdMascota());
            c.setIdVeterinario(cita.getIdVeterinario());
            c.setIdCita(cita.getIdCita());
            c.setIdTratamiento(idTratamiento);

            controlConsulta.agregar(c);

            cita.setEstadoCita(EstadoCita.ATENDIDA);
            controlCitas.actualizar(cita);

            JOptionPane.showMessageDialog(this,
                    "Atención guardada correctamente");

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}