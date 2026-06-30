package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.ConsultaServicio;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ConsultaServicios extends AccesoAleatorio {

    public ConsultaServicios(String nomArchivo) {
        super(nomArchivo, 16);
    }

    private ConsultaServicio leeConsultaServicio()
            throws IOException {

        ConsultaServicio consultaServicio =
                new ConsultaServicio();

        consultaServicio.setIdConsultaServicio(
                archivo.readInt());

        consultaServicio.setIdConsulta(
                archivo.readInt());

        consultaServicio.setIdServicio(
                archivo.readInt());

        consultaServicio.setCantidad(
                archivo.readInt());

        return consultaServicio;
    }

    private void escribeConsultaServicio(
            ConsultaServicio consultaServicio)
            throws IOException {

        archivo.writeInt(
                consultaServicio.getIdConsultaServicio());

        archivo.writeInt(
                consultaServicio.getIdConsulta());

        archivo.writeInt(
                consultaServicio.getIdServicio());

        archivo.writeInt(
                consultaServicio.getCantidad());
    }

    public int siguienteId()
            throws PersistenciaException {

        int mayor = 0;

        for (ConsultaServicio consultaServicio : lista()) {

            if (consultaServicio.getIdConsultaServicio() > mayor) {
                mayor = consultaServicio.getIdConsultaServicio();
            }
        }

        return mayor + 1;
    }

    public void agrega(ConsultaServicio consultaServicio)
            throws PersistenciaException {

        try {

            consultaServicio.setIdConsultaServicio(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeConsultaServicio(
                    consultaServicio);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar servicio de consulta",
                    e);
        }
    }

    public void elimina(ConsultaServicio consultaServicio)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                ConsultaServicio leido =
                        leeConsultaServicio();

                if (leido.getIdConsultaServicio()
                        == consultaServicio.getIdConsultaServicio()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Servicio de consulta inexistente");

        } catch (FileNotFoundException e) {

            throw new PersistenciaException(
                    "Servicio de consulta inexistente",
                    e);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar servicio de consulta",
                    e);
        }
    }

    public ArrayList<ConsultaServicio> lista()
            throws PersistenciaException {

        ArrayList<ConsultaServicio> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {
                lista.add(
                        leeConsultaServicio());
            }

        } catch (FileNotFoundException e) {

            return lista;

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar servicios de consulta",
                    e);
        }
    }

    public ArrayList<ConsultaServicio> listaPorConsulta(
            int idConsulta)
            throws PersistenciaException {

        ArrayList<ConsultaServicio> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                ConsultaServicio consultaServicio =
                        leeConsultaServicio();

                if (consultaServicio.getIdConsulta()
                        == idConsulta) {

                    lista.add(
                            consultaServicio);
                }
            }

        } catch (FileNotFoundException e) {

            return lista;

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar servicios de la consulta",
                    e);
        }
    }
}
