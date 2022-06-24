package com.iiht.fse.BuyerService.controller;

import com.iiht.fse.BuyerService.model.BuyerInfo;
import com.iiht.fse.BuyerService.model.BuyerRequest;
import com.iiht.fse.BuyerService.model.BuyerResponse;
import com.iiht.fse.BuyerService.service.BuyerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;

public class BuyerControllerTest {

    @InjectMocks
    private BuyerController buyerController;

    @Mock
    private BuyerService buyerService;

    private BuyerResponse buyerResponse;

    private BuyerRequest buyerRequest;

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
        buyerResponse = null;
        buyerRequest = null;
    }

    @Test
    public void testPlaceBid() {
        Mockito.when(buyerService.placeBid(Mockito.any())).thenReturn(buildBuyerResponse());
        ResponseEntity<BuyerResponse> buyerResponse =  buyerController.placeBid(buildBuyerRequest());

        Assertions.assertTrue(buyerResponse != null);

        BuyerResponse response = buyerResponse.getBody();
        Assertions.assertTrue(response.getBuyerInfo() != null);
    }

    @Test
    public void testUpdateBid() {
        Mockito.when(buyerService.updateBid(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(buildBuyerResponse());
        ResponseEntity<BuyerResponse> buyerResponse =  buyerController.updateBid("sdfdg", "sa3@gmail.com", BigDecimal.valueOf(334L));

        Assertions.assertTrue(buyerResponse != null);

        BuyerResponse response = buyerResponse.getBody();
        Assertions.assertTrue(response.getBuyerInfo() != null);
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
}
