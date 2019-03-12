package com.ari.android.budidayaikanlele.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ari.android.budidayaikanlele.model.Pond;
import com.ari.android.budidayaikanlele.model.PondProgress;
import com.ari.android.budidayaikanlele.model.Progress;
import com.ari.android.budidayaikanlele.model.Report;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.attr.negativeButtonText;
import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by David on 09/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "BudidayaLeleDB";

    //table names
//    private static final String TABLE_INFORMATION = "Information";
    private static final String TABLE_POND = "pond";
    private static final String TABLE_PROGRESS = "progress";
    private static final String TABLE_POND_PROGRESS = "pond_progress";
    private static final String TABLE_REPORT = "report";

    //common column names
    private static final String KEY_ID = "id";

    //Information Table - column names
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";

    //Pond Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_SEED_AMOUNT = "seed_amount";
    private static final String KEY_DATE = "initial_date";

    //Progress Table - column names
    private static final String KEY_MONTH = "month";
    private static final String KEY_WEIGHT1 = "weight1";
    private static final String KEY_FEED_WEIGHT1 = "feed_weight1";
    private static final String KEY_WEIGHT2 = "weight2";
    private static final String KEY_FEED_WEIGHT2 = "feed_weight2";
    private static final String KEY_WEIGHT3 = "weight3";
    private static final String KEY_FEED_WEIGHT3 = "feed_weight3";

    //Pond_Progress Table - column names
    private static final String KEY_POND_ID = "pond_id";
    private static final String KEY_PROGRESS_ID = "progress_id";

    //Report Table - column names
    private static final String KEY_HARVEST_DATE = "harvest_date";
    private static final String KEY_TOTAL_FEED1 = "total_feed1";
    private static final String KEY_TOTAL_FEED2 = "total_feed2";
    private static final String KEY_TOTAL_FEED3 = "total_feed3";

    //Table Create Statements
//    private static final String CREATE_TABLE_INFORMATION = "CREATE TABLE "
//            + TABLE_INFORMATION + "(" +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE
//            + " TEXT," + KEY_IMAGE + " BLOB," + KEY_DESCRIPTION
//            + " TEXT" + ")";
    private static final String CREATE_TABLE_POND = "CREATE TABLE "
            + TABLE_POND + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
            + " TEXT," + KEY_SEED_AMOUNT + " INTEGER," + KEY_DATE
            + " TEXT" + ")";
    private static final String CREATE_TABLE_PROGRESS = "CREATE TABLE "
            + TABLE_PROGRESS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MONTH
            + " TEXT," + KEY_WEIGHT1 + " FLOAT," + KEY_FEED_WEIGHT1
            + " FLOAT," + KEY_WEIGHT2 + " FLOAT," + KEY_FEED_WEIGHT2
            + " FLOAT," + KEY_WEIGHT3 + " FLOAT," + KEY_FEED_WEIGHT3
            + " FLOAT" + ")";
    private static final String CREATE_TABLE_POND_PROGRESS = "CREATE TABLE "
            + TABLE_POND_PROGRESS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_POND_ID
            + " INTEGER," + KEY_PROGRESS_ID + " INTEGER" + ")";
    private static final String CREATE_TABLE_REPORT = "CREATE TABLE "
            + TABLE_REPORT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_POND_ID
            + " INTEGER, " + KEY_HARVEST_DATE + " TEXT," + KEY_TOTAL_FEED1
            + " FLOAT," + KEY_TOTAL_FEED2 + " FLOAT," + KEY_TOTAL_FEED3
            + " FLOAT" + ")";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating required tables
//        db.execSQL(CREATE_TABLE_INFORMATION);
        db.execSQL(CREATE_TABLE_POND);
        db.execSQL(CREATE_TABLE_PROGRESS);
        db.execSQL(CREATE_TABLE_POND_PROGRESS);
        db.execSQL(CREATE_TABLE_REPORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POND_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORT);

        //create new tables
        onCreate(db);
    }

    //"Information" table methods

