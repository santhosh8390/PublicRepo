package com.iiht.fse.BuyerService.service.impl;

import com.iiht.fse.BuyerService.exception.BuyerBusinessException;
import com.iiht.fse.BuyerService.exception.BuyerSystemException;
import com.iiht.fse.BuyerService.model.*;
import com.iiht.fse.BuyerService.proxy.SellerServiceProxy;
import com.iiht.fse.BuyerService.repository.BuyerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BuyerServiceImplTest {

    @InjectMocks
    private BuyerServiceImpl buyerServiceImpl;

    @Mock
    private BuyerRepository buyerRepository;

    private BuyerResponse buyerResponse;

    private BuyerRequest buyerRequest;

    @Mock
    private SellerServiceProxy sellerServiceProxy;

    @Mock
    private MessageSource messageSource;

    @Mock
    private EventProducer eventProducer;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private KafkaTemplate kafkaTemplate;

    @Mock
    private SellerResponse sellerResponse;

    @Mock
    private SellerInfo sellerInfo;

    @Mock
    private List<Product> productList;

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
        buyerResponse = null;
        buyerRequest = null;
    }

    @Test
    public void testPlaceBid(){
        BuyerRequest buyerRequest = buildBuyerRequest();
        SellerResponse sellerResponse = buildSellerResponse();
        SellerInfo sellerInfo = sellerResponse.getSellerInfo();
        Product product = sellerResponse.getSellerInfo().getProducts().stream().findFirst().get();
        product.setBidEndDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        sellerInfo.setProducts(Arrays.asList(product));
        sellerResponse.setSellerInfo(sellerInfo);
        Mockito.when(sellerServiceProxy.fetchProductDetails(Mockito.anyString())).thenReturn(new ResponseEntity<>(sellerResponse, HttpStatus.OK));
        BuyerResponse buyerResponse = buyerServiceImpl.placeBid(buyerRequest);
        Assertions.assertTrue(buyerResponse != null);

        Assertions.assertEquals(buyerResponse.getStatusCode(), "200 OK");
        Assertions.assertEquals(buyerResponse.getErrors(), null);
    }

    public void testPlaceBidForInvalidBidEndDate() {
        Product p = new Product();
        productList.add(p);
        BuyerRequest buyerRequest = buildBuyerRequest();
        Mockito.when(sellerServiceProxy.fetchProductDetails(Mockito.anyString())).thenReturn(new ResponseEntity<>(sellerResponse, HttpStatus.OK));
        Mockito.when(sellerResponse.getSellerInfo()).thenReturn(sellerInfo);
        Mockito.when(sellerInfo.getProducts()).thenReturn(productList);
        Mockito.when(productList.stream().findFirst().get()).thenReturn(p);
        Mockito.when(productList.stream().findFirst().get().getBidEndDate().before(new Date())).thenReturn(Boolean.TRUE);
        BuyerResponse buyerResponse = buyerServiceImpl.placeBid(buyerRequest);
        Assertions.assertTrue(buyerResponse != null);
        Assertions.assertEquals(buyerResponse.getStatusCode(), "400 BAD_REQUEST");
    }

    @Test
    public void testUpdateBid(){
        SellerResponse sellerResponse = buildSellerResponse();
        SellerInfo sellerInfo = sellerResponse.getSellerInfo();
        Product product = sellerResponse.getSellerInfo().getProducts().stream().findFirst().get();
        product.setBidEndDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        sellerInfo.setProducts(Arrays.asList(product));
        sellerResponse.setSellerInfo(sellerInfo);
        Mockito.when(buyerRepository.findBuyerByProductIdAndEmail(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(getBuyerInfo()));
        Mockito.when(sellerServiceProxy.fetchProductDetails(Mockito.anyString())).thenReturn(new ResponseEntity<>(sellerResponse, HttpStatus.OK));
        BuyerResponse buyerResponse = buyerServiceImpl.updateBid("sdfsdf","sa@gmail.com", BigDecimal.valueOf(2344L));
        Assertions.assertTrue(buyerResponse != null);

        Assertions.assertEquals(buyerResponse.getStatusCode(), "200 OK");
        Assertions.assertEquals(buyerResponse.getErrors(), null);
    }

    @Test
    public void testUpdateBidForInvalidProductId(){
        SellerResponse sellerResponse = buildSellerResponse();
        SellerInfo sellerInfo = sellerResponse.getSellerInfo();
        Product product = sellerResponse.getSellerInfo().getProducts().stream().findFirst().get();
        product.setBidEndDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        sellerInfo.setProducts(Arrays.asList(product));
        sellerResponse.setSellerInfo(sellerInfo);
        Mockito.when(buyerRepository.findBuyerByProductIdAndEmail(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(getBuyerInfo()));
        Mockito.when(sellerServiceProxy.fetchProductDetails(Mockito.anyString())).thenReturn(new ResponseEntity<>(sellerResponse, HttpStatus.OK));
        Assertions.assertThrows(BuyerSystemException.class, () -> {
            buyerServiceImpl.updateBid(null,"sa@gmail.com", BigDecimal.valueOf(2344L));
        });
    }


    private BuyerResponse buildBuyerResponse() {
        BuyerResponse buyerResponse = new BuyerResponse();
        buyerResponse.setBuyerInfo(Arrays.asList(getBuyerInfo()));
        buyerResponse.setStatusCode("200");
        buyerResponse.setStatusMessage("200 OK");
        return buyerResponse;
    }

    private BuyerRequest buildBuyerRequest() {
        BuyerRequest buyerRequest = new BuyerRequest();
        BuyerInfo buyerInfo = getBuyerInfo();
        buyerRequest.setBuyerInfo(buyerInfo);
        return buyerRequest;
    }

    private BuyerInfo getBuyerInfo() {
        BuyerInfo buyerInfo = new BuyerInfo();
        buyerInfo.setBidAmount(BigDecimal.valueOf(34234L));
        buyerInfo.setAddress("address");
        buyerInfo.setCity("city");
        buyerInfo.setEmail("sa@gmail.com");
        buyerInfo.setFirstName("firstname");
        buyerInfo.setId("dfsgfdfgdfg");
        buyerInfo.setLastName("lastname");
        buyerInfo.setPhone("(1233453455)");
        buyerInfo.setPin("435664");
        buyerInfo.setProductId("dfgdfgdhfgh");
        buyerInfo.setState("fsdfsdf");
        return buyerInfo;
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
