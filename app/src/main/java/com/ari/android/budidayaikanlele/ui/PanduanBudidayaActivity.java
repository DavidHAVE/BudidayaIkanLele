package com.ari.android.budidayaikanlele.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.ari.android.budidayaikanlele.R;
import com.ari.android.budidayaikanlele.adapter.DrawerItemCustomAdapter;
import com.ari.android.budidayaikanlele.fragment.HamaPenyakitFragment;
import com.ari.android.budidayaikanlele.fragment.PanenFragment;
import com.ari.android.budidayaikanlele.fragment.PembuatanKolamFragment;
import com.ari.android.budidayaikanlele.fragment.PemilihanBibitFragment;
import com.ari.android.budidayaikanlele.fragment.PerawatanPakanFragment;
import com.ari.android.budidayaikanlele.helper.Constant;
import com.ari.android.budidayaikanlele.helper.DatabaseHelper;
import com.ari.android.budidayaikanlele.model.DataModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static android.R.attr.tag;

public class PanduanBudidayaActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    FrameLayout contentFrame;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_budidaya);

        contentFrame = (FrameLayout) findViewById(R.id.content_frame);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[5];

        drawerItem[0] = new DataModel(R.drawable.pool_24, "Pembuatan Kolam");
        drawerItem[1] = new DataModel(R.drawable.seed_24, "Pemilihan Bibit");
        drawerItem[2] = new DataModel(R.drawable.food_24, "Perawatan dan Pakan");
        drawerItem[3] = new DataModel(R.drawable.insect_24, "Hama dan Penyakit");
        drawerItem[4] = new DataModel(R.drawable.harvest_24, "Panen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setupDrawerToggle();

//        db = new DatabaseHelper(getApplicationContext());
//
//        //mendapatkan image dari drawable
//        Bitmap imageKolam1 = BitmapFactory.decodeResource(getResources(), R.drawable.kolam_tanah);
//        Bitmap imageKolam2 = BitmapFactory.decodeResource(getResources(), R.drawable.kolam_terpal_atas_tanah);
//        Bitmap imageKolam3 = BitmapFactory.decodeResource(getResources(), R.drawable.kolam_terpal_bawah_tanah);
//        Bitmap imageKolam4 = BitmapFactory.decodeResource(getResources(), R.drawable.kolam_beton);
//
//        Bitmap imageBibit = BitmapFactory.decodeResource(getResources(), R.drawable.benih_lele);
//
//        //image perawatan dan pakan tidak ada
//
//        Bitmap imagePenyakit1 = BitmapFactory.decodeResource(getResources(), R.drawable.penyakit_bintik_putih);
//        Bitmap imagePenyakit2 = BitmapFactory.decodeResource(getResources(), R.drawable.penyakit_bakterial);
//        Bitmap imagePenyakit3 = BitmapFactory.decodeResource(getResources(), R.drawable.penyakit_jamur);
//
//        Bitmap imagePanen = BitmapFactory.decodeResource(getResources(), R.drawable.panen_lele);
//
//        //konvert bitmap ke byte
////        ByteArrayOutputStream stream = new ByteArrayOutputStream();
////        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//        byte imagePondInByte1[] = DbBitmapUtility.getBytes(imageKolam1);
//        byte imagePondInByte2[] = DbBitmapUtility.getBytes(imageKolam2);
//        byte imagePondInByte3[] = DbBitmapUtility.getBytes(imageKolam3);
//        byte imagePondInByte4[] = DbBitmapUtility.getBytes(imageKolam4);
//
//        byte imageSeedInByte1[] = DbBitmapUtility.getBytes(imageBibit);
//
//        byte imagePenyakitInByte1[] = DbBitmapUtility.getBytes(imagePenyakit1);
//        byte imagePenyakitInByte2[] = DbBitmapUtility.getBytes(imagePenyakit2);
//        byte imagePenyakitPondInByte3[] = DbBitmapUtility.getBytes(imagePenyakit3);
//
//        byte imagePanenInByte1[] = DbBitmapUtility.getBytes(imagePanen);
//
////        byte imageInByte[] = stream.toByteArray();
//
//        //creating information
//        Information information = new Information(getResources().getString(R.string.title_kolam1), imagePondInByte1, getResources().getString(R.string.description_kolam1));
//        Information information1 = new Information(getResources().getString(R.string.title_kolam2), imagePondInByte2, getResources().getString(R.string.description_kolam2));
//        Information information2 = new Information(getResources().getString(R.string.title_kolam3), imagePondInByte3, getResources().getString(R.string.description_kolam3));
//        Information information3 = new Information(getResources().getString(R.string.title_kolam4), imagePondInByte4, getResources().getString(R.string.description_kolam4));
//
//        //inserting information in db
//        long information_id = db.createInformation(information);
//        long information1_id = db.createInformation(information1);
//        long information2_id = db.createInformation(information2);
//        long information3_id = db.createInformation(information3);
//
//        int count = db.getInformationCount();
//        Log.e("PanduanBudidaya", "count :"+count+", information_id :"+information_id+", information1_id :"+information_id);
//
////        informations = db.getAllInformation();
//
//        List<Information> allInformations = db.getAllInformation();
//        for (Information informationn : allInformations) {
//            Log.e("Tag Name", informationn.getTitle()+", image : " +informationn.getImage()+ ", deskripsi : "+informationn.getDescription());
//        }
////        Log.e("HamaPenyakit", "Id : "+informations.get(2).getId()+" Title : "+informations.get(2).getTitle());
//        db.closeDB();

        getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new PembuatanKolamFragment()).commit();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new PembuatanKolamFragment();
                break;
            case 1:
                fragment = new PemilihanBibitFragment();
                break;
            case 2:
                fragment = new PerawatanPakanFragment();
                break;
            case 3:
                fragment = new HamaPenyakitFragment();
                break;
            case 4:
                fragment = new PanenFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cultivation_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        if (id ==  R.id.home_action_menu){
            Intent i = new Intent(PanduanBudidayaActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PanduanBudidayaActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar, R.string.panduan_budidaya, R.string.panduan_budidaya);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}
