package com.infobite.niramayahospital.ui.doctor.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.gson.Gson;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.user.UserModel;
import com.infobite.niramayahospital.ui.SignInActivity;
import com.infobite.niramayahospital.ui.pathology.activity.MainActivity;
import com.infobite.niramayahospital.utils.AppPreference;
import com.infobite.niramayahospital.utils.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SplashActivity extends BaseActivity {

    List<String> permissionsNeeded;
    List<String> permissionsList;
    final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        permissionsNeeded = new ArrayList<String>();
        permissionsList = new ArrayList<String>();

        permissions();

        init();
    }

    private void init() {
        final boolean isLogin = AppPreference.getBooleanPreference(mContext, Constant.IS_LOGIN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {

                    String userData = AppPreference.getStringPreference(mContext, Constant.USER_LOGIN_DATA);
                    Gson gson = new Gson();

                    UserModel userModel = gson.fromJson(userData, UserModel.class);

                    UserModel.setUserModel(userModel);
                    switch (userModel.getUser().getUserType()) {
                        case "doctor":
                            Intent intent = new Intent(mContext, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                            break;
                        case "pharmacy":
                            Intent intent1 = new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
                            finish();
                            break;
                        case "pathology":
                            Intent intent2 = new Intent(mContext, MainActivity.class);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent2);
                            finish();
                            break;
                        case "receptionist":
                            Intent intent3 = new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent3);
                            finish();
                            break;
                        case "account":
                            Intent intent4 = new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent4.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent4);
                            finish();
                            break;
                        case "manager":
                            Intent intent5 = new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class);
                            intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent5.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent5);
                            finish();
                            break;
                        case "nurse":
                            Intent intent6 = new Intent(mContext, com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.class);
                            intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent6.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent6);
                            finish();
                            break;

                    }
/*
                     Intent intent = new Intent(mContext, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();*/
                } else {
                    Intent intent = new Intent(mContext, SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

    public void permissions() {

        /*if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("Get Your Location");
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsNeeded.add("Get Your COARSE Location");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("Take Picture from CAMERA");*/
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Get your STORAGE Data");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                /*showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {*/
                ActivityCompat.requestPermissions(SplashActivity.this, permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            /*}
                        });*/
                return;
            }
            ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

        //splash_permission();

    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(SplashActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            return ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, permission);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                //perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);


                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (/*perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        &&*/ perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        /*&& perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED*/
                ) {

                    // All Permissions Granted
                    // insertDummyContact();
                    //splash_permission();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
