package com.infobite.niramayahospital.ui.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.infobite.niramayahospital.R;
import com.crashlytics.android.Crashlytics;
import com.infobite.niramayahospital.ui.activity.schedule_section.ScheduleActivity;

import io.fabric.sdk.android.Fabric;

public class PrescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private RelativeLayout leftDrawer;
    private RelativeLayout rightDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_prescription);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (RelativeLayout) findViewById(R.id.nav_left);
        rightDrawer = (RelativeLayout) findViewById(R.id.nav_right);

        ((ImageView)findViewById(R.id.ivAddNew)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.ivSearch)).setOnClickListener(this);

        ((Button)findViewById(R.id.btnAdd)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivAddNew:
                drawer.openDrawer(Gravity.START);
                break;
            case R.id.ivSearch:
                drawer.openDrawer(Gravity.END);
                break;
            case R.id.btnAdd:
                drawer.closeDrawer(Gravity.START);
                break;
        }
    }
}
