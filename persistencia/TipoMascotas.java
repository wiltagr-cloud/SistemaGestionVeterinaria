package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.TipoMascota;

import java.io.*;
import java.util.ArrayList;

public class TipoMascotas
        extends AccesoAleatorio {

    public TipoMascotas(
            String nomArchivo) {

        super(
                nomArchivo,
                64);
        try {
            File f = new File(nomArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear archivo", e);
        }
    }
    private TipoMascota leeTipoMascota()
            throws IOException {

        TipoMascota tipoMascota =
                new TipoMascota();

        tipoMascota.setIdTipoMascota(
                archivo.readInt());

        tipoMascota.setNombreTipoMascota(
                leeString(30));

        return tipoMascota;
    }
    private void escribeTipoMascota(
            TipoMascota tipoMascota)
            throws IOException {

        archivo.writeInt(
                tipoMascota.getIdTipoMascota());

        escribeString(
                tipoMascota.getNombreTipoMascota(),
                30);
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (TipoMascota tm :
                    lista()) {

                if (tm.getIdTipoMascota()
                        > mayor) {

                    mayor =
                            tm.getIdTipoMascota();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public TipoMascota obten(
            TipoMascota buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                TipoMascota tipoMascota =
                        leeTipoMascota();

                if (tipoMascota.equals(
                        buscada)) {

                    return tipoMascota;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer tipos de mascota",
                    e);
        }
    }
    public void agrega(
            TipoMascota tipoMascota)
            throws PersistenciaException {

        try {

            tipoMascota.setIdTipoMascota(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeTipoMascota(
                    tipoMascota);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar tipo de mascota",
                    e);
        }
    }
    public ArrayList<TipoMascota> lista()
            throws PersistenciaException {

        ArrayList<TipoMascota> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeTipoMascota());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar tipos de mascota",
                    e);
        }
    }
    public void actualiza(
            TipoMascota tipoMascota)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                TipoMascota leida =
                        leeTipoMascota();

                if (leida.getIdTipoMascota()
                        == tipoMascota.getIdTipoMascota()) {

                    archivo.seek(
                            posicion);

                    escribeTipoMascota(
                            tipoMascota);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Tipo de mascota inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar tipo de mascota",
                    e);
        }
    }
    public void elimina(
            TipoMascota tipoMascota)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                TipoMascota leida =
                        leeTipoMascota();

                if (leida.getIdTipoMascota()
                        == tipoMascota.getIdTipoMascota()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Tipo de mascota inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar tipo de mascota",
                    e);
        }
    }
}