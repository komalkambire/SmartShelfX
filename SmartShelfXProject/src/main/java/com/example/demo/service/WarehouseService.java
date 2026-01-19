package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepo;

    public WarehouseService(WarehouseRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));
    }
}

