package com.example.exam_io;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam_io.data.ExamDataAccessObject;
import com.example.exam_io.models.Exam;


import java.util.ArrayList;
import java.util.List;

public class ScheduleExam extends Activity {



    private void alert( String text){
        Toast.makeText(ScheduleExam.this, text, Toast.LENGTH_LONG).show();
    }
    List<Exam> exames;
    Spinner exames_spinner_list = null;
    Button enviar = null;
    Exam exam_model = new Exam();
    TextView text_view_nome;
    TextView text_view_cpf;
    TextView text_view_email;
    EditText data_exame;
    Spinner spinner_exame;
    boolean  ehValido = true;
    boolean editar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        text_view_nome = findViewById(R.id.nome_schedule_exam);
        text_view_email = findViewById(R.id.email_schedule_exam);
        text_view_cpf = findViewById(R.id.cpf_schedule_exam);
        data_exame = findViewById(R.id.exam_schedule_date);
        spinner_exame = findViewById((R.id.spinner_exames));

        //Gerando a lista do spinner de tipos de exames
        exames_spinner_list = findViewById(R.id.spinner_exames);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.exames_spinner, android.R.layout.simple_dropdown_item_1line);
        exames_spinner_list.setAdapter(adapter);

       ExamDataAccessObject Data = (ExamDataAccessObject)getIntent().getSerializableExtra("ExamDataAccessObject");
       Exam exam = (Exam)getIntent().getSerializableExtra("Exam");
       if(Data.getAll()==null){
           exames = new ArrayList<>();
       }else{
           exames = Data.getAll();
       }
       if(exam!=null){
           editar = true;
           text_view_nome.setText(exam.getNome());
           text_view_email.setText(exam.getEmail());
           text_view_cpf.setText(exam.getCpf());
           data_exame.setText(exam.getData());
       }

        enviar = this.findViewById(R.id.enviar_agendamento);
        enviar.setOnClickListener((View v) -> {
            if(editar){
                for (Exam e:exames){
                    if(e.getCpf().equals(exam.getCpf())){
                        exam_model = e;
                    }
                }
            }

            exam_model.setNome(text_view_nome.getText().toString());

            exam_model.setEmail(text_view_email.getText().toString());

            exam_model.setCpf(text_view_cpf.getText().toString());

            exam_model.setData(data_exame.getText().toString());

            exam_model.setTipoExame(spinner_exame.getSelectedItem().toString());
            if(!editar) {
                for (Exam e : exames) {
                    if (e.getData().equals(data_exame.getText().toString())) {
                        alert("Esta data est?? indisponivel, tente outra data por favor!");
                        ehValido = false;
                    }
                }
            }

            if (text_view_nome.getText().toString().isEmpty() || text_view_email.getText().toString().isEmpty() || text_view_cpf.getText().toString().isEmpty() || data_exame.getText().toString().isEmpty()){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_schedule_exam);
                AlertDialog alerta;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Como preencher?");
                builder.setMessage("N??o esque??a nenhum campo em branco na hora de agendar seu exame. O CPF deve ser inserido somente com n??meros e a data deve ser separada por '/' no formato DD/MM/AAAA. Se a data do exame estiver ocupada, ser?? necess??rio escolher outra data que seja valida.");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alerta = builder.create();
                alerta.show();
                ehValido = false;
            }
            if(spinner_exame.getSelectedItem().toString().equals("Selecione um item")){
                alert("Por favor, selecione o exame desejado");
                ehValido = false;
            }
            if(text_view_cpf.getText().toString().length() < 11 || text_view_cpf.getText().toString().length() > 11){
                alert("A quantidade de n??meros do CPF est?? menor/maior do que o esperado. Corrija e tente novamente.");
                ehValido = false;
            }
            if(data_exame.getText().toString().length() < 10 || data_exame.getText().toString().length() > 10){
                alert("O formato correto da data do exame ?? DD/MM/AAAA. Por favor, corrija e tente novamente.");
                ehValido = false;
            }
            if(ehValido){
                alert("O seu exame foi agendado e j?? pode ser visto na agenda.");
                Intent back_to_home = new Intent(ScheduleExam.this, HomePage.class);
                if(!editar) {
                    exames.add(exam_model);
                }
                Data.setAll(exames);
                back_to_home.putExtra("ExamDataAccessObject", Data);
                startActivity(back_to_home);
            } else {
                ehValido = true;
            }


        });



    }
}