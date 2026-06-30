package Interfaces;
import objetosNegocio.*;
import Excepciones.FachadaException;

import java.util.ArrayList;

public interface IFachada {
    //Clientes
    Cliente obten(
            Cliente cliente)
            throws FachadaException;

    void agrega(
            Cliente cliente)
            throws FachadaException;

    void actualiza(
            Cliente cliente)
            throws FachadaException;

    void elimina(
            Cliente cliente)
            throws FachadaException;
    ArrayList<Cliente>
    consultaClientes()
            throws FachadaException;
    Cliente obtenerClientePorId(int id) throws Exception;

    //Usuarios
    Usuario obten(
            Usuario usuario)
            throws FachadaException;

    void agrega(
            Usuario usuario)
            throws FachadaException;

    void actualiza(
            Usuario usuario)
            throws FachadaException;

    void elimina(
            Usuario usuario)
            throws FachadaException;
    ArrayList<Usuario>
    consultaUsuarios()
            throws FachadaException;
    Usuario obtenerUsuarioPorId(int id) throws Exception;
    ArrayList<Usuario> consultaUsuariosPorRol(int idRol) throws FachadaException;
    //Mascotas
    Mascota obten(
            Mascota mascota)
            throws FachadaException;

    void agrega(
            Mascota mascota)
            throws FachadaException;

    void actualiza(
            Mascota mascota)
            throws FachadaException;

    void elimina(
            Mascota mascota)
            throws FachadaException;
    ArrayList<Mascota>
    consultaMascotas()
            throws FachadaException;

    ArrayList<Mascota>
    consultaMascotasCliente(
            int idCliente)
            throws FachadaException;
    Mascota obtenerMascotaPorId(int id) throws Exception;
    //Veterinarios
    Veterinario obten(
            Veterinario veterinario)
            throws FachadaException;

    void agrega(
            Veterinario veterinario)
            throws FachadaException;

    void actualiza(
            Veterinario veterinario)
            throws FachadaException;

    void elimina(
            Veterinario veterinario)
            throws FachadaException;
    ArrayList<Veterinario> consultaVeterinarios()
            throws FachadaException;
    ArrayList<Veterinario>
    consultaVeterinariosEspecialidad(
            int idEspecialidad)
            throws FachadaException;
    Veterinario obtenerVeterinarioPorId(int id) throws Exception;
    //Medicamentos
    Medicamento obten(
            Medicamento medicamento)
            throws FachadaException;

    void agrega(
            Medicamento medicamento)
            throws FachadaException;

    void actualiza(
            Medicamento medicamento)
            throws FachadaException;

    void elimina(
            Medicamento medicamento)
            throws FachadaException;
    ArrayList<Medicamento>
    consultaMedicamentos()
            throws FachadaException;

    ArrayList<Medicamento>
    consultaMedicamentosCategoria(
            String categoria)
            throws FachadaException;
    Medicamento obtenerMedicamentoPorId(int id) throws Exception;
    //Citas
    Cita obten(
            Cita cita)
            throws FachadaException;

    void agrega(
            Cita cita)
            throws FachadaException;

    void actualiza(
            Cita cita)
            throws FachadaException;

    void elimina(
            Cita cita)
            throws FachadaException;
    ArrayList<Cita>
    consultaCitas()
            throws FachadaException;

    ArrayList<Cita>
    consultaCitasMascota(
            int idMascota)
            throws FachadaException;

    ArrayList<Cita>
    consultaCitasVeterinario(
            int idVeterinario)
            throws FachadaException;
    Cita obtenerCitaPorId(int id) throws Exception;
    //Consultas
    Consulta obten(
            Consulta consulta)
            throws FachadaException;

    void agrega(
            Consulta consulta)
            throws FachadaException;

    void actualiza(
            Consulta consulta)
            throws FachadaException;

    void elimina(
            Consulta consulta)
            throws FachadaException;
    ArrayList<Consulta>
    consultaConsultas()
            throws FachadaException;

    ArrayList<Consulta>
    consultaConsultasMascota(
            int idMascota)
            throws FachadaException;

    ArrayList<Consulta>
    consultaConsultasVeterinario(
            int idVeterinario)
            throws FachadaException;
    Consulta obtenerConsultaPorId(int id) throws Exception;

