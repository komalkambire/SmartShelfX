package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    private final AuditService auditService;

    public ProductService(ProductRepository productRepo, AuditService auditService){
        this.productRepo = productRepo; this.auditService = auditService;
    }

    public Product create(Product p, String actor){
        Product saved = productRepo.save(p);
        auditService.log("Product", saved.getId(), "CREATE", actor, "Created product " + saved.getSku());
        return saved;
    }

    public Product update(Long id, Product updated, String actor){
        Product existing = productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        // restrict changing SKU if caller is not ADMIN — enforce at controller via @PreAuthorize or check role passed
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setVendorId(updated.getVendorId());
        Product saved = productRepo.save(existing);
        auditService.log("Product", saved.getId(), "UPDATE", actor, "Updated product fields");
        return saved;
    }

    public void delete(Long id, String actor){
        productRepo.deleteById(id);
        auditService.log("Product", id, "DELETE", actor, "Deleted product");
    }

    public List<Product> filter(String category, Long vendorId, String sku){
        // simple filtering. For complex filters use Specification/Criteria
        if(vendorId!=null) return productRepo.findByVendorId(vendorId);
        if(category!=null) return productRepo.findAll().stream()
                  .filter(p -> category.equals(p.getCategory())).toList();
        return productRepo.findAll();
    }
}
