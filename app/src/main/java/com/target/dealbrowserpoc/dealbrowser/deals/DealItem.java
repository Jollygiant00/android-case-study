package com.target.dealbrowserpoc.dealbrowser.deals;


public class DealItem {
    private int mIndex;
    public String mId;
    private String mTitle;
    private String mDescription;
    private String mOriginalPrice;
    private String mSalePrice;
    private String mImage;
    private String mAisle;

    public DealItem(int index,
                    String id,
                    String title,
                    String description,
                    String originalPrice,
                    String salePrice,
                    String image,
                    String aisle) {
        mIndex = index;
        mId = id;
        mTitle = title;
        mDescription = description;
        mOriginalPrice = originalPrice;
        mSalePrice = salePrice;
        mImage = image;
        mAisle = aisle;
    }

    public String getProductImage(){
        return mImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getOriginalPrice() {
        return mOriginalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        mOriginalPrice = originalPrice;
    }

    public String getSalePrice() {
        return  mSalePrice;
    }

    public void setSalePrice(String salePrice) {
        mSalePrice = salePrice;
    }

    public String getAisle() {
        return mAisle;
    }
}