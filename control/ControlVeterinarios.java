package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Usuario;
import objetosNegocio.Veterinario;

import java.util.ArrayList;

public class ControlVeterinarios {
    private IFachada fachada;

    public ControlVeterinarios() {
        fachada = new FachadaArchivos();
    }

    public void agregar(
            Veterinario veterinario)
            throws Exception {

        fachada.agrega(veterinario);
    }

    public void actualizar(
            Veterinario veterinario)
            throws Exception {

        fachada.actualiza(veterinario);
    }

    public void eliminar(
            Veterinario veterinario)
            throws Exception {

        fachada.elimina(veterinario);
    }

    public Veterinario obtener(
            Veterinario veterinario)
            throws Exception {

        return fachada.obten(veterinario);
    }
    public ArrayList<Veterinario>
    consultar()
            throws Exception {

        return fachada
                .consultaVeterinarios();
    }

    public ArrayList<Veterinario>
    consultarEspecialidad(
            int idEspecialidad)
            throws Exception {

        return fachada.consultaVeterinariosEspecialidad(
                idEspecialidad);
    }
    public Veterinario obtenerPorId(int id) throws Exception {
        return fachada.obtenerVeterinarioPorId(id);
    }
    public Veterinario
    obtenerPorUsuario(
            int idUsuario)
            throws Exception {

        for (Veterinario v :
                consultar()) {

            if (v.getIdUsuario()
                    == idUsuario) {

                return v;
            }
        }

        return null;
    }

}
