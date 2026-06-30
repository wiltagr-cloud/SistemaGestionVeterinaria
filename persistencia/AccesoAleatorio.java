package persistencia;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalTime;

public class AccesoAleatorio {

    protected RandomAccessFile archivo;
    protected String nomArchivo;
    protected int tamRegistro;
    protected byte[] blancos;

    public AccesoAleatorio(String nomArchivo,
                           int tamRegistro) {

        this.nomArchivo = nomArchivo;
        this.tamRegistro = tamRegistro;

        blancos = new byte[tamRegistro];

        for (int i = 0; i < tamRegistro; i++) {
            blancos[i] = 0;
        }
    }

    public String leeString(int tam) throws IOException {

        char[] cadena = new char[tam];

        for (int i = 0; i < tam; i++) {

            cadena[i] = archivo.readChar();
        }

        return new String(cadena).trim();
    }

    public void escribeString(String sCadena, int tam) throws IOException {

        StringBuilder cadena;

        if (sCadena != null) {
            cadena = new StringBuilder(sCadena);
        } else {
            cadena = new StringBuilder();
        }

        cadena.setLength(tam);

        archivo.writeChars(cadena.toString());
    }

    public LocalDate leeFecha()
            throws IOException {

        int dia = archivo.readInt();
        int mes = archivo.readInt();
        int anio = archivo.readInt();

        if (dia == 0 && mes == 0 && anio == 0) {
            return null;
        }

        return LocalDate.of(
                anio,
                mes,
                dia);
    }

    public void escribeFecha(
            LocalDate fecha)
            throws IOException {

        if (fecha != null) {

            archivo.writeInt(
                    fecha.getDayOfMonth());

            archivo.writeInt(
                    fecha.getMonthValue());

            archivo.writeInt(
                    fecha.getYear());

        } else {

            archivo.writeInt(0);
            archivo.writeInt(0);
            archivo.writeInt(0);
        }
    }

    public LocalTime leeHora()
            throws IOException {

        int hora = archivo.readInt();
        int minuto = archivo.readInt();

        if (hora == 0 && minuto == 0) {
            return null;
        }

        return LocalTime.of(
                hora,
                minuto);
    }

    public void escribeHora(
            LocalTime hora)
            throws IOException {

        if (hora != null) {

            archivo.writeInt(
                    hora.getHour());

            archivo.writeInt(
                    hora.getMinute());

        } else {

            archivo.writeInt(0);
            archivo.writeInt(0);
        }
    }

    public void borraRegistro()
            throws IOException {

        archivo.write(blancos);
    }

    public boolean estaRegistroBorrado(
            byte[] registro) {

        for (int i = 0; i < tamRegistro; i++) {

            if (registro[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public void empaca()
            throws IOException {

        byte[] registro =
                new byte[tamRegistro];

        int registrosBorrados = 0;

        int numRegistros =
                (int) archivo.length()
                        / tamRegistro;

        for (int i = 0;
             i < numRegistros;
             i++) {

            archivo.seek(
                    i * tamRegistro);

            archivo.read(registro);

            if (estaRegistroBorrado(
                    registro)) {

                for (int j = i;
                     j < numRegistros - 1;
                     j++) {

                    archivo.seek(
                            (j + 1)
                                    * tamRegistro);

                    archivo.read(registro);

                    archivo.seek(
                            j * tamRegistro);

                    archivo.write(registro);
                }

                numRegistros--;
                registrosBorrados++;
            }
        }

        archivo.setLength(
                archivo.length()
                        - registrosBorrados
                        * tamRegistro);
    }
}