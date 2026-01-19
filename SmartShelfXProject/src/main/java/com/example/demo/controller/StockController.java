package com.example.demo.controller;

import com.example.demo.dto.AdjustStockRequest;
import com.example.demo.entity.StockLevel;
import com.example.demo.entity.StockTransaction;
import com.example.demo.service.StockService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

	private final StockService stockService;

	public StockController(StockService stockService) {
		this.stockService = stockService;
	}

	@PostMapping("/adjust")
	@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
	public StockLevel adjust(@RequestBody AdjustStockRequest req, Principal principal) {

		Long userId = 1L; // later get from Principal

		StockTransaction.TransactionType type = StockTransaction.TransactionType.valueOf(req.getType().toUpperCase());

		return stockService.adjustStock(req.getProductId(), req.getWarehouse(), req.getDelta(), type, userId,
				req.getNotes());
	}

	@PostMapping("/import")
	@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
	public ResponseEntity<?> importBatch(@RequestParam("file") MultipartFile file, Principal principal) {

		Long userId = 1L;

		List<String> result = stockService.importStockCsv(file, userId);
		return ResponseEntity.ok(result);
	}
}
