package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RoomRepo extends JpaRepository<RoomEntity,RoomId> {
    List<RoomEntity> findByGameId(int gameId);

    @Transactional
    void deleteByGameId(int gameId);
}
