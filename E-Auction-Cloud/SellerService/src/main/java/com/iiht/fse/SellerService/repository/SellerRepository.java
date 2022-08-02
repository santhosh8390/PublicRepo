package com.iiht.fse.SellerService.repository;

import com.iiht.fse.SellerService.model.SellerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends MongoRepository<SellerInfo, String> {

    SellerInfo findSellerByProductsId(String id);


}
