package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.DetalleFactura;
import objetosNegocio.DetalleHorario;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class DetalleFacturas extends AccesoAleatorio{
    public DetalleFacturas (String nomArchivo){
        super(nomArchivo,30);
    }
    private DetalleFactura
    leeDetalleFactura()
            throws IOException {

        DetalleFactura detalle =
                new DetalleFactura();

        detalle.setIdDetalleFactura(
                archivo.readInt());

        detalle.setIdFactura(
                archivo.readInt());

        detalle.setIdServicio(
                archivo.readInt());

        detalle.setIdMedicamento(
                archivo.readInt());

        detalle.setCantidad(
                archivo.readInt());

        detalle.setSubtotal(
                archivo.readDouble());

        detalle.setTotalDetalle(
                archivo.readDouble());

        return detalle;
    }
    private void escribeDetalleFactura(
            DetalleFactura detalle)
            throws IOException {

        archivo.writeInt(
                detalle.getIdDetalleFactura());

        archivo.writeInt(
                detalle.getIdFactura());

        archivo.writeInt(
                detalle.getIdServicio());

        archivo.writeInt(
                detalle.getIdMedicamento());

        archivo.writeInt(
                detalle.getCantidad());

        archivo.writeDouble(
                detalle.getSubtotal());

        archivo.writeDouble(
                detalle.getTotalDetalle());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (DetalleFactura detalle :
                    lista()) {

                if (detalle.getIdDetalleFactura()
                        > mayor) {

                    mayor =
                            detalle.getIdDetalleFactura();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public DetalleFactura obten(
            DetalleFactura buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                DetalleFactura detalle =
                        leeDetalleFactura();

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
                    "Error al obtener detalle factura",
                    e);
        }
    }
    public void agrega(
            DetalleFactura detalle)
            throws PersistenciaException {

        try {

            detalle.setIdDetalleFactura(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeDetalleFactura(
                    detalle);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar detalle factura",
                    e);
        }
    }
    public ArrayList<DetalleFactura> lista()
            throws PersistenciaException {

        ArrayList<DetalleFactura> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeDetalleFactura());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar detalles factura",
                    e);
        }
    }
    public void actualiza(
            DetalleFactura detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleFactura leido =
                        leeDetalleFactura();

                if (leido.getIdDetalleFactura()
                        == detalle.getIdDetalleFactura()) {

                    archivo.seek(
                            posicion);

                    escribeDetalleFactura(
                            detalle);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle factura inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar detalle factura",
                    e);
        }
    }
    public void elimina(
            DetalleFactura detalle)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                DetalleFactura leido =
                        leeDetalleFactura();

                if (leido.getIdDetalleFactura()
                        == detalle.getIdDetalleFactura()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Detalle factura inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar detalle factura",
                    e);
        }
    }
    public ArrayList<DetalleFactura>
    listaPorFactura(
            int idFactura)
            throws PersistenciaException {

        ArrayList<DetalleFactura> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                DetalleFactura detalle =
                        leeDetalleFactura();

                if (detalle.getIdFactura()
                        == idFactura) {

                    lista.add(
                            detalle);
                }
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar detalles de factura",
                    e);
        }
    }
}
