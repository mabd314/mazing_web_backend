package com.mazing.repos;

import com.mazing.entities.GameSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSettingsRepo extends JpaRepository<GameSettingsEntity,Integer> {
}
