package objetosNegocio;

public class Rol {
    private int idRol;
    private String nombreRol;
    public Rol(){}

    public Rol (int idRol,
                String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Rol other =
                (Rol) obj;

        return this.idRol
                == other.idRol;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idRol
        );
    }

    @Override
    public String toString() {
        return nombreRol;
    }
}
