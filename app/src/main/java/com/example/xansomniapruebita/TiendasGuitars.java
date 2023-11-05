package com.example.xansomniapruebita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TiendasGuitars extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLat, txtLong;

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas_guitarras);
        txtLat=findViewById(R.id.txt_Latitud);
        txtLong=findViewById(R.id.txt_Longitud);
        Button btnVolver = findViewById(R.id.btn_mapVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TiendasGuitars.this, SecondMain.class);
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.TiendasGuitarra);
        mapFragment.getMapAsync(this);
    }
    private void agregarTienda(double latitud, double longitud, String titulo) {
        LatLng ubicacion = new LatLng(latitud, longitud);
        gMap.addMarker(new MarkerOptions().position(ubicacion).title(titulo));
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap=googleMap;
        this.gMap.setOnMapClickListener(this);
        this.gMap.setOnMapLongClickListener(this);
        LatLng UbicacionSur = new LatLng(-39.115657423017815, -72.43117220699787);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UbicacionSur, 10));
        agregarTienda(-39.356774, -72.153430, "Huincacara music");
        agregarTienda(-38.975260, -72.604938, "Qulquilil rock");
        agregarTienda(-39.1016509,-72.6707419, "True Gorbeian black metal");
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLat.setText(""+latLng.latitude);
        txtLong.setText(""+latLng.longitude);

        agregarTienda(latLng.latitude, latLng.longitude, "Marcador de toque");
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLat.setText(""+latLng.latitude);
        txtLong.setText(""+latLng.longitude);

        agregarTienda(latLng.latitude, latLng.longitude, "Marcador de toque");
    }

}