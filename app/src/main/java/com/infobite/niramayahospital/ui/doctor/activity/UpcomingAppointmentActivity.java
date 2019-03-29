package com.infobite.niramayahospital.ui.doctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.ui.doctor.fragment.NotificationFragment;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.FragmentUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpcomingAppointmentActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentUtils fragmentUtils;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForSelectedMonth = new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForSelectedDay = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatWithDay = new SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault());
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatMonthN = new SimpleDateFormat("M", Locale.getDefault());
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());
    private int selectedYear = 0;
    private int selectedMonth = 0;
    private int currentYear = 0;
    private int currentMonth = 0;
    private boolean isPrevious = false;

    private CompactCalendarView comCal;

    private TextView tvYear, tvMonth, tvDate, tvNext, tvPrevious;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointment);

        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDate = findViewById(R.id.tvDate);
        tvNext = findViewById(R.id.tvNext);
        tvPrevious = findViewById(R.id.tvPrevious);
        imgBack = findViewById(R.id.imgBack);


        initViews();
        ((ImageView) findViewById(R.id.imgNotification)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ((LinearLayout)findViewById(R.id.llUpcomingActivity)).setVisibility(View.GONE);
                ((ImageView)findViewById(R.id.imgNotification)).setVisibility(View.GONE);
                ((ImageView)findViewById(R.id.imgSearch)).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.txtTitle)).setText("Notification");
                fragmentManager = getSupportFragmentManager();
               fragmentUtils = new FragmentUtils(fragmentManager);
                fragmentUtils.replaceFragment(new NotificationFragment(),Constant.NotificationFragment,R.id.frame_upcoming);
*/
                Intent intent = new Intent(mContext, NotificationActivity.class);
                //    intent.putExtra("from","upcoming");
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        comCal = findViewById(R.id.comCal);
        comCal.setUseThreeLetterAbbreviation(true);

        try {
            currentMonth = Integer.parseInt(dateFormatMonthN.format(Calendar.getInstance().getTime()));
            currentYear = Integer.parseInt(dateFormatYear.format(Calendar.getInstance().getTime()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        if (!isPrevious) {
            tvPrevious.setVisibility(View.GONE);
        }

        tvYear.setText(dateFormatYear.format(Calendar.getInstance().getTime()));
        tvMonth.setText(dateFormatMonth.format(Calendar.getInstance().getTime()));
        tvDate.setText(dateFormatWithDay.format(Calendar.getInstance().getTime()));

        comCal.shouldScrollMonth(false);

        comCal.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tvDate.setText(dateFormatWithDay.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tvYear.setText(dateFormatYear.format(firstDayOfNewMonth));
                tvMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
                try {
                    selectedMonth = Integer.parseInt(dateFormatMonthN.format(firstDayOfNewMonth));
                    selectedYear = Integer.parseInt(dateFormatYear.format(firstDayOfNewMonth));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (selectedYear == currentYear) {
                    if (selectedMonth > currentMonth) {
                        isPrevious = true;
                        tvPrevious.setVisibility(View.VISIBLE);
                    } else {
                        isPrevious = false;
                        tvPrevious.setVisibility(View.GONE);
                    }
                } else if (selectedYear > currentYear) {
                    isPrevious = true;
                    tvPrevious.setVisibility(View.VISIBLE);
                } else {
                    isPrevious = false;
                    tvPrevious.setVisibility(View.GONE);
                }

            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comCal.scrollRight();
            }
        });

        tvPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comCal.scrollLeft();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
