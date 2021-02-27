package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomEntity,Integer> {
    List<RoomEntity> findByGameId(int gameId);
}
