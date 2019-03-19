package com.infobite.niramayahospital;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.infobite.niramayahospital.ui.fragment.profile_details.PersonalDetailFragment;
import com.infobite.niramayahospital.ui.fragment.profile_details.ProfileMainFragment;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.BaseFragment;

public class TestActivity extends BaseActivity {
public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_test,new ProfileMainFragment())
                    .commit();
        }
    }
}
