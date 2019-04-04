package com.infobite.niramayahospital.ui.pathology.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.ui.SignInActivity;
import com.infobite.niramayahospital.ui.pathology.fragment.FragmentAllTests;
import com.infobite.niramayahospital.ui.pathology.fragment.ProfileFragment;
import com.infobite.niramayahospital.ui.pathology.fragment.patient_fragment_module.PatientMainFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.DashboardFragment;
import com.infobite.niramayahospital.utils.AppPreference;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.FragmentUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static TextView txtTitle;
    public static ImageView imgSearch, imgSort, imgNotification;
    private SlidingRootNav slidingRootNav;
    public static FragmentUtils fragmentUtilsHome;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_activity_main);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        imgSearch = findViewById(R.id.imgSearch);
        imgNotification = findViewById(R.id.imgNotification);
        imgSort = findViewById(R.id.imgSort);
        txtTitle = findViewById(R.id.txtTitle);

        fragmentManager = getSupportFragmentManager();
        fragmentUtilsHome = new FragmentUtils(fragmentManager);
        txtTitle.setText("Dashboard");
        fragmentUtilsHome.replaceFragment(new FragmentAllTests(), Constant.HomeFragment, R.id.home_frame);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.path_menu_left_drawer)
                .inject();

        clickListener();
    }

    private void clickListener() {
        findViewById(R.id.txtDashboard).setOnClickListener(this);
        findViewById(R.id.txtPatient).setOnClickListener(this);
        findViewById(R.id.txtLabTests).setOnClickListener(this);
        findViewById(R.id.txtProfile).setOnClickListener(this);
        findViewById(R.id.txtExpences).setOnClickListener(this);
        findViewById(R.id.txtDonor).setOnClickListener(this);
        findViewById(R.id.txtSetting).setOnClickListener(this);
        findViewById(R.id.txtNotification).setOnClickListener(this);
        findViewById(R.id.llLogout).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Fragment FragmentAllTestsTag = fragmentManager.findFragmentByTag(Constant.FragmentAllTests);


        switch (v.getId()) {
            case R.id.llLogout:
                logout();
                break;
            case R.id.txtDashboard:
                txtTitle.setText("All Tests");
                if (FragmentAllTestsTag == null) {
                    fragmentUtilsHome.replaceFragment(new FragmentAllTests(), Constant.FragmentAllTests, R.id.home_frame);
                }
                break;
            case R.id.txtProfile:
                txtTitle.setText("All Tests");
                if (FragmentAllTestsTag == null) {
                    fragmentUtilsHome.replaceFragment(new ProfileFragment(), Constant.ProfileFragment, R.id.home_frame);
                }
                break;
            case R.id.txtPatient:
                txtTitle.setText("Patient");
                if (FragmentAllTestsTag == null) {
                    fragmentUtilsHome.replaceFragment(new PatientMainFragment(), Constant.ProfileFragment, R.id.home_frame);
                }
                break;

        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onBackPressed() {
        Fragment FragmentAllTests = fragmentManager.findFragmentByTag(Constant.FragmentAllTests);
        Fragment FragmentProfile = fragmentManager.findFragmentByTag(Constant.ProfileFragment);
        Fragment FragmentPatientMain = fragmentManager.findFragmentByTag(Constant.PatientFragment);


        if (FragmentAllTests != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new FragmentAllTests(), Constant.FragmentAllTests, R.id.home_frame);
        } else if (FragmentProfile != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new FragmentAllTests(), Constant.FragmentAllTests, R.id.home_frame);
        } else if (FragmentPatientMain != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new FragmentAllTests(), Constant.FragmentAllTests, R.id.home_frame);
        } else {
            finish();
        }
    }

    private void logout() {
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
