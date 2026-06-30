package persistencia;

import Enums.SexoMascota;
import Excepciones.PersistenciaException;
import objetosNegocio.Mascota;
import objetosNegocio.Usuario;

import java.io.*;
import java.util.ArrayList;

public class Mascotas
        extends AccesoAleatorio {

    public Mascotas(
            String nomArchivo) {

        super(
                nomArchivo,
                172);
    }
    private Mascota leeMascota()
            throws IOException {

        Mascota mascota =
                new Mascota();

        mascota.setIdMascota(
                archivo.readInt());

        mascota.setNombreMascota(
                leeString(30));

        mascota.setFechaNacimiento(
                leeFecha());

        mascota.setPeso(
                archivo.readDouble());

        mascota.setIdTipoMascota(
                archivo.readInt());

        mascota.setIdCliente(
                archivo.readInt());

        mascota.setRaza(
                leeString(30));

        mascota.setSexo(
                SexoMascota.values()[
                        archivo.readInt()]);

        return mascota;
    }
    private void escribeMascota(
            Mascota mascota)
            throws IOException {

        archivo.writeInt(
                mascota.getIdMascota());

        escribeString(
                mascota.getNombreMascota(),
                30);

        escribeFecha(
                mascota.getFechaNacimiento());

        archivo.writeDouble(
                mascota.getPeso());

        archivo.writeInt(
                mascota.getIdTipoMascota());

        archivo.writeInt(
                mascota.getIdCliente());

        escribeString(
                mascota.getRaza(),
                30);

        archivo.writeInt(
                mascota.getSexoMascota().ordinal());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Mascota m :
                    lista()) {

                if (m.getIdMascota()
                        > mayor) {

                    mayor =
                            m.getIdMascota();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Mascota obten(
            Mascota buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Mascota mascota =
                        leeMascota();

                if (mascota.equals(
                        buscada)) {

                    return mascota;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer mascotas",
                    e);
        }
    }
    public void agrega(
            Mascota mascota)
            throws PersistenciaException {

        try {

            mascota.setIdMascota(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeMascota(
                    mascota);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar mascota",
                    e);
        }
    }
    public ArrayList<Mascota> lista()
            throws PersistenciaException {

        ArrayList<Mascota> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeMascota());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar mascotas",
                    e);
        }
    }
    public void actualiza(
            Mascota mascota)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Mascota leida =
                        leeMascota();

                if (leida.getIdMascota()
                        == mascota.getIdMascota()) {

                    archivo.seek(
                            posicion);

                    escribeMascota(
                            mascota);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Mascota inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar mascota",
                    e);
        }
    }
    public void elimina(
            Mascota mascota)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Mascota leida =
                        leeMascota();

                if (leida.getIdMascota()
                        == mascota.getIdMascota()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Mascota inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar mascota",
                    e);
        }
    }
    public ArrayList<Mascota> listaPorCliente(
            int idCliente)
            throws PersistenciaException {

        ArrayList<Mascota> mascotas =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Mascota mascota =
                        leeMascota();

                if (mascota.getIdCliente()
                        == idCliente) {

                    mascotas.add(
                            mascota);
                }
            }

        } catch (EOFException e) {

            return mascotas;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar mascotas del cliente",
                    e);
        }
    }
    public Mascota obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Mascota mas = leeMascota();

                if (mas.getIdMascota() == id) {
                    return mas;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar usuario", e);
        }
    }
}