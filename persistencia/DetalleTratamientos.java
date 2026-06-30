package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.DetalleTratamiento;

import java.io.*;
import java.util.ArrayList;

public class DetalleTratamientos
        extends AccesoAleatorio {

    public DetalleTratamientos(
            String nomArchivo) {

        super(
                nomArchivo,
                20);
    }
    private DetalleTratamiento leeDetalleTratamiento()
            throws IOException {

        DetalleTratamiento detalle = new DetalleTratamiento();

        detalle.setIdDetalleTratamiento(archivo.readInt());
        detalle.setIdTratamiento(archivo.readInt());
        detalle.setIdMedicamento(archivo.readInt());
        detalle.setDosis(archivo.readDouble());

        return detalle;
    }
    private void escribeDetalleTratamiento(DetalleTratamiento detalle)
            throws IOException {

        archivo.writeInt(detalle.getIdDetalleTratamiento());
        archivo.writeInt(detalle.getIdTratamiento());
        archivo.writeInt(detalle.getIdMedicamento());
        archivo.writeDouble(detalle.getDosis());
    }
    public int siguienteId() throws PersistenciaException {

        try {

            int mayor = 0;

            for (DetalleTratamiento d : lista()) {
                if (d.getIdDetalleTratamiento() > mayor) {
                    mayor = d.getIdDetalleTratamiento();
                }
            }

            return mayor + 1;

        } catch (Exception e) {
            return 1;
        }
    }
    public DetalleTratamiento obten(
            DetalleTratamiento buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                DetalleTratamiento detalle =
                        leeDetalleTratamiento();

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
                    "Error al leer detalle tratamiento",
                    e);
        }
    }
    public void agrega(DetalleTratamiento detalle)
            throws PersistenciaException {

        try {

            detalle.setIdDetalleTratamiento(siguienteId());

            archivo = new RandomAccessFile(nomArchivo, "rw");
            archivo.seek(archivo.length());

            escribeDetalleTratamiento(detalle);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar detalle tratamiento", e);
        }
    }
    public ArrayList<DetalleTratamiento> lista()
            throws PersistenciaException {

        ArrayList<DetalleTratamiento> lista = new ArrayList<>();

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {
                lista.add(leeDetalleTratamiento());
            }

        } catch (EOFException e) {
            return lista;

        } catch (IOException e) {
            throw new PersistenciaException(
                    "Error al listar detalle tratamiento", e);
        }
    }
    public void actualiza(
            DetalleTratamiento detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleTratamiento leido =
                        leeDetalleTratamiento();

                if (leido.getIdDetalleTratamiento()
                        == detalle.getIdDetalleTratamiento()) {

                    archivo.seek(
                            posicion);

                    escribeDetalleTratamiento(
                            detalle);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle tratamiento inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar detalle tratamiento",
                    e);
        }
    }
    public void elimina(
            DetalleTratamiento detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleTratamiento leido =
                        leeDetalleTratamiento();

                if (leido.getIdDetalleTratamiento()
                        == detalle.getIdDetalleTratamiento()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle tratamiento inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar detalle tratamiento",
                    e);
        }
    }
    public ArrayList<DetalleTratamiento> listaPorTratamiento(int idTratamiento)
            throws PersistenciaException {

        ArrayList<DetalleTratamiento> lista = new ArrayList<>();

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                DetalleTratamiento detalle = leeDetalleTratamiento();

                if (detalle.getIdTratamiento() == idTratamiento) {
                    lista.add(detalle);
                }
            }

        } catch (EOFException e) {
            return lista;

        } catch (IOException e) {
            throw new PersistenciaException(
                    "Error al listar medicamentos del tratamiento", e);
        }
    }
}