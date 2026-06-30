package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.Consulta;
import objetosNegocio.Factura;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Facturas extends AccesoAleatorio{
    public Facturas(String nomArchivo){
        super(nomArchivo, 120);

    }
    private Factura leeFactura()
            throws IOException {

        Factura factura =
                new Factura();

        factura.setIdFactura(
                archivo.readInt());

        factura.setFecha(
                leeFecha());

        factura.setIdCliente(
                archivo.readInt());

        factura.setNombrePagador(
                leeString(30));

        factura.setCedulaPagador(
                leeString(10));

        factura.setIdConsulta(
                archivo.readInt());

        factura.setIvaTotal(
                archivo.readDouble());

        factura.setTotal(
                archivo.readDouble());

        return factura;
    }
    private void escribeFactura(
            Factura factura)
            throws IOException {

        archivo.writeInt(
                factura.getIdFactura());

        escribeFecha(
                factura.getFecha());

        archivo.writeInt(
                factura.getIdCliente());

        escribeString(
                factura.getNombrePagador(),
                30);

        escribeString(
                factura.getCedulaPagador(),
                10);

        archivo.writeInt(
                factura.getIdConsulta());

        archivo.writeDouble(
                factura.getIvaTotal());

        archivo.writeDouble(
                factura.getTotal());
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Factura factura :
                    lista()) {

                if (factura.getIdFactura()
                        > mayor) {

                    mayor =
                            factura.getIdFactura();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Factura obten(
            Factura buscada)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Factura factura =
                        leeFactura();

                if (factura.equals(
                        buscada)) {

                    return factura;
                }
            }

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al obtener factura",
                    e);
        }
    }
    public void agrega(
            Factura factura)
            throws PersistenciaException {

        try {

            factura.setIdFactura(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeFactura(
                    factura);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar factura",
                    e);
        }
    }
    public void actualiza(
            Factura factura)
        throws PersistenciaException{

        try{
            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");
            while (true){

                long posicion =
                        archivo.getFilePointer();

                Factura leida = leeFactura();

                if (leida.getIdFactura()
                        == factura.getIdFactura()) {

                    archivo.seek(
                            posicion);

                    escribeFactura(
                            factura);

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
            Factura factura)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Factura leida =
                        leeFactura();

                if (leida.getIdFactura()
                        == factura.getIdFactura()) {

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
    public ArrayList<Factura> lista()
            throws PersistenciaException {

        ArrayList<Factura> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeFactura());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar facturas",
                    e);
        }
    }
    public Factura obtenPorConsulta(
            int idConsulta)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Factura factura =
                        leeFactura();

                if (factura.getIdConsulta()
                        == idConsulta) {

                    return factura;
                }
            }

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al buscar factura",
                    e);
        }
    }
    public ArrayList<Factura>
    listaPorCliente(
            int idCliente)
            throws PersistenciaException {

        ArrayList<Factura> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Factura factura =
                        leeFactura();

                if (factura.getIdCliente()
                        == idCliente) {

                    lista.add(
                            factura);
                }
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar facturas",
                    e);
        }
    }
}
