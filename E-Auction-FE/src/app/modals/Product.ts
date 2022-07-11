export interface Product {
    id?: string;
    name?: string;
    shortDesc?: string;
    detailedDesc?: string;
    category?: Category;
    startingPrice?: number;     
    bidEndDate?: Date;
}

export enum Category {
    PAINTING = 'PAINTING',
    SCULPTOR = 'SCULPTOR',
    ORNAMENT = 'ORNAMENT',
    Delivered = 'delivered'
  }