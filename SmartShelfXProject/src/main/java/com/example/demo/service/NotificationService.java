package com.example.demo.service;

import com.example.demo.entity.StockLevel;

public interface NotificationService {

    void notifyVendorLowStock(Long vendorId, StockLevel stockLevel);

    void notifyWarehouseManager(String warehouse, StockLevel stockLevel);
}
