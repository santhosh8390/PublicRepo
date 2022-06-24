package com.iiht.fse.BuyerService.repository;

import com.iiht.fse.BuyerService.model.BuyerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends MongoRepository<BuyerInfo, String> {

    Optional<BuyerInfo> findBuyerByProductIdAndEmail(String id, String email);

    List<BuyerInfo> findBuyerByProductId(String productId);
}
