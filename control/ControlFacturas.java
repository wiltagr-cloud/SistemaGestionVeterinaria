package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.Factura;

import java.util.ArrayList;

public class ControlFacturas {

    private IFachada fachada;

    public ControlFacturas() {

        fachada = new FachadaArchivos();
    }

    public Factura obtener(
            Factura factura)
            throws Exception {

        return fachada.obten(factura);
    }

    public void agregar(
            Factura factura)
            throws Exception {

        fachada.agrega(factura);
    }

    public void actualizar(
            Factura factura)
            throws Exception {

        fachada.actualiza(factura);
    }

    public void eliminar(
            Factura factura)
            throws Exception {

        fachada.elimina(factura);
    }

    public ArrayList<Factura>
    consultar()
            throws Exception {

        return fachada.consultaFacturas();
    }

    public ArrayList<Factura>
    consultarPorCliente(
            int idCliente)
            throws Exception {

        return fachada
                .consultaFacturasCliente(
                        idCliente);
    }
}