package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.DetalleHorario;

import java.util.ArrayList;

public class ControlDetalleHorarios {

    private IFachada fachada;

    public ControlDetalleHorarios() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            DetalleHorario detalleHorario)
            throws Exception {

        fachada.agrega(
                detalleHorario);
    }

    public void eliminar(
            DetalleHorario detalleHorario)
            throws Exception {

        fachada.elimina(
                detalleHorario);
    }

    public ArrayList<DetalleHorario>
    consultarPorVeterinario(
            int idVeterinario)
            throws Exception {

        return fachada
                .consultaDetalleHorariosVeterinario(
                        idVeterinario);
    }
}