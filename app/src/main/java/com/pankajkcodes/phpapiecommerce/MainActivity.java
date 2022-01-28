package com.pankajkcodes.phpapiecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText logInEmail, logInPassword;
    Button logInSubmit;
    TextView tv, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        logInEmail = (EditText) findViewById(R.id.log_in_email);
        logInPassword = (EditText) findViewById(R.id.log_in_password);
        logInSubmit = (Button) findViewById(R.id.log_in_submit);

        verifyUserExistences();


        logInSubmit.setOnClickListener(v -> {
            processLogIn(logInEmail.getText().toString(), logInPassword.getText().toString());
        });


        tv = (TextView) findViewById(R.id.login_tv);
        report = (TextView) findViewById(R.id.log_in_reportr);
        tv.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
    }


    private void processLogIn(String email, String password) {

        Call<SignInResponseModel> call = ApiController.getInstance()
                .getApi()
                .getSignIn(email, password);
        call.enqueue(new Callback<SignInResponseModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                SignInResponseModel obj = response.body();
                assert obj != null;
                String result = obj.getMessage().trim();
                if (result.equals("exists")) {

                    SharedPreferences shrd = getSharedPreferences("eCOM_CREDENTIALS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
                if (result.equals("not exists")) {
                    report.setText("Invalid User");
                    logInEmail.setText("");
                    logInPassword.setText("");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignInResponseModel> call, @NonNull Throwable t) {
                report.setText(t.getMessage());

            }
        });
    }


    private void verifyUserExistences() {
        SharedPreferences shrd = getSharedPreferences("eCOM_CREDENTIALS", MODE_PRIVATE);
        if (shrd.contains("email")) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();

        }
    }
}