//    public long createInformation(Information information){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, information.getTitle());
//        values.put(KEY_IMAGE, information.getImage());
//        values.put(KEY_DESCRIPTION, information.getDescription());
//
//        //insert row
//        long information_id = db.insert(TABLE_INFORMATION, null, values);
//
//        return information_id;
//    }
//
//    public Information getInformation(long information_id){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT * FROM " + TABLE_INFORMATION + " WHERE "
//                + KEY_ID + " = " + information_id;
//
//        Log.e(LOG, selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c != null)
//            c.moveToFirst();
//
//            Information information = new Information();
//            information.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//            information.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
//            information.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
//            information.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
//
//            return information;
//    }
//
//    public List<Information> getAllInformation(){
//        List<Information> informations = new ArrayList<Information>();
//        String selectQuery = "SELECT * FROM " + TABLE_INFORMATION;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        //looping through all rows and adding to list
//        if (c.moveToFirst()){
//            do {
//                Information information = new Information();
//                information.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//                information.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
//                information.setImage(c.getBlob(c.getColumnIndex(KEY_IMAGE)));
//                information.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
//
//                //adding to information list
//                informations.add(information);
//            }while (c.moveToNext());
//        }
//
//        return informations;
//    }
//
//    //delete information
//    public void deleteInformation(long information_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_INFORMATION, KEY_ID + " = ?",
//                new String[]{String.valueOf(information_id)});
//    }
//
//
//    public int getInformationCount(){
//        String countQuery = "SELECT * FROM " + TABLE_INFORMATION;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//        return count;
//    }


    //"pond" table methods

    public long createPond(Pond pond, long[] progress1_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pond.getName());
        values.put(KEY_SEED_AMOUNT, pond.getSeed_amount());
        values.put(KEY_DATE, pond.getInital_date());

        //insert row
        long pond_id = db.insert(TABLE_POND, null, values);

        //insert progress_ids
        for(long progress_id : progress1_id){
            createPondProgress(pond_id, progress_id);
        }
        return pond_id;
    }

