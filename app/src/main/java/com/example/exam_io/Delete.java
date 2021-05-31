package com.example.exam_io;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exam_io.data.ExamDataAccessObject;
import com.example.exam_io.models.Exam;

import java.util.List;


public class Delete extends AppCompatActivity {
    Button sim;
    Button nao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ExamDataAccessObject Data = (ExamDataAccessObject) getIntent().getSerializableExtra("exames");
        int pos = (int) getIntent().getSerializableExtra("index");

        sim = this.findViewById(R.id.botao_aceitar);
        sim.setOnClickListener((View v) -> {
            List<Exam> examesList = Data.getAll();
            examesList.remove(pos);
            Data.setAll(examesList);
            Intent sim = new Intent(Delete.this, HomePage.class);
            sim.putExtra("ExamDataAccessObject", Data);
            startActivity(sim);
        });
        nao = this.findViewById(R.id.botao_rejeitar);
        nao.setOnClickListener((View v) -> {
            Intent nao = new Intent(Delete.this, AppointmentBook.class);
            startActivity(nao);
        });
    }
}
