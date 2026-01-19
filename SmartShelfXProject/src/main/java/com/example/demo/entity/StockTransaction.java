package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transactions")
public class StockTransaction {

    public enum TransactionType {
        STOCK_IN,
        STOCK_OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private String warehouse;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Long performedBy;
    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();

    // setters
    public void setProduct(Product product) { this.product = product; }
    public void setWarehouse(String warehouse2) { this.warehouse = warehouse2; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setType(TransactionType type) { this.type = type; }
    public void setPerformedBy(Long performedBy) { this.performedBy = performedBy; }
    public void setNotes(String notes) { this.notes = notes; }
	public void setHandler(String string) {
		// TODO Auto-generated method stub
		
	}
}
