package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.Cita;
import objetosNegocio.Horario;

import java.util.ArrayList;

public class ControlCitas {

    private IFachada fachada;

    public ControlCitas() {

        fachada = new FachadaArchivos();
    }

    public Cita obtener(Cita cita)
            throws Exception {

        return fachada.obten(cita);
    }

    public void agregar(Cita cita)
            throws Exception {

        fachada.agrega(cita);
    }

    public void actualizar(Cita cita)
            throws Exception {

        fachada.actualiza(cita);
    }

    public void eliminar(Cita cita)
            throws Exception {

        fachada.elimina(cita);
    }

    public ArrayList<Cita>
    consultar()
            throws Exception {

        return fachada.consultaCitas();
    }

    public ArrayList<Cita>
    consultarPorMascota(
            int idMascota)
            throws Exception {

        return fachada
                .consultaCitasMascota(
                        idMascota);
    }

    public ArrayList<Cita>
    consultarPorVeterinario(
            int idVeterinario)
            throws Exception {

        return fachada
                .consultaCitasVeterinario(
                        idVeterinario);
    }
    public Cita obtenerCitaPorId(int id) throws Exception {
        return fachada.obtenerCitaPorId(id);
    }
}