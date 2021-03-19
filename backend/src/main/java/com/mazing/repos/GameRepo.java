package com.mazing.repos;

import com.mazing.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<GameEntity,Integer> {
}
