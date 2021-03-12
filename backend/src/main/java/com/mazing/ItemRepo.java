package com.mazing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<ItemEntity,Integer> {
    List<ItemEntity> findByWallId(int itemId);
    List<ItemEntity> findByUserName(String userName);
}
