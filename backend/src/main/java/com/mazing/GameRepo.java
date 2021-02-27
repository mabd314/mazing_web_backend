package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<GameEntity,Integer> {
}
