package objetosNegocio;

import Enums.EstadoCita;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private int idMascota;
    private int idVeterinario;
    private EstadoCita estadoCita;

    public Cita(){}
    public Cita(int idCita,
                LocalDate fecha,
                LocalTime hora,
                int idMascota,
                int idVeterinario,
                EstadoCita estadoCita){
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.estadoCita = estadoCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    @Override
    public String toString() {
        return "Cita #" + idCita +
                " - " + fecha +
                " " + hora;
    }
}
