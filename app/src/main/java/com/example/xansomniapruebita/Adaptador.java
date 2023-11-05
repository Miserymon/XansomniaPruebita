package com.example.xansomniapruebita;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Guitar> lista;
    daoGuitar dao;
    Guitar guitar;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Guitar> lista, daoGuitar dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        guitar = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        guitar = lista.get(i);
        return guitar.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        guitar = lista.get(posicion);

        TextView guitarModel = v.findViewById(R.id.txt_guitarmodel);
        TextView guitarBrand = v.findViewById(R.id.txt_guitarbrand);
        TextView guitarType = v.findViewById(R.id.txt_guitartype);
        TextView guitarPrice = v.findViewById(R.id.txt_precio);
        TextView storeLocation = v.findViewById(R.id.txt_storelocation);

        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);

        guitarModel.setText(guitar.getGuitarModel());
        guitarBrand.setText(guitar.getGuitarBrand());
        guitarType.setText(guitar.getGuitarType());
        guitarPrice.setText(guitar.getGuitarPrice());
        storeLocation.setText(guitar.getStoreLocation());

        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog);
                dialog.show();

                final EditText guitarModel = dialog.findViewById(R.id.et_guitarbrand);
                final EditText guitarBrand = dialog.findViewById(R.id.et_guitarmodel);
                final EditText guitarType = dialog.findViewById(R.id.et_guitartype);
                final EditText guitarPrice = dialog.findViewById(R.id.et_guitarprice);
                final EditText storeLocation = dialog.findViewById(R.id.et_guitarstoreloca);

                Button guardar = dialog.findViewById(R.id.btn_addtolist);
                Button cancelar = dialog.findViewById(R.id.btn_cancelthisshit);

                guitar = lista.get(pos);
                setId(guitar.getId());

                guitarModel.setText(guitar.getGuitarModel());
                guitarBrand.setText(guitar.getGuitarBrand());
                guitarType.setText(guitar.getGuitarType());
                guitarPrice.setText(guitar.getGuitarPrice());
                storeLocation.setText(guitar.getStoreLocation());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            guitar = new Guitar(getId(), guitarModel.getText().toString(),
                                    guitarBrand.getText().toString(),
                                    guitarType.getText().toString(),
                                    guitarPrice.getText().toString(),
                                    storeLocation.getText().toString());
                            dao.editar(guitar);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                guitar = lista.get(pos);
                setId(guitar.getId());

                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("¿Estás seguro de eliminar?");
                del.setCancelable(false);

                del.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }
                });

                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                del.show();
            }
        });

        return v;
    }
}
