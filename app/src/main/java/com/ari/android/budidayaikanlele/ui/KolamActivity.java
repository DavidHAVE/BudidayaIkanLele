package com.ari.android.budidayaikanlele.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.adapter.PondAdapter;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import com.ari.android.budidayaikanlele.model.Pond;

import java.util.ArrayList;
import java.util.List;

public class KolamActivity extends AppCompatActivity implements View.OnClickListener {

    ListView mKolamListView;
    Button mTambahKolamButton;
    private LinearLayout mEmptyView;

    PondAdapter mPondAdapter;
    List<Pond> ponds;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolam);

        mKolamListView = (ListView) findViewById(R.id.kolam_list_view);
        mTambahKolamButton = (Button) findViewById(R.id.tambah_kolam_button);
        mEmptyView = (LinearLayout) findViewById(R.id.emptyView);
        mKolamListView.setEmptyView(mEmptyView);

        db = new DatabaseHelper(getApplicationContext());

        ponds = new ArrayList<>();
        mPondAdapter = new PondAdapter(this, R.layout.item_pond, ponds);

        mTambahKolamButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tambah_kolam_button){
            startActivity(new Intent(this, TambahKolamActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllPond();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPondAdapter.clear();
    }

    public void getAllPond() {
        // Getting all Pond
        Log.d("Get Pond", "Getting All Pond");

        ponds = db.getAllPond();
        mPondAdapter.addAll(ponds);
        for (Pond pond : ponds) {
            Log.e("Pond", pond.getId()+", " +pond.getName()+", "+pond.getSeed_amount()+", "+pond.getInital_date());
        }
        mKolamListView.setAdapter(mPondAdapter);

        db.closeDB();
    }
}
