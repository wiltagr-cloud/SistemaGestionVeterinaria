package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Horario;

import java.util.ArrayList;

public class ControlHorarios {
    private IFachada fachada;

    public ControlHorarios() {

        fachada = new FachadaArchivos();
    }

    public void agregar(Horario horario)
            throws Exception {

        fachada.agrega(horario);
    }

    public void actualizar(Horario horario)
            throws Exception {

        fachada.actualiza(horario);
    }

    public void eliminar(Horario horario)
            throws Exception {

        fachada.elimina(horario);
    }

    public Horario obtener(Horario horario)
            throws Exception {

        return fachada.obten(horario);
    }

    public ArrayList<Horario>
    consultar()
            throws Exception {

        return fachada.consultaHorarios();
    }
    public Horario obtenerPorId(int id) throws Exception {
        return fachada.obtenerHorarioPorId(id);
    }
}
