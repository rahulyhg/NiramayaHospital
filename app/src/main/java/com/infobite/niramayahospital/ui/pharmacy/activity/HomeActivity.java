package com.infobite.niramayahospital.ui.pharmacy.activity;

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
import com.infobite.niramayahospital.ui.doctor.activity.NotificationActivity;
import com.infobite.niramayahospital.ui.pharmacy.fragment.AddMedicineFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.DashboardFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.InvoiceFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.PrescriptionsFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.ProfileFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.SalesAndExpensesFragment;
import com.infobite.niramayahospital.utils.AppPreference;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.FragmentUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    public static TextView txtTitle;
    public static ImageView imgSearch, imgSort,imgNotification;
    private SlidingRootNav slidingRootNav;
    public static FragmentUtils fragmentUtilsHome;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fr_activity_home);

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
        fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.HomeFragment, R.id.home_frame);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.fr_menu_left_drawer)
                .inject();

        clickListener();
    }

    private void clickListener() {
        findViewById(R.id.txtDashboard).setOnClickListener(this);
        findViewById(R.id.txtPrescription).setOnClickListener(this);
        findViewById(R.id.txtMedicine).setOnClickListener(this);
        findViewById(R.id.txtAddMedicine).setOnClickListener(this);
        findViewById(R.id.txtSales).setOnClickListener(this);
        findViewById(R.id.txtExpenses).setOnClickListener(this);
        findViewById(R.id.txtProfile).setOnClickListener(this);
        findViewById(R.id.txtNotification).setOnClickListener(this);
        findViewById(R.id.txtSetting).setOnClickListener(this);
        findViewById(R.id.txtInvoice).setOnClickListener(this);
        findViewById(R.id.llLogout).setOnClickListener(this);
        findViewById(R.id.imgNotification).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment DashboardFragmentTag = fragmentManager.findFragmentByTag(Constant.DashboardFragment);
        Fragment PrescriptionFragmentTag = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment SalesAndExpensesFragmentTag = fragmentManager.findFragmentByTag(Constant.SalesAndExpensesFragment);
        Fragment AddMedicineFragmentTag = fragmentManager.findFragmentByTag(Constant.AddMedicineFragment);
        Fragment InvoiceFragmentTag = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);
        Fragment ProfileFragment = fragmentManager.findFragmentByTag(Constant.ProfileFragment);

        switch (v.getId()) {
            case R.id.llLogout:
                logout();
                break;
            case R.id.imgNotification:
                Intent intent = new Intent(mContext, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.txtDashboard:
                txtTitle.setText("Dashboard");
                if (DashboardFragmentTag == null) {
                    fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
                }
                break;
            case R.id.txtSales:
                txtTitle.setText("Sales And Expenses");
                if (SalesAndExpensesFragmentTag == null) {
                    fragmentUtilsHome.replaceFragment(new SalesAndExpensesFragment(), Constant.SalesAndExpensesFragment,
                            R.id.home_frame);
                }
                break;
            case R.id.txtPrescription:
                txtTitle.setText("Prescription");
                if (PrescriptionFragmentTag == null) {
                    fragmentUtilsHome.replaceFragment(new PrescriptionsFragment(), Constant.PrescriptionFragment, R.id.home_frame);
                }
                break;
            case R.id.txtInvoice:
                txtTitle.setText("Invoice");
                if (InvoiceFragmentTag == null) {
                    fragmentUtilsHome.replaceFragment(new InvoiceFragment(), Constant.InvoiceFragment, R.id.home_frame);
                }
                break;
            case R.id.txtAddMedicine:
                txtTitle.setText("Add Medicine");
                if (AddMedicineFragmentTag == null) {
                    fragmentUtilsHome.replaceFragment(new AddMedicineFragment(), Constant.AddMedicineFragment, R.id.home_frame);
                }
                break;
            case R.id.txtProfile:
                txtTitle.setText("Profile");
                if (ProfileFragment == null) {
                    fragmentUtilsHome.replaceFragment(new ProfileFragment(), Constant.ProfileFragment, R.id.home_frame);
                }
                break;
        }
        slidingRootNav.closeMenu();
    }

    @Override
    public void onBackPressed() {
        Fragment PrescriptionFragmentTag = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment InvoiceFragmentTag = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);

        Fragment DashboardFragment = fragmentManager.findFragmentByTag(Constant.DashboardFragment);
        Fragment PrescriptionFragment = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment ProfileFragment = fragmentManager.findFragmentByTag(Constant.ProfileFragment);
        Fragment AddMedicineFragment = fragmentManager.findFragmentByTag(Constant.AddMedicineFragment);

        Fragment LatestExpensesFragmentTag = fragmentManager.findFragmentByTag(Constant.LatestExpensesFragment);
        Fragment LatestSalesFragmentTag = fragmentManager.findFragmentByTag(Constant.LatestSalesFragment);
        Fragment LatestMedicineFragmentTag = fragmentManager.findFragmentByTag(Constant.LatestMedicineFragment);

        if (LatestExpensesFragmentTag != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (LatestSalesFragmentTag != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (LatestMedicineFragmentTag != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (InvoiceFragmentTag != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (PrescriptionFragmentTag != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (DashboardFragment != null) {
            finish();
        } else if (PrescriptionFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (ProfileFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else if (AddMedicineFragment != null) {
            txtTitle.setText("Dashboard");
            fragmentUtilsHome.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
        } else {
            finish();
        }
    }
    private void logout(){
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
