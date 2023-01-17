package com.pvdong.inventory.infrastracture.repository;

import com.pvdong.inventory.domain.model.Inventory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long length;
    private Long width;
    private Long height;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<InventoryItemEntity> items;

    public InventoryEntity(Inventory inventory) {
        this.id = inventory.getId();
        this.length = inventory.getLength();
        this.width = inventory.getWidth();
        this.height = inventory.getHeight();
        if (inventory.getItems() != null) {
            this.items = inventory.getItems().stream().map(inventoryItem -> {
                InventoryItemEntity item = new InventoryItemEntity(inventoryItem);
                item.setInventory(this);
                return item;
            }).collect(Collectors.toList());
        } else {
            this.items = new ArrayList<>();
        }
    }
}
