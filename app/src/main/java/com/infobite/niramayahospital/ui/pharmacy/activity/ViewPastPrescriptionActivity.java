package com.infobite.niramayahospital.ui.pharmacy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.ViewPastPrescriptionAdapter;
import com.infobite.niramayahospital.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPastPrescriptionActivity extends BaseActivity implements View.OnClickListener {
    private ViewPastPrescriptionAdapter pastPrescriptionAdapter;
    private List<String> pastPrescriptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_prescription);

        init();
    }

    private void init() {
        RecyclerView rvViewPastscription = findViewById(R.id.rvViewPastPrescription);

        for (int i = 0; i <= 6; i++) {
            pastPrescriptionList.add("12/03/2019");
            pastPrescriptionList.add("Hospital Name");
            pastPrescriptionList.add("Dr Name");
        }


        rvViewPastscription.setHasFixedSize(true);
        rvViewPastscription.setLayoutManager(new GridLayoutManager(mContext, 2));
        pastPrescriptionAdapter = new ViewPastPrescriptionAdapter(pastPrescriptionList, mContext, this);
        rvViewPastscription.setAdapter(pastPrescriptionAdapter);
        onclicktioner();
    }

    private void onclicktioner() {
        findViewById(R.id.icBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icBack:
                finish();
                break;
            case R.id.txtOpen:
                int position = Integer.parseInt(v.getTag().toString());
                Intent intent = new Intent(mContext, PastPrescriptionDetailActivity.class);
                startActivity(intent);
                break;
        }

    }
}
