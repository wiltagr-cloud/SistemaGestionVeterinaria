package objetosNegocio;

import Enums.SexoMascota;

import java.time.LocalDate;
import java.time.Period;

public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private LocalDate fechaNacimiento;
    private double peso;
    private int idTipoMascota;
    private int idCliente;
    private String raza;
    private SexoMascota sexoMascota;
    public Mascota(){}
    public Mascota(int idMascota,
                   String nombreMascota,
                   LocalDate fechaNacimiento,
                   double peso,
                   int idTipoMascota,
                   int idCliente,
                   String raza,
                   SexoMascota sexoMascota){
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.idTipoMascota = idTipoMascota;
        this.idCliente = idCliente;
        this.raza = raza;
        this.sexoMascota = sexoMascota;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public int getIdTipoMascota() {
        return idTipoMascota;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getRaza() {
        return raza;
    }

    public SexoMascota getSexoMascota() {
        return sexoMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setIdTipoMascota(int idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setSexo(SexoMascota sexoMascota) {
        this.sexoMascota = sexoMascota;
    }
    public int calcularEdad() {

        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Mascota other =
                (Mascota) obj;

        return this.idMascota
                == other.idMascota;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idMascota
        );
    }

    @Override
    public String toString() {
        return nombreMascota + " - " + raza;
    }
}
