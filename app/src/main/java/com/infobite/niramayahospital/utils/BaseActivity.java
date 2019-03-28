package com.infobite.niramayahospital.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.infobite.niramayahospital.retrofit.RetrofitApiClient;
import com.infobite.niramayahospital.retrofit.RetrofitService;

public class BaseActivity extends AppCompatActivity {

    public RetrofitApiClient retrofitApiClient;
    public ConnectionDetector cd;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
    }

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}