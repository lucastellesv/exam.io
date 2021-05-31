package com.example.exam_io;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Toast.makeText(SplashActivity.this, "Bem Vindo!!!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 3000);
    }
}
