package com.pvdong.inventory.infrastracture.configuration;

import com.pvdong.inventory.infrastracture.repository.SpringDataInventoryRepo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataInventoryRepo.class)
public class MySQLConfiguration {
}
