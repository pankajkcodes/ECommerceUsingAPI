package com.pankajkcodes.phpapiecommerce;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    EditText regEmail, regMobile, regPassword;
    Button regSubmit;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        regEmail = (EditText) findViewById(R.id.reg_email);
        regMobile = (EditText) findViewById(R.id.reg_mobile);
        regPassword = (EditText) findViewById(R.id.reg_password);
        tv = (TextView) findViewById(R.id.signup_report_tv);

        regSubmit = (Button) findViewById(R.id.reg_submit);
        regSubmit.setOnClickListener(view ->
                userRegister(regEmail.getText().toString(),
                        regMobile.getText().toString(),
                        regPassword.getText().toString()));
    }


    public void userRegister(String email, String mobile, String password) {
        String name = "not applicable";
        String address = "not applicable";

        Call<SignupResponseModel> call = ApiController.getInstance()
                .getApi()
                .getRegister(name, email, password, mobile, address);

        call.enqueue(new Callback<SignupResponseModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                SignupResponseModel obj = response.body();
                assert obj != null;
                String result = obj.getMessage().trim();
                if (result.equals("inserted")) {
                    tv.setText("Successfully Registered");
                    tv.setTextColor(Color.GREEN);
                    regEmail.setText("");
                    regMobile.setText("");
                    regPassword.setText("");
                }
                if (result.equals("exist")) {
                    tv.setText("Sorry...! You are already registered");
                    tv.setTextColor(Color.RED);
                    regEmail.setText("");
                    regMobile.setText("");
                    regPassword.setText("");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<SignupResponseModel> call, Throwable t) {
                tv.setText(t.getMessage());
                tv.setTextColor(Color.RED);
                regEmail.setText("");
                regMobile.setText("");
                regPassword.setText("");
            }
        });
    }
}