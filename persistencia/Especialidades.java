package persistencia;
import Excepciones.PersistenciaException;
import objetosNegocio.Especialidad;
import objetosNegocio.Usuario;

import java.io.*;
import java.util.ArrayList;
public class Especialidades extends AccesoAleatorio{
    public Especialidades(String nomArchivo) {

        super(
                nomArchivo,
                64);
    }
    private Especialidad leeEspecialidad()
            throws IOException {

        Especialidad especialidad =
                new Especialidad();

        especialidad.setIdEspecialidad(
                archivo.readInt());

        especialidad.setNombreEspecialidad(
                leeString(30));

        return especialidad;
    }
    private void escribeEspecialidad(
            Especialidad especialidad)
            throws IOException {

        archivo.writeInt(
                especialidad.getIdEspecialidad());

        escribeString(
                especialidad.getNombreEspecialidad(),
                30);
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Especialidad e :
                    lista()) {

                if (e.getIdEspecialidad()
                        > mayor) {

                    mayor =
                            e.getIdEspecialidad();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Especialidad obten(
            Especialidad buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Especialidad especialidad =
                        leeEspecialidad();

                if (especialidad.equals(
                        buscada)) {

                    return especialidad;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer especialidades",
                    e);
        }
    }
    public void agrega(
            Especialidad especialidad)
            throws PersistenciaException {

        try {

            especialidad.setIdEspecialidad(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeEspecialidad(
                    especialidad);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar especialidad",
                    e);
        }
    }
    public ArrayList<Especialidad> lista()
            throws PersistenciaException {

        ArrayList<Especialidad> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeEspecialidad());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar especialidades",
                    e);
        }
    }
    public void actualiza(
            Especialidad especialidad)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Especialidad leida =
                        leeEspecialidad();

                if (leida.getIdEspecialidad()
                        == especialidad.getIdEspecialidad()) {

                    archivo.seek(
                            posicion);

                    escribeEspecialidad(
                            especialidad);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Especialidad inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar especialidad",
                    e);
        }
    }
    public void elimina(
            Especialidad especialidad)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Especialidad leida =
                        leeEspecialidad();

                if (leida.getIdEspecialidad()
                        == especialidad.getIdEspecialidad()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Especialidad inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar especialidad",
                    e);
        }
    }
    public Especialidad obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Especialidad e = leeEspecialidad();

                if (e.getIdEspecialidad() == id) {
                    return e;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar especialidad", e);
        }
    }
}
