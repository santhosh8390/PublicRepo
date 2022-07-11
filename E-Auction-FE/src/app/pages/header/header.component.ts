import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { SellerService } from 'src/app/services/seller.service';
import { FetchResult } from 'src/app/modals/FetchResult';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
// Class to display header
export class HeaderComponent implements OnInit {

  @Output() public childEvent = new EventEmitter<FetchResult>();
 
  constructor(private sellerService: SellerService
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
  }

  fetchDetails(){
    console.log('fetchDetails called-->');
    this.sellerService.getAllProducts().subscribe((data) => {
        console.log("RESULT--> ", data.products);
        let fetchParamters:FetchResult={result : true, products : data.products};
        this.childEvent.emit(fetchParamters);
    });
  }
}
