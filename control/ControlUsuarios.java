package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Horario;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlUsuarios {
    private IFachada fachada;

    public ControlUsuarios() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            Usuario usuario)
            throws Exception {

        fachada.agrega(
                usuario);
    }

    public void actualizar(
            Usuario usuario)
            throws Exception {

        fachada.actualiza(
                usuario);
    }

    public void eliminar(
            Usuario usuario)
            throws Exception {

        fachada.elimina(
                usuario);
    }

    public Usuario obtener(
            Usuario usuario)
            throws Exception {

        return fachada.obten(
                usuario);
    }

    public ArrayList<Usuario>
    consultar()
            throws Exception {

        return fachada
                .consultaUsuarios();
    }

    public Usuario obtenerPorId(int id) throws Exception {
        return fachada.obtenerUsuarioPorId(id);
    }
    public ArrayList<Usuario> consultarVeterinarios() throws Exception {
        return fachada.consultaUsuariosPorRol(2);
    }
}
