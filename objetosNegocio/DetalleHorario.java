package objetosNegocio;

public class DetalleHorario {
    private int idDetalle;
    private int idHorario;
    private int idVeterinario;

    public DetalleHorario(){}

    public DetalleHorario(int idDetalle,
                          int idHorario,
                          int idVeterinario){
        this.idDetalle = idDetalle;
        this.idHorario = idHorario;
        this.idVeterinario = idVeterinario;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DetalleHorario other =
                (DetalleHorario) obj;

        return this.idDetalle
                == other.idDetalle;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idDetalle
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
