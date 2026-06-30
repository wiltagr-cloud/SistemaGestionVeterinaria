package control;

import Fachadas.FachadaArchivos;
import Interfaces.IFachada;
import objetosNegocio.Medicamento;
import objetosNegocio.Usuario;

import java.util.ArrayList;

public class ControlMedicamentos {
    private IFachada fachada;

    public ControlMedicamentos() {
        fachada = new FachadaArchivos();
    }

    public void agregar(
            Medicamento medicamento)
            throws Exception {

        fachada.agrega(medicamento);
    }

    public void actualizar(
            Medicamento medicamento)
            throws Exception {

        fachada.actualiza(medicamento);
    }

    public void eliminar(
            Medicamento medicamento)
            throws Exception {

        fachada.elimina(medicamento);
    }

    public Medicamento obtener(
            Medicamento medicamento)
            throws Exception {

        return fachada.obten(medicamento);
    }

    public ArrayList<Medicamento>
    consultar()
            throws Exception {

        return fachada.consultaMedicamentos();
    }

    public ArrayList<Medicamento>
    consultarCategoria(
            String categoria)
            throws Exception {

        return fachada.consultaMedicamentosCategoria(
                categoria);
    }
    public Medicamento obtenerPorId(int id) throws Exception {
        return fachada.obtenerMedicamentoPorId(id);
    }
}
