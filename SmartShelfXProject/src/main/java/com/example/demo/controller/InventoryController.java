package com.example.demo.controller;

import com.example.demo.dto.StockRequest;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.InventoryService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService service;
    private final WarehouseRepository warehouseRepo;

    public InventoryController(InventoryService service,
                               WarehouseRepository warehouseRepo) {
        this.service = service;
        this.warehouseRepo = warehouseRepo;
    }

    @PostMapping("/stock-in")
    public void stockIn(@RequestBody StockRequest req) {
//
//        Warehouse warehouse = warehouseRepo.findById(req.warehouseId)
//                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Long userId = 1L; // later from JWT

        service.stockIn(
                req.productId,
                req.quantity,
                req.notes,
                null, userId
        );
    }

    @PostMapping("/stock-out")
    public void stockOut(@RequestBody StockRequest req) {

        Warehouse warehouse = warehouseRepo.findById(req.warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Long userId = 1L;

        service.stockOut(
                req.productId,
                req.quantity,
                req.notes,
                null,
                userId
        );
    }
}
