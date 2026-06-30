package objetosNegocio;

import java.util.ArrayList;
import java.util.List;

public class Tratamiento {
    private int idTratamiento;
    private String descripcion;
    private int duracionDias;
    public Tratamiento(){}

    public Tratamiento(int idTratamiento,
                       String descripcion,
                       int duracionDias){
        this.idTratamiento = idTratamiento;
        this.descripcion = descripcion;
        this.duracionDias = duracionDias;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Tratamiento other =
                (Tratamiento) obj;

        return this.idTratamiento
                == other.idTratamiento;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idTratamiento
        );
    }

    @Override
    public String toString() {
        return "Tratamiento #" + idTratamiento;
    }
}
