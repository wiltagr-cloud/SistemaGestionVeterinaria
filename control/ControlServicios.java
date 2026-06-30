package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Servicio;

import java.util.ArrayList;

public class ControlServicios {
    private IFachada fachada;

    public ControlServicios() {

        fachada = new FachadaArchivos();
    }

    public void agregar(Servicio servicio)
            throws Exception {

        fachada.agrega(servicio);
    }

    public void actualizar(Servicio servicio)
            throws Exception {

        fachada.actualiza(servicio);
    }

    public void eliminar(Servicio servicio)
            throws Exception {

        fachada.elimina(servicio);
    }

    public Servicio obtener(Servicio servicio)
            throws Exception {

        return fachada.obten(servicio);
    }

    public ArrayList<Servicio>
    consultar()
            throws Exception {

        return fachada.consultaServicios();
    }
}
