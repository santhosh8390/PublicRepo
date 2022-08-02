package com.iiht.fse.SellerService.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductBids {

    private String productId;

    private String name;

    private String shortDesc;

    private String detailedDesc;

    private Category category;

    private BigDecimal startingPrice;

    private Date bidEndDate;

    private BigDecimal bidAmount;

    private String buyerName;

    private String buyerEmail;

    private String buyerMobile;

    public String getProductId() {
        return productId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getDetailedDesc() {
        return detailedDesc;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public Date getBidEndDate() {
        return bidEndDate;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public static final class ProductBidsMapper {

        private String productId;

        private String name;

        private String shortDesc;

        private String detailedDesc;

        private Category category;

        private BigDecimal startingPrice;

        private Date bidEndDate;

        private BigDecimal bidAmount;

        private String buyerName;

        private String buyerEmail;

        private String buyerMobile;

        private ProductBidsMapper() {

        }

        public static ProductBidsMapper productBidsMapper() {
            return new ProductBidsMapper();
        }

        public ProductBidsMapper setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public ProductBidsMapper setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBidsMapper setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
            return this;
        }

        public ProductBidsMapper setDetailedDesc(String detailedDesc) {
            this.detailedDesc = detailedDesc;
            return this;
        }

        public ProductBidsMapper setCategory(Category category) {
            this.category = category;
            return this;
        }

        public ProductBidsMapper setStartingPrice(BigDecimal startingPrice) {
            this.startingPrice = startingPrice;
            return this;
        }

        public ProductBidsMapper setBidEndDate(Date bidEndDate) {
            this.bidEndDate = bidEndDate;
            return this;
        }

        public ProductBidsMapper setBidAmount(BigDecimal bidAmount) {
            this.bidAmount = bidAmount;
            return this;
        }

        public ProductBidsMapper setBuyerName(String buyerName) {
            this.buyerName = buyerName;
            return this;
        }

        public ProductBidsMapper setBuyerEmail(String buyerEmail) {
            this.buyerEmail = buyerEmail;
            return this;
        }

        public ProductBidsMapper setBuyerMobile(String buyerMobile) {
            this.buyerMobile = buyerMobile;
            return this;
        }

        public ProductBids build() {
            ProductBids productBids = new ProductBids();
            productBids.productId = this.productId;
            productBids.name = this.name;
            productBids.shortDesc = this.shortDesc;
            productBids.detailedDesc = this.detailedDesc;
            productBids.category = this.category;
            productBids.startingPrice = this.startingPrice;
            productBids.bidEndDate = this.bidEndDate;
            productBids.bidAmount = this.bidAmount;
            productBids.buyerEmail = this.buyerEmail;
            productBids.buyerMobile = this.buyerMobile;
            productBids.buyerName = this.buyerName;
            return productBids;
        }

    }
}
