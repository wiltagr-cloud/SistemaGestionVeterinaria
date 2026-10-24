package control;

import Interfaces.IFachada;
import Fachadas.FachadaArchivos;
import objetosNegocio.Cita;
import objetosNegocio.Horario;

import java.util.ArrayList;

public class ControlCitas {

    private IFachada fachada;

    public ControlCitas() {

        fachada = new FachadaArchivos();
    }

    public Cita obtener(Cita cita)
            throws Exception {

        return fachada.obten(cita);
    }

    public void agregar(Cita cita)
            throws Exception {

        fachada.agrega(cita);
    }

    public void actualizar(Cita cita)
            throws Exception {

        fachada.actualiza(cita);
    }

    public void eliminar(Cita cita)
            throws Exception {

        fachada.elimina(cita);
    }

    public ArrayList<Cita>
    consultar()
            throws Exception {

        return fachada.consultaCitas();
    }

    public ArrayList<Cita>
    consultarPorMascota(
            int idMascota)
            throws Exception {

        return fachada
                .consultaCitasMascota(
                        idMascota);
    }

    public ArrayList<Cita>
    consultarPorVeterinario(
            int idVeterinario)
            throws Exception {

        return fachada
                .consultaCitasVeterinario(
                        idVeterinario);
    }
    public Cita obtenerCitaPorId(int id) throws Exception {
        return fachada.obtenerCitaPorId(id);
    }
}


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard de Torneos Gamer</title>

    <link rel="stylesheet" href="estilos.css">
    <script src="dashboard.js" defer></script>
</head>

<body>

    <header class="encabezado">
        <div class="logo">🎮 GAMER ZONE</div>
        <p>Panel de administración de torneos</p>
    </header>

    <main class="contenedor">

        <section class="bienvenida">
            <span class="etiqueta">PANEL DE CONTROL</span>
            <h1>Dashboard de Torneos Gamer</h1>
            <p>
                Registra tus torneos de videojuegos, administra participantes
                y consulta la recaudación estimada.
            </p>
        </section>

        <section class="panel">

            <div class="titulo-seccion">
                <span class="icono">🏆</span>
                <div>
                    <h2>Registrar torneo</h2>
                    <p>Completa los siguientes datos.</p>
                </div>
            </div>

            <form id="formularioTorneo"
                  onsubmit="event.preventDefault(); registrarTorneo();">

                <div class="grupo-formulario">
                    <label for="nombre">Nombre del torneo</label>
                    <input
                        type="text"
                        id="nombre"
                        placeholder="Ej. Copa Legends"
                    >
                    <small id="errorNombre" class="error"></small>
                </div>

                <div class="grupo-formulario">
                    <label for="categoria">Categoría</label>
                    <select id="categoria">
                        <option value="">Seleccione una categoría</option>
                        <option value="Lucha">Lucha</option>
                        <option value="Estrategia">Estrategia</option>
                        <option value="Arcade">Arcade</option>
                        <option value="Deportes">Deportes</option>
                        <option value="Aventura">Aventura</option>
                    </select>
                    <small id="errorCategoria" class="error"></small>
                </div>

                <div class="fila-formulario">

                    <div class="grupo-formulario">
                        <label for="participantes">Participantes</label>
                        <input
                            type="number"
                            id="participantes"
                            placeholder="Entre 1 y 100"
                        >
                        <small id="errorParticipantes" class="error"></small>
                    </div>

                    <div class="grupo-formulario">
                        <label for="valorInscripcion">
                            Valor de inscripción ($)
                        </label>
                        <input
                            type="number"
                            id="valorInscripcion"
                            placeholder="Ej. 5.00"
                            step="any"
                        >
                        <small id="errorInscripcion" class="error"></small>
                    </div>

                </div>

                <div class="grupo-formulario">
                    <label for="email">Email del organizador</label>
                    <input
                        type="email"
                        id="email"
                        placeholder="organizador@correo.com"
                    >
                    <small id="errorEmail" class="error"></small>
                </div>

                <button type="submit" class="boton-registrar">
                    REGISTRAR TORNEO
                </button>

                <p id="mensajeExito" class="mensaje-exito"></p>

            </form>

        </section>

        <section class="panel panel-tabla">

            <div class="titulo-seccion">
                <span class="icono">📊</span>
                <div>
                    <h2>Torneos registrados</h2>
                    <p>Listado de torneos y recaudación estimada.</p>
                </div>
            </div>

            <div class="tabla-contenedor">
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Categoría</th>
                            <th>Participantes</th>
                            <th>Valor de inscripción</th>
                            <th>Email</th>
                            <th>Recaudación estimada</th>
                        </tr>
                    </thead>

                    <tbody id="cuerpoTabla">
                        <tr>
                            <td colspan="6" class="sin-datos">
                                Todavía no hay torneos registrados.
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </section>

    </main>

    <footer>
        <p>🎮 Gamer Zone | Dashboard de Torneos</p>
        <p>Proyecto académico de JavaScript</p>
    </footer>

</body>
</html>

