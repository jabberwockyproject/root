package com.gw150914.jabberwocky.core;

import android.app.Fragment;
import android.os.Bundle;

public class SoundEngineFragment extends Fragment{
    // data object we want to retain
    private SoundEngine data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(SoundEngine data) {
        this.data = data;
    }

    public SoundEngine getData() {
        return data;
    }

}
