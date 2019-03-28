package com.infobite.niramayahospital.ui;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.doctor.fragment.PatientListFragment;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.FragmentUtils;

public class TestActivity extends BaseActivity {

    private FragmentUtils fragmentUtils;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fragmentManager = getSupportFragmentManager();
       /* fragmentUtils = new FragmentUtils(fragmentManager);
        fragmentUtils.replaceFragment(new PatientListMainFragment());*/
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_test, new PatientListFragment())
                    .commit();
        }
    }
}
