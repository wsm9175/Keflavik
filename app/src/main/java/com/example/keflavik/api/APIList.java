package com.example.keflavik.api;

import com.example.keflavik.TechDTO;
import com.example.keflavik.datas.AddAdressData;
import com.example.keflavik.datas.LoginData;
import com.example.keflavik.datas.UserData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIList {

    //회원가입
    //@FormUrlEncoded
  /*  @POST("/rest/v1/auth/construct/do")
    Call<User> putRequestSignUp(
            @Field("id")String id,
            @Field("pw")String pw
    );*/
    //로그인
    @POST("/rest/v1/auth/construct/do")
    Call<LoginData> putRequestSignUp(@Body JsonObject object);

    //로그인
    @POST("/rest/v1/auth/construct/do")
    Call<String> putRequestSignUp2(@Body String phoneNumber, String pw);

    //휴대폰 중복검사
    @POST("/rest/v1/auth/construct/duplicate-check")
    Call<String> getPhoneDupCheck(@Body JsonObject object);

    //휴대폰 본인인증 인증번호
    @GET("/rest/v1/auth/construct/send-sms")
    Call<String> certificationCheck(@Query("phoneNumber") String phoneNumber);

    //기업 회원가입
    @Multipart
    @POST("/rest/v1/auth/construct/registration-constructor")
    Call<UserData> businessSignUp(
            @Part("file") File file,
            //@Par            @Part("constructorDTO") JsonObject object,t("jsonArray") JsonArray jsonArray
            @Part("jsonArray") String jsonArray
            );


    //개인 회원가입
    @Multipart
    @POST("/rest/v1/auth/construct/registration-user")
    Call<UserData> individualSignUp(
            @Part("user") UserData user,
            @Part("jsonArray")RequestBody requestBody
            );


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //작업관리 Add 부분들

    //도시 버튼들꺼 가져오는 용도
    @GET("/rest/v1/create/construct/get/work-area")
    Call<ArrayList<AddAdressData>> getWorkCityList(@Query("") String token);

    //모든 도시 리스트 가져오기기
    @GET("/rest/v1/create/construct/get/all-area")
    Call<ArrayList<AddAdressData>> getWorkCityList();
   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

}
