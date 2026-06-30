package objetosNegocio;

public class Especialidad {
    private int idEspecialidad;
    private String nombreEspecialidad;
    public Especialidad(){}
    public Especialidad(int idEspecialidad,
                        String nombreEspecialidad){
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Especialidad other =
                (Especialidad) obj;

        return this.idEspecialidad
                == other.idEspecialidad;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idEspecialidad
        );
    }

    @Override
    public String toString() {
        return nombreEspecialidad;
    }
}
