import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { InventoryService } from '../inventory.service';
import { Product } from '../models/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-list.html',
  styleUrls: ['./product-list.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private service: InventoryService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.service.getProducts().subscribe(res => this.products = res);
  }

 delete(id: number | null | undefined): void {
  if (id == null) return; // ignore null/undefined

  this.service.deleteProduct(id).subscribe({
    next: () => {
      console.log('Product deleted', id);
      this.loadProducts(); // refresh list after delete
    },
    error: err => console.error('Delete failed', err)
  });
}



}
