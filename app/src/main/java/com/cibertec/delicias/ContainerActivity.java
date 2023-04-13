package com.cibertec.delicias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cibertec.delicias.adapter.PictureAdapterRecyclerView;
import com.cibertec.delicias.dao.ProductoDAO;
import com.cibertec.delicias.login.LoginActivity;
import com.cibertec.delicias.models.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContainerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingButton;
    Button btnMapa;
    PictureAdapterRecyclerView adapterView;
    LinearLayoutManager layoutManager;

    private ArrayList<Producto> productos;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        recyclerView = findViewById(R.id.pictureRecycler);
        floatingButton = findViewById(R.id.floating);
        btnMapa = findViewById(R.id.btnMapa);

        productos = new ArrayList<>();
        productoDAO = new ProductoDAO(this);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContainerActivity.this, MapActivity.class));
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContainerActivity.this, RegistrarActivity.class));
            }
        });

        recycler();

    }

    private void recycler(){
        productos.clear();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        productos = productoDAO.listProd();
        adapterView = new PictureAdapterRecyclerView(productos);
        recyclerView.setAdapter(adapterView);
        adapterView.notifyDataSetChanged();
        deleteSwipe();
    }

    private void deleteSwipe(){
        ItemTouchHelper.SimpleCallback touchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                boolean value = productoDAO.delete(viewHolder.getAdapterPosition());
                adapterView.deleteItem(viewHolder.getAdapterPosition());
                if (value == false) {
                    Toast.makeText(ContainerActivity.this, "No se pudo eliminar el producto", Toast.LENGTH_LONG).show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}