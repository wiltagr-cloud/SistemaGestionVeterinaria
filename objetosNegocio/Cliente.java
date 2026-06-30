package objetosNegocio;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    public Cliente(int idCliente,
                   String nombre,
                   String cedula,
                   String telefono,
                   String direccion){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Cliente(){}

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Cliente other =
                (Cliente) obj;

        return this.idCliente
                == other.idCliente;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idCliente
        );
    }

    @Override
    public String toString() {
        return nombre + " - " + cedula;
    }
}
