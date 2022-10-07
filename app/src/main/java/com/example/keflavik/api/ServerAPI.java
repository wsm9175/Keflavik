package com.example.keflavik.api;

import android.content.Context;
import android.util.Log;

import com.example.keflavik.utils.ContextUtil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerAPI {

    static String baseUrl = "http://192.168.0.7:9090/";
    static Retrofit retrofit = null;

    private static final String AUTHORIZATION = "Authorization";
    private static Interceptor interceptor;
    private static OkHttpClient okHttpClient;

    private static APIList apiList;


    /*public static Retrofit getRetrofit(Context context) {*/


    /* private RetrofitService() {
             initRetrofit();
         }

         *//*if(retrofit == null){
            retrofit = Retrofit.Builder().base

        }*//*



        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(provideLoggingClient())
                    .client()
                    .build();
        }

        return retrofit;
    }
*/
    private ServerAPI() {

    }


    public static Retrofit getRetrofit(Context context) {
        interceptor = chain -> {
            //로그인시 저장된 토큰 가져오기
            String token = ContextUtil.getLoginToken(context);

            Request newRequest = chain.request().newBuilder().
                    addHeader(AUTHORIZATION, token == null ? "" : token).build();
            return chain.proceed(newRequest);
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        /*OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();*/
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    //.addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideLoggingClient())
                    .build();
        }
        //? 이건 지워도 될까?
        //apiList = retrofit.create(APIList.class);

        return retrofit;
    }

    private static OkHttpClient provideLoggingClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }
}
