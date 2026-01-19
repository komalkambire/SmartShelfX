package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_levels")
public class StockLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private String warehouse;

    private Integer quantity = 0;
    private Integer reorderLevel = 10;

    public Product getProduct() { return product; }
    public Integer getQuantity() { return quantity; }
    public Integer getReorderLevel() { return reorderLevel; }

    public void setProduct(Product product) { this.product = product; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setReorderLevel(Integer reorderLevel) { this.reorderLevel = reorderLevel; }
}
