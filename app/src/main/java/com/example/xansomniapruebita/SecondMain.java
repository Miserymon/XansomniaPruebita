package com.example.xansomniapruebita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SecondMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);

        Button btnMapita = findViewById(R.id.btn_Mapita);

        btnMapita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opStores = new Intent(SecondMain.this, TiendasGuitars.class);
                startActivity(opStores);
                //op por el "open" kasdljkas k inteligente
            }
        });

        Button btnGuitarList = findViewById(R.id.btn_ListasAdd);
        btnGuitarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opLists = new Intent(SecondMain.this, ListaGuitars.class);
                startActivity(opLists);
                // lo mismo op :b
            }
        });

    }




}