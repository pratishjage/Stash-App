package com.app.sampleapp.ui;

import android.os.Bundle;

import com.app.sampleapp.stash.base.StashBaseFragment;

public class StepOneFragment extends StashBaseFragment {
    @Override
    public void showMiniView() {

    }

    @Override
    public void showExtendedView() {

    }

    @Override
    public int getCurrentScreenPosition() {
        return 0;
    }

     public static StepOneFragment newInstance(Bundle bundle) {
        StepOneFragment fragment = new StepOneFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
