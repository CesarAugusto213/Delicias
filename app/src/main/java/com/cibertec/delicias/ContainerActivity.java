package com.cibertec.delicias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cibertec.delicias.adapter.PictureAdapterRecyclerView;
import com.cibertec.delicias.models.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    Button btnMapa;
    PictureAdapterRecyclerView adapterView;
    LinearLayoutManager layoutManager;

    private ArrayList<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        recyclerView = findViewById(R.id.pictureRecycler);
        floatingActionButton = findViewById(R.id.floating);
        btnMapa = findViewById(R.id.btnMapa);

        productos = new ArrayList<>();

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContainerActivity.this, MapActivity.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContainerActivity.this, RegistrarActivity.class));
            }
        });

        recycler();

    }

    private void recycler(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterView = new PictureAdapterRecyclerView(productos);
        recyclerView.setAdapter(adapterView);
        //Content();
        //deleteSwipe();
    }

}