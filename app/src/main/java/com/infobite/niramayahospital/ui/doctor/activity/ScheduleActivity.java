package com.infobite.niramayahospital.ui.doctor.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.SchedulePatientListAdapter;
import com.infobite.niramayahospital.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends BaseActivity {

    private SchedulePatientListAdapter schedulePatientListAdapter;
    private List<String> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        init();
    }

    private void init() {
        RecyclerView rvPatientList = findViewById(R.id.rvSchedulePatientList);

        for (int i=0; i<=8; i++){
            patientList.add("David Backhem");
            patientList.add("Today");
            patientList.add("Room no 115");
            patientList.add("11:45 PM");
        }

        rvPatientList.setHasFixedSize(true);
        rvPatientList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        schedulePatientListAdapter = new SchedulePatientListAdapter(mContext, patientList);
        rvPatientList.setAdapter(schedulePatientListAdapter);
        schedulePatientListAdapter.notifyDataSetChanged();
    }
}
