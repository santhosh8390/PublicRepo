package com.iiht.fse.SellerService.repository;

import com.iiht.fse.SellerService.model.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends MongoRepository<Bid, String> {

    List<Bid> findByProductId(final String productId);

}
