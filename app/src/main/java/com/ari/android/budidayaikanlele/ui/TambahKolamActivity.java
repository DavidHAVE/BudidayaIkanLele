package com.ari.android.budidayaikanlele.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.helper.Constant;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import com.ari.android.budidayaikanlele.model.Pond;
import com.ari.android.budidayaikanlele.model.PondProgress;
import com.ari.android.budidayaikanlele.model.Progress;

import java.util.Calendar;
import java.util.List;

public class TambahKolamActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mNamaKolamEditText, mJumlahBibitEditText;
    TextView mInfoTextView;
    private static EditText mTanggalTebarEditText;
    Button mSimpanButton, mHapusButton;


    private int pond_id;
    private boolean edit;
    private String name;
    private int seedAmount;
    private String initialDate;
    private String namaKolam;
    private int jumlahBibit;
    private static String tanggalTebar;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kolam);

        mInfoTextView = (TextView) findViewById(R.id.info_text_view);
        mNamaKolamEditText = (EditText) findViewById(R.id.nama_kolam_edit_text);
        mJumlahBibitEditText = (EditText) findViewById(R.id.jumlah_bibit_edit_text);
        mTanggalTebarEditText = (EditText) findViewById(R.id.tanggal_tebar_edit_text);
        mSimpanButton = (Button) findViewById(R.id.simpan_button);
        mHapusButton = (Button) findViewById(R.id.hapus_button);

        db = new DatabaseHelper(getApplicationContext());

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            pond_id = extra.getInt(Constant.ID);
            edit = extra.getBoolean(Constant.EDIT);
        }
        Log.e("TambahKolam", "ID : "+pond_id);

