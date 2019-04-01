package com.infobite.niramayahospital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.doctor.activity.HomeActivity;
import com.infobite.niramayahospital.utils.BaseActivity;

public class BypassActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bypass);

        initViews();
    }

    private void initViews() {
        ((Button)findViewById(R.id.btnDoctor)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnPharmacy)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnPathology)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnNurse)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnAccountant)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnReception)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnAmbulance)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnAdmin)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDoctor :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnPharmacy :
                startActivity(new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class));
                break;
            case R.id.btnPathology :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnNurse :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnAccountant :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnReception :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnAmbulance :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.btnAdmin :
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
        }
    }
}
