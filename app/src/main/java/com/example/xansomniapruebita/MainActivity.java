package com.example.xansomniapruebita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar proBa;
    Button btnMain;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proBa = findViewById(R.id.ProgresBarMain);
        btnMain = findViewById(R.id.btn_IngresarMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proBa.setVisibility(View.VISIBLE);

                Timer timecount = new Timer();
                TimerTask tiempo = new TimerTask() {
                    @Override
                    public void run() {
                        count++;
                        proBa.setProgress(count);
                        if (count == 50){
                            timecount.cancel();
                            Intent Secondmain = new Intent(MainActivity.this, SecondMain.class);
                            startActivity(Secondmain);
                        }
                    }
                };
                timecount.schedule(tiempo, 50, 50);
            }
        });
    }
}