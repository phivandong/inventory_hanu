package com.pvdong.inventory.domain.service;

import com.pvdong.inventory.domain.model.Inventory;
import com.pvdong.inventory.domain.model.InventoryItem;

import java.util.List;

public interface InventoryService {
    long createInventory(Inventory inventory);
    void addItems(long id, List<InventoryItem> items);
    List<Inventory> getAll();
}
