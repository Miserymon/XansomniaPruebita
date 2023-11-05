package com.example.xansomniapruebita;



public class Guitar {
    int id;
    String guitarModel;
    String guitarBrand;
    String guitarType;
    String guitarPrice;
    String storeLocation;

    public Guitar() {

    }

    public Guitar(String guitarModel, String guitarBrand, String guitarType, String guitarPrice, String storeLocation) {
        this.guitarModel = guitarModel;
        this.guitarBrand = guitarBrand;
        this.guitarType = guitarType;
        this.guitarPrice = guitarPrice;
        this.storeLocation = storeLocation;
    }

    public Guitar(int id, String guitarModel, String guitarBrand, String guitarType, String guitarPrice, String storeLocation) {
        this.id = id;
        this.guitarModel = guitarModel;
        this.guitarBrand = guitarBrand;
        this.guitarType = guitarType;
        this.guitarPrice = guitarPrice;
        this.storeLocation = storeLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuitarModel() {
        return guitarModel;
    }

    public void setGuitarModel(String guitarModel) {
        this.guitarModel = guitarModel;
    }

    public String getGuitarBrand() {
        return guitarBrand;
    }

    public void setGuitarBrand(String guitarBrand) {
        this.guitarBrand = guitarBrand;
    }

    public String getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(String guitarType) {
        this.guitarType = guitarType;
    }

    public String getGuitarPrice() {
        return guitarPrice;
    }

    public void setGuitarPrice(String guitarPrice) {
        this.guitarPrice = guitarPrice;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }


}

