import { BuyerDetails } from './BuyerDetails';

export interface ProductDetails {
    productName?: string;
    shortDesc?: string;
    detailedDesc?: string;
    category?: Category;
    startingPrice?: number;     
    bidEndDate?: Date;
    buyerDetails?: BuyerDetails[];
}

export enum Category {
    PAINTING = 'PAINTING',
    SCULPTOR = 'SCULPTOR',
    ORNAMENT = 'ORNAMENT',
    Delivered = 'delivered'
  }
  