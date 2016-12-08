package com.gw150914.jabberwocky.core;

import android.app.Fragment;
import android.os.Bundle;

public class ThemeEngineFragment extends Fragment{
    // data object we want to retain
    private ThemeEngine data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(ThemeEngine data) {
        this.data = data;
    }

    public ThemeEngine getData() {
        return data;
    }

}
