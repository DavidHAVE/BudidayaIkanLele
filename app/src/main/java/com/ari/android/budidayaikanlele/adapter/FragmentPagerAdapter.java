package com.ari.android.budidayaikanlele.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ari.android.budidayaikanlele.fragment.Bulan1Fragment;
import com.ari.android.budidayaikanlele.fragment.Bulan2Fragment;
import com.ari.android.budidayaikanlele.fragment.Bulan3Fragment;

/**
 * Created by David on 29/09/2017.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    int mNumOfTabs;

    public FragmentPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Bulan1Fragment();
        } else if (position == 1) {
            return new Bulan2Fragment();
        } else {
            return new Bulan3Fragment();
        }
    }

    @Override
    public int getCount() {
//        return 3;
        return mNumOfTabs;
    }
}