//        List<PondProgress> pondProgresses = db.getAllPondProgress();
//        for (PondProgress pondProgress: pondProgresses) {
//            Log.e("TambahKolam", "ID : "+pondProgress.getId()+", "+pondProgress.getPond_id()+", "+pondProgress.getProgress_id());
//        }
//        db.getAllPondProgress();

        mTanggalTebarEditText.setOnClickListener(this);
        mSimpanButton.setOnClickListener(this);
        mHapusButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tanggal_tebar_edit_text){
            DatePickerFragment mDatePicker = new DatePickerFragment();
            mDatePicker.show(getFragmentManager(), "Select date");
        }else if (id == R.id.simpan_button){
            if (edit){
                ubahKolam();
            }else {
                simpanKolam();
            }
        }else if(id == R.id.hapus_button){
            bersihkan();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toolbar toolbar = new Toolbar(this);
        if (edit){
            getSupportActionBar().setTitle("Edit Kolam");
            readPond();
        }else{
            getSupportActionBar().setTitle("Tambah Kolam");;
        }

        Log.e("TambahKolom", "count pond :"+db.getPondCount()+", count progress :"+db.getProgressCount()+", count pond progress:"
                +db.getPondProgressCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (edit) {
            getMenuInflater().inflate(R.menu.pond_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_action_menu){
            showDeleteConfirmationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void readPond(){
        Pond pond;
        pond = db.getPond(pond_id);
        name = pond.getName();
        seedAmount = pond.getSeed_amount();
        initialDate = pond.getInital_date();

        mNamaKolamEditText.setText(name);
        mJumlahBibitEditText.setText(String.valueOf(seedAmount));
        mTanggalTebarEditText.setText(initialDate);

    }

    private void ubahKolam() {

        if (!TextUtils.isEmpty(mNamaKolamEditText.getText().toString()) &&
                !TextUtils.isEmpty(mJumlahBibitEditText.getText().toString()) &&
                !TextUtils.isEmpty(tanggalTebar)) {
            namaKolam = mNamaKolamEditText.getText().toString();
            jumlahBibit = Integer.parseInt(mJumlahBibitEditText.getText().toString());
            tanggalTebar = mTanggalTebarEditText.getText().toString();

            Pond pond = new Pond(pond_id, namaKolam, jumlahBibit, tanggalTebar);
            long id_pond = db.updatePond(pond);
            db.closeDB();
            finish();
            Log.e("EditPond", "id_pond :" + id_pond);
        }else{
            Toast.makeText(this, "Harus diisi semua.", Toast.LENGTH_SHORT).show();
        }

    }


    private void simpanKolam() {

        if (!TextUtils.isEmpty(mNamaKolamEditText.getText().toString()) &&
                !TextUtils.isEmpty(mJumlahBibitEditText.getText().toString()) &&
                !TextUtils.isEmpty(tanggalTebar)) {
            namaKolam = mNamaKolamEditText.getText().toString();
            jumlahBibit = Integer.parseInt(mJumlahBibitEditText.getText().toString());

            //creating progress
            Progress progress1 = new Progress("Bulan 1", 0, 0, 0, 0, 0, 0);
            Progress progress2 = new Progress("Bulan 2", 0, 0, 0, 0, 0, 0);
            Progress progress3 = new Progress("Bulan 3", 0, 0, 0, 0, 0, 0);

            long progress1_id = db.createProgress(progress1);
            long progress2_id = db.createProgress(progress2);
            long progress3_id = db.createProgress(progress3);

            mInfoTextView.setText("Progress Count :" + db.getProgressCount());

            Log.e("TambahKolam", "Progress Count :" + db.getProgressCount());

//            Toast.makeText(getApplicationContext(), "Proress Count :"+db.getProgressCount(), Toast.LENGTH_SHORT).show();
            //creating pond
            Pond pond1 = new Pond(namaKolam, jumlahBibit, tanggalTebar);

//            long pond1_id = db.createPond(pond1, new long[]{progress1_id}, new long[] {progress2_id},
//                    new long[]{progress3_id});

            long pond1_id = db.createPond(pond1, new long[]{progress1_id});

            db.createPondProgress(pond1_id, progress2_id);
            db.createPondProgress(pond1_id, progress3_id);

            Log.e("TambahKolam", "Pond Count :" + db.getPondCount());

            // Getting all Pond
            Log.d("Get Pond", "Getting All Pond");

            List<Pond> ponds = db.getAllPond();
            for (Pond pond : ponds) {
                Log.e("Pond", pond.getId() + ", " + pond.getName() + ", " + pond.getSeed_amount() + ", " + pond.getInital_date());
            }

            // Getting all Progress
            Log.d("Get Progress", "Getting All Progress");

            List<Progress> progresses = db.getAllProgress();
            for (Progress progress : progresses) {
                Log.e("Progress", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", " + progress.getFeed_weight1());
            }

            // Getting all PondProgress
            Log.d("Get Progress", "Getting All Progress");
            List<PondProgress> pondProgresses = db.getAllPondProgress();
            for (PondProgress pondProgress : pondProgresses) {
                Log.e("PondProgress", pondProgress.getId() + ", " + pondProgress.getPond_id() + ", " + pondProgress.getProgress_id());
            }


            db.closeDB();
            finish();
        }else {
            Toast.makeText(this, "Harus diisi semua.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Kolam akan di hapus ?");
        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                Log.e("TAMBAHKOLAM", "IDD"+pond_id);
                db.deletePond(name);
                db.deleteProgress("Bulan 1");
                db.deleteProgress("Bulan 2");
                db.deleteProgress("Bulan 3");
                int delete = db.deletePondProgress(pond_id);
                Log.e("TambahKolom", "id :"+id+", count pond :"+db.getPondCount()+", count progress :"+db.getProgressCount()+", count pond progress:"
                        +db.getPondProgressCount()+", "+delete);


                db.closeDB();
                finish();

            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void bersihkan(){
        mNamaKolamEditText.setText("");
        mJumlahBibitEditText.setText("");
        mTanggalTebarEditText.setText("");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String monthFix;
            String dayFix;
            //Change date 8 Februari 2017 from 2017/1/8 to 2017/02/08
            if (month < 10) {
                monthFix = "0" + (month + 1);
            } else {
                monthFix = String.valueOf(month + 1);
            }
            if (day < 10) {
                dayFix = "0" + day;
            } else {
                dayFix = String.valueOf(day);
            }

            tanggalTebar = String.valueOf(year) + "/" + monthFix + "/" + dayFix;

            mTanggalTebarEditText.setText(String.valueOf(year) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(day));

        }
    }
}
