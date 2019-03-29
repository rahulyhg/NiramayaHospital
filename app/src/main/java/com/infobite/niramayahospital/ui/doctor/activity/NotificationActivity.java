package com.infobite.niramayahospital.ui.doctor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseActivity;

public class NotificationActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        viewMethod();
    }

    private void viewMethod() {
        ((ImageView)findViewById(R.id.imgBack)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                startActivity(new Intent(mContext,UpcomingAppointmentActivity.class));
        }
    }
}
