package com.example.neomarket;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.modelos.Producto;
import com.example.modelos.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import static java.sql.DriverManager.getConnection;

public class ConexionBDD {
    private static final String URL = "jdbc:mysql://51.91.56.13:3306/dam2_24_omar";
    private static final String USER = "dam2_24_omar";
    private static final String PASSWORD = "7-09yai*P_vLGuFP";

    private Connection conexion;

    public ConexionBDD() {
        conectar();
    }

    private void conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión establecida.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conectar();
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la conexión: " + e.getMessage());
        }
        return conexion;
    }

    public Usuario verificarCredenciales(String email, String password) {
        String query = "SELECT * FROM nm_usuarios WHERE email = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("contrasena"); // Obtener la contraseña hasheada

                if (BCrypt.checkpw(password, hashedPassword)) { // Comparar la contraseña ingresada con la hasheada
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("descripcion"),
                            rs.getString("dni"),
                            rs.getString("email"),
                            rs.getString("fecha_nacimiento"),
                            hashedPassword, // Guardar la contraseña hasheada en el objeto Usuario
                            rs.getString("direccion_envio"),
                            rs.getString("estado"),
                            rs.getBoolean("verificacion"),
                            rs.getBoolean("newsletter"),
                            rs.getString("tipo_usuario"),
                            rs.getDate("ult_login")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Retorna null si las credenciales son incorrectas
    }

    public boolean registrarUsuario(String nombre, String apellido, String email, String password, String fechaNacimiento) {
        if (!esEmailUnico(email)) {
            System.out.println("Error: El email ya está registrado.");
            return false;
        }

        if (!esPasswordValida(password)) {
            System.out.println("Error: La contraseña debe tener al menos 8 caracteres y una mayúscula.");
            return false;
        }

        if (!esMayorDeEdad(fechaNacimiento)) {
            System.out.println("Error: Debes ser mayor de edad para registrarte.");
            return false;
        }

        String hashedPassword = hashPassword(password);  // Hashea la contraseña

        String insertQuery = "INSERT INTO nm_usuarios (nombre, apellido, email, contrasena, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConexion().prepareStatement(insertQuery)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            stmt.setString(5, fechaNacimiento);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    private boolean esEmailUnico(String email) {
        String query = "SELECT email FROM nm_usuarios WHERE email = ?";

        try (PreparedStatement stmt = getConexion().prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.err.println("Error al verificar email único: " + e.getMessage());
            return false;
        }
    }

    private boolean esPasswordValida(String password) {
        return password.length() >= 8 && password.chars().anyMatch(Character::isUpperCase);
    }

    private boolean esMayorDeEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate fechaActual = LocalDate.now();

        return Period.between(fechaNac, fechaActual).getYears() >= 18;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));  // Hashea la contraseña con BCrypt
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id_producto, id_vendedor, nombre, descripcion, imagenes, likes, categoria, marca, estado, estado_anuncio, fecha_publicacion, precio, unidades, favoritos FROM nm_productos";
        try (Connection conn = getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_vendedor"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("imagenes"),
                        rs.getInt("likes"),
                        rs.getString("categoria"),
                        rs.getString("marca"),
                        rs.getString("estado"),
                        rs.getString("estado_anuncio"),
                        rs.getString("fecha_publicacion"),
                        rs.getDouble("precio"),
                        rs.getInt("unidades"),
                        rs.getInt("favoritos")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
