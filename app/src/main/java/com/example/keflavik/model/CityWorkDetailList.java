package com.example.keflavik.model;

import java.util.ArrayList;

public class CityWorkDetailList {
    ArrayList<CityWorkDetail> cityWorkDetail = new ArrayList<>();

    public CityWorkDetailList(ArrayList<CityWorkDetail> cityWorkDetail) {
        this.cityWorkDetail = cityWorkDetail;
    }

    public ArrayList<CityWorkDetail> getCityWorkDetail() {
        return cityWorkDetail;
    }

    public void setCityWorkDetail(ArrayList<CityWorkDetail> cityWorkDetail) {
        this.cityWorkDetail = cityWorkDetail;
    }
}
