package com.example.exam_io.data;


import com.example.exam_io.models.Exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExamDataAccessObject implements Serializable {
    private List<Exam> ExamData;

    public ExamDataAccessObject (List<Exam> ExamData){
        this.ExamData = ExamData;
    }

    public List<Exam> getAll(){
        return ExamData;
    }

    public Exam getItem(int position) {
        return this.ExamData.get(position);
    }


    public void setAll(List<Exam> AlunoData){
        this.ExamData = AlunoData;
    }
    public ExamDataAccessObject (){}
}
