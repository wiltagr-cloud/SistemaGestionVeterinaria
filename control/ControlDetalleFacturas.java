package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.DetalleFactura;

import java.util.ArrayList;

public class ControlDetalleFacturas {

    private IFachada fachada;

    public ControlDetalleFacturas() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            DetalleFactura detalleFactura)
            throws Exception {

        fachada.agrega(
                detalleFactura);
    }

    public void eliminar(
            DetalleFactura detalleFactura)
            throws Exception {

        fachada.elimina(
                detalleFactura);
    }

    public ArrayList<DetalleFactura>
    consultarPorFactura(
            int idFactura)
            throws Exception {

        return fachada
                .consultaDetallesFactura(
                        idFactura);
    }
}