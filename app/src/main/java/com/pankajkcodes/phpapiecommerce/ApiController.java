package com.pankajkcodes.phpapiecommerce;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    static final String url="http://192.168.43.180/ecommapi/";
    private static ApiController clientObject;
    private static Retrofit retrofit;

       ApiController() {
           retrofit=new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
       }

       public static synchronized ApiController getInstance() {
             if(clientObject ==null)
                  clientObject =new ApiController();
             return clientObject;
       }

       // API CALL
       ApiInterface getApi() {
           return retrofit.create(ApiInterface.class);
       }
}
