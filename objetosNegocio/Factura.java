package objetosNegocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int idFactura;
    private LocalDate fecha;
    private int idCliente;
    private String nombrePagador;
    private String cedulaPagador;
    private int idConsulta;
    private List<DetalleFactura> detalleFacturas;
    private double ivaTotal;
    private double total;
    public Factura(){}
    public Factura(int idFactura,
                   LocalDate fecha,
                   int idCliente,
                   String nombrePagador,
                   String cedulaPagador,
                   int idConsulta,
                   double ivaTotal,
                   double total){
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombrePagador = nombrePagador;
        this.cedulaPagador = cedulaPagador;
        this.idConsulta = idConsulta;
        this.detalleFacturas = new ArrayList<>();
        this.ivaTotal = ivaTotal;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombrePagador() {
        return nombrePagador;
    }

    public String getCedulaPagador() {
        return cedulaPagador;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public List<DetalleFactura> getDetalleFacturas() {
        return detalleFacturas;
    }

    public double getIvaTotal() {
        return ivaTotal;
    }

    public double getTotal() {
        return total;
    }
    public void agregarDetalleFactura(DetalleFactura detalleFacturas) {
        this.detalleFacturas.add(detalleFacturas);
    }
    public void calcularTotal() {

        double subtotal = 0;

        for (DetalleFactura detalle : detalleFacturas) {
            subtotal += detalle.getSubtotal();
        }

        ivaTotal = subtotal * 0.15;

        total = subtotal + ivaTotal;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCedulaPagador(String cedulaPagador) {
        this.cedulaPagador = cedulaPagador;
    }

    public void agregarDetalleFacturas(DetalleFactura detalleFacturas) {
        this.detalleFacturas.add(detalleFacturas);
    }

    public void setIvaTotal(double ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public void setNombrePagador(String nombrePagador) {
        this.nombrePagador = nombrePagador;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Factura other =
                (Factura) obj;

        return this.idFactura
                == other.idFactura;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idFactura
        );
    }

    @Override
    public String toString() {
        return "Factura #" + idFactura;
    }
}
