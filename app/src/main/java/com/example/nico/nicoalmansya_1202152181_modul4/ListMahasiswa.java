package com.example.nico.nicoalmansya_1202152181_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private String[] mUsers = {
            "Dina", "Rian", "Gina", "Osas", "Reno",
            "Lina", "Roi", "Shifa", "Budi", "Asep",
            "Adi", "Ida", "Eni", "Ine"};

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;

    //Berisi komponen-komponen yang digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mListView = (ListView) findViewById(R.id.list_view);
        mStartAsyncTask = (Button) findViewById(R.id.button_async);
        mListView.setVisibility(View.GONE);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        ////Menginisialisasi komponen yang digunakan

        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();

                //Mengeksekusi AsyncTask saat Button diklik
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);

        //Berisi komponen-komponen yang digunakan

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //for progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show();

            //Digunakan untuk mengeksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mUsers) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;

            //Digunakan untuk menjalankan aktivitas dibackground dengan AsyncTask
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter / (float) mUsers.length) * 100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;

            //Digunakan untuk menghitung presentase progress dialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);

            //Digunakan untuk mengeksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
