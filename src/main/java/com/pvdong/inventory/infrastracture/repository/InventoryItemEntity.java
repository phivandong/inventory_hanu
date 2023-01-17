package com.pvdong.inventory.infrastracture.repository;

import com.pvdong.inventory.domain.model.InventoryItem;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "inventory_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private InventoryEntity inventory;

    public InventoryItemEntity(InventoryItem inventoryItem) {
        this.id = inventoryItem.getId();
        this.name = inventoryItem.getName();
        this.quantity = inventoryItem.getQuantity();
    }
}
