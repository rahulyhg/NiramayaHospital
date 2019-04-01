package com.infobite.niramayahospital.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.Gson;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.user.UserData;
import com.infobite.niramayahospital.models.user.UserModel;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.ui.doctor.activity.HomeActivity;
import com.infobite.niramayahospital.ui.pathology.activity.MainActivity;
import com.infobite.niramayahospital.utils.AppPreference;
import com.infobite.niramayahospital.utils.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class SignInActivity extends BaseActivity {

    private EditText etUserName, etPassword;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
    }

    private void initViews() {

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        ((Button) findViewById(R.id.loginBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = etUserName.getText().toString().trim();
                String userPassword = etPassword.getText().toString().trim();

                if (userName.isEmpty()) {
                    showToast(mContext, "Username should not be empty");
                } else if (!validateUserId(userName)) {
                    showToast(mContext, "You entered a wrong format Username");
                } else if (userPassword.isEmpty()) {
                    showToast(mContext, "Password should not be empty");
                } else {
                    doLogin(userName, userPassword);
                }
            }
        });
    }

    private void doLogin(String userName, String userPassword) {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.login(userName, userPassword, "", "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {

                    ResponseBody response = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(response.string());
                        if (!jsonObject.getBoolean("error")) {
                            AppPreference.setBooleanPreference(mContext, Constant.IS_LOGIN, true);
                            AppPreference.setStringPreference(mContext, Constant.USER_LOGIN_DATA, jsonObject.toString());

                            Gson gson = new Gson();

                            UserModel userModel = gson.fromJson(jsonObject.toString(), UserModel.class);

                            UserModel.setUserModel(userModel);


                            switch (userModel.getUser().getUserType()) {
                                case "doctor":
                                    startActivity(new Intent(mContext, HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "pharmacy":
                                    startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "pathology":
                                    startActivity(new Intent(mContext, MainActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "receptionist":
                                    startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "account":
                                    startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "manager":
                                    startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;
                                case "nurse":
                                    startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    break;

                            }


                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Log.d("NIRAMAYA HOSPITAL", error);
                }
            });
            /*startActivity(new Intent(mContext, BypassActivity.class));*/
        }
    }

    private boolean validateUserId(String userId) {
        return Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2,}[-]{1}[A-Z]{2}[-]{1}[0-9]{4,}", userId);
    }
}
