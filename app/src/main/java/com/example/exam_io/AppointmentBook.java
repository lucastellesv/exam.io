package com.example.exam_io;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exam_io.data.ExamDataAccessObject;
import com.example.exam_io.models.Exam;

import java.util.ArrayList;
import java.util.List;

public class AppointmentBook extends AppCompatActivity {
    List<Exam> exames;
    ListAdapter ListaExamesAdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alerta;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Como usar?");
        builder.setMessage("Dê um clique único sob o exame para edita-lo. Pressione o exame agendado para excluir.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(AppointmentBook.this, "" + arg1, Toast.LENGTH_SHORT);
            }
        });
        alerta = builder.create();
        alerta.show();

        setContentView(R.layout.activity_appointment_book);
        ExamDataAccessObject Data = (ExamDataAccessObject)getIntent().getSerializableExtra("lista");

        if(Data.getAll()==null){
            exames = new ArrayList<>();
        }else{
            exames = Data.getAll();
        }

        ListView ListaExames = findViewById(R.id.list_view_exames);
        ListaExamesAdp = new Adapter(exames, this);
        ListaExames.setAdapter(ListaExamesAdp);

        ListaExames.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exam todos_exames = exames.get(position);
                Intent go_to_edit_exam = new Intent(AppointmentBook.this, ScheduleExam.class);
                go_to_edit_exam.putExtra("Exam", todos_exames);
                go_to_edit_exam.putExtra("ExamDataAccessObject", Data);
                startActivity(go_to_edit_exam);

            }
        });
        
        ListaExames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent go_to_delete = new Intent(AppointmentBook.this, Delete.class);
                go_to_delete.putExtra("exames", Data);
                go_to_delete.putExtra("index", position);
                startActivity(go_to_delete);
                return true;
            }

        });
    }
}
