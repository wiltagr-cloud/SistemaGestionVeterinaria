package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.Mascota;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlMascotas {

    private IFachada fachada;

    public ControlMascotas() {

        fachada = new FachadaArchivos();
    }

    public Mascota obtener(Mascota mascota)
            throws Exception {

        return fachada.obten(mascota);
    }

    public void agregar(Mascota mascota)
            throws Exception {

        fachada.agrega(mascota);
    }

    public void actualizar(Mascota mascota)
            throws Exception {

        fachada.actualiza(mascota);
    }

    public void eliminar(Mascota mascota)
            throws Exception {

        fachada.elimina(mascota);
    }

    public ArrayList<Mascota>
    consultar()
            throws Exception {

        return fachada.consultaMascotas();
    }

    public ArrayList<Mascota>
    consultarPorCliente(
            int idCliente)
            throws Exception {

        return fachada
                .consultaMascotasCliente(
                        idCliente);
    }
    public Mascota obtenerPorId(int id) throws Exception {
        return fachada.obtenerMascotaPorId(id);
    }
}