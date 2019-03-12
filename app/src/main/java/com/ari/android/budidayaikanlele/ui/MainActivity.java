package com.ari.android.budidayaikanlele.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ari.android.budidayaikanlele.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton tentangImageButton;
    Button panduanBudidayaButton, kolamButton, keluarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tentangImageButton = (ImageButton) findViewById(R.id.tentang_image_button);
        panduanBudidayaButton = (Button) findViewById(R.id.panduanbudaya_button);
        kolamButton = (Button) findViewById(R.id.kolam_button);
        keluarButton = (Button) findViewById(R.id.keluar_button);

        tentangImageButton.setOnClickListener(this);
        panduanBudidayaButton.setOnClickListener(this);
        kolamButton.setOnClickListener(this);
        keluarButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tentang_image_button){
            Intent i = new Intent(MainActivity.this, TentangActivity.class);
            startActivity(i);
        }else if (id == R.id.panduanbudaya_button){
            Intent i = new Intent(MainActivity.this, PanduanBudidayaActivity.class);
            startActivity(i);
            finish();
        }else if (id == R.id.kolam_button){
            Intent i = new Intent(MainActivity.this, KolamActivity.class);
            startActivity(i);
        }else if (id == R.id.keluar_button){
            finish();
        }
    }
}
