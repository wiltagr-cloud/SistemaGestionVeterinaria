package objetosNegocio;

import Excepciones.CredencialesInvalidasException;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private int idRol;
    public Usuario(){}

    public Usuario (int idUsuario,
                    String nombreUsuario,
                    String contrasenia,
                    String nombre,
                    String apellido,
                    int idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idRol = idRol;

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Usuario other =
                (Usuario) obj;

        return this.idUsuario
                == other.idUsuario;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idUsuario
        );
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
