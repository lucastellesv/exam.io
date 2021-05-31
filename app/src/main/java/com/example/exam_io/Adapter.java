package com.example.exam_io;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exam_io.models.Exam;

import java.util.List;

public class Adapter extends BaseAdapter {
    private List<Exam> exame = null;
    private Activity Lista;

    public Adapter (List<Exam> exame, Activity Lista){
        this.exame = exame;
        this.Lista = Lista;
    }

    @Override
    public int getCount() {
        return this.exame.size();
    }

    @Override
    public Object getItem(int position) {
        return this.exame.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layout = Lista.getLayoutInflater();

        View v = layout.inflate(R.layout.activity_lista, null);
        TextView textoEmail = v.findViewById(R.id.email_textView_lista);
        TextView textoNome = v.findViewById(R.id.nome_textView_lista);
        TextView textoTipo = v.findViewById(R.id.tipo_exame_textView_lista);
        TextView textoData = v.findViewById(R.id.data_textView_lista);
        TextView textoCpf = v.findViewById(R.id.cpf_textView_lista);



        textoNome.setText(exame.get(position).getNome());
        textoEmail.setText(exame.get(position).getEmail());
        textoData.setText(exame.get(position).getTipoExame());
        textoCpf.setText(exame.get(position).getCpf());
        textoTipo.setText(exame.get(position).getData());
        return v;
    }
}
