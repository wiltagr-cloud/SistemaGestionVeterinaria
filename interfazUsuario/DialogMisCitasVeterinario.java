package interfazUsuario;

import control.ControlCitas;
import control.ControlClientes;
import control.ControlMascotas;
import objetosNegocio.Cita;
import objetosNegocio.Cliente;
import objetosNegocio.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;

public class DialogMisCitasVeterinario extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private ControlCitas controlCitas = new ControlCitas();
    private ControlMascotas controlMascotas = new ControlMascotas();
    private ControlClientes controlClientes = new ControlClientes();

    private int idVeterinario;

    public DialogMisCitasVeterinario(int idVeterinario) {

        this.idVeterinario = idVeterinario;

        setTitle("Mis Citas - Veterinario");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarCitas();
    }

    private void initComponents() {

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Mascota", "Fecha", "Hora", "Estado"}, 0
        );

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnAtender = new JButton("Atender cita");

        btnAtender.addActionListener(e -> atenderCita());

        JPanel south = new JPanel();
        south.add(btnAtender);

        add(south, BorderLayout.SOUTH);
    }

    private void cargarCitas() {

        try {

            modelo.setRowCount(0);

            for (Cita c : controlCitas.consultarPorVeterinario(idVeterinario)) {

                Mascota m = controlMascotas.obtener(
                        new Mascota(c.getIdMascota(), null, null, 0, 0, 0, null, null)
                );

                Cliente cl = controlClientes.obtener(
                        new Cliente(m.getIdCliente(), null, null, null, null)
                );

                modelo.addRow(new Object[]{
                        c.getIdCita(),
                        cl.getNombre(),
                        m.getNombreMascota(),
                        c.getFecha(),
                        c.getHora(),
                        c.getEstadoCita()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void atenderCita() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita");
            return;
        }

        try {

            int idCita = (int) modelo.getValueAt(fila, 0);

            Cita cita = controlCitas.obtenerCitaPorId(idCita);

            if (cita == null) {
                JOptionPane.showMessageDialog(this, "Cita no encontrada");
                return;
            }

            new DialogAtencionVeterinaria(cita).setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}