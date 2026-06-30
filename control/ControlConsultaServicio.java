package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.ConsultaServicio;

import java.util.ArrayList;

public class ControlConsultaServicio {

    private IFachada fachada;

    public ControlConsultaServicio() {
        fachada = new FachadaArchivos();
    }

    public void agregar(ConsultaServicio consultaServicio)
            throws Exception {

        fachada.agrega(consultaServicio);
    }

    public void eliminar(ConsultaServicio consultaServicio)
            throws Exception {

        fachada.elimina(consultaServicio);
    }

    public ArrayList<ConsultaServicio> consultarPorConsulta(
            int idConsulta)
            throws Exception {

        return fachada.consultaServiciosConsulta(idConsulta);
    }
}
