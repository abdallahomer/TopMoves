package com.example.prog_abdallah.topmoves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NoInternetActivity extends AppCompatActivity {
    TextView part_1;
    TextView part_2;
    TextView part_3;
    TextView part_4;
    TextView part_5;
    TextView part_6;
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        part_1 = (TextView) findViewById(R.id.t_no_internet_p1);
        part_2 = (TextView) findViewById(R.id.t_no_internet_p2);
        part_3 = (TextView) findViewById(R.id.t_no_internet_p3);
        part_4 = (TextView) findViewById(R.id.t_no_internet_p4);
        part_5 = (TextView) findViewById(R.id.t_no_internet_p5);
        part_6 = (TextView) findViewById(R.id.t_no_internet_p6);
        retry = (Button) findViewById(R.id.t_retry_id);

        part_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        part_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CheckInternet.isOnline(getApplicationContext())){
                    Intent i = new Intent(NoInternetActivity.this,MoviesActivity.class);
                    startActivity(i);
                }else{
                    finish();
                    startActivity(getIntent());
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,MoviesActivity.class);
        startActivity(i);
    }
}
