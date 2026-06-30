package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Tratamiento;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlTratamientos {
    private IFachada fachada;

    public ControlTratamientos() {
        fachada = new FachadaArchivos();
    }

    public void agregar(
            Tratamiento tratamiento)
            throws Exception {

        fachada.agrega(tratamiento);
    }

    public void actualizar(
            Tratamiento tratamiento)
            throws Exception {

        fachada.actualiza(tratamiento);
    }

    public void eliminar(
            Tratamiento tratamiento)
            throws Exception {

        fachada.elimina(tratamiento);
    }

    public Tratamiento obtener(
            Tratamiento tratamiento)
            throws Exception {

        return fachada.obten(tratamiento);
    }

    public ArrayList<Tratamiento>
    consultar()
            throws Exception {

        return fachada.consultaTratamientos();
    }
    public Tratamiento obtenerPorId(int id) throws Exception {
        return fachada.obtenerTratamientoPorId(id);
    }
}
