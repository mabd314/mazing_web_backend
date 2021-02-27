package com.mazing;

import com.mazing.logic.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepo extends JpaRepository<PlayerEntity,String> {
    List<PlayerEntity> findByGameId(int gameId);
}
