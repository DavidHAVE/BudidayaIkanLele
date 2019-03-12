package com.ari.android.budidayaikanlele.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class PembuatanKolamFragment extends Fragment {

//    TextView mTitle1TextView, mTitle2TextView, mTitle3TextView, mTitle4TextView;
//    TextView mDescription1TextView, mDescription2TextView, mDescription3TextView, mDescription4TextView;
//    ImageView mImage1ImageView, mImage2ImageView, mImage3ImageView, mImage4ImageView;
//
//    DatabaseHelper db;
//
//    List<Information> informations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pembuatan_kolam, container, false);

        WebView view = new WebView(getContext());
        view.setVerticalScrollBarEnabled(false);

        ((FrameLayout)rootView.findViewById(R.id.rootView)).addView(view);

//        view.loadData(getString(R.string.kolam), "text/html; charset=utf-8", "utf-8");
        view.loadUrl("file:///android_asset/kolam.html");

//        mTitle1TextView = (TextView) rootView.findViewById(R.id.title1_text_view);
//        mTitle2TextView = (TextView) rootView.findViewById(R.id.title2_text_view);
//        mTitle3TextView = (TextView) rootView.findViewById(R.id.title3_text_view);
//        mTitle4TextView = (TextView) rootView.findViewById(R.id.title4_text_view);
//        mDescription1TextView = (TextView) rootView.findViewById(R.id.description1_text_view);
//        mDescription2TextView = (TextView) rootView.findViewById(R.id.description2_text_view);
//        mDescription3TextView = (TextView) rootView.findViewById(R.id.description3_text_view);
//        mDescription4TextView = (TextView) rootView.findViewById(R.id.description4_text_view);
//        mImage1ImageView = (ImageView) rootView.findViewById(R.id.image1_image_view);
//        mImage2ImageView = (ImageView) rootView.findViewById(R.id.image2_image_view);
//        mImage3ImageView = (ImageView) rootView.findViewById(R.id.image3_image_view);
//        mImage4ImageView = (ImageView) rootView.findViewById(R.id.image4_image_view);
//
//        informations = new ArrayList<>();
//
//        db = new DatabaseHelper(getContext());
//
//        Information information = new Information();
//        information = db.getInformation(1);
//
//        mTitle1TextView.setText(information.getTitle());
//        Bitmap image = DbBitmapUtility.getImage(information.getImage());
//        mImage1ImageView.setImageBitmap(image);
//        mDescription1TextView.setText(information.getDescription());
//
//        Information information2 = new Information();
//        information2 = db.getInformation(2);
//
//        mTitle2TextView.setText(information2.getTitle());
//        Bitmap image2 = DbBitmapUtility.getImage(information2.getImage());
//        mImage2ImageView.setImageBitmap(image2);
//        mDescription2TextView.setText(information2.getDescription());
//
//
//        Information information3 = new Information();
//        information3 = db.getInformation(3);
//
//        mTitle3TextView.setText(information3.getTitle());
//        Bitmap image3 = DbBitmapUtility.getImage(information3.getImage());
//        mImage3ImageView.setImageBitmap(image3);
//        mDescription3TextView.setText(information3.getDescription());
//
//
//        Information information4 = new Information();
//        information4 = db.getInformation(4);
//
//        mTitle4TextView.setText(information4.getTitle());
//        Bitmap image4 = DbBitmapUtility.getImage(information4.getImage());
//        mImage4ImageView.setImageBitmap(image4);
//        mDescription4TextView.setText(information4.getDescription());
//
//
//        db.closeDB();

        return rootView;
    }

}
