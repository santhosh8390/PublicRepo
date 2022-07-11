import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './../src/app/pages/header/header.component';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from '../src/app/pages/home/home.component';
import { SELLER_RESPONSE } from './return-data';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting
} from '@angular/platform-browser-dynamic/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SellerService } from 'src/app/services/seller.service';
import { BrowserModule } from '@angular/platform-browser';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let sellerService: SellerService; 

  beforeEach(async(() => {
    TestBed.resetTestEnvironment();
    TestBed.initTestEnvironment(BrowserDynamicTestingModule,
      platformBrowserDynamicTesting());
    TestBed.configureTestingModule({
        declarations: [HeaderComponent],
        schemas: [
          CUSTOM_ELEMENTS_SCHEMA
        ],
        imports: [
          CommonModule,
          BrowserAnimationsModule,
          ReactiveFormsModule,
          BrowserModule,
          RouterTestingModule.withRoutes([
            {
              path: '',
              component: HeaderComponent,
            }
          ]),
          HttpClientModule,
        ],
        providers: [SellerService]
    })
    .compileComponents();
    sellerService = TestBed.get(SellerService);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have changeProduct function', () => {
    // Testcase to check function existence
    expect(component.changeProduct).toBeTruthy();
  });

  it('should have fetchDetails function', () => {
    // Testcase to check function existence
    expect(component.fetchDetails).toBeTruthy();
  });

  it('fetch details produced result', () => {
    spyOn(sellerService, 'getBidDetails').and.callFake(function () {
      return of(SELLER_RESPONSE);
    });
    component.fetchDetails();
    expect(component.productBids).not.toBeNull();
    expect(component.productBids).toEqual(SELLER_RESPONSE.productBids);
  });

});
