import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

import { ProductListComponent } from './inventory/product-list/product-list.component';
import { ProductFormComponent } from './inventory/product-form/product-form.component';
import { TransactionFormComponent } from './inventory/transaction/transaction-form.component';

export const routes: Routes = [

  { path: '', redirectTo: 'transaction', pathMatch: 'full' },

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },

  // Inventory
  { path: 'inventory', component: ProductListComponent },
  { path: 'inventory/add', component: ProductFormComponent },
  { path: 'inventory/edit/:id', component: ProductFormComponent },

  // Transactions
  { path: 'transaction', component: TransactionFormComponent },

  // Fallback
  { path: '**', redirectTo: 'transaction' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
