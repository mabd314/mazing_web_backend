package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WallRepo extends JpaRepository<WallEntity,Integer> {
    List<WallEntity> findByGameId(int gameId);
}
