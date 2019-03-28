package com.infobite.niramayahospital.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.ui.fragment.DashboardFragment;
import com.infobite.niramayahospital.ui.fragment.DutiesFragment;
import com.infobite.niramayahospital.ui.fragment.NotificationFragment;
import com.infobite.niramayahospital.ui.fragment.list.PatientListMainFragment;
import com.infobite.niramayahospital.ui.fragment.profile_details.ProfileMainFragment;
import com.infobite.niramayahospital.utils.FragmentUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public static TextView txtTitle;
    public static ImageView imgSearch, imgSort, imgNotification, imgEditProfile;
    private SlidingRootNav slidingRootNav;
    private FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
   public  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        imgNotification = findViewById(R.id.imgNotification);
        imgEditProfile = findViewById(R.id.imgEditProfile);
        imgSearch = findViewById(R.id.imgSearch);
        imgSort = findViewById(R.id.imgSort);
        txtTitle = findViewById(R.id.txtTitle);

        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        clickListener();
    }
    private void getIntentData(){
        if (getIntent() != null){
            Intent intent = getIntent();
            String strFrom = intent.getStringExtra("from");
            if (strFrom.equals("Prescription")){
            }
        }
    }

    private void clickListener() {
        findViewById(R.id.txtDashboard).setOnClickListener(this);
        findViewById(R.id.txtPatients).setOnClickListener(this);
        findViewById(R.id.txtDuties).setOnClickListener(this);
        findViewById(R.id.txtProfile).setOnClickListener(this);
        findViewById(R.id.txtSettings).setOnClickListener(this);
        findViewById(R.id.txtNotification).setOnClickListener(this);
        findViewById(R.id.imgNotification).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment AddUserFragment = fragmentManager.findFragmentByTag(Constant.AddUserFragment);
        Fragment BloodDonationFragment = fragmentManager.findFragmentByTag(Constant.BloodDonationFragment);
        Fragment BedFragment = fragmentManager.findFragmentByTag(Constant.BedFragment);
        Fragment DashboardFragment = fragmentManager.findFragmentByTag(Constant.DashboardFragment);
        Fragment NotificationFragment = fragmentManager.findFragmentByTag(Constant.NotificationFragment);
        Fragment PatientFragment = fragmentManager.findFragmentByTag(Constant.PatientFragment);
        Fragment DutiesFragment = fragmentManager.findFragmentByTag(Constant.DutiesFragment);
        Fragment InvoiceFragment = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);
        Fragment ProfileFragment = fragmentManager.findFragmentByTag(Constant.ProfileFragment);
        Fragment PrescriptionFragment = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment ReportFragment = fragmentManager.findFragmentByTag(Constant.ReportsFragment);

        switch (v.getId()) {
            case R.id.txtDashboard:
                txtTitle.setText("Dashboard");
                if (DashboardFragment == null) {
                    fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
                }
                break;
            case R.id.txtPatients:
                txtTitle.setText("Patient List");
                if (PatientFragment == null) {
                    fragmentUtils.replaceFragment(new PatientListMainFragment(), Constant.PatientFragment, R.id.home_frame);
                }
                break;
            case R.id.txtDuties:
                txtTitle.setText("Duties");
                if (DutiesFragment == null) {
                    fragmentUtils.replaceFragment(new DutiesFragment(), Constant.DutiesFragment, R.id.home_frame);
                }
                break;
            case R.id.txtProfile:
                txtTitle.setText("Profile");
                if (ProfileFragment == null) {
                    fragmentUtils.replaceFragment(new ProfileMainFragment(), Constant.ProfileFragment, R.id.home_frame);
                }
                break;
            case R.id.txtSettings:
                txtTitle.setText("Settings");
                if (BedFragment == null) {
                    //fragmentUtils.replaceFragment(new BedFragment(), Constant.BedFragment, R.id.home_frame);
                }
                break;
            case R.id.txtNotification:
                txtTitle.setText("Notification");
                if (NotificationFragment == null) {
                    fragmentUtils.replaceFragment(new NotificationFragment(), Constant.NotificationFragment, R.id.home_frame);
                }
                break;
            case R.id.imgNotification:
                txtTitle.setText("Notification");
                if (NotificationFragment == null) {
                    fragmentUtils.replaceFragment(new NotificationFragment(), Constant.NotificationFragment, R.id.home_frame);
                }
                break;
            /*case R.id.txtBloodDonation:
                txtTitle.setText("Blood Donation");
                if (BloodDonationFragment == null) {
                    //fragmentUtils.replaceFragment(new BloodDonationFragment(), Constant.BloodDonationFragment, R.id.home_frame);
                }
                break;
            case R.id.txtDocuments:
                break;
            case R.id.txtSettings:
                break;
            case R.id.txtAddUser:
                txtTitle.setText("Add User");
                if (AddUserFragment == null) {
                    //fragmentUtils.replaceFragment(new AddUserFragment(), Constant.AddUserFragment, R.id.home_frame);
                }
                break;*/
        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onBackPressed() {
        Fragment AddUserFragment = fragmentManager.findFragmentByTag(Constant.AddUserFragment);
        Fragment BedFragment = fragmentManager.findFragmentByTag(Constant.BedFragment);
        Fragment DashboardFragment = fragmentManager.findFragmentByTag(Constant.DashboardFragment);
        Fragment InvoiceFragment = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);
        Fragment PrescriptionFragment = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment ReportFragment = fragmentManager.findFragmentByTag(Constant.ReportsFragment);

        if (DashboardFragment != null) {
            finish();
        } else if (PrescriptionFragment != null) {
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (ReportFragment != null) {
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (InvoiceFragment != null) {
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (BedFragment != null) {
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else {
            finish();
        }
    }
}
