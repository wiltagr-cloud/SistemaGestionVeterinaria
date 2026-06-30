package objetosNegocio;

public class MedicamentoComprimidos extends Medicamento {

    public MedicamentoComprimidos() {
    }

    public MedicamentoComprimidos(
            int idMedicamento,
            String nombreMedicamento,
            String categoria,
            String viaAdministracion,
            String tipoMedicamento,
            double dosisPorKg,
            double cantidadFija,
            int stock,
            double precio) {

        super(
                idMedicamento,
                nombreMedicamento,
                categoria,
                viaAdministracion,
                tipoMedicamento,
                dosisPorKg,
                cantidadFija,
                stock,
                precio
        );
    }

    @Override
    public double calcularDosis(double peso) {

        return Math.ceil(
                peso / getDosisPorKg()
        );
    }
}