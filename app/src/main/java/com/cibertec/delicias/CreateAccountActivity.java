package com.cibertec.delicias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.delicias.dao.UsuarioDAO;
import com.cibertec.delicias.models.Usuario;

public class CreateAccountActivity extends AppCompatActivity {

    UsuarioDAO dao;
    Usuario usuario;
    EditText mail, name, user, pass, confirmPass;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dao = new UsuarioDAO(this);

        mail = findViewById(R.id.mailCreateAccount);
        name = findViewById(R.id.nameCreateAccount);
        user = findViewById(R.id.userCreateAccount);
        pass = findViewById(R.id.passwordCreateAccount);
        confirmPass = findViewById(R.id.confirmCreateAccount);
        registrar = findViewById(R.id.registrarAccount);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

    }

    private void registrar() {
        String mailS = mail.getText().toString();
        String nameS = name.getText().toString();
        String userS = user.getText().toString();
        String passS = pass.getText().toString();
        String confirmS = confirmPass.getText().toString();

        if (!mailS.isEmpty() && !nameS.isEmpty() && !userS.isEmpty() && !passS.isEmpty() && !confirmS.isEmpty()) {
            if (passS.equals(confirmS)) {
                usuario = new Usuario(mailS, nameS, userS, passS);
                boolean value = dao.insertUser(usuario);

                if (value == true) {
                    Toast.makeText(CreateAccountActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateAccountActivity.this, ContainerActivity.class));
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Error al registrar", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(CreateAccountActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CreateAccountActivity.this, "No puede haber campos vacios", Toast.LENGTH_LONG).show();
        }

    }

    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

}