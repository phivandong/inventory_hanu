package com.pvdong.inventory.domain.repository;

import com.pvdong.inventory.domain.model.Inventory;

import java.util.List;

public interface InventoryRepository {
    long save(Inventory inventory);
    List<Inventory> getAll();
    Inventory getById(Long id);
}
