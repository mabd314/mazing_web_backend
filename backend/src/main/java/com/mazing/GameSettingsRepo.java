package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSettingsRepo extends JpaRepository<GameSettingsEntity,Integer> {
    GameSettingsEntity findByGameId(int gameId);
}
