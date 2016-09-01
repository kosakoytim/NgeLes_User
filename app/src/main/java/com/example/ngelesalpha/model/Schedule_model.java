package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/30/2016.
 */

public class Schedule_model {

    private String classname_sch;
    private String classday_sch;
    private String classdate_sch;
    private String classroom_sch;
    private String classtime_sch;

    //SETTER
    public void setClassname_sch(String classname_sch){
        this.classname_sch=classname_sch;
    }
    public void setClassday_sch(String classday_sch){
        this.classday_sch=classday_sch;
    }
    public void setClassdate_sch(String classdate_sch){
        this.classdate_sch=classdate_sch;
    }
    public void setClassroom_sch(String classroom_sch){
        this.classroom_sch=classroom_sch;
    }
    public void setClasstime_sch(String classtime_sch){
        this.classtime_sch=classtime_sch;
    }


    //GETTER
    public String getText_classname_sch()
    {
        return classname_sch;
    }
    public String getText_classday_sch()
    {
        return classday_sch;
    }
    public String getText_classdate_sch()
    {
        return classdate_sch;
    }
    public String getText_classroom_sch()
    {
        return classroom_sch;
    }
    public String getText_classtime_sch() { return classtime_sch;}

    public static ArrayList<Schedule_model> getData()
    {
        ArrayList<Schedule_model> dataList = new ArrayList<>();
        String[] text1= getText1();
        String[] text2= getText2();
        String[] text3= getText3();
        String[] text4= getText4();
        String[] text5= getText5();
        for(int i=0;i<text1.length;i++)
        {
            Schedule_model schedule_model =new Schedule_model();
            schedule_model.setClassname_sch(text1[i]);
            schedule_model.setClassday_sch(text2[i]);
            schedule_model.setClassdate_sch(text3[i]);
            schedule_model.setClassroom_sch(text4[i]);
            schedule_model.setClasstime_sch(text5[i]);
            dataList.add(schedule_model);
        }

        return dataList;
    }

    public static String[] getText1(){

        String[] text1={
                "English For Business","English For Business","English For Business","English For Business","English For Business",
                "English For Business","English For Business","English For Business","English For Business","English For Business"
        };

        return text1;
    }

    public static String[] getText2(){

        String[] text2={
                "Senin","Senin","Senin","Senin","Senin","Senin","Senin","Senin","Senin","Senin"
        };

        return text2;
    }

    public static String[] getText3(){

        String[] text3={
                "16/05/16","16/05/16","16/05/16","16/05/16","16/05/16","16/05/16","16/05/16","16/05/16","16/05/16","16/05/16"
        };

        return text3;
    }

    public static String[] getText4(){

        String[] text4={
                "L1D","L1D","L1D","L1D","L1D","L1D","L1D","L1D","L1D","L1D"
        };

        return text4;
    }

    public static String[] getText5(){

        String[] text5={
                "16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00","16.00-18.00"
        };

        return text5;
    }
}
