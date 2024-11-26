package edu.dam2.ad.ejercicios;

import java.sql.*;

public class CRUD_MySQL {

    private static final String URL = "jdbc:mysql://localhost:3306"; // Update with your database name
    private static final String USER = "root"; // Update with your username
    private static final String PASSWORD = "root_pswd"; // Update with your password

    private static Connection connection = null;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión con la base de datos establecida.");
        } catch (SQLException e) {
            System.err.println("Error conectando con la base de datos:\n" + e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión con la base de datos terminada.");
            }
        } catch (SQLException e) {
            System.err.println("Error intentando terminar la conexión: " + e.getMessage());
        }
    }

    // CREATE: Insert a new record
    public static void createVideoGame(String title, String genre, String releaseDate, String platform, String developer, String publisher, double rating) {
        String sql = "INSERT INTO videogames (title, genre, release_date, platform, developer, publisher, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setDate(3, Date.valueOf(releaseDate)); // Format: YYYY-MM-DD
            pstmt.setString(4, platform);
            pstmt.setString(5, developer);
            pstmt.setString(6, publisher);
            pstmt.setDouble(7, rating);
            pstmt.executeUpdate();
            System.out.println("Video game added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding video game: " + e.getMessage());
        }
    }

    // READ: Retrieve all records
    public static void readVideoGames() {
        String sql = "SELECT * FROM videogames";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Title: %s, Genre: %s, Release Date: %s, Platform: %s, Developer: %s, Publisher: %s, Rating: %.1f%n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("genre"), rs.getDate("release_date"),
                        rs.getString("platform"), rs.getString("developer"), rs.getString("publisher"), rs.getDouble("rating"));
            }
        } catch (SQLException e) {
            System.err.println("Error reading video games: " + e.getMessage());
        }
    }

    // UPDATE: Modify an existing record
    public static void updateVideoGame(int id, String newTitle, double newRating) {
        String sql = "UPDATE videogames SET title = ?, rating = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setDouble(2, newRating);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Video game updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating video game: " + e.getMessage());
        }
    }

    // DELETE: Remove a record
    public static void deleteVideoGame(int id) {
        String sql = "DELETE FROM videogames WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Video game deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting video game: " + e.getMessage());
        }
    }

    // Main method to test the CRUD operations
    public static void main(String[] args) {
        connect();
        // CREATE
        // createVideoGame("Halo 3", "First-person shooter", "2007-09-25", "Xbox 360", "Bungie", "Microsoft Game Studios", 9.5);
        // READ
        // readVideoGames();
        // UPDATE
        // updateVideoGame(1, "Halo 3: Anniversary", 4.8);
        // DELETE
        // deleteVideoGame(1);
        disconnect();
    }
}
