import { Bid } from 'src/app/modals/Bid';
import { Product } from '../src/app/modals/Product';
import { BuyerDetails } from 'src/app/modals/BuyerDetails';
import { FetchResult } from 'src/app/modals/FetchResult';
import { ProductDetails, Category } from 'src/app/modals/ProductDetails';
import { SellerInfo } from 'src/app/modals/SellerInfo';
import { SellerResponse } from 'src/app/modals/SellerResponse';

export const PRODUCTS: string[] = [
  '2343rgdfjhhsdgd34,Brush',
  '2343rgdfjhhsdgdsfg4,Scissor',
  '2343rgdfjhhsdgdfggzd34,Earing',
  '2343rgdfjhhsdgd3dfggf4,chain',
  '2343rgdfjhhsdgd34,paint'
];

export const fetchResult: FetchResult = {
  result: true,
  products: PRODUCTS
};

export const PRODUCT: Product = {
  id: "string",
  name: "string",
  shortDesc: "string",
  detailedDesc: "string",
  category: Category.ORNAMENT,
  startingPrice: 122,
  bidEndDate: new Date(2022-7-28)
};

export const PRODUCTLIST: Product[] = [PRODUCT];

export const SELLER: SellerInfo = {
  id: "string",
  firstName: "string",
  lastName: "string",
  address: "string",
  city: "string",
  state: "string",
  phone: "string",
  email: "string",
  products: PRODUCTLIST
};

export const SELLER_INFO: SellerInfo[] = [SELLER];

export const BID: Bid = {
  id: "string",
  name: "string",
  shortDesc: "string",
  detailedDesc: "string",
  category: Category.ORNAMENT,
  startingPrice: 122,
  bidEndDate: new Date(2022-7-28),
  bidAmount: 1223,
  buyerName: 'string',
  buyerEmail: "string",
  buyerMobile: 123234234234
};

export const BIDLIST: Bid[] = [BID];

export const SELLER_RESPONSE: SellerResponse = {
  message: "200 OK",
  statusCode: "200",
  statusMessage: 'string',
  sellerInfo: SELLER_INFO,
  products:PRODUCTS,
  productBids:BIDLIST
};

export const BID_DETAILS: ProductDetails[] = [
  {
  productName: 'brush',
  shortDesc: '...enter icon URL',
  detailedDesc: 'Akira',
  category: Category.ORNAMENT,
  startingPrice: 120,
  bidEndDate: new Date(2022-7-28),
  buyerDetails: [{
      bidAmount: 150,
      buyerName: 'name',
      buyerEmail: 'sa@gmail.com',
      mobile: 9898999897
  }]
},
];