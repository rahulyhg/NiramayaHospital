package com.infobite.niramayahospital.ui.doctor.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.user.UserData;
import com.infobite.niramayahospital.models.user.UserModel;
import com.infobite.niramayahospital.ui.SignInActivity;
import com.infobite.niramayahospital.ui.doctor.fragment.DashboardFragment;
import com.infobite.niramayahospital.ui.doctor.fragment.DutiesFragment;
import com.infobite.niramayahospital.ui.doctor.fragment.NotificationFragment;
import com.infobite.niramayahospital.ui.doctor.fragment.PatientListFragment;
import com.infobite.niramayahospital.ui.doctor.fragment.profile_details.ProfileMainFragment;
import com.infobite.niramayahospital.utils.AppPreference;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.FragmentUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    public static TextView txtTitle;
    public static ImageView imgSearch, imgSort, imgNotification, imgEditProfile;
    private SlidingRootNav slidingRootNav;
    public static FragmentUtils fragmentUtils;
    public static FragmentManager fragmentManager;
    private String strFrom = "";
    public Toolbar toolbar;

    private UserData userData;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = this;
        userData = UserModel.getUserModel().getUser();

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {

        imgNotification = findViewById(R.id.imgNotification);
        imgNotification.setOnClickListener(this);
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
        //   getIntentData();
    }

    private void getIntentData() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            String strFrom = intent.getStringExtra("from");
            if (strFrom.equalsIgnoreCase("upcoming")) {
                //     toolbar.setTitle(Constant.ProductsFragment);
                fragmentUtils.replaceFragment(new NotificationFragment(), Constant.NotificationFragment, R.id.home_frame);
            }
        }
    }

    private void clickListener() {

        ((TextView) findViewById(R.id.tvUserName)).setText(userData.getUserName());
        ((TextView) findViewById(R.id.tvUserType)).setText(userData.getUserEmail());

        findViewById(R.id.txtDashboard).setOnClickListener(this);
        findViewById(R.id.txtPatients).setOnClickListener(this);
        findViewById(R.id.txtDuties).setOnClickListener(this);
        findViewById(R.id.txtProfile).setOnClickListener(this);
        findViewById(R.id.txtSettings).setOnClickListener(this);
        findViewById(R.id.txtNotification).setOnClickListener(this);
        findViewById(R.id.llLogout).setOnClickListener(this);
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
        Fragment ProfileMainFragment = fragmentManager.findFragmentByTag(Constant.ProfileMainFragment);
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
                //if (PatientFragment == null) {
                fragmentUtils.replaceFragment(new PatientListFragment(), Constant.PatientFragment, R.id.home_frame);
                //}
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
            case R.id.llLogout:
                doLogout();
                break;
            case R.id.imgNotification:
                txtTitle.setText("Notification");
                if (NotificationFragment == null) {
                    fragmentUtils.replaceFragment(new NotificationFragment(), Constant.NotificationFragment, R.id.home_frame);
                }
                break;
        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onBackPressed() {
        Fragment DutiesFragment = fragmentManager.findFragmentByTag(Constant.DutiesFragment);
        Fragment DashboardFragment = fragmentManager.findFragmentByTag(Constant.DashboardFragment);
        Fragment ProfileFragment = fragmentManager.findFragmentByTag(Constant.ProfileFragment);
        Fragment ProfileMainFragment = fragmentManager.findFragmentByTag(Constant.ProfileMainFragment);
        Fragment NotificationFragment = fragmentManager.findFragmentByTag(Constant.NotificationFragment);
        Fragment SettingsFragment = fragmentManager.findFragmentByTag(Constant.SettingsFragment);
        Fragment PatientFragment = fragmentManager.findFragmentByTag(Constant.PatientFragment);
        Fragment PatientDetailFragment = fragmentManager.findFragmentByTag(Constant.PatientDetailFragment);

        if (DashboardFragment != null) {
            finish();
        } else if (PatientDetailFragment != null) {
            txtTitle.setText("Patient List");
            fragmentUtils.replaceFragment(new PatientListFragment(), Constant.PatientFragment, R.id.home_frame);
        } else if (NotificationFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (SettingsFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (ProfileFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (DutiesFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (PatientFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else {
            finish();
        }
    }

    private void doLogout() {
        new AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure want to doLogout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppPreference.clearAllPreferences(mContext);
                        Intent intent = new Intent(mContext, SignInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create()
                .show();
    }
}
