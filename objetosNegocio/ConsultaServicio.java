package objetosNegocio;

public class ConsultaServicio {

    private int idConsultaServicio;
    private int idConsulta;
    private int idServicio;
    private int cantidad;

    public ConsultaServicio() {
    }

    public ConsultaServicio(int idConsultaServicio,
                            int idConsulta,
                            int idServicio,
                            int cantidad) {
        this.idConsultaServicio = idConsultaServicio;
        this.idConsulta = idConsulta;
        this.idServicio = idServicio;
        this.cantidad = cantidad;
    }

    public int getIdConsultaServicio() {
        return idConsultaServicio;
    }

    public void setIdConsultaServicio(int idConsultaServicio) {
        this.idConsultaServicio = idConsultaServicio;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        ConsultaServicio other =
                (ConsultaServicio) obj;

        return this.idConsultaServicio
                == other.idConsultaServicio;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idConsultaServicio);
    }

    @Override
    public String toString() {
        return "ConsultaServicio #" + idConsultaServicio;
    }
}
