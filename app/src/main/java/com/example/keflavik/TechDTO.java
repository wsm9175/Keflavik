package com.example.keflavik;

import com.google.gson.annotations.SerializedName;

public class TechDTO {
    @SerializedName("name")
    private String name;

    public TechDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
