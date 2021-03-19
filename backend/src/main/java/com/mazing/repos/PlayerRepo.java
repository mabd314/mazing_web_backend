package com.mazing.repos;

import com.mazing.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepo extends JpaRepository<PlayerEntity,String> {
    List<PlayerEntity> findByGameId(int gameId);
}
