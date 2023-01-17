package com.pvdong.inventory.domain.service;

import com.pvdong.inventory.domain.model.Inventory;
import com.pvdong.inventory.domain.model.InventoryItem;
import com.pvdong.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public long createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void addItems(long id, List<InventoryItem> items) {
        Inventory inventory = inventoryRepository.getById(id);
        items.forEach(inventory::addItem);
        inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.getAll();
    }
}
