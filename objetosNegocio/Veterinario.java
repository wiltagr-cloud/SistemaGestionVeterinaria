package objetosNegocio;

import java.util.ArrayList;
import java.util.List;

public class Veterinario {
    private int idVeterinario;
    private int idUsuario;
    private int idEspecialidad;
    private List<DetalleHorario> detalleHorarios;
    public Veterinario(){}

    public Veterinario (int idVeterinario,
                        int idUsuario,
                        int idEspecialidad
                        ){
        this.idVeterinario = idVeterinario;
        this.idUsuario = idUsuario;
        this.idEspecialidad = idEspecialidad;
        this.detalleHorarios = new ArrayList<>();
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public List<DetalleHorario> getDetalleHorarios() {
        return detalleHorarios;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public void agregarDetalleHorarios(DetalleHorario detalleHorarios) {
        this.detalleHorarios.add(detalleHorarios);
    }

    public void agregarDetalleHorario(
            DetalleHorario detalleHorario) {
        this.detalleHorarios.add(detalleHorario);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Veterinario other =
                (Veterinario) obj;

        return this.idVeterinario
                == other.idVeterinario;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idVeterinario
        );
    }

    @Override
    public String toString() {
        return "Veterinario #" + idVeterinario;
    }
}
