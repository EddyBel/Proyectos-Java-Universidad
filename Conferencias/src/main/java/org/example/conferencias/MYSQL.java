package org.example.conferencias;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class MYSQL {

    private static final String URL = "jdbc:mysql://localhost:3306/reservassalas";
    private static final String USER = "root";
    private static final String PASSWORD = "54628";
    private Connection connection;

    // Constructor para establecer la conexión
    public MYSQL() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para registrar un usuario
    public void registrarUsuario(String nombre, String apellido, String email, int telefono) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setInt(4, telefono);
            ps.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        }
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(int idUsuario, String nombre, String apellido, String email, int telefono) throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, email);
            ps.setInt(4, telefono);
            ps.setInt(5, idUsuario);
            ps.executeUpdate();
            System.out.println("Usuario actualizado correctamente.");
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int idUsuario) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            System.out.println("Usuario eliminado correctamente.");
        }
    }

    // Método para listar usuarios
    public void listarUsuarios() throws SQLException {
        String query = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_usuario") + ", Nombre: " + rs.getString("nombre") +
                        ", Apellido: " + rs.getString("apellido") + ", Email: " + rs.getString("email") +
                        ", Teléfono: " + rs.getInt("telefono"));
            }
        }
    }

    // Método para registrar una sala
    public void registrarSala(String nombreSala, int capacidad, String ubicacion) throws SQLException {
        String query = "INSERT INTO salas (nombre_sala, capacidad, ubicacion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombreSala);
            ps.setInt(2, capacidad);
            ps.setString(3, ubicacion);
            ps.executeUpdate();
            System.out.println("Sala registrada correctamente.");
        }
    }

    // Método para actualizar una sala
    public void actualizarSala(int idSala, String nombreSala, int capacidad, String ubicacion) throws SQLException {
        String query = "UPDATE salas SET nombre_sala = ?, capacidad = ?, ubicacion = ? WHERE id_sala = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombreSala);
            ps.setInt(2, capacidad);
            ps.setString(3, ubicacion);
            ps.setInt(4, idSala);
            ps.executeUpdate();
            System.out.println("Sala actualizada correctamente.");
        }
    }

    // Método para eliminar una sala
    public void eliminarSala(int idSala) throws SQLException {
        String query = "DELETE FROM salas WHERE id_sala = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idSala);
            ps.executeUpdate();
            System.out.println("Sala eliminada correctamente.");
        }
    }

    // Método para listar salas
    public void listarSalas() throws SQLException {
        String query = "SELECT * FROM salas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_sala") + ", Nombre: " + rs.getString("nombre_sala") +
                        ", Capacidad: " + rs.getInt("capacidad") + ", Ubicación: " + rs.getString("ubicacion"));
            }
        }
    }

    // Método para crear una reserva
    public void crearReserva(int idUsuario, int idSala, Date fechaReserva, Time horaInicio, Time horaFin) throws SQLException {
        if (validarReserva(idSala, fechaReserva, horaInicio, horaFin)) {
            String query = "INSERT INTO reservas (id_usuario, id_sala, fecha_reserva, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, idUsuario);
                ps.setInt(2, idSala);
                ps.setDate(3, fechaReserva);
                ps.setTime(4, horaInicio);
                ps.setTime(5, horaFin);
                ps.executeUpdate();
                System.out.println("Reserva creada correctamente.");
            }
        } else {
            System.out.println("La sala ya está reservada para este horario.");
        }
    }

    // Método para consultar reservas por fecha
    public void consultarReservasPorFecha(Date fechaReserva) throws SQLException {
        String query = "SELECT * FROM reservas WHERE fecha_reserva = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, fechaReserva);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID Reserva: " + rs.getInt("id_reserva") + ", Usuario: " + rs.getInt("id_usuario") +
                            ", Sala: " + rs.getInt("id_sala") + ", Fecha: " + rs.getDate("fecha_reserva") +
                            ", Hora inicio: " + rs.getTime("hora_inicio") + ", Hora fin: " + rs.getTime("hora_fin"));
                }
            }
        }
    }

    // Método para consultar reservas por usuario
    public void consultarReservasPorUsuario(int idUsuario) throws SQLException {
        String query = "SELECT * FROM reservas WHERE id_usuario = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("ID Reserva: " + rs.getInt("id_reserva") + ", Usuario: " + rs.getInt("id_usuario") +
                            ", Sala: " + rs.getInt("id_sala") + ", Fecha: " + rs.getDate("fecha_reserva") +
                            ", Hora inicio: " + rs.getTime("hora_inicio") + ", Hora fin: " + rs.getTime("hora_fin"));
                }
            }
        }
    }

    // Método para cancelar una reserva
    public void cancelarReserva(int idReserva) throws SQLException {
        String query = "DELETE FROM reservas WHERE id_reserva = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idReserva);
            ps.executeUpdate();
            System.out.println("Reserva cancelada correctamente.");
        }
    }

    // Validación de reservas que se solapan en horario
    private boolean validarReserva(int idSala, Date fechaReserva, Time horaInicio, Time horaFin) throws SQLException {
        String query = "SELECT * FROM reservas WHERE id_sala = ? AND fecha_reserva = ? AND ((hora_inicio < ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin > ?))";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idSala);
            ps.setDate(2, fechaReserva);
            ps.setTime(3, horaInicio);
            ps.setTime(4, horaInicio);
            ps.setTime(5, horaFin);
            ps.setTime(6, horaFin);
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next();  // Si hay registros, el horario está ocupado
            }
        }
    }

    // Cerrar conexión
    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión cerrada.");
        }
    }

    public static void main(String[] args) {
        try {
            MYSQL mysql = new MYSQL();

            // Ejemplo de uso
            mysql.registrarUsuario("Juan", "Perez", "juan.perez@email.com", 123456789);
            mysql.listarUsuarios();

            mysql.registrarSala("Sala 1", 10, "Planta baja");
            mysql.listarSalas();

            mysql.crearReserva(1, 1, Date.valueOf("2025-01-01"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"));
            mysql.consultarReservasPorFecha(Date.valueOf("2025-01-01"));

            mysql.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

