package com.infobite.niramayahospital.ui.fragment.profile_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;

import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSort;

public class ContactEditFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contact_edit_detail,container,false);
        initViews();
        return rootView;
    }

    private void initViews() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);
    }
}
