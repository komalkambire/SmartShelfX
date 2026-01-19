import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TransactionService } from '../services/transaction.service';
import { InventoryService } from '../services/inventory.service';
import { Transaction } from '../models/transaction';

@Component({
  selector: 'app-transaction-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './transaction-form.component.html',
  styleUrls: ['./transaction-form.component.css']
})
export class TransactionFormComponent {

  // STOCK IN FORM MODEL
  stockIn: Transaction = {
    productId: 0,
    type: 'STOCK_IN',
    quantity: 1,
    vendor: '',
    handler: '',
    notes: ''
  };

  // STOCK OUT FORM MODEL
  stockOut: Transaction = {
    productId: 0,
    type: 'STOCK_OUT',
    quantity: 1,
    customerRef: '',
    handler: '',
    notes: ''
  };

  message = '';

  constructor(
    private transactionService: TransactionService,
    private inventoryService: InventoryService
  ) {}

  // SAVE STOCK IN
  saveStockIn() {
    this.transactionService.recordTransaction(this.stockIn).subscribe({
      next: () => {
        this.message = 'Stock In recorded successfully';
        this.resetStockIn();
      },
      error: () => {
        this.message = 'Stock In failed';
      }
    });
  }

  // SAVE STOCK OUT
  saveStockOut() {
    this.transactionService.recordTransaction(this.stockOut).subscribe({
      next: () => {
        this.message = 'Stock Out recorded successfully';

        // Reorder check
        this.inventoryService
          .checkReorder(this.stockOut.productId)
          .subscribe(isLow => {
            if (isLow) {
              alert('⚠ Stock below reorder level!');
            }
          });

        this.resetStockOut();
      },
      error: () => {
        this.message = 'Stock Out failed';
      }
    });
  }

  private resetStockIn() {
    this.stockIn = {
      productId: 0,
      type: 'STOCK_IN',
      quantity: 1,
      vendor: '',
      handler: '',
      notes: ''
    };
  }

  private resetStockOut() {
    this.stockOut = {
      productId: 0,
      type: 'STOCK_OUT',
      quantity: 1,
      customerRef: '',
      handler: '',
      notes: ''
    };
  }
}
