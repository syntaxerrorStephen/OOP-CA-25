package org.example;

import java.util.List;

public interface PlayerDAO
{
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerByID(int id);
    void deletePlayerById(int id);
    PlayerDTO insertPlayer(PlayerDTO player);
}
