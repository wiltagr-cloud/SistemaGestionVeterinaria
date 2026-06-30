package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Especialidad;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlEspecialidades {
    private IFachada fachada;

    public ControlEspecialidades() {

        fachada = new FachadaArchivos();
    }

    public void agregar(Especialidad especialidad)
            throws Exception {

        fachada.agrega(especialidad);
    }

    public void actualizar(Especialidad especialidad)
            throws Exception {

        fachada.actualiza(especialidad);
    }

    public void eliminar(Especialidad especialidad)
            throws Exception {

        fachada.elimina(especialidad);
    }

    public Especialidad obtener(Especialidad especialidad)
            throws Exception {

        return fachada.obten(especialidad);
    }

    public ArrayList<Especialidad>
    consultar()
            throws Exception {

        return fachada.consultaEspecialidades();
    }
    public Especialidad obtenerPorId(int id) throws Exception {
        return fachada.obtenerEspecialidadPorId(id);
    }
}


