package com.iiht.fse.SellerService.controller.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.fse.SellerService.exception.SellerBusinessException;
import com.iiht.fse.SellerService.model.*;
import com.iiht.fse.SellerService.proxy.BuyerServiceProxy;
import com.iiht.fse.SellerService.repository.BidRepository;
import com.iiht.fse.SellerService.repository.SellerRepository;
import com.iiht.fse.SellerService.service.impl.SellerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class SellerServiceImplTest {

    @InjectMocks
    private SellerServiceImpl sellerServiceImpl;

    @Mock
    private SellerRepository sellerRepository;

    private SellerResponse sellerResponse;

    private SellerRequest sellerRequest;

    @Mock
    private BuyerServiceProxy buyerServiceProxy;

    @Mock
    private MessageSource messageSource;

    @Mock
    private BidRepository bidRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

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
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerRepository.save(sellerRequest.getSellerInfo())).thenReturn(sellerRequest.getSellerInfo());
        SellerResponse sellerResponse = sellerServiceImpl.addNewProduct(sellerRequest);

        Assertions.assertTrue(sellerResponse != null);
        Assertions.assertTrue(sellerResponse.getSellerInfo() != null);
        Assertions.assertEquals(sellerResponse.getSellerInfo(), sellerRequest.getSellerInfo());
    }

    @Test
    public void testAddProductWithInvalidFirstName() {
        sellerRequest = buildSellerRequest();
        SellerInfo sellerInfo = sellerRequest.getSellerInfo();
        sellerInfo.setFirstName("IV");
        sellerRequest.setSellerInfo(sellerInfo);
        Set<ConstraintViolation<SellerInfo>> violations = validator.validate(sellerInfo);

        Assertions.assertEquals(violations.size(), 1);
    }

    @Test
    public void testDeleteProduct() {
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerRepository.save(sellerRequest.getSellerInfo())).thenReturn(sellerRequest.getSellerInfo());
        BuyerResponse response = new BuyerResponse();
        response.setBuyerInfo(new ArrayList<>());
        Mockito.when(buyerServiceProxy.fetchBidDetails(Mockito.anyString())).thenReturn( new ResponseEntity<>(response, HttpStatus.OK));
        Mockito.when(sellerRepository.findSellerByProductsId(Mockito.anyString())).thenReturn(sellerRequest.getSellerInfo());
        SellerResponse sellerResponse = sellerServiceImpl.deleteProduct("34534sdfgdfg");
        Assertions.assertTrue(sellerResponse != null);
        Assertions.assertTrue(sellerResponse.getSellerInfo() != null);
        Assertions.assertEquals(sellerResponse.getSellerInfo(), sellerRequest.getSellerInfo());
    }

    @Test
    public void testDeleteProductForInvalidProductId() {
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerRepository.save(sellerRequest.getSellerInfo())).thenReturn(sellerRequest.getSellerInfo());
        BuyerResponse response = new BuyerResponse();
        response.setBuyerInfo(new ArrayList<>());
        Mockito.when(buyerServiceProxy.fetchBidDetails(Mockito.anyString())).thenReturn( new ResponseEntity<>(response, HttpStatus.OK));
        Mockito.when(sellerRepository.findSellerByProductsId(Mockito.anyString())).thenReturn(sellerRequest.getSellerInfo());
        Assertions.assertThrows(SellerBusinessException.class, () -> {
            sellerServiceImpl.deleteProduct(null);
        });
    }

    @Test
    public void testFetchBids() {
        sellerRequest = buildSellerRequest();
        Mockito.when(sellerRepository.findSellerByProductsId(Mockito.anyString())).thenReturn(sellerRequest.getSellerInfo());
        ProductBids productBids = new ProductBids();
        Bid buyerInfo = new Bid();
        buyerInfo.setBidAmount(BigDecimal.valueOf(3243L));
        Mockito.when(bidRepository.findByProductId(Mockito.anyString())).thenReturn(Arrays.asList(buyerInfo));
        SellerResponse sellerResponse = sellerServiceImpl.fetchBids("sdfsdfsdf");

        Assertions.assertTrue(sellerResponse != null);
        Assertions.assertTrue(sellerResponse.getProductBids() != null);
        Assertions.assertEquals(sellerResponse.getProductBids().size() , 1);
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
        sellerInfo.setPhone("(0123456789)");
        sellerInfo.setState("state");
        Product product = new Product();
        product.setId("khjdfjzgdfhjkgkjghjhjvhjgjhf");
        product.setCategory(Category.Ornament);
        product.setBidEndDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
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
