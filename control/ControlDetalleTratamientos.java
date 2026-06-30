package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.DetalleTratamiento;

import java.util.ArrayList;

public class ControlDetalleTratamientos {

    private IFachada fachada;

    public ControlDetalleTratamientos() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            DetalleTratamiento detalleTratamiento)
            throws Exception {

        fachada.agrega(
                detalleTratamiento);
    }

    public void eliminar(
            DetalleTratamiento detalleTratamiento)
            throws Exception {

        fachada.elimina(
                detalleTratamiento);
    }

    public ArrayList<DetalleTratamiento>
    consultarPorTratamiento(
            int idTratamiento)
            throws Exception {

        return fachada
                .consultaDetallesTratamiento(
                        idTratamiento);
    }
    public ArrayList<DetalleTratamiento>
    listaPorTratamiento(
            int idTratamiento)
            throws Exception {

        return fachada
                .listaPorTratamiento(
                        idTratamiento);
    }
}