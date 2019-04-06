package com.infobite.niramayahospital.ui.pathology.fragment.patient_fragment_module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseFragment;

public class ContactFragment extends BaseFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.path_fragment_contact, container, false);

        initViews();

        return rootView;
    }

    private void initViews() {
     /*   imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.VISIBLE);*/
    }
}
