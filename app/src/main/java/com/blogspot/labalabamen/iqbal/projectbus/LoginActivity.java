package com.blogspot.labalabamen.iqbal.projectbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.labalabamen.iqbal.projectbus.model.Login;
import com.blogspot.labalabamen.iqbal.projectbus.model.User;
import com.blogspot.labalabamen.iqbal.projectbus.networkService.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private TextView email;
    private TextView pass;

    private String token ;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BuildConfig.LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.email_sign_in_button);
        btnLogin.setOnClickListener(this);

        mEditEmail = findViewById(R.id.email);
        mEditPassword = findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        login();

    }



    private void login(){
        String email =  mEditEmail.getText().toString();
        String pass = mEditPassword.getText().toString();
        Login login = new Login(email,pass);
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Login No correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Login No correct :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
