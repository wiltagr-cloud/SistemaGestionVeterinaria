package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.Servicio;

import java.io.*;
import java.util.ArrayList;

public class Servicios
        extends AccesoAleatorio {

    public Servicios(
            String nomArchivo) {

        super(
                nomArchivo,
                72);
        try {
            File f = new File(nomArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear archivo", e);
        }
    }
    private Servicio leeServicio()
            throws IOException {

        Servicio servicio =
                new Servicio();

        servicio.setIdServicio(
                archivo.readInt());

        servicio.setNombreServicio(
                leeString(30));

        servicio.setPrecio(
                archivo.readDouble());

        return servicio;
    }
    private void escribeServicio(
            Servicio servicio)
            throws IOException {

        archivo.writeInt(
                servicio.getIdServicio());

        escribeString(
                servicio.getNombreServicio(),
                30);

        archivo.writeDouble(
                servicio.getPrecio());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Servicio s :
                    lista()) {

                if (s.getIdServicio()
                        > mayor) {

                    mayor =
                            s.getIdServicio();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Servicio obten(
            Servicio buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Servicio servicio =
                        leeServicio();

                if (servicio.equals(
                        buscado)) {

                    return servicio;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer servicios",
                    e);
        }
    }
    public void agrega(
            Servicio servicio)
            throws PersistenciaException {

        try {

            servicio.setIdServicio(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeServicio(
                    servicio);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar servicio",
                    e);
        }
    }
    public ArrayList<Servicio> lista()
            throws PersistenciaException {

        ArrayList<Servicio> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeServicio());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar servicios",
                    e);
        }
    }
    public void actualiza(
            Servicio servicio)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Servicio leido =
                        leeServicio();

                if (leido.getIdServicio()
                        == servicio.getIdServicio()) {

                    archivo.seek(
                            posicion);

                    escribeServicio(
                            servicio);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Servicio inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar servicio",
                    e);
        }
    }
    public void elimina(
            Servicio servicio)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Servicio leido =
                        leeServicio();

                if (leido.getIdServicio()
                        == servicio.getIdServicio()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Servicio inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar servicio",
                    e);
        }
    }
}