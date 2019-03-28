package com.infobite.niramayahospital.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.ui.activity.HomeActivity;
import com.infobite.niramayahospital.utils.BaseActivity;

import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    private EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    private void initViews() {

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        ((Button)findViewById(R.id.loginBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = etUserName.getText().toString().trim();
                String userPassword = etPassword.getText().toString().trim();

                if (userName.isEmpty()){
                    showToast(mContext, "Username should not be empty");
                }else if (userPassword.isEmpty()){
                    showToast(mContext, "Password should not be empty");
                }else {
                    doLogin(userName, userPassword);
                }
            }
        });
    }

    private void doLogin(String userName, String userPassword) {
        if (cd.isNetworkAvailable()) {
            /*RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.login(userName, userPassword), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {

                }

                @Override
                public void onResponseFailed(String error) {

                }
            });*/
            startActivity(new Intent(mContext, BypassActivity.class));
        }
    }
}
