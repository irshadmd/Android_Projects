package com.example.irshad.vistaproduct;

public class DataModel {

    String text1, text2, text3, text4;
    int imgId;

    public DataModel(String text1, String text2, String text3, String text4, int imgId) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.imgId = imgId;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public int getImgId() {
        return imgId;
    }

}