import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { InventoryService } from '../inventory.service';
import { Product } from '../models/product';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './product-form.html',
  styleUrls: ['./product-form.css']
})
export class ProductFormComponent implements OnInit {

  product: Product = {
  id: null,
  name: '',
  sku: '',
  category: '',
  description: '',
  price: null,
  vendorId: null
};

  isEdit = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: InventoryService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id']; // convert to number
    if (id) {
      this.isEdit = true;
      this.service.getProducts().subscribe(res => {
        const found = res.find(p => p.id === id);
        if (found) this.product = found;
      });
    }
  }

  save(): void {
  console.log("Save clicked!", this.product); // debug

  if (!this.product.name || !this.product.sku || !this.product.category) {
    alert("Please fill all required fields!");
    return;
  }

  if (this.isEdit) {

    this.service.updateProduct(this.product.id!, this.product).subscribe({
      next: () => this.router.navigate(['/inventory']),
      error: err => console.error("Update failed", err)
    });

  } else {
    const body = { ...this.product };
    delete body.id;   // 🚨 IMPORTANT: remove id=null from POST payload

    this.service.addProduct(body).subscribe({
      next: () => this.router.navigate(['/inventory']),
      error: err => console.error("Add failed", err)
    });
  }
}

}
