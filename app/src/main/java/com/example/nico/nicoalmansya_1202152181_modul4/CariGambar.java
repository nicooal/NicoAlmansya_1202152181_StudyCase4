package com.example.nico.nicoalmansya_1202152181_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class CariGambar extends AppCompatActivity {

    private EditText edUrl;
    private Button btnCari;
    private ImageView ivGambar;
    private ProgressDialog progressDialog;
    private String text;

    //Berisi komponen-komponen yang digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        edUrl = (EditText) findViewById(R.id.ed_url);
        btnCari = (Button) findViewById(R.id.btn_cari);
        ivGambar = (ImageView) findViewById(R.id.iv_gambar);

        //Menginisialisasi komponen yang digunakan
    }

    public void cariGambar(View view) {
        text = edUrl.getText().toString();
        //Mengubah Text menjadi String
        if (text.isEmpty()) {
            Toast.makeText(this, "Masukkan URL gambar", Toast.LENGTH_LONG).show();
            //Memunculkan Toast jika EditText kosong
        } else {
            new LoadingGambar().execute(text);
            //Mengeksekusi jika EditText tidak kosong
        }
    }

    private class LoadingGambar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CariGambar.this);
            progressDialog.setMessage("Loading");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            //Digunakan untuk mengeksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;

            //Digunakan untuk menjalankan aktivitas dibackground dengan AsyncTask
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivGambar.setImageBitmap(bitmap);
            progressDialog.dismiss();

            //Digunakan untuk mengeksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
