package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.Cliente;
import objetosNegocio.Consulta;
import objetosNegocio.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Consultas
        extends AccesoAleatorio {

    public Consultas(
            String nomArchivo) {

        super(
                nomArchivo,
                632);
    }
    private Consulta
    leeConsulta()
            throws IOException {

        Consulta consulta =
                new Consulta();

        consulta.setIdConsulta(
                archivo.readInt());

        consulta.setFecha(
                leeFecha());

        consulta.setMotivo(
                leeString(100));

        consulta.setDiagnostico(
                leeString(200));

        consulta.setIdMascota(
                archivo.readInt());

        consulta.setIdVeterinario(
                archivo.readInt());

        consulta.setIdTratamiento(
                archivo.readInt());

        consulta.setIdCita(
                archivo.readInt());

        return consulta;
    }
    private void escribeConsulta(
            Consulta consulta)
            throws IOException {

        archivo.writeInt(
                consulta.getIdConsulta());

        escribeFecha(
                consulta.getFecha());

        escribeString(
                consulta.getMotivo(),
                100);

        escribeString(
                consulta.getDiagnostico(),
                200);

        archivo.writeInt(
                consulta.getIdMascota());

        archivo.writeInt(
                consulta.getIdVeterinario());

        archivo.writeInt(
                consulta.getIdTratamiento());

        archivo.writeInt(
                consulta.getIdCita());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Consulta consulta :
                    lista()) {

                if (consulta.getIdConsulta()
                        > mayor) {

                    mayor =
                            consulta.getIdConsulta();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public void agrega(
            Consulta consulta)
            throws PersistenciaException {

        try {

            consulta.setIdConsulta(
                    siguienteId());
            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeConsulta(
                    consulta);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar consulta",
                    e);
        }
    }
    public ArrayList<Consulta> lista()
            throws PersistenciaException {

        ArrayList<Consulta> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeConsulta());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar consultas",
                    e);
        }
    }
    public ArrayList<Consulta>
    listaPorMascota(
            int idMascota)
            throws PersistenciaException {

        ArrayList<Consulta> consultas =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Consulta consulta =
                        leeConsulta();

                if (consulta.getIdMascota()
                        == idMascota) {

                    consultas.add(
                            consulta);
                }
            }

        } catch (EOFException e) {

            return consultas;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar consultas",
                    e);
        }
    }
    public ArrayList<Consulta>
    listaPorVeterinario(
            int idVeterinario)
            throws PersistenciaException {

        ArrayList<Consulta> consultas =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Consulta consulta =
                        leeConsulta();

                if (consulta.getIdVeterinario()
                        == idVeterinario) {

                    consultas.add(
                            consulta);
                }
            }

        } catch (EOFException e) {

            return consultas;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar consultas",
                    e);
        }
    }
    public Consulta
    obten(
            Consulta buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Consulta consulta =
                        leeConsulta();

                if (consulta.equals(
                        buscada)) {

                    return consulta;
                }
            }

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al buscar consulta",
                    e);
        }
    }
    public void actualiza(
            Consulta consulta)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Consulta leida =
                        leeConsulta();

                if (leida.getIdConsulta()
                        == consulta.getIdConsulta()) {

                    archivo.seek(
                            posicion);

                    escribeConsulta(
                            consulta);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Consulta inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar consulta",
                    e);
        }
    }
    public void elimina(
            Consulta consulta)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Consulta leida =
                        leeConsulta();

                if (leida.getIdConsulta()
                        == consulta.getIdConsulta()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Consulta inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar consulta",
                    e);
        }
    }
    public Consulta obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Consulta co = leeConsulta();

                if (co.getIdConsulta() == id) {
                    return co;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar consulta", e);
        }
    }
}