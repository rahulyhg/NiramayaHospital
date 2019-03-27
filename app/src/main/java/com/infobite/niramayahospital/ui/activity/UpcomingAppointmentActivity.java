package com.infobite.niramayahospital.ui.activity;

import android.os.Bundle;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseActivity;

public class UpcomingAppointmentActivity extends BaseActivity {

    private CompactCalendarView comCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointment);

        initViews();
    }

    private void initViews() {
        comCal = findViewById(R.id.comCal);
        comCal.setUseThreeLetterAbbreviation(true);
    }
}
