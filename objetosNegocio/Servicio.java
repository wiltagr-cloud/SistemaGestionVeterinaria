package objetosNegocio;

public class Servicio {
    private int idServicio;
    private String nombreServicio;
    private double precio;
    public Servicio(){}
    public Servicio(int idServicio,
                    String nombreServicio,
                    double precio){
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Servicio other =
                (Servicio) obj;

        return this.idServicio
                == other.idServicio;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idServicio
        );
    }

    @Override
    public String toString() {
        return nombreServicio + " ($" + precio + ")";
    }
}
