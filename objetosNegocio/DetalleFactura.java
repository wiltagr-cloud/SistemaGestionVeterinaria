package objetosNegocio;

public class DetalleFactura {
    private int idDetalleFactura;
    private int idFactura;
    private int idServicio;
    private int idMedicamento;
    private int cantidad;
    private double subtotal;
    private double totalDetalle;
    public DetalleFactura(){}
    public DetalleFactura(int idDetalleFactura,
                          int idFactura,
                          int idServicio,
                          int idMedicamento,
                          int cantidad,
                          double subtotal,
                          double totalDetalle){
        this.idDetalleFactura = idDetalleFactura;
        this.idFactura = idFactura;
        this.idServicio = idServicio;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.totalDetalle = totalDetalle;
    }

    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public int getIdMedicamento() {return idMedicamento;}

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotalDetalle() {
        return totalDetalle;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotalDetalle(double totalDetalle) {
        this.totalDetalle = totalDetalle;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DetalleFactura other =
                (DetalleFactura) obj;

        return this.idDetalleFactura
                == other.idDetalleFactura;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idDetalleFactura
        );
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
