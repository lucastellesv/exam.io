package com.example.exam_io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button entrar = null;
    private void alert( String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrar = this.findViewById(R.id.botao_entrar);
        entrar.setOnClickListener((View v) -> {
            TextView email = (TextView) findViewById(R.id.usuario_textview_input);
            TextView senha = (TextView) findViewById(R.id.senha_textview_input);
            String email_str = email.getText().toString();
            String senha_str = senha.getText().toString();
            if(email_str.equals("devtest") && senha_str.equals("passtest")){
                alert("Sessão iniciada!");
                Intent go_to_homepage = new Intent(MainActivity.this, HomePage.class);
                startActivity(go_to_homepage);
            } else {
                alert("Usuario e/ou senha incorreto(s)");
            }
        });

    }
}