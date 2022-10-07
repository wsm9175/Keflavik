package com.example.keflavik.datas;

public class AddAdressData {
    long id; //id
    SiggAreas siggAreas;  // 하위 주소들 클래스

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SiggAreas getSiggAreas() {
        return siggAreas;
    }

    public void setSiggAreas(SiggAreas siggAreas) {
        this.siggAreas = siggAreas;
    }
}
