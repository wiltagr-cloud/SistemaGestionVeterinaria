package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.DetalleHorario;

import java.io.*;
import java.util.ArrayList;

public class DetalleHorarios
        extends AccesoAleatorio {

    public DetalleHorarios(
            String nomArchivo) {

        super(
                nomArchivo,
                12);
        try {
            File f = new File(nomArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear archivo", e);
        }
    }
    private DetalleHorario
    leeDetalleHorario()
            throws IOException {

        DetalleHorario detalle =
                new DetalleHorario();

        detalle.setIdDetalle(
                archivo.readInt());

        detalle.setIdHorario(
                archivo.readInt());

        detalle.setIdVeterinario(
                archivo.readInt());

        return detalle;
    }
    private void escribeDetalleHorario(
            DetalleHorario detalle)
            throws IOException {

        archivo.writeInt(
                detalle.getIdDetalle());

        archivo.writeInt(
                detalle.getIdHorario());

        archivo.writeInt(
                detalle.getIdVeterinario());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (DetalleHorario d :
                    lista()) {

                if (d.getIdDetalle()
                        > mayor) {

                    mayor =
                            d.getIdDetalle();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public DetalleHorario obten(
            DetalleHorario buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                DetalleHorario detalle =
                        leeDetalleHorario();

                if (detalle.equals(
                        buscado)) {

                    return detalle;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer detalle horario",
                    e);
        }
    }
    public void agrega(
            DetalleHorario detalle)
            throws PersistenciaException {

        try {

            detalle.setIdDetalle(
                    siguienteId());
            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeDetalleHorario(
                    detalle);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar detalle horario",
                    e);
        }
    }
    public ArrayList<DetalleHorario> lista()
            throws PersistenciaException {

        ArrayList<DetalleHorario> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeDetalleHorario());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar detalle horario",
                    e);
        }
    }
    public void actualiza(
            DetalleHorario detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleHorario leido =
                        leeDetalleHorario();

                if (leido.getIdDetalle()
                        == detalle.getIdDetalle()) {

                    archivo.seek(
                            posicion);

                    escribeDetalleHorario(
                            detalle);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle horario inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar detalle horario",
                    e);
        }
    }
    public void elimina(
            DetalleHorario detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleHorario leido =
                        leeDetalleHorario();

                if (leido.getIdDetalle()
                        == detalle.getIdDetalle()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle horario inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar detalle horario",
                    e);
        }
    }
    public ArrayList<DetalleHorario>
    listaPorVeterinario(
            int idVeterinario)
            throws PersistenciaException {

        ArrayList<DetalleHorario> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                DetalleHorario detalle =
                        leeDetalleHorario();

                if (detalle.getIdVeterinario()
                        == idVeterinario) {

                    lista.add(detalle);
                }
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar horarios del veterinario",
                    e);
        }
    }
}