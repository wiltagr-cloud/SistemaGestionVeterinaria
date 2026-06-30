package objetosNegocio;

public class MedicamentoPorPeso extends Medicamento {

    public MedicamentoPorPeso() {
    }

    public MedicamentoPorPeso(
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

        return peso * getDosisPorKg();
    }
}