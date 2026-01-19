import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-stock-update',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './stock-update.html',
  styleUrls: ['./stock-update.css']
})
export class StockUpdateComponent {

  model = {
    productId: 0,
    warehouseId: 1,
    delta: 0,
    type: "IN",
    userId: 1,
    notes: ""
  };

  constructor(private service: InventoryService) {}

  update() {
    this.service.adjustStock(this.model).subscribe(res => {
      alert("Stock updated successfully");
    });
  }
}
