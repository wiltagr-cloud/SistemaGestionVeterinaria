package objetosNegocio;

public class DetalleTratamiento {

    private int idDetalleTratamiento;
    private int idTratamiento;
    private int idMedicamento;
    private double dosis;

    public DetalleTratamiento(){}

    public DetalleTratamiento(
            int idDetalleTratamiento,
            int idTratamiento,
            int idMedicamento,
            double dosis) {

        this.idDetalleTratamiento =
                idDetalleTratamiento;

        this.idTratamiento =
                idTratamiento;

        this.idMedicamento =
                idMedicamento;
        this.dosis = dosis;
    }

    public int getIdDetalleTratamiento() {
        return idDetalleTratamiento;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public double getDosis() {
        return dosis;
    }

    public void setIdDetalleTratamiento(
            int idDetalleTratamiento) {

        this.idDetalleTratamiento =
                idDetalleTratamiento;
    }

    public void setIdTratamiento(
            int idTratamiento) {

        this.idTratamiento =
                idTratamiento;
    }

    public void setIdMedicamento(
            int idMedicamento) {

        this.idMedicamento =
                idMedicamento;
    }

    public void setDosis(double dosis) {
        this.dosis = dosis;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DetalleTratamiento other =
                (DetalleTratamiento) obj;

        return this.idDetalleTratamiento
                == other.idDetalleTratamiento;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idDetalleTratamiento);
    }

    @Override
    public String toString() {

        return "DetalleTratamiento{" +
                "idDetalleTratamiento=" + idDetalleTratamiento +
                ", idTratamiento=" + idTratamiento +
                ", idMedicamento=" + idMedicamento +
                ", dosis=" + dosis +
                '}';
    }
}