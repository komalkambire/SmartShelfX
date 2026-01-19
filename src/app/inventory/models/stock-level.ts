export interface StockLevel {
  id?: number;
  productId: number;
  warehouseId: number;
  quantity: number;
  reorderLevel?: number;
}
