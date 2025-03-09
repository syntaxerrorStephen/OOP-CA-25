package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOImpl implements PlayerDAO
{
    private final String url = "jdbc:mysql://localhost:3306/player";
    private final String user = "root";
    private final String password = "";

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        // Create Arraylist for each player data
        List<PlayerDTO> players = new ArrayList<>();

        // String will be SQL query and will trigger the feature when called
        String sql = "SELECT * FROM Player";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                PlayerDTO player = new PlayerDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getFloat("rating")
                );
                players.add(player);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public PlayerDTO getPlayerByID(int id) {
        // "player" will be null until the sql queryis triggered
        PlayerDTO player = null;
        String sql = "SELECT * FROM Player WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery())
            {
                if (rs.next())
                {
                    player = new PlayerDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getFloat("rating")
                    );
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return player;
    }
    @Override
public void deletePlayerById(int id) {
    String sql = "DELETE FROM Player WHERE id = ?";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Player with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Player with ID " + id + " not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
@Override
public PlayerDTO insertPlayer(PlayerDTO player) {
    String sql = "INSERT INTO Player (name, age, rating) VALUES (?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setString(1, player.getName());
        pstmt.setInt(2, player.getAge());
        pstmt.setFloat(3, player.getRating());

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    player.setId(generatedId);  // Assign generated ID to DTO
                    System.out.println("Player inserted with ID: " + generatedId);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return player;
}

    
}