//    public long createPond(Pond pond, long[] progress1_id, long[] progress2_id,
//                           long[] progress3_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, pond.getName());
//        values.put(KEY_SEED_AMOUNT, pond.getSeed_amount());
//        values.put(KEY_DATE, pond.getDate());
//
//        //insert row
//        long pond_id = db.insert(TABLE_POND, null, values);
//
//        //insert progress_ids
//        for(long progress_id : progress1_id){
//            createPondProgress(pond_id, progress_id);
//        }
//        for(long progress_id : progress2_id){
//            createPondProgress(pond_id, progress_id);
//        }
//        for(long progress_id : progress3_id){
//            createPondProgress(pond_id, progress_id);
//        }
//
//        return pond_id;
//    }

    public Pond getPond(long pond_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_POND + " WHERE "
                + KEY_ID + " = " + pond_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Pond pond = new Pond();
        pond.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        pond.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        pond.setSeed_amount(c.getInt(c.getColumnIndex(KEY_SEED_AMOUNT)));
        pond.setInital_date(c.getString(c.getColumnIndex(KEY_DATE)));

        return pond;
    }

    public List<Pond> getAllPond(){
        List<Pond> ponds = new ArrayList<Pond>();
        String selectQuery = "SELECT * FROM " + TABLE_POND;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c.moveToFirst()){
            do {
                Pond pond = new Pond();
                pond.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                pond.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                pond.setSeed_amount(c.getInt(c.getColumnIndex(KEY_SEED_AMOUNT)));
                pond.setInital_date(c.getString(c.getColumnIndex(KEY_DATE)));

                //adding to information list
                ponds.add(pond);
            }while (c.moveToNext());
        }

        return ponds;
    }

    public int getPondCount(){
        String countQuery = "SELECT * FROM " + TABLE_POND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //update pond
    public int updatePond(Pond pond){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pond.getName());
        values.put(KEY_SEED_AMOUNT, pond.getSeed_amount());
        values.put(KEY_DATE, pond.getInital_date());

        //updateing row
        return db.update(TABLE_POND, values, KEY_ID + " = ?",
                new String[]{String.valueOf(pond.getId())});
    }

    //delete pond
    public void deletePond(String pond){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POND, KEY_NAME + " = ?",
                new String[]{String.valueOf(pond)});
    }

    //"progress" table methods

    public long createProgress(Progress progress){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONTH, progress.getMonth());
        values.put(KEY_WEIGHT1, progress.getWeight1());
        values.put(KEY_FEED_WEIGHT1, progress.getFeed_weight1());
        values.put(KEY_WEIGHT2, progress.getWeight2());
        values.put(KEY_FEED_WEIGHT2, progress.getFeed_weight2());
        values.put(KEY_WEIGHT3, progress.getWeight3());
        values.put(KEY_FEED_WEIGHT3, progress.getFeed_weight3());

        //insert row
        long progress_id = db.insert(TABLE_PROGRESS, null, values);

        return progress_id;
    }

    public Progress getProgress(long progress_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PROGRESS + " WHERE "
                + KEY_ID + " = " + progress_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Progress progress = new Progress();
        progress.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        progress.setMonth(c.getString(c.getColumnIndex(KEY_MONTH)));
        progress.setWeight1(c.getInt(c.getColumnIndex(KEY_WEIGHT1)));
        progress.setFeed_weight1(c.getInt(c.getColumnIndex(KEY_FEED_WEIGHT1)));
        progress.setWeight2(c.getInt(c.getColumnIndex(KEY_WEIGHT2)));
        progress.setFeed_weight2(c.getInt(c.getColumnIndex(KEY_FEED_WEIGHT2)));
        progress.setWeight3(c.getInt(c.getColumnIndex(KEY_WEIGHT3)));
        progress.setFeed_weight3(c.getInt(c.getColumnIndex(KEY_FEED_WEIGHT3)));

        return progress;
    }

    public List<Progress> getAllProgress(){
        List<Progress> progresses = new ArrayList<Progress>();
        String selectQuery = "SELECT * FROM " + TABLE_PROGRESS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c.moveToFirst()){
            do {
                Progress progress = new Progress();
                progress.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                progress.setMonth(c.getString(c.getColumnIndex(KEY_MONTH)));
                progress.setWeight1(c.getDouble(c.getColumnIndex(KEY_WEIGHT1)));
                progress.setFeed_weight1(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT1)));
                progress.setWeight2(c.getDouble(c.getColumnIndex(KEY_WEIGHT2)));
                progress.setFeed_weight2(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT2)));
                progress.setWeight3(c.getDouble(c.getColumnIndex(KEY_WEIGHT3)));
                progress.setFeed_weight3(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT3)));

                //adding to information list
                progresses.add(progress);
            }while (c.moveToNext());
        }

        return progresses;
    }

    /*
 * getting all progress under pond
 * */
    public List<Progress> getAllProgressByPond(int pond_id, String month) {
        List<Progress> progresses = new ArrayList<Progress>();

        String selectQuery = "SELECT  * FROM " + TABLE_PROGRESS + " td, "
                + TABLE_POND + " tg, " + TABLE_POND_PROGRESS + " tt WHERE tg."
                + KEY_ID + " = '" + pond_id + "'" + " AND td." + KEY_MONTH
                + " = '" + month + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_POND_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_PROGRESS_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Progress td = new Progress();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setMonth((c.getString(c.getColumnIndex(KEY_MONTH))));
                td.setWeight1(c.getDouble(c.getColumnIndex(KEY_WEIGHT1)));
                td.setFeed_weight1(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT1)));
                td.setWeight2(c.getDouble(c.getColumnIndex(KEY_WEIGHT2)));
                td.setFeed_weight2(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT2)));
                td.setWeight3(c.getDouble(c.getColumnIndex(KEY_WEIGHT3)));
                td.setFeed_weight3(c.getDouble(c.getColumnIndex(KEY_FEED_WEIGHT3)));

                // adding to todo list
                progresses.add(td);
            } while (c.moveToNext());
        }

        return progresses;
    }

    public int getProgressCount(){
        String countQuery = "SELECT * FROM " + TABLE_PROGRESS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //updating progress
    public int updateProgress(Progress progress){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MONTH, progress.getMonth());
        values.put(KEY_WEIGHT1, progress.getWeight1());
        values.put(KEY_FEED_WEIGHT1, progress.getFeed_weight1());
        values.put(KEY_WEIGHT2, progress.getWeight2());
        values.put(KEY_FEED_WEIGHT2, progress.getFeed_weight2());
        values.put(KEY_WEIGHT3, progress.getWeight3());
        values.put(KEY_FEED_WEIGHT3, progress.getFeed_weight3());

        //updateing row
        return db.update(TABLE_PROGRESS, values, KEY_ID + " = ?"+ " AND " + KEY_MONTH + " = ?",
                new String[]{String.valueOf(progress.getId()), String.valueOf(progress.getMonth())});
    }

    //deleting a progress
    public void deleteProgress(String month){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROGRESS, KEY_MONTH + " = ?" ,
                new String[]{String.valueOf(month)});
    }



    //"pond_progress" table methods

    //creating pond_progress
    public long createPondProgress(long pond_id, long progress_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POND_ID, pond_id);
        values.put(KEY_PROGRESS_ID, progress_id);

        long id = db.insert(TABLE_POND_PROGRESS, null, values);

        return id;
    }

    public List<PondProgress> getAllPondProgress(){
        List<PondProgress> pondProgresses = new ArrayList<PondProgress>();
        String selectQuery = "SELECT * FROM " + TABLE_POND_PROGRESS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (c.moveToFirst()){
            do {
                PondProgress pondProgress = new PondProgress();
                pondProgress.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                pondProgress.setPond_id(c.getInt(c.getColumnIndex(KEY_POND_ID)));
                pondProgress.setProgress_id(c.getInt(c.getColumnIndex(KEY_PROGRESS_ID)));;

                //adding to information list
                pondProgresses.add(pondProgress);
            }while (c.moveToNext());
        }

        return pondProgresses;
    }

    public int getPondProgressCount(){
        String countQuery = "SELECT * FROM " + TABLE_POND_PROGRESS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //updateing pond_progress
    public int updatePondProgress(long id, long progress_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROGRESS_ID, progress_id);

        //updating row
        return db.update(TABLE_POND, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

//    //delete pond
//    public void deletePond(String pond){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_POND, KEY_NAME + " = ?",
//                new String[]{String.valueOf(pond)});
//    }
//
//    //deleting a progress
//    public void deleteProgress(String month){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_PROGRESS, KEY_MONTH + " = ?" ,
//                new String[]{String.valueOf(month)});
//    }



    //deleting pond_progress
    public int deletePondProgress(int pond_id){
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_POND_PROGRESS, KEY_POND_ID + " = ?",
                new String[]{String.valueOf(pond_id)});

        return delete;
    }

    public void deletePondProgressByPond(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POND_PROGRESS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    //"report" table methods

    public long createReport(Report report){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POND_ID, report.getPond_id());
        values.put(KEY_HARVEST_DATE, report.getHarvestDate());
        values.put(KEY_TOTAL_FEED1, report.getTotalFeed1());
        values.put(KEY_TOTAL_FEED2, report.getTotalFeed2());
        values.put(KEY_TOTAL_FEED3, report.getTotalFeed3());

        //insert row
        long report_id = db.insert(TABLE_REPORT, null, values);

        return report_id;
    }


    public Report getReport(long pond_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_REPORT + " WHERE "
                + KEY_POND_ID + " = " + pond_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Report report = new Report();
//        report.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        report.setPond_id(c.getInt(c.getColumnIndex(KEY_POND_ID)));
        report.setHarvestDate(c.getString(c.getColumnIndex(KEY_HARVEST_DATE)));
        report.setTotalFeed1(c.getDouble(c.getColumnIndex(KEY_TOTAL_FEED1)));
        report.setTotalFeed2(c.getDouble(c.getColumnIndex(KEY_TOTAL_FEED2)));
        report.setTotalFeed3(c.getDouble(c.getColumnIndex(KEY_TOTAL_FEED3)));

        return report;
    }

    public int getReportCount(long pond_id){
        String countQuery = "SELECT * FROM " + TABLE_REPORT + " WHERE "
                + KEY_POND_ID + " = " + pond_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    //update pond
    public int updateReport(Report report){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POND_ID, report.getPond_id());
        values.put(KEY_HARVEST_DATE, report.getHarvestDate());
        values.put(KEY_TOTAL_FEED1, report.getTotalFeed1());
        values.put(KEY_TOTAL_FEED2, report.getTotalFeed2());
        values.put(KEY_TOTAL_FEED3, report.getTotalFeed3());

        //updating row
        return db.update(TABLE_REPORT, values, KEY_POND_ID + " = ?",
                new String[]{String.valueOf(report.getPond_id())});
    }


    //closing database
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
