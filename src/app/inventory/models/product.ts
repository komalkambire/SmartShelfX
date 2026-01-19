export interface Product {
  id?: number | null;
  name: string;
  sku: string;
  category: string;
  description?: string;
  price?: number | null;
  vendorId?: number | null;
}
