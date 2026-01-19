import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { FormsModule } from '@angular/forms'; 
import { ProductListComponent } from './product-list/product-list.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { StockUpdateComponent } from './stock-update/stock-update.component';
import { CsvUploadComponent } from './csv-upload/csv-upload.component';

const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'add', component: ProductFormComponent },
  { path: 'edit/:id', component: ProductFormComponent },
  { path: 'stock', component: StockUpdateComponent },
  { path: 'csv', component: CsvUploadComponent }
];

@NgModule({
 
  imports: [
     CommonModule,
    FormsModule 
  ]
})
export class InventoryModule {}
