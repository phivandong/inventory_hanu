package com.pvdong.inventory.application.rest;

import com.pvdong.inventory.application.response.CreateInventoryResponse;
import com.pvdong.inventory.domain.model.Inventory;
import com.pvdong.inventory.domain.model.InventoryItem;
import com.pvdong.inventory.domain.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    ResponseEntity<CreateInventoryResponse> createInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(new CreateInventoryResponse(inventoryService.createInventory(inventory)), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Inventory>> getAllInventory() {
        return new ResponseEntity<>(inventoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{id}/check-in")
    @ResponseStatus(HttpStatus.OK)
    void addItems(@PathVariable Long id,@RequestBody List<InventoryItem> items) {
        inventoryService.addItems(id, items);
    }
}
