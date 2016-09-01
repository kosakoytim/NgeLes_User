package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class Home_Popular_model {
    private int imageId;
    private String classname_pop;
    private String classnewregistrant_pop;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setClassname_pop(String classname_pop){
        this.classname_pop=classname_pop;
    }

    public void setClassnewregistrant_pop(String classnewregistrant_pop){
        this.classnewregistrant_pop=classnewregistrant_pop;
    }

    //GETTER
    public int getImageId()
    {
        return imageId;
    }

    public String getClassname_pop()
    {
        return classname_pop;
    }

    public String getClassnewregistrant_pop()
    {
        return classnewregistrant_pop;
    }

    public static ArrayList<Home_Popular_model> getData()
    {
        ArrayList<Home_Popular_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text1= getText1();
        String[] text2= getText2();

        for(int i=0;i<images.length;i++)
        {
            Home_Popular_model home_popular_model =new Home_Popular_model();
            home_popular_model.setImageId(images[i]);
            home_popular_model.setClassname_pop(text1[i]);
            home_popular_model.setClassnewregistrant_pop(text2[i]);
            dataList.add(home_popular_model);
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
                "22","22","22","22","22","22","22","22","22","22"
        };

        return text2;
    }

}
