package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.service.CsvService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/csv")
public class CsvController {
    private final CsvService csvService;
    public CsvController(CsvService csvService){ this.csvService = csvService; }

    @PostMapping("/products/import")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('VENDOR') or hasAuthority('MANAGER')")
    public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file, Principal p) throws Exception {
        List<Product> created = csvService.importProducts(file, p.getName());
        return ResponseEntity.ok(created);
    }

    @GetMapping("/products/export")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void export(HttpServletResponse resp) throws Exception {
        resp.setHeader("Content-Disposition", "attachment; filename=products.csv");
        csvService.exportProducts(resp.getWriter());
    }
}

