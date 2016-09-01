package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class Home_NearYou_model {
    private int imageId;
    private String classname_ny;
    private String classaddress_ny;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setClassname_ny(String classname_ny){
        this.classname_ny=classname_ny;
    }

    public void setClassaddress_ny(String classaddress_ny){
        this.classaddress_ny=classaddress_ny;
    }

    //GETTER
    public int getImageId()
    {
        return imageId;
    }

    public String getClassname_ny()
    {
        return classname_ny;
    }

    public String getClassaddress_ny()
    {
        return classaddress_ny;
    }

    public static ArrayList<Home_NearYou_model> getData()
    {
        ArrayList<Home_NearYou_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text1= getText1();
        String[] text2= getText2();

        for(int i=0;i<images.length;i++)
        {
            Home_NearYou_model home_nearYou_model =new Home_NearYou_model();
            home_nearYou_model.setImageId(images[i]);
            home_nearYou_model.setClassname_ny(text1[i]);
            home_nearYou_model.setClassaddress_ny(text2[i]);
            dataList.add(home_nearYou_model);
        }

        return dataList;
    }

    public static int[] getImages(){

        int[] images={
                R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute,
                R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute, R.drawable.test_institute,
        };

        return images;
    }

    public static String[] getText1(){

        String[] text1={
                "Business Model","Business Model","Business Model","Business Model","Business Model","Business Model","Business Model","Business Model","Business Model","Business Model"
        };

        return text1;
    }

    public static String[] getText2(){

        String[] text2={
                "Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi","Jl. Ruby X14, Jatisari, Bekasi",
        };

        return text2;
    }

}
