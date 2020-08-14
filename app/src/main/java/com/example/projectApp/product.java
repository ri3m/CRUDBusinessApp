package com.example.projectApp;

import java.util.ArrayList;

public class product {

    String productName, prodcutDes;
    int productPrice;

    ArrayList<Integer> productIDAL;
    ArrayList<String> productNameAL;
    ArrayList<String> prodcutDesAL;
    ArrayList<String> productPriceAL;

    public ArrayList<Integer> getProductIDAL() {
        return productIDAL;
    }

    public void setProductIDAL(ArrayList<Integer> productIDAL) {
        this.productIDAL = productIDAL;
    }


    public ArrayList<String> getProductNameAL() {
        return productNameAL;
    }

    public void setProductNameAL(ArrayList<String> productNameAL) {
        this.productNameAL = productNameAL;
    }

    public ArrayList<String> getProdcutDesAL() {
        return prodcutDesAL;
    }

    public void setProdcutDesAL(ArrayList<String> prodcutDesAL) {
        this.prodcutDesAL = prodcutDesAL;
    }

    public ArrayList<String> getProductPriceAL() {
        return productPriceAL;
    }

    public void setProductPriceAL(ArrayList<String> productPriceAL) {
        this.productPriceAL = productPriceAL;
    }

    public product(String productName, String prodcutDes, int productPrice) {
        this.productName = productName;
        this.prodcutDes = prodcutDes;
        this.productPrice = productPrice;
    }
    public product() {
        this.productName = productName;
        this.prodcutDes = prodcutDes;
        this.productPrice = productPrice;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProdcutDes() {
        return prodcutDes;
    }

    public void setProdcutDes(String prodcutDes) {
        this.prodcutDes = prodcutDes;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }




}
