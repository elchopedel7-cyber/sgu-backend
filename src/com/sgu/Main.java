package com.sgu;

import com.sgu.dao.UsuarioDAO;
import com.sgu.modelo.Usuario;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE USUARIOS (SENA)  ");
            System.out.println("==========================================");
            System.out.println("1. Ver lista de usuarios (READ)");
            System.out.println("2. Registrar nuevo usuario (CREATE)");
            System.out.println("3. Editar un usuario (UPDATE)");
            System.out.println("4. Eliminar un usuario (DELETE)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el búfer

            switch (opcion) {
                case 1:
                    // CONSULTAR (READ)
                    System.out.println("\n--- LISTA DE USUARIOS ---");
                    List<Usuario> usuarios = dao.consultarTodos();
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println("ID: " + u.getIdUsuario() + " | Nombre: " + u.getNombre() + " | Correo: " + u.getCorreo());
                        }
                    }
                    break;

                case 2:
                    // INSERTAR (CREATE)
                    System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el correo: ");
                    String correo = scanner.nextLine();
                    
                    Usuario nuevo = new Usuario(nombre, correo, "hash_seguro", true, 1);
                    if (dao.insertar(nuevo)) {
                        System.out.println("✅ Usuario registrado exitosamente.");
                    } else {
                        System.out.println("❌ Error al registrar el usuario.");
                    }
                    break;

                case 3:
                    // ACTUALIZAR (UPDATE)
                    System.out.println("\n--- EDITAR USUARIO ---");
                    System.out.print("Ingrese el ID del usuario que desea editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine(); // Limpiar búfer

                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese el nuevo correo: ");
                    String nuevoCorreo = scanner.nextLine();

                    Usuario usuarioEditar = new Usuario();
                    usuarioEditar.setIdUsuario(idEditar);
                    usuarioEditar.setNombre(nuevoNombre);
                    usuarioEditar.setCorreo(nuevoCorreo);
                    usuarioEditar.setEstado(true);

                    if (dao.actualizar(usuarioEditar)) {
                        System.out.println("✅ Usuario actualizado exitosamente.");
                    } else {
                        System.out.println("❌ Error al actualizar el usuario.");
                    }
                    break;

                case 4:
                    // ELIMINAR (DELETE)
                    System.out.println("\n--- ELIMINAR USUARIO ---");
                    System.out.print("Ingrese el ID del usuario a eliminar: ");
                    int idEliminar = scanner.nextInt();

                    if (dao.eliminar(idEliminar)) {
                        System.out.println("✅ Usuario eliminado exitosamente.");
                    } else {
                        System.out.println("❌ Error al eliminar el usuario.");
                    }
                    break;

                case 5:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}