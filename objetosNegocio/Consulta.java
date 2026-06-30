package objetosNegocio;

import java.time.LocalDate;

public class Consulta {
    private int idConsulta;
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private int idMascota;
    private int idVeterinario;
    private int idTratamiento;
    private int idCita;
    public Consulta(int idConsulta,
                    LocalDate fecha,
                    String motivo,
                    String diagnostico,
                    int idMascota,
                    int idVeterinario,
                    int idTratamiento,
                    int idCita){
        this.idConsulta =  idConsulta;
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.idTratamiento = idTratamiento;
        this.idCita = idCita;
    }
    public Consulta(){}

    public int getIdConsulta() {
        return idConsulta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Consulta other =
                (Consulta) obj;

        return this.idConsulta
                == other.idConsulta;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(
                idConsulta
        );
    }

    @Override
    public String toString() {
        return "Consulta #" + idConsulta;
    }
}
