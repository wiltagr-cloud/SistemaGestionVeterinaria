import control.ControlRoles;
import control.ControlUsuarios;
import interfazUsuario.FrameLogin;
import objetosNegocio.Rol;
import objetosNegocio.Usuario;
import persistencia.Roles;
import persistencia.Usuarios;

public class MainPruebas {

    public static void main(String[] args) {
//        try {
//
//            ControlRoles controlRoles =
//                    new ControlRoles();
//
//            ControlUsuarios controlUsuarios =
//                    new ControlUsuarios();
//
//
//            Rol rolAdmin = null;
//
//            for (Rol rol :
//                    controlRoles.consultar()) {
//
//                if (rol.getNombreRol()
//                        .equalsIgnoreCase(
//                                "Administrador")) {
//
//                    rolAdmin = rol;
//                    break;
//                }
//            }
//
//            if (rolAdmin == null) {
//
//                System.out.println(
//                        "No se pudo crear el rol administrador");
//
//                return;
//            }
//
//            // Crear usuario admin
//
//            Usuario usuario =
//                    new Usuario();
//
//            usuario.setNombreUsuario(
//                    "admin12");
//
//            usuario.setContrasenia(
//                    "111");
//
//            usuario.setNombre(
//                    "Administrador");
//
//            usuario.setApellido(
//                    "Sistema");
//
//            usuario.setIdRol(
//                    rolAdmin.getIdRol());
//
//            controlUsuarios.agregar(
//                    usuario);
//
//            System.out.println(
//                    "Sistema inicializado correctamente");
//
//            System.out.println(
//                    "Usuario: admin12");
//
//            System.out.println(
//                    "Contraseña: 111");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameLogin().setVisible(true);
            }
        });
    }
}