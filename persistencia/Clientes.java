package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.Cliente;
import objetosNegocio.Usuario;

import java.io.*;
import java.util.ArrayList;

public class Clientes
        extends AccesoAleatorio {

    public Clientes(String nomArchivo) {

        super(
                nomArchivo,
                204);
        try {
            File f = new File(nomArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear archivo", e);
        }
    }
    private Cliente leeCliente()
            throws IOException {

        Cliente cliente =
                new Cliente();

        cliente.setIdCliente(
                archivo.readInt());

        cliente.setNombre(
                leeString(30));

        cliente.setCedula(
                leeString(10));

        cliente.setTelefono(
                leeString(10));

        cliente.setDireccion(
                leeString(50));

        return cliente;
    }
    private void escribeCliente(
            Cliente cliente)
            throws IOException {

        archivo.writeInt(
                cliente.getIdCliente());

        escribeString(
                cliente.getNombre(),
                30);

        escribeString(
                cliente.getCedula(),
                10);

        escribeString(
                cliente.getTelefono(),
                10);

        escribeString(
                cliente.getDireccion(),
                50);
    }
    public int siguienteId() throws PersistenciaException {
        int mayor = 0;

        try {
            for (Cliente c : lista()) {
                if (c.getIdCliente() > mayor) {
                    mayor = c.getIdCliente();
                }
            }
        } catch (Exception e) {
            return 1; // archivo vacío o no existe
        }

        return mayor + 1;
    }
    public Cliente obten(
            Cliente buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Cliente cliente =
                        leeCliente();

                if (cliente.equals(
                        buscado)) {

                    return cliente;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer clientes",
                    e);
        }
    }
    public void agrega(
            Cliente cliente)
            throws PersistenciaException {

        try {

            cliente.setIdCliente(
                    siguienteId());
            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");
            archivo.seek(
                    archivo.length());

            escribeCliente(
                    cliente);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar cliente",
                    e);
        }
    }
    public ArrayList<Cliente> lista()
            throws PersistenciaException {

        ArrayList<Cliente> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeCliente());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar clientes",
                    e);
        }
    }
    public void actualiza(
            Cliente cliente)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Cliente leido =
                        leeCliente();

                if (leido.getIdCliente()
                        == cliente.getIdCliente()) {

                    archivo.seek(
                            posicion);

                    escribeCliente(
                            cliente);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Cliente inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar cliente",
                    e);
        }
    }
    public void elimina(
            Cliente cliente)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Cliente leido =
                        leeCliente();

                if (leido.getIdCliente()
                        == cliente.getIdCliente()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Cliente inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar cliente",
                    e);
        }
    }
    public Cliente obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Cliente cl = leeCliente();

                if (cl.getIdCliente() == id) {
                    return cl;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar usuario", e);
        }
    }
}