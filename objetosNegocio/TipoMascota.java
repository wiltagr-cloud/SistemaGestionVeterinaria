package objetosNegocio;

public class TipoMascota {
    private int idTipoMascota;
    private String nombreTipoMascota;
    public TipoMascota(){}
    public TipoMascota(int idTipoMascota,
                   String nombreTipoMascota){
    this.idTipoMascota = idTipoMascota;
    this.nombreTipoMascota = nombreTipoMascota;
    }

    public int getIdTipoMascota() {
        return idTipoMascota;
    }

    public String getNombreTipoMascota() {
        return nombreTipoMascota;
    }

    public void setIdTipoMascota(int idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public void setNombreTipoMascota(String nombreTipoMascota) {
        this.nombreTipoMascota = nombreTipoMascota;
    }
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        TipoMascota other =
                (TipoMascota) obj;

        return this.idTipoMascota
                == other.idTipoMascota;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idTipoMascota
        );
    }
    @Override
    public String toString() {
        return nombreTipoMascota;
    }
}
