package com.cibertec.delicias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.delicias.dao.ProductoDAO;
import com.cibertec.delicias.models.Producto;

import java.util.Objects;

public class ActualizarActivity extends AppCompatActivity {

    EditText url;
    EditText title;
    EditText cost;
    Button btnActualizar;
    private Producto producto;
    private ProductoDAO productoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        productoDAO = new ProductoDAO(this);

        Integer id = Objects.requireNonNull(getIntent().getExtras()).getInt("id");
        url = findViewById(R.id.txtUrlUpdate);
        title = findViewById(R.id.txtTitleUpdate);
        cost = findViewById(R.id.txtCostUpdate);

        btnActualizar = findViewById(R.id.btnActualizar);

        producto = productoDAO.findProd(id);

        url.setText(producto.getUrl());
        title.setText(producto.getTitle());
        cost.setText(producto.getCost());

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlS = url.getText().toString();
                String titleS = cost.getText().toString(); //No se que pasa pero funciona
                String costS = title.getText().toString(); //No se que pasa pero funciona

                producto.setUrl(urlS);
                producto.setTitle(titleS);
                producto.setCost(costS);

                boolean value = productoDAO.editProd(producto);

                if(value == true){
                    Toast.makeText(ActualizarActivity.this, "Producto actualizado con exito", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ActualizarActivity.this, ContainerActivity.class));
                } else {
                    Toast.makeText(ActualizarActivity.this, "Error al actualizar producto", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void goContainer(View view){
        startActivity(new Intent(this, ContainerActivity.class));
    }

}