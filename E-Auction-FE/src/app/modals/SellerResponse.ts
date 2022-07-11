import { SellerInfo } from './SellerInfo';
import { Bid } from './Bid';

export interface SellerResponse {
    message?: string;
    statusCode?: string;
    statusMessage?: string;
    sellerInfo?: SellerInfo[];
    products?:string[];
    productBids?:Bid[];
  }