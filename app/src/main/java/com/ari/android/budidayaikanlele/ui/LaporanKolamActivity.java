package com.ari.android.budidayaikanlele.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.helper.Constant;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import com.ari.android.budidayaikanlele.model.Pond;
import com.ari.android.budidayaikanlele.model.Progress;
import com.ari.android.budidayaikanlele.model.Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LaporanKolamActivity extends AppCompatActivity {

    TextView mTanggalPanenTextView, mTotalPakan781_1TextView, mTotalPakan781_2TextView, mTotalPakan781_3TextView;

    private String dateInString;
    private int pond_id;
    private String initialDate;
    private int progress_id;
    private String month;
    private int seedAmount = 0;
    private String tanggalPanen;
    private double beratPakan11, beratPakan12, beratPakan13;
    private double beratPakan21, beratPakan22, beratPakan23;
    private double beratPakan31, beratPakan32, beratPakan33 ;
    private double totalPakan781_1, totalPakan781_2, totalPakan781_3;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_kolam);

        mTanggalPanenTextView = (TextView) findViewById(R.id.tanggal_panen_text_view);
        mTotalPakan781_1TextView = (TextView) findViewById(R.id.total_pakan1_text_view);
        mTotalPakan781_2TextView = (TextView) findViewById(R.id.total_pakan2_text_view);
        mTotalPakan781_3TextView = (TextView) findViewById(R.id.total_pakan3_text_view);

        db = new DatabaseHelper(getApplicationContext());


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            pond_id = extra.getInt(Constant.ID);
            Log.e("LaporanKolam", "ID :" + pond_id + ", DATE :" + initialDate);
            readPond();
            readProgress();
//            readReport();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void readPond() {
        Pond pond = new Pond();
        pond = db.getPond(pond_id);
        initialDate = pond.getInital_date();
    }

    private void readProgress() {

        Log.e("LaporanKolam", "ID :" + pond_id + ", DATE :" + initialDate);


        dateInString = initialDate;  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 90);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(c.getTimeInMillis());
        dateInString = sdf.format(resultdate);
        System.out.println("String date:"+dateInString);

            Log.e("LaporanKolam2", "ID :" + pond_id + ", DATE :" +dateInString);

//        mTanggalPanenTextView.setText(dateInString);


        Log.d("Progress", "Get Progress under Pond");

        List<Progress> progressesWatchList = db.getAllProgressByPond(pond_id, "Bulan 1");
        for (Progress progress : progressesWatchList) {
            Log.e("Progress1", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", "
                    + progress.getFeed_weight1()+", "+progress.getFeed_weight2()+", "+progress.getFeed_weight3());
            progress_id = progress.getId();
            month = progress.getMonth();
            beratPakan11 = progress.getFeed_weight1();
            beratPakan12 = progress.getFeed_weight2();
            beratPakan13 = progress.getFeed_weight3();
        }

        progressesWatchList = db.getAllProgressByPond(pond_id, "Bulan 2");
        for (Progress progress : progressesWatchList) {
            Log.e("Progress2", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", "
                    + progress.getFeed_weight1()+", "+progress.getFeed_weight2()+", "+progress.getFeed_weight3());            progress_id = progress.getId();
            month = progress.getMonth();
            beratPakan21 = progress.getFeed_weight1();
            beratPakan22 = progress.getFeed_weight2();
            beratPakan23 = progress.getFeed_weight3();
        }

        progressesWatchList = db.getAllProgressByPond(pond_id, "Bulan 3");
        for (Progress progress : progressesWatchList) {
            Log.e("Progress3", progress.getId() + ", " + progress.getMonth() + ", " + progress.getWeight1() + ", "
                    + progress.getFeed_weight1()+", "+progress.getFeed_weight2()+", "+progress.getFeed_weight3());            progress_id = progress.getId();
            month = progress.getMonth();
            beratPakan31 = progress.getFeed_weight1();
            beratPakan32 = progress.getFeed_weight2();
            beratPakan33 = progress.getFeed_weight3();
        }

        totalPakan781_1 = ((beratPakan11 * 3) * 10) + ((beratPakan12 * 3) * 10) + ((beratPakan13 * 3) * 10);
        totalPakan781_2 = ((beratPakan21 * 3) * 10) + ((beratPakan22 * 3) * 10) + ((beratPakan23 * 3) * 10);
        totalPakan781_3 = ((beratPakan31 * 3) * 10) + ((beratPakan32 * 3) * 10) + ((beratPakan33 * 3) * 10);

        Log.e("Total", totalPakan781_1 + ", " + totalPakan781_2 + ", " + totalPakan781_3);



        int reportCount = db.getReportCount(pond_id);
        Log.e("LaporanKolam", "reportCOUNT :"+reportCount);
        if (reportCount == 0){
            Report report = new Report(pond_id, dateInString, totalPakan781_1, totalPakan781_2, totalPakan781_3);
            long report_id = db.createReport(report);

            Log.e("LaporanKolam", "report_id :"+report_id);
            Log.e("LaporanKolam", "1");
        }else{
            updateReport();
            Log.e("LaporanKolam", "2");

        }

        readReport();

    }

    private void readReport(){
        Report report = new Report();
        report = db.getReport(pond_id);

        Log.e("LAPORANKOLAM", report.getHarvestDate()+", "+report.getTotalFeed1()+", "+report.getTotalFeed2()+", "
        +report.getTotalFeed3());

        mTanggalPanenTextView.setText(report.getHarvestDate());
        mTotalPakan781_1TextView.setText(String.valueOf(report.getTotalFeed1()));
        mTotalPakan781_2TextView.setText(String.valueOf(report.getTotalFeed2()));
        mTotalPakan781_3TextView.setText(String.valueOf(report.getTotalFeed3()));
    }

    private void updateReport(){
        Report report = new Report(pond_id, dateInString, totalPakan781_1, totalPakan781_2, totalPakan781_3);
        int report_id = db.updateReport(report);

        Log.e("LaporanKolam", "report_id : "+report_id);
    }
}
