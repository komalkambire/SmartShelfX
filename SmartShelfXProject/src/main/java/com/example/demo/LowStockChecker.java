package com.example.demo;

import com.example.demo.entity.StockLevel;
import com.example.demo.repository.StockLevelRepository;
import com.example.demo.service.NotificationService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LowStockChecker {

    private final StockLevelRepository stockRepo;
    private final NotificationService notificationService;

    public LowStockChecker(StockLevelRepository stockRepo,
                           NotificationService notificationService) {
        this.stockRepo = stockRepo;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 600000)
    public void check() {

        List<StockLevel> lowStock = stockRepo.findAll().stream()
                .filter(s -> s.getQuantity() != null
                        && s.getReorderLevel() != null
                        && s.getQuantity() < s.getReorderLevel())
                .toList();

        for (StockLevel s : lowStock) {
            notificationService.notifyVendorLowStock(
                    s.getProduct().getVendorId(), s
            );
        }
    }
}
