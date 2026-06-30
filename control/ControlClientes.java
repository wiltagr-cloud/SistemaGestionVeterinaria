package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;

import objetosNegocio.Cliente;
import objetosNegocio.Mascota;

import java.util.ArrayList;

public class ControlClientes {

    private IFachada fachada;

    public ControlClientes() {

        fachada =
                new FachadaArchivos();
    }

    public void agregar(
            Cliente cliente)
            throws Exception {

        validarCliente(cliente);

        for (Cliente c : consultar()) {

            if (c.getCedula().equals(cliente.getCedula())) {

                throw new Exception(
                        "Ya existe un cliente con esa cédula"
                );
            }
        }

        fachada.agrega(cliente);
    }

    public void actualizar(
            Cliente cliente)
            throws Exception {

        validarCliente(cliente);

        for (Cliente c : consultar()) {

            if (c.getIdCliente() != cliente.getIdCliente()
                    && c.getCedula().equals(cliente.getCedula())) {

                throw new Exception(
                        "Ya existe un cliente con esa cédula"
                );
            }
        }

        fachada.actualiza(cliente);
    }

    public void eliminar(
            Cliente cliente)
            throws Exception {

        fachada.elimina(cliente);
    }

    public Cliente obtener(
            Cliente cliente)
            throws Exception {

        return fachada.obten(cliente);
    }

    public ArrayList<Cliente>
    consultar()
            throws Exception {

        return fachada.consultaClientes();
    }
    private void validarCliente(
            Cliente cliente)
            throws Exception {

        if (cliente.getNombre() == null
                || cliente.getNombre().trim().isEmpty()) {

            throw new Exception(
                    "El nombre es obligatorio"
            );
        }

        if (cliente.getCedula() == null
                || cliente.getCedula().trim().isEmpty()) {

            throw new Exception(
                    "La cédula es obligatoria"
            );
        }

        if (!cliente.getCedula().matches("\\d{10}")) {

            throw new Exception(
                    "La cédula debe tener exactamente 10 dígitos"
            );
        }

        if (cliente.getTelefono() == null
                || cliente.getTelefono().trim().isEmpty()) {

            throw new Exception(
                    "El teléfono es obligatorio"
            );
        }

        if (!cliente.getTelefono().matches("\\d{10}")) {

            throw new Exception(
                    "El teléfono debe tener 10 dígitos"
            );
        }

        if (cliente.getDireccion() == null
                || cliente.getDireccion().trim().isEmpty()) {

            throw new Exception(
                    "La dirección es obligatoria"
            );
        }
    }
    public Cliente obtenerPorId(int id) throws Exception {
        return fachada.obtenerClientePorId(id);
    }
}