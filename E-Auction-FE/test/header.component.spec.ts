import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from '../src/app/pages/header/header.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';
import { of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting
} from '@angular/platform-browser-dynamic/testing';
import { CUSTOM_ELEMENTS_SCHEMA, EventEmitter } from '@angular/core';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { SellerService } from 'src/app/services/seller.service';
import { fetchResult } from './return-data';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let sellerService: SellerService;
  let fixture: ComponentFixture<HeaderComponent>;

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
        FormsModule,
        RouterTestingModule.withRoutes([
          {
            path: '',
            component: HeaderComponent,
          }
        ]),
        HttpClientModule,
        NgbModalModule,
      ],
      providers: [SellerService]
    })
      .compileComponents();
    sellerService = TestBed.get(SellerService);
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.ngOnInit();
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have fetchDetails function', () => {
    // Testcase to check function existence
    expect(component.fetchDetails).toBeTruthy();
  });

  it('check event emitted properly', () => {
    spyOn(sellerService, 'getAllProducts').and.callFake(function () {
      return of(fetchResult);
    });
    spyOn(component.childEvent, 'emit');
    component.fetchDetails(); // call the onClick method directly
    expect(component.childEvent.emit).toHaveBeenCalledWith(fetchResult);
  });

  it('should call get all products service function', () => {
    spyOn(sellerService, 'getAllProducts').and.callThrough();
    fixture.detectChanges();
    expect(component.fetchDetails).toBeTruthy();
  });
});
