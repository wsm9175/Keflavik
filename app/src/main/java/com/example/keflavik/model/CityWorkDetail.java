package com.example.keflavik.model;

public class CityWorkDetail {
    String adressTitle;
    String adressSubTitle;

    public CityWorkDetail(String adressTitle, String adressSubTitle) {
        this.adressTitle = adressTitle;
        this.adressSubTitle = adressSubTitle;
    }

    public String getAdressTitle() {
        return adressTitle;
    }

    public void setAdressTitle(String adressTitle) {
        this.adressTitle = adressTitle;
    }

    public String getAdressSubTitle() {
        return adressSubTitle;
    }

    public void setAdressSubTitle(String adressSubTitle) {
        this.adressSubTitle = adressSubTitle;
    }
}
