import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SellerResponse } from '../modals/SellerResponse';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ErrorHandler } from '../error-handler';

@Injectable({
  providedIn: 'root',
})
// Use 'API_URL' from environment.ts
export class SellerService {
  constructor(private http: HttpClient, private errorHandler: ErrorHandler) {}

  // Function to get all products available with JWT authentication token
  // and error handling using erro-handler.ts
  getAllProducts(): Observable<SellerResponse> {
    const headers = {'content-type': 'application/json'}
    return this.http.get<SellerResponse>(environment.API_URL+ 'products', { 'headers': headers })
    .pipe(catchError(this.errorHandler.handleError));
  }

  getBidDetails(productId: string): Observable<SellerResponse> {
    return this.http.get<SellerResponse>(environment.API_URL+ 'show-bids/' + productId)
    .pipe(catchError(this.errorHandler.handleError));
  }
}
