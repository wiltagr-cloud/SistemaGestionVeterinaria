package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.TipoMascota;

import java.util.ArrayList;

public class ControlTipoMascotas {
    private IFachada fachada;

    public ControlTipoMascotas() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            TipoMascota tipoMascota)
            throws Exception {

        fachada.agrega(
                tipoMascota);
    }

    public void actualizar(
            TipoMascota tipoMascota)
            throws Exception {

        fachada.actualiza(
                tipoMascota);
    }

    public void eliminar(
            TipoMascota tipoMascota)
            throws Exception {

        fachada.elimina(
                tipoMascota);
    }

    public TipoMascota obtener(
            TipoMascota tipoMascota)
            throws Exception {

        return fachada.obten(
                tipoMascota);
    }

    public ArrayList<TipoMascota>
    consultar()
            throws Exception {

        return fachada
                .consultaTipoMascotas();
    }
}
