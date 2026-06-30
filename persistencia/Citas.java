package persistencia;
import Enums.EstadoCita;
import Excepciones.PersistenciaException;
import objetosNegocio.Cita;

import java.io.*;
import java.util.ArrayList;

public class Citas extends AccesoAleatorio{
    public Citas (String nomArchivo) {

        super(
                nomArchivo,
                36);
        try {
            File f = new File(nomArchivo);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear archivo", e);
        }
    }
    private Cita leeCita()
            throws IOException {

        Cita cita =
                new Cita();

        cita.setIdCita(
                archivo.readInt());

        cita.setFecha(
                leeFecha());

        cita.setHora(
                leeHora());

        cita.setIdMascota(
                archivo.readInt());

        cita.setIdVeterinario(
                archivo.readInt());

        cita.setEstadoCita(
                EstadoCita.values()[
                        archivo.readInt()]);

        return cita;
    }
    private void escribeCita(
            Cita cita)
            throws IOException {

        archivo.writeInt(
                cita.getIdCita());

        escribeFecha(
                cita.getFecha());

        escribeHora(
                cita.getHora());

        archivo.writeInt(
                cita.getIdMascota());

        archivo.writeInt(
                cita.getIdVeterinario());

        archivo.writeInt(
                cita.getEstadoCita()
                        .ordinal());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Cita c :
                    lista()) {

                if (c.getIdCita()
                        > mayor) {

                    mayor =
                            c.getIdCita();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Cita obten(
            Cita buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Cita cita =
                        leeCita();

                if (cita.equals(
                        buscada)) {

                    return cita;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer citas",
                    e);
        }
    }
    public void agrega(
            Cita cita)
            throws PersistenciaException {

        try {

            cita.setIdCita(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeCita(
                    cita);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar cita",
                    e);
        }
    }
    public ArrayList<Cita> lista()
            throws PersistenciaException {

        ArrayList<Cita> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeCita());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar citas",
                    e);
        }
    }
    public void actualiza(
            Cita cita)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Cita leida =
                        leeCita();

                if (leida.getIdCita()
                        == cita.getIdCita()) {

                    archivo.seek(
                            posicion);

                    escribeCita(
                            cita);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Cita inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar cita",
                    e);
        }
    }
    public void elimina(
            Cita cita)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Cita leida =
                        leeCita();

                if (leida.getIdCita()
                        == cita.getIdCita()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Cita inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar cita",
                    e);
        }
    }
    public ArrayList<Cita> listaPorVeterinario(
            int idVeterinario)
            throws PersistenciaException {

        ArrayList<Cita> citas =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Cita cita =
                        leeCita();

                if (cita.getIdVeterinario()
                        == idVeterinario) {

                    citas.add(cita);
                }
            }

        } catch (EOFException e) {

            return citas;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar citas",
                    e);
        }
    }
    public ArrayList<Cita> listaPorMascota(
            int idMascota)
            throws PersistenciaException{
        ArrayList<Cita> citas =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Cita cita =
                        leeCita();

                if (cita.getIdMascota()
                        == idMascota) {

                    citas.add(cita);
                }
            }

        } catch (EOFException e) {

            return citas;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar citas",
                    e);
        }
    }
    public Cita obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Cita c = leeCita();

                if (c.getIdCita() == id) {
                    return c;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error", e);
        }
    }
}
