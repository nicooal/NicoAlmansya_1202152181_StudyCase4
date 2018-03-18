package com.example.nico.nicoalmansya_1202152181_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnList, btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = (Button)findViewById(R.id.btn_mhsw);
        btnCari = (Button)findViewById(R.id.btn_gambar);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListMahasiswa.class);
                startActivity(i);
            }
        }); //Menjalankan Activity ListMahasiswa

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, CariGambar.class);
                startActivity(j);
            }
        }); //Menjalankan Activity CariGambar
    }
}
