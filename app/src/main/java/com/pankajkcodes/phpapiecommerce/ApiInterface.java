package com.pankajkcodes.phpapiecommerce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("signup.php")
    Call<SignupResponseModel> getRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("signin.php")
    Call<SignInResponseModel> getSignIn(
            @Field("email") String email,
            @Field("password") String password

    );

}
