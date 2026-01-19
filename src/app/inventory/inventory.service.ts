import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './models/product';
import { StockLevel } from './models/stock-level';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private base = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // ===== PRODUCTS =====
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.base}/products`);
  }

  addProduct(p: Product): Observable<Product> {
    return this.http.post<Product>(`${this.base}/products`, p);
  }

  updateProduct(id: number, p: Product): Observable<Product> {
    return this.http.put<Product>(`${this.base}/products/${id}`, p);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.base}/products/${id}`);
  }

  // ===== STOCK =====
  adjustStock(payload: {
    productId: number,
    warehouseId: number,
    delta: number,
    type: string,
    userId: number,
    notes?: string
  }): Observable<StockLevel> {
    return this.http.post<StockLevel>(`${this.base}/stock/adjust`, payload);
  }

  // ===== CSV UPLOAD =====
  uploadCsv(file: File, actor: string) {
    const form = new FormData();
    form.append("file", file);
    form.append("actor", actor);

    return this.http.post(`${this.base}/products/import`, form);
  }

  downloadCsv() {
    return this.http.get(`${this.base}/products/export`, { responseType: "blob" });
  }
}
