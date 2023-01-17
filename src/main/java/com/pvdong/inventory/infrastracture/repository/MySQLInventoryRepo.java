package com.pvdong.inventory.infrastracture.repository;

import com.pvdong.inventory.domain.model.Inventory;
import com.pvdong.inventory.domain.model.InventoryItem;
import com.pvdong.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MySQLInventoryRepo implements InventoryRepository {
    private final SpringDataInventoryRepo inventoryRepo;

    @Override
    public long save(Inventory inventory) {
        if (inventory.getHeight() <= 0) {
            throw new RuntimeException("Height must be greater than 0");
        } else if (inventory.getLength() <= 0) {
            throw new RuntimeException("Length must be greater than 0");
        } else if (inventory.getWidth() <= 0) {
            throw new RuntimeException("Width must be greater than 0");
        } else {
            // inventory.getItems().forEach(item -> inventoryItemRepo.save(new InventoryItemEntity(item)));
            return inventoryRepo.save(new InventoryEntity(inventory)).getId();
        }
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepo.findAll().stream().map(this::mapEntityToInventory).collect(Collectors.toList());
    }

    @Override
    public Inventory getById(Long id) {
        return mapEntityToInventory(inventoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Inventory with id: " + id + " not found")));
    }

    private Inventory mapEntityToInventory(InventoryEntity entity) {
        Inventory inventory = new Inventory();
        inventory.setId(entity.getId());
        inventory.setLength(entity.getLength());
        inventory.setWidth(entity.getWidth());
        inventory.setHeight(entity.getHeight());
        inventory.setItems(entity.getItems().stream().map(this::mapEntityToInventoryItem).collect(Collectors.toList()));
        return inventory;
    }

    private InventoryItem mapEntityToInventoryItem(InventoryItemEntity entity) {
        InventoryItem item = new InventoryItem();
        item.setId(entity.getId());
        item.setName(entity.getName());
        item.setQuantity(entity.getQuantity());
        return item;
    }
}
