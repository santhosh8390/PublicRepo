export interface Bid {
    id?: string;
    name?: string;
    shortDesc?: string;
    detailedDesc?: string;
    category?: Category;
    startingPrice?: number;     
    bidEndDate?: Date;
    bidAmount?: number;
    buyerName?: string;
    buyerEmail?: string;
    buyerMobile?: number;
}

export enum Category {
    PAINTING = 'PAINTING',
    SCULPTOR = 'SCULPTOR',
    ORNAMENT = 'ORNAMENT',
    Delivered = 'delivered'
  }