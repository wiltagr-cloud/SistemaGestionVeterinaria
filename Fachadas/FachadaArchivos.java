package Fachadas;
import Excepciones.FachadaException;
import Excepciones.PersistenciaException;
import Interfaces.IFachada;
import persistencia.*;
import objetosNegocio.*;

import java.util.ArrayList;

public class FachadaArchivos
        implements IFachada {

    private Clientes catalogoClientes;
    private Usuarios catalogoUsuarios;
    private Mascotas catalogoMascotas;
    private Veterinarios catalogoVeterinarios;
    private Medicamentos catalogoMedicamentos;
    private Citas catalogoCitas;
    private Consultas catalogoConsultas;
    private Tratamientos catalogoTratamientos;
    private Facturas catalogoFacturas;
    private Roles catalogoRoles;
    private Especialidades catalogoEspecialidades;
    private Servicios catalogoServicios;
    private ConsultaServicios catalogoConsultaServicios;
    private TipoMascotas catalogoTipoMascotas;
    private Horarios catalogoHorarios;
    private DetalleHorarios catalogoDetallesHorarios;
    private DetalleTratamientos catalogoDetallesTratamientos;
    private DetalleFacturas catalogoDetallesFacturas;

    public FachadaArchivos() {

        catalogoClientes =
                new Clientes(
                        "Clientes.dat");

        catalogoUsuarios =
                new Usuarios(
                        "Usuarios.dat");

        catalogoRoles =
                new Roles(
                        "Roles.dat");

        catalogoMascotas =
                new Mascotas(
                        "Mascotas.dat");

        catalogoTipoMascotas =
                new TipoMascotas(
                        "TiposMascota.dat");

        catalogoServicios =
                new Servicios(
                        "Servicios.dat");
        catalogoConsultaServicios =
                new ConsultaServicios(
                        "ConsultaServicios.dat");

        catalogoEspecialidades =
                new Especialidades(
                        "Especialidades.dat");

        catalogoHorarios =
                new Horarios(
                        "Horarios.dat");

        catalogoVeterinarios =
                new Veterinarios(
                        "Veterinarios.dat");

        catalogoCitas =
                new Citas(
                        "Citas.dat");

        catalogoMedicamentos =
                new Medicamentos(
                        "Medicamentos.dat");

        catalogoTratamientos =
                new Tratamientos(
                        "Tratamientos.dat");

        catalogoConsultas =
                new Consultas(
                        "Consultas.dat");

        catalogoFacturas =
                new Facturas(
                        "Facturas.dat");

        catalogoDetallesHorarios =
                new DetalleHorarios(
                        "DetalleHorarios.dat");

        catalogoDetallesTratamientos =
                new DetalleTratamientos(
                        "DetalleTratamientos.dat");

        catalogoDetallesFacturas =
                new DetalleFacturas(
                        "DetalleFacturas.dat");
    }

    // CLIENTES

    @Override
    public Cliente obten(
            Cliente cliente)
            throws FachadaException {

        try {

            return catalogoClientes.obten(
                    cliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener cliente",
                    e);
        }
    }

    @Override
    public void agrega(
            Cliente cliente)
            throws FachadaException {

        try {

            catalogoClientes.agrega(
                    cliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar cliente",
                    e);
        }
    }

    @Override
    public void actualiza(
            Cliente cliente)
            throws FachadaException {

        try {

            catalogoClientes.actualiza(
                    cliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar cliente",
                    e);
        }
    }

    @Override
    public void elimina(
            Cliente cliente)
            throws FachadaException {

        try {

            catalogoClientes.elimina(
                    cliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar cliente",
                    e);
        }
    }

    @Override
    public ArrayList<Cliente>
    consultaClientes()
            throws FachadaException {

        try {

            return catalogoClientes.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar clientes",
                    e);
        }
    }
    @Override
    public Cliente obtenerClientePorId(int id) throws FachadaException {

        try {
            return catalogoClientes.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener mascota", e);
        }
    }

    //USUARIOS
    @Override
    public Usuario obten(
            Usuario usuario)
            throws FachadaException {

        try {

            return catalogoUsuarios.obten(usuario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar usuarios",
                    e);
        }
    }

    @Override
    public void agrega(
            Usuario usuario)
            throws FachadaException {

        try {

            catalogoUsuarios.agrega(usuario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar usuario",
                    e);
        }
    }

    @Override
    public void actualiza(
            Usuario usuario)
            throws FachadaException {

        try {

            catalogoUsuarios.actualiza(usuario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar usuario",
                    e);
        }
    }

    @Override
    public void elimina(
            Usuario usuario)
            throws FachadaException {

        try {

            catalogoUsuarios.elimina(usuario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar usuario",
                    e);
        }
    }

    @Override
    public ArrayList<Usuario> consultaUsuarios()
            throws FachadaException {

        try {

            return catalogoUsuarios.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar usuarios",
                    e);
        }
    }

    public Usuario obtenPorNombreUsuario(
            String nombreUsuario)
            throws FachadaException {

        try {

            return catalogoUsuarios.obtenPorNombreUsuario(nombreUsuario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar usuario por nombre",
                    e);
        }
    }
    @Override
    public Usuario obtenerUsuarioPorId(int id) throws FachadaException {

        try {
            return catalogoUsuarios.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener Usuario", e);
        }
    }

    @Override
    public ArrayList<Usuario> consultaUsuariosPorRol(int idRol)
            throws FachadaException {

        try {

            ArrayList<Usuario> filtrados = new ArrayList<>();

            for (Usuario u : catalogoUsuarios.lista()) {

                if (u.getIdRol() == idRol) {
                    filtrados.add(u);
                }
            }

            return filtrados;

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar usuarios por rol",
                    e
            );
        }
    }

    //ROLES
    @Override
    public Rol obten(
            Rol rol)
            throws FachadaException {

        try {

            return catalogoRoles.obten(rol);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar roles",
                    e);
        }
    }

    @Override
    public void agrega(
            Rol rol)
            throws FachadaException {

        try {

            catalogoRoles.agrega(rol);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar rol",
                    e);
        }
    }

    @Override
    public void actualiza(
            Rol rol)
            throws FachadaException {

        try {

            catalogoRoles.actualiza(rol);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar rol",
                    e);
        }
    }

    @Override
    public void elimina(
            Rol rol)
            throws FachadaException {

        try {

            catalogoRoles.elimina(rol);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar rol",
                    e);
        }
    }

    @Override
    public ArrayList<Rol>
    consultaRoles()
            throws FachadaException {

        try {

            return catalogoRoles.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar roles",
                    e);
        }
    }

    // MASCOTAS
    @Override
    public Mascota obten(
            Mascota mascota)
            throws FachadaException {
        try {
            return catalogoMascotas.obten(
                    mascota);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al obtener mascota",
                    e);
        }
    }

    @Override
    public void agrega(
            Mascota mascota)
            throws FachadaException {
        try {
            catalogoMascotas.agrega(
                    mascota);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al agregar mascota", e
            );
        }
    }

    @Override
    public void actualiza(
            Mascota mascota)
            throws FachadaException {
        try {
            catalogoMascotas.actualiza(mascota);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al actualizar mascota", e
            );
        }
    }

    @Override
    public void elimina(
            Mascota mascota)
            throws FachadaException {

        try {

            catalogoMascotas.elimina(
                    mascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar mascota",
                    e);
        }
    }

    @Override
    public ArrayList<Mascota>
    consultaMascotas()
            throws FachadaException {

        try {

            return catalogoMascotas.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar mascota",
                    e);
        }
    }

    @Override
    public ArrayList<Mascota>
    consultaMascotasCliente(
            int idCliente)
            throws FachadaException {

        try {

            return catalogoMascotas
                    .listaPorCliente(
                            idCliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar mascotas",
                    e);
        }
    }
    @Override
    public Mascota obtenerMascotaPorId(int id) throws FachadaException {

        try {
            return catalogoMascotas.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener mascota", e);
        }
    }

    // TIPOS MASCOTAS
    @Override
    public TipoMascota obten(
            TipoMascota tipoMascota)
            throws FachadaException {

        try {

            return catalogoTipoMascotas.obten(tipoMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar tipos de mascota",
                    e);
        }
    }

    @Override
    public void agrega(
            TipoMascota tipoMascota)
            throws FachadaException {

        try {

            catalogoTipoMascotas.agrega(tipoMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar tipo de mascota",
                    e);
        }
    }

    @Override
    public void actualiza(
            TipoMascota tipoMascota)
            throws FachadaException {

        try {

            catalogoTipoMascotas.actualiza(tipoMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar tipo de mascota",
                    e);
        }
    }

    @Override
    public void elimina(
            TipoMascota tipoMascota)
            throws FachadaException {

        try {

            catalogoTipoMascotas.elimina(tipoMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar tipo de mascota",
                    e);
        }
    }

    @Override
    public ArrayList<TipoMascota>
    consultaTipoMascotas()
            throws FachadaException {

        try {

            return catalogoTipoMascotas.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar tipos de mascota",
                    e);
        }
    }
    // CITAS

    public Cita obten(
            Cita cita)
            throws FachadaException {
        try {
            return catalogoCitas.obten(cita);
        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar mascotas",
                    e);
        }
    }

    public void agrega(
            Cita cita)
            throws FachadaException {
        try {
            catalogoCitas.agrega(cita);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al agregar cita", e
            );
        }
    }

    public void actualiza(
            Cita cita)
            throws FachadaException {
        try {
            catalogoCitas.actualiza(cita);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al actualizar cita", e
            );
        }
    }

    public void elimina(
            Cita cita)
            throws FachadaException {
        try {
            catalogoCitas.elimina(cita);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al eliminar cita", e
            );
        }
    }

    public ArrayList<Cita>
    consultaCitas()
            throws FachadaException {

        try {

            return catalogoCitas.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar cita",
                    e);
        }
    }

    public ArrayList<Cita>
    consultaCitasMascota(
            int idMascota)
            throws FachadaException {

        try {

            return catalogoCitas.listaPorMascota(idMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar citas de la mascota",
                    e);
        }
    }

    public ArrayList<Cita>
    consultaCitasVeterinario(
            int idVeterinario)
            throws FachadaException {

        try {
            return catalogoCitas.listaPorVeterinario(idVeterinario);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al consultar citas por veterinario"
            );
        }
    }
    @Override
    public Cita obtenerCitaPorId(int id) throws FachadaException {

        try {
            return catalogoCitas.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener Cita", e);
        }
    }

    //VETERINARIOS
    @Override
    public Veterinario obten(
            Veterinario veterinario)
            throws FachadaException {

        try {

            return catalogoVeterinarios.obten(
                    veterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener veterinario",
                    e);
        }
    }

    @Override
    public void agrega(
            Veterinario veterinario)
            throws FachadaException {

        try {

            catalogoVeterinarios.agrega(
                    veterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar veterinario",
                    e);
        }
    }

    @Override
    public void actualiza(
            Veterinario veterinario)
            throws FachadaException {

        try {

            catalogoVeterinarios.actualiza(
                    veterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar veterinario",
                    e);
        }
    }

    @Override
    public void elimina(
            Veterinario veterinario)
            throws FachadaException {
        try {

            catalogoVeterinarios.elimina(
                    veterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar veterinario",
                    e);
        }
    }
    @Override
    public ArrayList<Veterinario>
    consultaVeterinarios()
            throws FachadaException {

        try {

            return catalogoVeterinarios.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Veterinarios",
                    e);
        }
    }

    @Override
    public ArrayList<Veterinario>
    consultaVeterinariosEspecialidad(
            int idEspecialidad)
            throws FachadaException {

        try {

            return catalogoVeterinarios
                    .listaPorEspecialidad(
                            idEspecialidad);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar veterinarios por especialidad",
                    e);
        }
    }
    @Override
    public Veterinario obtenerVeterinarioPorId(int id) throws FachadaException {

        try {
            return catalogoVeterinarios.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener veterinario", e);
        }
    }
    //MEDICAMENTOS

    @Override
    public Medicamento obten(
            Medicamento medicamento)
            throws FachadaException {

        try {

            return catalogoMedicamentos.obten(
                    medicamento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener medicamento",
                    e);
        }
    }

    @Override
    public void agrega(
            Medicamento medicamento)
            throws FachadaException {
        try {

            catalogoMedicamentos.agrega(
                    medicamento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar medicamento",
                    e);
        }
    }

    @Override
    public void actualiza(
            Medicamento medicamento)
            throws FachadaException {

        try {

            catalogoMedicamentos.actualiza(
                    medicamento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar medicamento",
                    e);
        }
    }

    @Override
    public void elimina(
            Medicamento medicamento)
            throws FachadaException {

        try {

            catalogoMedicamentos.elimina(
                    medicamento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar medicamento",
                    e);
        }
    }

    @Override
    public ArrayList<Medicamento>
    consultaMedicamentos()
            throws FachadaException {

        try {

            return catalogoMedicamentos.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar medicamentos",
                    e);
        }
    }

    @Override
    public ArrayList<Medicamento>
    consultaMedicamentosCategoria(
            String categoria)
            throws FachadaException {

        try {

            return catalogoMedicamentos
                    .listaPorCategoria(
                            categoria);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar medicamentos por categoría",
                    e);
        }
    }
    @Override
    public Medicamento obtenerMedicamentoPorId(int id) throws FachadaException {

        try {
            return catalogoMedicamentos.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener medicamento", e);
        }
    }

    //TRATAMIENTOS
    @Override
    public Tratamiento obten(
            Tratamiento tratamiento)
            throws FachadaException {

        try {

            return catalogoTratamientos.obten(
                    tratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener tratamiento",
                    e);
        }
    }

    @Override
    public void agrega(
            Tratamiento tratamiento)
            throws FachadaException {

        try {

            catalogoTratamientos.agrega(
                    tratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar tratamiento",
                    e);
        }
    }

    @Override
    public void actualiza(
            Tratamiento tratamiento)
            throws FachadaException {

        try {

            catalogoTratamientos.actualiza(
                    tratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar tratamiento",
                    e);
        }
    }

    @Override
    public void elimina(
            Tratamiento tratamiento)
            throws FachadaException {

        try {

            catalogoTratamientos.elimina(
                    tratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar tratamiento",
                    e);
        }
    }

    @Override
    public ArrayList<Tratamiento>
    consultaTratamientos()
            throws FachadaException {

        try {

            return catalogoTratamientos.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar tratamientos",
                    e);
        }
    }
    @Override
    public Tratamiento obtenerTratamientoPorId(int id) throws FachadaException {

        try {
            return catalogoTratamientos.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener tratamientos", e);
        }
    }
    @Override
    public ArrayList<DetalleTratamiento> listaPorTratamiento(int idTratamiento)
            throws FachadaException {

            try {

                return catalogoDetallesTratamientos.listaPorTratamiento(idTratamiento);

            } catch (PersistenciaException e) {

                throw new FachadaException(
                        "Error al consultar detalles del tratamientotratamientos",
                        e);
            }
        }

    // SERVICIOS
    @Override
    public Servicio obten(Servicio servicio)
            throws FachadaException {
        try {
            return catalogoServicios.obten(servicio);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener servicio", e);
        }
    }

    @Override
    public void agrega(Servicio servicio)
            throws FachadaException {
        try {
            catalogoServicios.agrega(servicio);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al agregar servicio",
                    e);
        }
    }

    @Override
    public void actualiza(Servicio servicio)
            throws FachadaException {
        try {
            catalogoServicios.actualiza(servicio);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al actualizar servicio", e);
        }
    }

    @Override
    public void elimina(Servicio servicio)
            throws FachadaException {
        try {
            catalogoServicios.elimina(servicio);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al eliminar servicio", e);
        }
    }

    @Override
    public ArrayList<Servicio>
    consultaServicios()
            throws FachadaException {
        try {
            return catalogoServicios.lista();
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al consultar servicios", e);
        }
    }

    // ESPECIALIDADES
    @Override
    public Especialidad obten(Especialidad especialidad)
            throws FachadaException {
        try {
            return catalogoEspecialidades.obten(especialidad);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener especialidad", e);
        }
    }

    @Override
    public void agrega(Especialidad especialidad)
            throws FachadaException {
        try {
            catalogoEspecialidades.agrega(especialidad);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al agregar especialidad", e);
        }
    }

    @Override
    public void actualiza(Especialidad especialidad)
            throws FachadaException {
        try {
            catalogoEspecialidades.actualiza(especialidad);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al actualizar especialidad", e);
        }
    }

    @Override
    public void elimina(Especialidad especialidad)
            throws FachadaException {
        try {
            catalogoEspecialidades.elimina(especialidad);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al eliminar especialidad", e);
        }
    }

    @Override
    public ArrayList<Especialidad>
    consultaEspecialidades()
            throws FachadaException {
        try {
            return catalogoEspecialidades.lista();
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al consultar especialidades", e);
        }
    }
    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws FachadaException {

        try {
            return catalogoEspecialidades.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener categoria", e);
        }
    }

    // HORARIOS
    @Override
    public Horario obten(Horario horario)
            throws FachadaException {
        try {
            return catalogoHorarios.obten(horario);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener horario", e);
        }
    }

    @Override
    public void agrega(Horario horario)
            throws FachadaException {
        try {
            catalogoHorarios.agrega(horario);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al agregar horario", e);
        }
    }

    @Override
    public void actualiza(Horario horario)
            throws FachadaException {
        try {
            catalogoHorarios.actualiza(horario);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al actualizar horario", e);
        }
    }

    @Override
    public void elimina(Horario horario)
            throws FachadaException {
        try {
            catalogoHorarios.elimina(horario);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al eliminar horario", e);
        }
    }

    @Override
    public ArrayList<Horario>
    consultaHorarios()
            throws FachadaException {
        try {
            return catalogoHorarios.lista();
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al consultar horarios", e);
        }

    }
    @Override
    public Horario obtenerHorarioPorId(int id) throws FachadaException {

        try {
            return catalogoHorarios.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener horario", e);
        }
    }

    //CONSULTAS
    @Override
    public Consulta obten(
            Consulta consulta)
            throws FachadaException {

        try {

            return catalogoConsultas.obten(
                    consulta);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener consulta",
                    e);
        }
    }

    @Override
    public void agrega(
            Consulta consulta)
            throws FachadaException {

        try {

            catalogoConsultas.agrega(
                    consulta);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar consulta",
                    e);
        }
    }

    @Override
    public void actualiza(
            Consulta consulta)
            throws FachadaException {

        try {

            catalogoConsultas.actualiza(
                    consulta);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar consulta",
                    e);
        }
    }

    @Override
    public void elimina(
            Consulta consulta)
            throws FachadaException {

        try {

            catalogoConsultas.elimina(
                    consulta);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar consulta",
                    e);
        }
    }

    @Override
    public ArrayList<Consulta>
    consultaConsultas()
            throws FachadaException {

        try {

            return catalogoConsultas.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Consultas",
                    e);
        }
    }

    @Override
    public ArrayList<Consulta>
    consultaConsultasMascota(int idMascota)
            throws FachadaException {

        try {

            return catalogoConsultas.listaPorMascota(idMascota);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Consultas de la mascota ",
                    e);
        }
    }
    @Override
    public ArrayList<Consulta>
    consultaConsultasVeterinario(int idVeterinario)
            throws FachadaException {

        try {

            return catalogoConsultas.listaPorVeterinario(idVeterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Consultas del veterinario",
                    e);
        }
    }
    @Override
    public Consulta obtenerConsultaPorId(int id) throws FachadaException {

        try {
            return catalogoConsultas.obtenPorId(id);
        } catch (PersistenciaException e) {
            throw new FachadaException("Error al obtener consultas", e);
        }
    }

    //FACTURAS
    @Override
    public Factura obten(
            Factura factura)
            throws FachadaException {

        try {

            return catalogoFacturas.obten(
                    factura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al obtener la factura",
                    e);
        }
    }

    @Override
    public void agrega(
            Factura factura)
            throws FachadaException {

        try {

            catalogoFacturas.agrega(
                    factura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar la factura",
                    e);
        }
    }

    @Override
    public void actualiza(
            Factura factura)
            throws FachadaException {

        try {

            catalogoFacturas.actualiza(
                    factura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al actualizar la factura",
                    e);
        }
    }

    @Override
    public void elimina(
            Factura factura)
            throws FachadaException {

        try {

            catalogoFacturas.elimina(
                    factura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar la factura",
                    e);
        }
    }

    @Override
    public ArrayList<Factura>
    consultaFacturas()
            throws FachadaException {

        try {

            return catalogoFacturas.lista();

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Consultas",
                    e);
        }
    }
    @Override
    public ArrayList<Factura>
    consultaFacturasCliente(int idCliente)
            throws FachadaException {

        try {

            return catalogoFacturas.listaPorCliente(idCliente);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar Consultas",
                    e);
        }
    }
    //Detalle Horarios
    @Override
    public void agrega(
            DetalleHorario detalleHorario)
            throws FachadaException {

        try {

            catalogoDetallesHorarios
                    .agrega(
                            detalleHorario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar detalle horario",
                    e);
        }
    }
    @Override
    public void elimina(
            DetalleHorario detalleHorario)
            throws FachadaException {

        try {

            catalogoDetallesHorarios
                    .elimina(
                            detalleHorario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar detalle horario",
                    e);
        }
    }
    @Override
    public ArrayList<DetalleHorario>
    consultaDetalleHorariosVeterinario(
            int idVeterinario)
            throws FachadaException {

        try {

            return catalogoDetallesHorarios
                    .listaPorVeterinario(
                            idVeterinario);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar horarios",
                    e);
        }
    }
    //Detalle Tratamiento
    @Override
    public void agrega(
            DetalleTratamiento detalleTratamiento)
            throws FachadaException {

        try {

            catalogoDetallesTratamientos
                    .agrega(
                            detalleTratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar detalle tratamiento",
                    e);
        }
    }
    @Override
    public void elimina(
            DetalleTratamiento detalleTratamiento)
            throws FachadaException {

        try {

            catalogoDetallesTratamientos
                    .elimina(
                            detalleTratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar detalle tratamiento",
                    e);
        }
    }
    @Override
    public ArrayList<DetalleTratamiento>
    consultaDetallesTratamiento(
            int idTratamiento)
            throws FachadaException {

        try {

            return catalogoDetallesTratamientos
                    .listaPorTratamiento(
                            idTratamiento);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar detalle tratamiento",
                    e);
        }
    }
    //Detalle Facturas
    @Override
    public void agrega(
            DetalleFactura detalleFactura)
            throws FachadaException {

        try {

            catalogoDetallesFacturas
                    .agrega(
                            detalleFactura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al agregar detalle factura",
                    e);
        }
    }
    @Override
    public void elimina(
            DetalleFactura detalleFactura)
            throws FachadaException {

        try {

            catalogoDetallesFacturas
                    .elimina(
                            detalleFactura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al eliminar detalle factura",
                    e);
        }
    }
    @Override
    public ArrayList<DetalleFactura>
    consultaDetallesFactura(
            int idFactura)
            throws FachadaException {

        try {

            return catalogoDetallesFacturas
                    .listaPorFactura(
                            idFactura);

        } catch (PersistenciaException e) {

            throw new FachadaException(
                    "Error al consultar detalle factura",
                    e);
        }
    }
    @Override
    public void agrega(ConsultaServicio consultaServicio)
            throws FachadaException {
        try {
            catalogoConsultaServicios.agrega(consultaServicio);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al agregar servicio de consulta",
                    e);
        }
    }

    @Override
    public void elimina(ConsultaServicio consultaServicio)
            throws FachadaException {
        try {
            catalogoConsultaServicios.elimina(consultaServicio);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al eliminar servicio de consulta",
                    e);
        }
    }

    @Override
    public ArrayList<ConsultaServicio> consultaServiciosConsulta(
            int idConsulta)
            throws FachadaException {
        try {
            return catalogoConsultaServicios.listaPorConsulta(idConsulta);
        } catch (PersistenciaException e) {
            throw new FachadaException(
                    "Error al consultar servicios de la consulta",
                    e);
        }
    }

}
