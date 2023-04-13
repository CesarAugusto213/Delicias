package com.cibertec.delicias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.delicias.dao.ProductoDAO;
import com.cibertec.delicias.login.CreateAccountActivity;
import com.cibertec.delicias.models.Producto;

public class RegistrarActivity extends AppCompatActivity {

    EditText url;
    EditText title;
    EditText cost;
    Button btnAgregar;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        productoDAO = new ProductoDAO(this);

        url = findViewById(R.id.txtUrl);
        title = findViewById(R.id.txtTitle);
        cost = findViewById(R.id.txtCost);
        btnAgregar = findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProd();
            }
        });

    }

    private void registrarProd(){
        String urlS = url.getText().toString();
        String titleS = title.getText().toString();
        String costS = cost.getText().toString();

        if (!urlS.isEmpty() && !titleS.isEmpty() && !costS.isEmpty()) {
            boolean value = productoDAO.insertProd(new Producto(urlS, titleS, costS));

            if(value == true) {
                Toast.makeText(RegistrarActivity.this, "Producto agregado con exito", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegistrarActivity.this, ContainerActivity.class));
            } else {
                Toast.makeText(RegistrarActivity.this, "Error al agregar producto", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(RegistrarActivity.this, "No puede haber campos vacios", Toast.LENGTH_LONG).show();
        }

    }

    public void goContainer(View view){
        startActivity(new Intent(this, ContainerActivity.class));
    }
}