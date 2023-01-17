package com.pvdong.inventory.infrastracture.configuration;

import com.pvdong.inventory.InventoryApplication;
import com.pvdong.inventory.domain.repository.InventoryRepository;
import com.pvdong.inventory.domain.service.InventoryService;
import com.pvdong.inventory.domain.service.InventoryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = InventoryApplication.class)
public class BeanConfiguration {
    @Bean
    InventoryService inventoryService(final InventoryRepository inventoryRepository) {
        return new InventoryServiceImpl(inventoryRepository);
    }
}
