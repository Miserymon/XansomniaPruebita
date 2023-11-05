package com.example.xansomniapruebita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaGuitars extends AppCompatActivity {
    daoGuitar dao;
    Adaptador adapter;
    ArrayList<Guitar> lista;
    Guitar guitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_guitars);

        dao = new daoGuitar(ListaGuitars.this);
        lista = dao.verTodo();
        adapter = new Adaptador(this, lista, dao);
        ListView list = findViewById(R.id.list_guitar);
        Button addGuitar = findViewById(R.id.btn_add_guitar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        addGuitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ListaGuitars.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                final EditText guitarModel = dialog.findViewById(R.id.et_guitarmodel);
                final EditText guitarBrand = dialog.findViewById(R.id.et_guitarbrand);
                final EditText guitarType = dialog.findViewById(R.id.et_guitartype);
                final EditText guitarPrice = dialog.findViewById(R.id.et_guitarprice);
                final EditText storeLocation = dialog.findViewById(R.id.et_guitarstoreloca);
                Button guardar = dialog.findViewById(R.id.btn_addtolist);
                guardar.setText("Agregar Guitarra");
                Button cancelar = dialog.findViewById(R.id.btn_cancelthisshit);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            guitar = new Guitar(guitarModel.getText().toString(),
                                    guitarBrand.getText().toString(),
                                    guitarType.getText().toString(),
                                    guitarPrice.getText().toString(),
                                    storeLocation.getText().toString());
                            dao.insertar(guitar);
                            lista = dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
