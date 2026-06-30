package objetosNegocio;

import java.util.ArrayList;
import java.util.List;

public class HistorialClinico {
    private int idHistorialClinico;
    private int idMascota;
    private List<Consulta> consultas;
    public HistorialClinico(){}

    public HistorialClinico(int idHistorialClinico,
                            int idMascota){
        this.idHistorialClinico = idHistorialClinico;
        this.idMascota = idMascota;
        this.consultas = new ArrayList<>();
    }

    public int getIdHistorialClinico() {
        return idHistorialClinico;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setIdHistorialClinico(int idHistorialClinico) {
        this.idHistorialClinico = idHistorialClinico;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void agregarConsulta(Consulta consultas) {
        this.consultas.add(consultas);
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        HistorialClinico other =
                (HistorialClinico) obj;

        return this.idHistorialClinico
                == other.idHistorialClinico;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idHistorialClinico
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
