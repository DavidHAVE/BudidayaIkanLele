package com.ari.android.budidayaikanlele.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.helper.Constant;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import com.ari.android.budidayaikanlele.model.Pond;
import com.ari.android.budidayaikanlele.model.Progress;

import java.util.List;


public class Bulan1Fragment extends Fragment implements View.OnClickListener {

    EditText mBeratBibit1EditText, mBeratBibit2EditText, mBeratBibit3EditText;
    TextView mBeratPakan1TextView, mBeratPakan2TextView, mBeratPakan3TextView;
    Button mSaveProgressButton;
    DatabaseHelper db;
    private int pond_id;
    private int progress_id;
    private String month;
    private int seedAmount = 0;
    private double beratBibit1, beratBibit2, beratBibit3 = 0;
    private double beratPakan1, beratPakan2, beratPakan3 = 0;
    private double beratBibit1Kg, beratBibit2Kg, beratBibit3Kg = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bulan1, container, false);

        mBeratBibit1EditText = (EditText) rootView.findViewById(R.id.berat_bibit1_edit_text);
        mBeratBibit2EditText = (EditText) rootView.findViewById(R.id.berat_bibit2_edit_text);
        mBeratBibit3EditText = (EditText) rootView.findViewById(R.id.berat_bibit3_edit_text);
        mBeratPakan1TextView = (TextView) rootView.findViewById(R.id.berat_pakan1_text_view);
        mBeratPakan2TextView = (TextView) rootView.findViewById(R.id.berat_pakan2_text_view);
        mBeratPakan3TextView = (TextView) rootView.findViewById(R.id.berat_pakan3_text_view);
        mSaveProgressButton = (Button) rootView.findViewById(R.id.save_progress_button);

        db = new DatabaseHelper(getContext());

        Bundle extra = getActivity().getIntent().getExtras();
        if (extra != null) {
            pond_id = extra.getInt(Constant.ID);
            readPond();
            readProgress();
        }

        mSaveProgressButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save_progress_button) {
            saveProgress();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void readPond() {
        Pond pond = new Pond();
        pond = db.getPond(pond_id);
        seedAmount = pond.getSeed_amount();
    }

    private void readProgress() {
        // Getting todos under "Watchlist" tag name
        Log.d("Progress", "Get Progress under Pond");

        List<Progress> progressesWatchList = db.getAllProgressByPond(pond_id, "Bulan 1");
        for (Progress progress : progressesWatchList) {
            Log.e("Progress", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", " + progress.getFeed_weight1());
            progress_id = progress.getId();
            month = progress.getMonth();
            beratBibit1 = progress.getWeight1();
            beratPakan1 = progress.getFeed_weight1();
            beratBibit2 = progress.getWeight2();
            beratPakan2 = progress.getFeed_weight2();
            beratBibit3 = progress.getWeight3();
            beratPakan3 = progress.getFeed_weight3();

            Log.e("BULAN1FRAGMENT1", progress_id + ", " + month + ", " + beratBibit1 + ", " + beratBibit2 + ", " + beratBibit3);

            beratBibit1 = beratBibit1 * 1000;
            beratBibit2 = beratBibit2 * 1000;
            beratBibit3 = beratBibit3 * 1000;

            Log.e("BULAN1FRAGMENT2", progress_id + ", " + month + ", " + beratBibit1 + ", " + beratBibit2 + ", " + beratBibit3);

            mBeratBibit1EditText.setText(String.valueOf(beratBibit1));
            mBeratPakan1TextView.setText(String.valueOf(beratPakan1));
            mBeratBibit2EditText.setText(String.valueOf(beratBibit2));
            mBeratPakan2TextView.setText(String.valueOf(beratPakan2));
            mBeratBibit3EditText.setText(String.valueOf(beratBibit3));
            mBeratPakan3TextView.setText(String.valueOf(beratPakan3));
        }
    }

    private void saveProgress() {
        if (!TextUtils.isEmpty(mBeratBibit1EditText.getText().toString()) &&
                !TextUtils.isEmpty(mBeratBibit2EditText.getText().toString()) &&
                !TextUtils.isEmpty(mBeratBibit2EditText.getText().toString())) {

            beratBibit1 = Double.parseDouble((mBeratBibit1EditText.getText().toString()));
            beratBibit2 = Double.parseDouble(mBeratBibit2EditText.getText().toString());
            beratBibit3 = Double.parseDouble(mBeratBibit3EditText.getText().toString());


            beratBibit1Kg = beratBibit1 / 1000;
            beratBibit2Kg = beratBibit2 / 1000;
            beratBibit3Kg = beratBibit3 / 1000;

            beratPakan1 = (beratBibit1Kg * seedAmount) * 3 / 100;
            beratPakan2 = (beratBibit2Kg * seedAmount) * 3 / 100;
            beratPakan3 = (beratBibit3Kg * seedAmount) * 3 / 100;

            Log.e("saveProgress", beratBibit1Kg + ", " + seedAmount + ", " + beratPakan1);

            Progress progress = new Progress(progress_id, month, beratBibit1Kg, beratPakan1, beratBibit2Kg, beratPakan2,
                    beratBibit3Kg, beratPakan3);

            long id_progress = db.updateProgress(progress);

            Log.e("saveProgress", "id_progress :" + id_progress);

            readProgress();

            db.closeDB();
        } else {
            Toast.makeText(getContext(), "Berat harus diisi.", Toast.LENGTH_SHORT).show();
        }
    }

}
