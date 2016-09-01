package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class Home_Assignment_model {
    private int imageId;
    private int checkbox_as;
    private String classname_as;
    private String classassignmenttitle_as;
    private String classassignmentdeadline_as;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setClassname_as(String classname_as){
        this.classname_as=classname_as;
    }

    public void setClassassignmenttitle_as(String classassignmenttitle_as){
        this.classassignmenttitle_as=classassignmenttitle_as;
    }

    public void setClassassignmentdeadline_as(String classassignmentdeadline_as){
        this.classassignmentdeadline_as=classassignmentdeadline_as;
    }

    public void setCheckbox_as(int checkbox_as){
        this.checkbox_as=checkbox_as;
    }

    //GETTER
    public int getImageId()
    {
        return imageId;
    }
    public int getCheckbox_as() { return checkbox_as; }
    public String getClassname_as()
    {
        return classname_as;
    }
    public String getClassassignmenttitle_as()
    {
        return classassignmenttitle_as;
    }
    public String getClassassignmentdeadline_as()
    {
        return classassignmentdeadline_as;
    }

    public static ArrayList<Home_Assignment_model> getData()
    {
        ArrayList<Home_Assignment_model> dataList = new ArrayList<>();
        int[] images = getImages();
        int[] images2 = getImages2();
        String[] text1= getText1();
        String[] text2= getText2();
        String[] text3= getText3();

        for(int i=0;i<images.length;i++)
        {
            Home_Assignment_model home_assignment_model =new Home_Assignment_model();
            home_assignment_model.setImageId(images[i]);
            home_assignment_model.setCheckbox_as(images2[i]);
            home_assignment_model.setClassname_as(text1[i]);
            home_assignment_model.setClassassignmenttitle_as(text2[i]);
            home_assignment_model.setClassassignmentdeadline_as(text3[i]);
            dataList.add(home_assignment_model);
        }

        return dataList;
    }

    public static int[] getImages(){

        int[] images={
               R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute

        };

        return images;
    }

    public static int[] getImages2(){

        int[] images2={
               R.drawable.ngeles_icon,R.drawable.ngeles_icon,R.drawable.ngeles_icon
        };

        return images2;
    }

    public static String[] getText1(){

        String[] text1={
                "English For Business","English For Business","English For Business"
        };

        return text1;
    }

    public static String[] getText2(){

        String[] text2={
                "Conversation Text Trial","Conversation Text Trial","Conversation Text Trial"
        };

        return text2;
    }

    public static String[] getText3(){

        String[] text3={
                "15 Maret 2016","15 Maret 2016","15 Maret 2016"
        };

        return text3;
    }

}
