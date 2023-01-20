package com.pvdong.inventory.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long id;
    private Long length;
    private Long width;
    private Long height;

    @Setter(AccessLevel.NONE)
    private List<InventoryItem> items;

    public void setItems(List<InventoryItem> items) {
        long quantityOfItems = items.stream().mapToLong(InventoryItem::getQuantity).sum();
        if (quantityOfItems > getCapacity()) {
            throw new RuntimeException("There is no space left in the inventory");
        } else {
            this.items = items;
        }
    }

    @JsonIgnore
    public long getCapacity() {
        long itemSize = 0;
        if (items != null) {
            itemSize = items.stream().mapToLong(InventoryItem::getQuantity).sum();
        }
        return height * length * width - itemSize;
    }

    public void addItem(final InventoryItem inventoryItem) {
        if (inventoryItem.getQuantity() > getCapacity()) {
            throw new RuntimeException("There is no space left in the inventory");
        } else {
            if (items != null) {
                InventoryItem savedItem = items.stream().filter(item -> inventoryItem.getName().equals(item.getName())).findAny().orElse(null);
                if (savedItem == null) {
                    items.add(inventoryItem);
                } else {
                    savedItem.setQuantity(savedItem.getQuantity() + inventoryItem.getQuantity());
                }
            } else {
                items = new ArrayList<>();
                items.add(inventoryItem);
            }
        }
    }
}
