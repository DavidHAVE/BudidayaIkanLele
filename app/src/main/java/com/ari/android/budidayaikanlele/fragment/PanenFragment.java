package com.ari.android.budidayaikanlele.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ari.android.budidayaikanlele.R;

public class PanenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_panen, container, false);

        WebView view = new WebView(getContext());
        view.setVerticalScrollBarEnabled(false);

        ((FrameLayout)rootView.findViewById(R.id.rootView)).addView(view);

//        view.loadData(getString(R.string.kolam), "text/html; charset=utf-8", "utf-8");
        view.loadUrl("file:///android_asset/panen.html");

        return rootView;
    }

}
