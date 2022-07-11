import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BrowserDynamicTestingModule,
  platformBrowserDynamicTesting } from '@angular/platform-browser-dynamic/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SellerService } from 'src/app/services/seller.service';

describe('SellerService', () => {
  let httpMock: HttpTestingController;
  let service: SellerService;

  beforeEach(() => {
    TestBed.resetTestEnvironment();
    TestBed.initTestEnvironment(BrowserDynamicTestingModule,
       platformBrowserDynamicTesting());
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SellerService],
      schemas: [
        CUSTOM_ELEMENTS_SCHEMA
      ],
    });
    httpMock = TestBed.get(HttpTestingController);
    service = TestBed.get(SellerService);
  });

  afterEach(() => httpMock.verify());

  afterAll(() => {
    TestBed.resetTestingModule();
  });

  it('should create', () => {
    expect(service).toBeTruthy();
  });

  it('should have getAllProducts function', () => {
    // Testcase to check function existence
    expect(service.getAllProducts).toBeTruthy();
  });

  it('should have getProductByCategory function', () => {
    // Testcase to check function existence
    expect(service.getBidDetails).toBeTruthy();
  });

  it('getProductByCategory function should get all products',async () => {
    service.getAllProducts().subscribe(prods => {
      expect(prods).toBeTruthy();
      expect(prods).not.toBeNull();
    });
    const request = httpMock.expectOne(req => req.url.includes('products'));
    expect(request.request.method).toBe('GET');
    expect(request.request.headers.get('content-type')).toEqual('application/json');
    request.flush({});
  });

  it('getBidDetails function should get bid details', () => {
    service.getBidDetails('2343rgdfjhhsdgdfggzd34').subscribe(bids => {
      expect(bids).toBeTruthy();
      expect(bids).not.toBeNull();
    });
    const request = httpMock.expectOne(req => req.url.includes('show-bids/'));
    expect(request.request.method).toBe('GET');
    expect(request.request.headers.get('content-type')).toEqual('application/json');
    request.flush({});
  });
});
