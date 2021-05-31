package com.example.exam_io;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.exam_io.data.ExamDataAccessObject;
import com.example.exam_io.models.Exam;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Activity {
    private Button botao_agenda = null;
    private Button botao_agendar_exame = null;
    private Button botao_sair = null;
    List<Exam> exames;
    ExamDataAccessObject Data;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        exames = new ArrayList<>();
        Data = new ExamDataAccessObject(exames);



       ExamDataAccessObject Data_ = (ExamDataAccessObject)getIntent().getSerializableExtra("ExamDataAccessObject");
       if(Data_ != null){
           Data = Data_;
        }
        botao_agendar_exame = this.findViewById(R.id.botao_agendar_exame);
        botao_agendar_exame.setOnClickListener((View v) -> {
            Intent go_to_agendar = new Intent(HomePage.this, ScheduleExam.class);
            go_to_agendar.putExtra("ExamDataAccessObject", Data);
            startActivity(go_to_agendar);
        });

        botao_agenda = this.findViewById(R.id.botao_agenda);
        botao_agenda.setOnClickListener((View v) -> {
            Intent go_to_agenda = new Intent(HomePage.this, AppointmentBook.class);
            go_to_agenda.putExtra("lista", Data);
            startActivity(go_to_agenda);
        });

        botao_sair = this.findViewById(R.id.botao_sair);
        botao_sair.setOnClickListener((View v) -> {
            Intent go_to_login = new Intent(HomePage.this, MainActivity.class);
            startActivity(go_to_login);
        });
    }
}