package persistencia;

import Excepciones.PersistenciaException;
import objetosNegocio.Rol;

import java.io.*;
import java.util.ArrayList;

public class Roles
        extends AccesoAleatorio {

    public Roles(String nomArchivo) {

        super(
                nomArchivo,
                64);
    }
    private Rol leeRol()
            throws IOException {

        Rol rol =
                new Rol();

        rol.setIdRol(
                archivo.readInt());

        rol.setNombreRol(
                leeString(30));

        return rol;
    }
    private void escribeRol(
            Rol rol)
            throws IOException {

        archivo.writeInt(
                rol.getIdRol());

        escribeString(
                rol.getNombreRol(),
                30);
    }
    public int siguienteId()
            throws PersistenciaException {

        try {

            int mayor = 0;

            for (Rol r : lista()) {

                if (r.getIdRol()
                        > mayor) {

                    mayor =
                            r.getIdRol();
                }
            }

            return mayor + 1;

        } catch (Exception e) {

            return 1;
        }
    }
    public Rol obten(
            Rol buscado)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                Rol rol =
                        leeRol();

                if (rol.equals(
                        buscado)) {

                    return rol;
                }
            }

        } catch (FileNotFoundException e) {

            return null;

        } catch (EOFException e) {

            return null;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al leer roles",
                    e);
        }
    }
    public void agrega(
            Rol rol)
            throws PersistenciaException {

        try {

            rol.setIdRol(
                    siguienteId());
            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            archivo.seek(
                    archivo.length());

            escribeRol(
                    rol);

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al agregar rol",
                    e);
        }
    }
    public ArrayList<Rol> lista()
            throws PersistenciaException {

        ArrayList<Rol> lista =
                new ArrayList<>();

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "r");

            while (true) {

                lista.add(
                        leeRol());
            }

        } catch (EOFException e) {

            return lista;

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al listar roles",
                    e);
        }
    }
    public void actualiza(
            Rol rol)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Rol leido =
                        leeRol();

                if (leido.getIdRol()
                        == rol.getIdRol()) {

                    archivo.seek(
                            posicion);

                    escribeRol(
                            rol);

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Rol inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al actualizar rol",
                    e);
        }
    }
    public void elimina(
            Rol rol)
            throws PersistenciaException {

        try {

            archivo =
                    new RandomAccessFile(
                            nomArchivo,
                            "rw");

            while (true) {

                long posicion =
                        archivo.getFilePointer();

                Rol leido =
                        leeRol();

                if (leido.getIdRol()
                        == rol.getIdRol()) {

                    archivo.seek(
                            posicion);

                    borraRegistro();

                    empaca();

                    return;
                }
            }

        } catch (EOFException e) {

            throw new PersistenciaException(
                    "Rol inexistente");

        } catch (IOException e) {

            throw new PersistenciaException(
                    "Error al eliminar rol",
                    e);
        }
    }
}