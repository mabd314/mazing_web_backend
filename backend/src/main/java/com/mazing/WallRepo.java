package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WallRepo extends JpaRepository<WallEntity,Integer> {
}
