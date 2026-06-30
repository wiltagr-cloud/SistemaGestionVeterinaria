package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.HistorialClinico;

import java.io.*;
import java.util.ArrayList;

public class HistorialesClinicos
        extends AccesoAleatorio {

    public HistorialesClinicos(
            String nomArchivo) {

        super(
                nomArchivo,
                8);
    }
    private HistorialClinico
    leeHistorialClinico()
            throws IOException {

        HistorialClinico historial =
                new HistorialClinico();

        historial.setIdHistorialClinico(
                archivo.readInt());

        historial.setIdMascota(
                archivo.readInt());

        return historial;
    }
    private void escribeHistorialClinico(
            HistorialClinico historial)
            throws IOException {

        archivo.writeInt(
                historial.getIdHistorialClinico());

        archivo.writeInt(
                historial.getIdMascota());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (HistorialClinico historial :
                    lista()) {

                if (historial.getIdHistorialClinico()
                        > mayor) {

                    mayor =
                            historial.getIdHistorialClinico();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public HistorialClinico obten(
            HistorialClinico buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                HistorialClinico historial =
                        leeHistorialClinico();

                if (historial.equals(
                        buscado)) {

                    return historial;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer historial clinico",
                    e);
        }
    }
    public void agrega(
            HistorialClinico historial)
            throws PersistenciaException {

        try {

            historial.setIdHistorialClinico(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeHistorialClinico(
                    historial);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar historial clinico",
                    e);
        }
    }
    public ArrayList<HistorialClinico> lista()
            throws PersistenciaException {

        ArrayList<HistorialClinico> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeHistorialClinico());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar historiales clinicos",
                    e);
        }
    }
    public void actualiza(
            HistorialClinico historial)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                HistorialClinico leido =
                        leeHistorialClinico();

                if (leido.getIdHistorialClinico()
                        == historial.getIdHistorialClinico()) {

                    archivo.seek(
                            posicion);

                    escribeHistorialClinico(
                            historial);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Historial clinico inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar historial clinico",
                    e);
        }
    }
    public void elimina(
            HistorialClinico historial)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                HistorialClinico leido =
                        leeHistorialClinico();

                if (leido.getIdHistorialClinico()
                        == historial.getIdHistorialClinico()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Historial clinico inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar historial clinico",
                    e);
        }
    }
    public HistorialClinico
    obtenPorMascota(
            int idMascota)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                HistorialClinico historial =
                        leeHistorialClinico();

                if (historial.getIdMascota()
                        == idMascota) {

                    return historial;
                }
            }

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al buscar historial clinico",
                    e);
        }
    }
}