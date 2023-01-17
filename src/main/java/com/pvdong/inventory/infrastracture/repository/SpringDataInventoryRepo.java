package com.pvdong.inventory.infrastracture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataInventoryRepo extends JpaRepository<InventoryEntity, Long> {
}
