import { Product } from './Product';

export interface SellerInfo {
    id?: string;
    firstName?: string;
    lastName?: string;
    address?: string;
    city?: string;
    state?: string;
    phone?: string;
    email?: string;
    products?: Product[];
  }