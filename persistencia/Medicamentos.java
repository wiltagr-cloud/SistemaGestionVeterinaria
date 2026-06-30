package persistencia;
import Excepciones.PersistenciaException;
import objetosNegocio.*;

import java.io.*;
import java.util.ArrayList;
public class Medicamentos extends AccesoAleatorio {
    public Medicamentos(String nomArchivo) {

        super(nomArchivo, 212);
    }

    private String obtenerTipo(Medicamento medicamento) {

        String tipo = medicamento.getTipoMedicamento();

        if (tipo.equals("Por Peso")) {
            return "POR_PESO";
        }

        if (tipo.equals("Dosis Fija")) {
            return "DOSIS_FIJA";
        }

        return "COMPRIMIDOS";
    }

    private Medicamento
    leeMedicamento()
            throws IOException {

        int idMedicamento =
                archivo.readInt();

        String tipo =
                leeString(20);

        String nombre =
                leeString(30);

        String categoria =
                leeString(20);

        String viaAdministracion =
                leeString(20);

        double dosisPorKg =
                archivo.readDouble();

        double cantidadFija =
                archivo.readDouble();

        int stock =
                archivo.readInt();

        double precio =
                archivo.readDouble();

        Medicamento medicamento;

        if (tipo.equals(
                "DOSIS_PESO")) {

            medicamento =
                    new MedicamentoPorPeso();

        } else if (tipo.equals(
                "DOSIS_FIJA")) {

            medicamento =
                    new MedicamentoDosisFija();

        } else {

            medicamento =
                    new MedicamentoComprimidos();
        }

        medicamento.setIdMedicamento(
                idMedicamento);

        medicamento.setNombreMedicamento(
                nombre);

        medicamento.setCategoria(
                categoria);

        medicamento.setViaAdministracion(
                viaAdministracion);

        medicamento.setDosisPorKg(
                dosisPorKg);

        medicamento.setCantidadFija(
                cantidadFija);

        medicamento.setStock(
                stock);

        medicamento.setPrecio(
                precio);

        medicamento.setTipoMedicamento(tipo);

        return medicamento;
    }

    private void escribeMedicamento(
            Medicamento medicamento)
            throws IOException {

        archivo.writeInt(
                medicamento.getIdMedicamento());

        escribeString(
                obtenerTipo(
                        medicamento),
                20);

        escribeString(
                medicamento.getNombreMedicamento(),
                30);

        escribeString(
                medicamento.getCategoria(),
                20);

        escribeString(
                medicamento.getViaAdministracion(),
                20);

        archivo.writeDouble(
                medicamento.getDosisPorKg());

        archivo.writeDouble(
                medicamento.getCantidadFija());

        archivo.writeInt(
                medicamento.getStock());

        archivo.writeDouble(
                medicamento.getPrecio());
    }

    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Medicamento m :
                    lista()) {

                if (m.getIdMedicamento()
                        > mayor) {

                    mayor =
                            m.getIdMedicamento();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Medicamento obten(
            Medicamento buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Medicamento medicamento =
                        leeMedicamento();

                if (medicamento.equals(
                        buscado)) {

                    return medicamento;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer medicamentos",
                    e);
        }
    }
    public void agrega(
            Medicamento medicamento)
            throws PersistenciaException {

        try {

            medicamento.setIdMedicamento(
                    siguienteId());

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeMedicamento(
                    medicamento);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar medicamento",
                    e);
        }
    }
    public ArrayList<Medicamento> lista()
            throws PersistenciaException {

        ArrayList<Medicamento> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeMedicamento());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar medicamentos",
                    e);
        }
    }
    public void actualiza(
            Medicamento medicamento)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Medicamento leida =
                        leeMedicamento();

                if (leida.getIdMedicamento()
                        == medicamento.getIdMedicamento()) {

                    archivo.seek(
                            posicion);

                    escribeMedicamento(
                            medicamento);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Medicamento inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar Medicamento",
                    e);
        }
    }
    public void elimina(
            Medicamento medicamento)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Medicamento leido =
                        leeMedicamento();

                if (leido.getIdMedicamento()
                        == medicamento.getIdMedicamento()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Medicamento inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar Medicamento",
                    e);
        }
    }
    public ArrayList<Medicamento>
    listaPorCategoria(
            String categoria)
            throws PersistenciaException {

        ArrayList<Medicamento> medicamentos =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Medicamento medicamento =
                        leeMedicamento();

                if (medicamento.getCategoria()
                        .equalsIgnoreCase(
                                categoria)) {

                    medicamentos.add(
                            medicamento);
                }
            }

        } catch (EOFException e) {

            return medicamentos;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar medicamentos por categoria",
                    e);
        }
    }
    public Medicamento obtenPorId(int id) throws PersistenciaException {

        try {

            archivo = new RandomAccessFile(nomArchivo, "r");

            while (true) {

                Medicamento me = leeMedicamento();

                if (me.getIdMedicamento() == id) {
                    return me;
                }
            }

        } catch (EOFException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenciaException("Error al buscar usuario", e);
        }
    }
}
