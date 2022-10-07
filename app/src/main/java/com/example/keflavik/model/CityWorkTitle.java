package com.example.keflavik.model;

import java.util.ArrayList;

public class CityWorkTitle {
    //아파트 주소
    //개수
    //상세 주소목록들을 저장해서 사용
    String adressTitle;
    int count;
    ArrayList<CityWorkDetail> cityWorkDetailList = new ArrayList<>();

    public CityWorkTitle(String adressTitle, int count, ArrayList<CityWorkDetail> cityWorkDetailList) {
        this.adressTitle = adressTitle;
        this.count = count;
        this.cityWorkDetailList = cityWorkDetailList;
    }

    public String getAdressTitle() {
        return adressTitle;
    }

    public void setAdressTitle(String adressTitle) {
        this.adressTitle = adressTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<CityWorkDetail> getCityWorkDetailList() {
        return cityWorkDetailList;
    }

    public void setCityWorkDetailList(ArrayList<CityWorkDetail> cityWorkDetailList) {
        this.cityWorkDetailList = cityWorkDetailList;
    }
}
