package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class ActiveClass_model {
    private int imageId;
    private String title;
    private String next_meet;
    private String time;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setNext_meet(String next_meet){
        this.next_meet=next_meet;
    }

    public void setTime(String time){
        this.time=time;
    }

    //GETTER
    public int getImageId()
    {
        return imageId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getNext_meet()
    {
        return next_meet;
    }

    public String getTime()
    {
        return time;
    }

    public static ArrayList<ActiveClass_model> getData()
    {
        ArrayList<ActiveClass_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text1= getText1();
        String[] text2= getText2();
        String[] text3= getText3();
        for(int i=0;i<images.length;i++)
        {
            ActiveClass_model activeClassModel =new ActiveClass_model();
            activeClassModel.setImageId(images[i]);
            activeClassModel.setTitle(text1[i]);
            activeClassModel.setNext_meet(text2[i]);
            activeClassModel.setTime(text3[i]);
            dataList.add(activeClassModel);
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
                "English For Business","English For Business","English For Business","English For Business","English For Business",
                "English For Business","English For Business","English For Business","English For Business","English For Business"
        };

        return text1;
    }

    public static String[] getText2(){

        String[] text2={
                "Today","Today","Today","Today","Today","Today","Today","Today","Today","Today"
        };

        return text2;
    }

    public static String[] getText3(){

        String[] text3={
                "17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00","17.00-21.00"
        };

        return text3;
    }
}
