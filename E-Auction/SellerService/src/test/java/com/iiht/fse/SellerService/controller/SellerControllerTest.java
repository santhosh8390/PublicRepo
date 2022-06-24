package com.iiht.fse.SellerService.controller;

import com.iiht.fse.SellerService.model.*;
import com.iiht.fse.SellerService.service.SellerService;

import org.junit.jupiter.api.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class SellerControllerTest {

    @InjectMocks
    private SellerController sellerController;

    @Mock
    private SellerService sellerService;

    private SellerResponse sellerResponse;

    private SellerRequest sellerRequest;

    /**
     * Sets the up.
     *
     */
    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown()
    {
        sellerResponse = null;
        sellerRequest = null;
    }

    @Test
    public void testAddProduct() {
        sellerResponse = buildSellerResponse();
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerService.addNewProduct(Mockito.any())).thenReturn(sellerResponse);
        ResponseEntity<SellerResponse> sellerResponse = sellerController.addProduct(buildSellerRequest());

        Assertions.assertTrue(sellerResponse != null);

        SellerResponse response = sellerResponse.getBody();
        Assertions.assertTrue(response.getSellerInfo() != null);
    }

    @Test
    public void testFetchBids() {
        sellerResponse = buildSellerResponse();
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerService.fetchBids(Mockito.any())).thenReturn(sellerResponse);
        ResponseEntity<SellerResponse> sellerResponse = sellerController.fetchBids("2345fgddfghdjfgh453jkh");

        Assertions.assertTrue(sellerResponse != null);

        SellerResponse response = sellerResponse.getBody();
        Assertions.assertTrue(response.getSellerInfo() != null);
    }

    @Test
    public void testDeleteProduct() {
        sellerResponse = buildSellerResponse();
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerService.deleteProduct(Mockito.any())).thenReturn(sellerResponse);
        ResponseEntity<SellerResponse> sellerResponse = sellerController.deleteProduct("2345fgddfghdjfgh453jkh");

        Assertions.assertTrue(sellerResponse != null);

        SellerResponse response = sellerResponse.getBody();
        Assertions.assertTrue(response.getSellerInfo() != null);
    }

    private SellerRequest buildSellerRequest() {
        SellerRequest sellerRequest = new SellerRequest();
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setAddress("address");
        sellerInfo.setCity("city");
        sellerInfo.setEmail("email@gmail.com");
        sellerInfo.setFirstName("firstName");
        sellerInfo.setLastName("lastName");
        sellerInfo.setId("wersfgdfghdfhgsdgfdfdghdgf");
        sellerInfo.setPhone("0123456789");
        sellerInfo.setState("state");
        Product product = new Product();
        product.setId("khjdfjzgdfhjkgkjghjhjvhjgjhf");
        product.setCategory(Category.Ornament);
        product.setBidEndDate(new Date());
        product.setName("name");
        product.setDetailedDesc("setDetailedDesc");
        product.setShortDesc("sellerResponse");
        product.setStartingPrice(new BigDecimal("100"));
        sellerInfo.setProducts(Arrays.asList(product));
        sellerRequest.setSellerInfo(sellerInfo);
        return sellerRequest;
    }

    private SellerResponse buildSellerResponse() {
        SellerResponse sellerResponse = new SellerResponse();
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setAddress("address");
        sellerInfo.setCity("city");
        sellerInfo.setEmail("email@gmail.com");
        sellerInfo.setFirstName("firstName");
        sellerInfo.setLastName("lastName");
        sellerInfo.setId("wersfgdfghdfhgsdgfdfdghdgf");
        sellerInfo.setPhone("0123456789");
        sellerInfo.setState("state");
        Product product = new Product();
        product.setId("khjdfjzgdfhjkgkjghjhjvhjgjhf");
        product.setCategory(Category.Ornament);
        product.setBidEndDate(new Date());
        product.setName("name");
        product.setDetailedDesc("setDetailedDesc");
        product.setShortDesc("sellerResponse");
        product.setStartingPrice(new BigDecimal("100"));
        sellerInfo.setProducts(Arrays.asList(product));

        sellerResponse.setSellerInfo(sellerInfo);

        return sellerResponse;
    }

}
