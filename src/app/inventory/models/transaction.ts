export type TransactionType = 'STOCK_IN' | 'STOCK_OUT';

export interface Transaction {

  id?: number;

  // Product details
  productId: number;
  productName?: string;

  // Transaction info
  type: TransactionType;
  quantity: number;

  // Stock-In specific
  vendor?: string;

  // Stock-Out specific
  customerRef?: string;   // order id / customer name

  // Metadata
  handler: string;        // who processed it
  notes?: string;         // batch no / invoice ref
  timestamp?: string;     // backend generated
}
