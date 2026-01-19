package com.example.demo.dto;

public class AdjustStockRequest {

    private Long productId;
    private String warehouse;   // ✅ STRING (important)
    private Integer delta;
    private String type;
    private String notes;

    public Long getProductId() {
        return productId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public Integer getDelta() {
        return delta;
    }

    public String getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }
}
