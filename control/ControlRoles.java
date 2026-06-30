package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Rol;

import java.util.ArrayList;

public class ControlRoles {
    private IFachada fachada;

    public ControlRoles() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            Rol rol)
            throws Exception {

        fachada.agrega(
                rol);
    }

    public void actualizar(
            Rol rol)
            throws Exception {

        fachada.actualiza(
                rol);
    }

    public void eliminar(
            Rol rol)
            throws Exception {

        fachada.elimina(
                rol);
    }

    public Rol obtener(
            Rol rol)
            throws Exception {

        return fachada.obten(
                rol);
    }

    public ArrayList<Rol>
    consultar()
            throws Exception {

        return fachada
                .consultaRoles();
    }
}
