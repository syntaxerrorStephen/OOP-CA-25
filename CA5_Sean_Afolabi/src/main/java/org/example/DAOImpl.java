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
}
