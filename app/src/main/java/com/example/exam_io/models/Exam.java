package com.example.exam_io.models;

import java.io.Serializable;


public class Exam implements Serializable {
    private String nome;
    private String email;
    private String cpf;
    private String data;
    private String tipo_exame;

    public Exam (String nome, String email, String cpf, String data, String tipo_exame){
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.tipo_exame = tipo_exame;
    }

    public String getEmail(){
        return email;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf() {return cpf;}
    public String getData() {return data;}
    public String getTipoExame() {return tipo_exame;}

    public void setEmail(String email){
        this.email = email;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setData(String data) {this.data = data;}
    public void setTipoExame(String tipo_exame) {this.tipo_exame = tipo_exame;}

    public Exam (){}
}

