package com.sgu.dao;

import com.sgu.conexion.ConexionBD;
import com.sgu.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // 1. INSERCIÓN
    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO usuario (nombre, correo, contrasena_hash, estado, id_rol) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasenaHash());
            ps.setBoolean(4, u.isEstado());
            ps.setInt(5, u.getIdRol());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // 2. CONSULTA
    public List<Usuario> consultarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setContrasenaHash(rs.getString("contrasena_hash"));
                u.setEstado(rs.getBoolean("estado"));
                u.setIdRol(rs.getInt("id_rol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar usuarios: " + e.getMessage());
        }
        return lista;
    }

    // 3. ACTUALIZACIÓN
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuario SET nombre = ?, correo = ?, estado = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setBoolean(3, u.isEstado());
            ps.setInt(4, u.getIdUsuario());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // 4. ELIMINACIÓN
    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}