package org.example;

import java.util.List;

public interface PlayerDAO
{
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerByID(int id);
}
