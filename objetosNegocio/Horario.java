package objetosNegocio;

import Enums.DiaSemana;

import java.time.LocalTime;

public class Horario {
    private int idHorario;
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    public Horario(){}

    public Horario (int idHorario,
                    DiaSemana diaSemana,
                    LocalTime horaInicio,
                    LocalTime horaFinal){
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Horario other =
                (Horario) obj;

        return this.idHorario
                == other.idHorario;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idHorario
        );
    }

    @Override
    public String toString() {
        return diaSemana + " " +
                horaInicio + " - " +
                horaFinal;
    }
}
