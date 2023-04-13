package com.cibertec.delicias.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.delicias.ContainerActivity;
import com.cibertec.delicias.R;
import com.cibertec.delicias.dao.UsuarioDAO;
import com.cibertec.delicias.models.Usuario;

public class LoginActivity extends AppCompatActivity {
    UsuarioDAO dao;
    EditText mail, pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = new UsuarioDAO(this);

        mail = findViewById(R.id.mailLogin);
        pass = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean value = dao.findUser(mail.getText().toString(), pass.getText().toString());

                if (value == true) {
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "El email o el password son incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void goCreateAccount(View view) {
        startActivity(new Intent(this, CreateAccountActivity.class));
    }
}