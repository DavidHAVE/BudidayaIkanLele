package com.ari.android.budidayaikanlele.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

//import static com.ari.android.budidayaikanlele.helper.DbBitmapUtility.getImage;

public class HamaPenyakitFragment extends Fragment {

//    TextView mTitleTextView, mDescriptionTextView;
//    ImageView mImageImageView;
//
//    DatabaseHelper db;
//
//    List<Information> informations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hama_penyakit, container, false);

        WebView view = new WebView(getContext());
        view.setVerticalScrollBarEnabled(false);

        ((FrameLayout)rootView.findViewById(R.id.rootView)).addView(view);

//        view.loadData(getString(R.string.kolam), "text/html; charset=utf-8", "utf-8");
        view.loadUrl("file:///android_asset/penyakit.html");


//        mTitleTextView = (TextView) rootView.findViewById(R.id.title_text_view);
//        mImageImageView = (ImageView) rootView.findViewById(R.id.image_image_view);
//        mDescriptionTextView = (TextView) rootView.findViewById(R.id.description_text_view);
//
//        informations = new ArrayList<>();
//
//        db = new DatabaseHelper(getContext());
//
//        Information information = new Information();
//        information = db.getInformation(1);
//        informations = db.getAllInformation();

//        List<Information> allInformations = db.getAllInformation();
//        for (Information informationn : allInformations) {
//            Log.e("Tag Name", informationn.getTitle()+", image : " +informationn.getImage()+ ", deskripsi : "+informationn.getDescription());
//
//        }

//        Log.e("HamaPenyakit", "Id : "+informations.get(2).getId()+" Title : "+informations.get(2).getTitle());
//        mTitleTextView.setText(information.getTitle());
//        Bitmap image = DbBitmapUtility.getImage(information.getImage());
//        mImageImageView.setImageBitmap(image);
//        mDescriptionTextView.setText(information.getDescription());
//
//        db.closeDB();

        return rootView;
    }

}