    //Tratamientos
    Tratamiento obten(
            Tratamiento tratamiento)
            throws FachadaException;

    void agrega(
            Tratamiento tratamiento)
            throws FachadaException;

    void actualiza(
            Tratamiento tratamiento)
            throws FachadaException;

    void elimina(
            Tratamiento tratamiento)
            throws FachadaException;
    ArrayList<Tratamiento>
    consultaTratamientos()
            throws FachadaException;
    Tratamiento obtenerTratamientoPorId(int id) throws Exception;
    ArrayList<DetalleTratamiento> listaPorTratamiento(int idTratamiento) throws Exception;
    //Facturas
    Factura obten(
            Factura factura)
            throws FachadaException;

    void agrega(
            Factura factura)
            throws FachadaException;

    void actualiza(
            Factura factura)
            throws FachadaException;

    void elimina(
            Factura factura)
            throws FachadaException;
    ArrayList<Factura>
    consultaFacturas()
            throws FachadaException;

    ArrayList<Factura>
    consultaFacturasCliente(
            int idCliente)
            throws FachadaException;

    //Roles
    Rol obten(Rol rol)
            throws FachadaException;

    void agrega(Rol rol)
            throws FachadaException;

    void actualiza(Rol rol)
            throws FachadaException;

    void elimina(Rol rol)
            throws FachadaException;
    ArrayList<Rol> consultaRoles()
            throws FachadaException;
    //Especialidades
    Especialidad obten(Especialidad especialidad)
            throws FachadaException;

    void agrega(Especialidad especialidad)
            throws FachadaException;

    void actualiza(Especialidad especialidad)
            throws FachadaException;

    void elimina(Especialidad especialidad)
            throws FachadaException;
    ArrayList<Especialidad> consultaEspecialidades()
            throws FachadaException;
    Especialidad obtenerEspecialidadPorId(int id) throws Exception;
    //Servicios
    Servicio obten(Servicio servicio)
            throws FachadaException;

    void agrega(Servicio servicio)
            throws FachadaException;

    void actualiza(Servicio servicio)
            throws FachadaException;

    void elimina(Servicio servicio)
            throws FachadaException;
    ArrayList<Servicio> consultaServicios()
            throws FachadaException;
    //TipoMascotas
    TipoMascota obten(TipoMascota tipoMascota)
            throws FachadaException;

    void agrega(TipoMascota tipoMascota)
            throws FachadaException;

    void actualiza(TipoMascota tipoMascota)
            throws FachadaException;

    void elimina(TipoMascota tipoMascota)
            throws FachadaException;
    ArrayList<TipoMascota> consultaTipoMascotas()
            throws FachadaException;
    //Horarios
    Horario obten(Horario horario)
            throws FachadaException;

    void agrega(Horario horario)
            throws FachadaException;

    void actualiza(Horario horario)
            throws FachadaException;

    void elimina(Horario horario)
            throws FachadaException;
    ArrayList<Horario> consultaHorarios()
            throws FachadaException;
    Horario obtenerHorarioPorId(int id) throws Exception;

    //Detalle Horario
    void agrega(
            DetalleHorario detalleHorario)
            throws FachadaException;

    void elimina(
            DetalleHorario detalleHorario)
            throws FachadaException;

    ArrayList<DetalleHorario>
    consultaDetalleHorariosVeterinario(
            int idVeterinario)
            throws FachadaException;
    //Detalle Tratamiento

    void agrega(
            DetalleTratamiento detalleTratamiento)
            throws FachadaException;

    void elimina(
            DetalleTratamiento detalleTratamiento)
            throws FachadaException;

    ArrayList<DetalleTratamiento>
    consultaDetallesTratamiento(
            int idTratamiento)
            throws FachadaException;

    void agrega(
            DetalleFactura detalleFactura)
            throws FachadaException;

    void elimina(
            DetalleFactura detalleFactura)
            throws FachadaException;

    ArrayList<DetalleFactura>
    consultaDetallesFactura(
            int idFactura)
            throws FachadaException;

    void agrega(ConsultaServicio consultaServicio)
            throws FachadaException;

    void elimina(ConsultaServicio consultaServicio)
            throws FachadaException;
    ArrayList<ConsultaServicio> consultaServiciosConsulta(
            int idConsulta)
            throws FachadaException;
}
