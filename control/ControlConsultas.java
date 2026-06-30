package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.Consulta;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlConsultas {

    private IFachada fachada;

    public ControlConsultas() {

        fachada = new FachadaArchivos();
    }

    public Consulta obtener(
            Consulta consulta)
            throws Exception {

        return fachada.obten(consulta);
    }

    public void agregar(
            Consulta consulta)
            throws Exception {

        fachada.agrega(consulta);
    }

    public void actualizar(
            Consulta consulta)
            throws Exception {

        fachada.actualiza(consulta);
    }

    public void eliminar(
            Consulta consulta)
            throws Exception {

        fachada.elimina(consulta);
    }

    public ArrayList<Consulta>
    consultar()
            throws Exception {

        return fachada.consultaConsultas();
    }

    public ArrayList<Consulta>
    consultarPorMascota(
            int idMascota)
            throws Exception {

        return fachada
                .consultaConsultasMascota(
                        idMascota);
    }

    public ArrayList<Consulta>
    consultarPorVeterinario(
            int idVeterinario)
            throws Exception {

        return fachada
                .consultaConsultasVeterinario(
                        idVeterinario);
    }
    public Consulta obtenerPorId(int id) throws Exception {
        return fachada.obtenerConsultaPorId(id);
    }
}