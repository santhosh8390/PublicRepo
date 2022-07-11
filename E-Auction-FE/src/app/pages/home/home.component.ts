import { Component, OnInit } from '@angular/core';
import { FetchResult } from 'src/app/modals/FetchResult';
import { SellerService } from 'src/app/services/seller.service';
import { Bid } from 'src/app/modals/Bid';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public fetchResult: FetchResult;

  public productBids: Bid[];

  public productId: string;

  constructor(private sellerService: SellerService) {
    
   }

  ngOnInit() {
    
  }

  changeProduct(e) {
    console.log(e.target.value);
    this.productId = e.target.value;
  }

  fetchDetails(){
    console.log('input productId---> ', this.productId);
    this.sellerService.getBidDetails(this.productId).subscribe((data) => {
      this.productBids = data.productBids;
    });
  }

}
