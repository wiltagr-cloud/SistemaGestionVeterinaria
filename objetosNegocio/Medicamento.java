package objetosNegocio;
import Interfaces.ICalculoDosis;

public abstract class Medicamento implements ICalculoDosis{
    private int idMedicamento;
    private String nombreMedicamento;
    private String categoria;
    private String viaAdministracion;
    private String tipoMedicamento;
    private double dosisPorKg;
    private double cantidadFija;
    private int stock;
    private double precio;
    public Medicamento(){}
    public Medicamento(int idMedicamento,
                       String nombreMedicamento,
                       String categoria,
                       String viaAdministracion,
                       String tipoMedicamento,
                       double dosisPorKg,
                       double cantidadFija,
                       int stock,
                       double precio){
        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.categoria = categoria;
        this.viaAdministracion = viaAdministracion;
        this.tipoMedicamento = tipoMedicamento;
        this.dosisPorKg = dosisPorKg;
        this.cantidadFija = cantidadFija;
        this.stock = stock;
        this.precio = precio;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public double getDosisPorKg() {
        return dosisPorKg;
    }

    public double getCantidadFija() {
        return cantidadFija;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public void setDosisPorKg(double dosisPorKg) {
        this.dosisPorKg = dosisPorKg;
    }

    public void setCantidadFija(double cantidadFija) {
        this.cantidadFija = cantidadFija;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public abstract double calcularDosis(
            double peso);
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Medicamento other =
                (Medicamento) obj;

        return this.idMedicamento
                == other.idMedicamento;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idMedicamento
        );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
