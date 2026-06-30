package persistencia;

import Enums.DiaSemana;
import Excepciones.PersistenciaException;
import objetosNegocio.Horario;

import java.io.*;
import java.util.ArrayList;

public class Horarios
        extends AccesoAleatorio {

    public Horarios(
            String nomArchivo) {

        super(
                nomArchivo,
                24);
    }
    private Horario leeHorario()
            throws IOException {

        Horario horario =
                new Horario();

        horario.setIdHorario(
                archivo.readInt());

        horario.setDiaSemana(
                DiaSemana.values()[
                        archivo.readInt()]);

        horario.setHoraInicio(
                leeHora());

        horario.setHoraFinal(
                leeHora());

        return horario;
    }
    private void escribeHorario(
            Horario horario)
            throws IOException {

        archivo.writeInt(
                horario.getIdHorario());

        archivo.writeInt(
                horario.getDiaSemana()
                        .ordinal());

        escribeHora(
                horario.getHoraInicio());

        escribeHora(
                horario.getHoraFinal());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Horario h :
                    lista()) {

                if (h.getIdHorario()
                        > mayor) {

                    mayor =
                            h.getIdHorario();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Horario obten(
            Horario buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Horario horario =
                        leeHorario();

                if (horario.equals(
                        buscado)) {

                    return horario;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer horarios",
                    e);
        }
    }
    public void agrega(
            Horario horario)
            throws PersistenciaException {

        try {

            horario.setIdHorario(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeHorario(
                    horario);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar horario",
                    e);
        }
    }
    public ArrayList<Horario> lista()
            throws PersistenciaException {

        ArrayList<Horario> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeHorario());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar horarios",
                    e);
        }
    }
    public void actualiza(
            Horario horario)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Horario leido =
                        leeHorario();

                if (leido.getIdHorario()
                        == horario.getIdHorario()) {

                    archivo.seek(
                            posicion);

                    escribeHorario(
                            horario);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Horario inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar horario",
                    e);
        }
    }
    public void elimina(
            Horario horario)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Horario leido =
                        leeHorario();

                if (leido.getIdHorario()
                        == horario.getIdHorario()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Horario inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar horario",
                    e);
        }
    }
    public Horario obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Horario h = leeHorario();

                if (h.getIdHorario() == id) {
                    return h;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar horario", e);
        }
    }
}