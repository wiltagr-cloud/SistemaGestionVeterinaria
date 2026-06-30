package interfazUsuario;

import control.*;
import objetosNegocio.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DialogHistorialClinico extends JFrame {

    private ControlConsultas controlConsulta = new ControlConsultas();
    private ControlTratamientos controlTratamiento = new ControlTratamientos();
    private ControlDetalleTratamientos controlDetalle = new ControlDetalleTratamientos();
    private ControlMedicamentos controlMedicamentos = new ControlMedicamentos();
    private ControlMascotas controlMascotas = new ControlMascotas();

    private JComboBox<ItemCombo> cmbMascotas;

    private JTable tablaConsultas;
    private JTable tablaTratamiento;
    private JTable tablaMedicamentos;
    private JTextField txtBuscarMascota;
    private ArrayList<Mascota> listaMascotas = new ArrayList<>();
    private DefaultTableModel modeloConsultas;
    private DefaultTableModel modeloTratamiento;
    private DefaultTableModel modeloMedicamentos;


    public DialogHistorialClinico() {

        setTitle("Historial Clínico");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
        cargarMascotas();
    }
    private void initComponents() {

        setLayout(new BorderLayout(10, 10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cmbMascotas = new JComboBox<>();
        txtBuscarMascota = new JTextField(20);
        JButton btnFactura = new JButton("Generar Factura");
        top.add(btnFactura);
        JButton btnBuscar = new JButton("Buscar");

        top.add(new JLabel("Buscar Mascota:"));
        top.add(txtBuscarMascota);
        top.add(new JLabel("Mascota:"));
        top.add(cmbMascotas);
        top.add(btnBuscar);

        add(top, BorderLayout.NORTH);

        top.add(new JLabel("Mascota:"));
        top.add(cmbMascotas);
        top.add(btnBuscar);

        add(top, BorderLayout.NORTH);
        modeloConsultas = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Motivo", "Diagnóstico"}, 0
        );

        tablaConsultas = new JTable(modeloConsultas);
        tablaConsultas.setRowHeight(25);
        tablaConsultas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane spConsultas = new JScrollPane(tablaConsultas);
        spConsultas.setBorder(new TitledBorder("Consultas"));

        modeloTratamiento = new DefaultTableModel(
                new Object[]{"Descripción", "Duración"}, 0
        );

        tablaTratamiento = new JTable(modeloTratamiento);
        tablaTratamiento.setRowHeight(25);
        tablaTratamiento.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane spTratamiento = new JScrollPane(tablaTratamiento);
        spTratamiento.setBorder(new TitledBorder("Tratamiento"));

        modeloMedicamentos = new DefaultTableModel(
                new Object[]{"Medicamento", "Dosis"}, 0
        );

        tablaMedicamentos = new JTable(modeloMedicamentos);
        tablaMedicamentos.setRowHeight(25);
        tablaMedicamentos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane spMedicamentos = new JScrollPane(tablaMedicamentos);
        spMedicamentos.setBorder(new TitledBorder("Medicamentos"));

        JSplitPane split1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                spConsultas,
                spTratamiento
        );
        split1.setResizeWeight(0.5);
        split1.setOneTouchExpandable(true);
        split1.setContinuousLayout(true);

        JSplitPane split2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                split1,
                spMedicamentos
        );
        split2.setResizeWeight(0.7);
        split2.setOneTouchExpandable(true);
        split2.setContinuousLayout(true);

        add(split2, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> cargarHistorial());
        btnFactura.addActionListener(e -> generarFactura());

        tablaConsultas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDetalleTratamiento();
            }
        });
        txtBuscarMascota.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarMascotas();
            }
        });
    }

    private void cargarMascotas() {

        try {
            cmbMascotas.removeAllItems();
            listaMascotas.clear();

            listaMascotas = controlMascotas.consultar();

            for (Mascota m : listaMascotas) {
                cmbMascotas.addItem(new ItemCombo(
                        m.getIdMascota(),
                        m.getNombreMascota()
                ));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarHistorial() {

        try {

            modeloConsultas.setRowCount(0);

            ItemCombo mascota = (ItemCombo) cmbMascotas.getSelectedItem();
            if (mascota == null) return;

            for (Consulta c : controlConsulta.consultarPorMascota(mascota.getId())) {

                modeloConsultas.addRow(new Object[]{
                        c.getIdConsulta(),
                        c.getFecha(),
                        c.getMotivo(),
                        c.getDiagnostico()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarDetalleTratamiento() {

        try {

            modeloTratamiento.setRowCount(0);
            modeloMedicamentos.setRowCount(0);

            int fila = tablaConsultas.getSelectedRow();
            if (fila == -1) return;

            int idConsulta = (int) modeloConsultas.getValueAt(fila, 0);

            Consulta c = controlConsulta.obtenerPorId(idConsulta);
            if (c == null) return;

            Tratamiento t = controlTratamiento.obtenerPorId(c.getIdTratamiento());
            if (t == null) return;

            modeloTratamiento.addRow(new Object[]{
                    t.getDescripcion(),
                    t.getDuracionDias()
            });

            ArrayList<DetalleTratamiento> lista =
                    controlDetalle.listaPorTratamiento(t.getIdTratamiento());

            for (DetalleTratamiento d : lista) {

                Medicamento m = controlMedicamentos.obtenerPorId(d.getIdMedicamento());

                if (m != null) {
                    modeloMedicamentos.addRow(new Object[]{
                            m.getNombreMedicamento(),
                            d.getDosis()
                    });
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void filtrarMascotas() {

        String texto = txtBuscarMascota.getText().toLowerCase();

        cmbMascotas.removeAllItems();

        for (Mascota m : listaMascotas) {

            if (m.getNombreMascota().toLowerCase().contains(texto)) {

                cmbMascotas.addItem(new ItemCombo(
                        m.getIdMascota(),
                        m.getNombreMascota()
                ));
            }
        }
    }
    private void generarFactura() {

        try {

            int fila = tablaConsultas.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione una consulta para generar la factura");
                return;
            }

            int idConsulta =
                    (int) modeloConsultas.getValueAt(fila, 0);

            Consulta consulta =
                    controlConsulta.obtenerPorId(idConsulta);

            if (consulta == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se encontro la consulta seleccionada");
                return;
            }

            DialogFactura dialogFactura =
                    new DialogFactura(this, consulta);
            dialogFactura.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}