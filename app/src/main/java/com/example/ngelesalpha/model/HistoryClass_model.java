package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class HistoryClass_model {
    private int imageId;
    private String title;
    private String start_date;
    private String end_date;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setStart_date(String start_date){
        this.start_date=start_date;
    }

    public void setEnd_date(String end_date){
        this.end_date=end_date;
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

    public String getStart_date()
    {
        return start_date;
    }

    public String getEnd_date()
    {
        return end_date;
    }

    public static ArrayList<HistoryClass_model> getData()
    {
        ArrayList<HistoryClass_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text1= getText1();
        String[] text2= getText2();
        String[] text3= getText3();
        for(int i=0;i<images.length;i++)
        {
            HistoryClass_model historyClassModel =new HistoryClass_model();
            historyClassModel.setImageId(images[i]);
            historyClassModel.setTitle(text1[i]);
            historyClassModel.setStart_date(text2[i]);
            historyClassModel.setEnd_date(text3[i]);
            dataList.add(historyClassModel);
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
                "English Conversation Public","English Conversation Public","English Conversation Public","English Conversation Public","English Conversation Public",
                "English Conversation Public","English Conversation Public","English Conversation Public","English Conversation Public","English Conversation Public"
        };

        return text1;
    }
    public static String[] getText2(){

        String[] text2={
                "16 Mei 2016","16 Mei 2016","16 Mei 2016","16 Mei 2016","16 Mei 2016",
                "16 Mei 2016","16 Mei 2016","16 Mei 2016","16 Mei 2016","16 Mei 2016"
        };

        return text2;
    }
    public static String[] getText3(){

        String[] text3={
                "30 Agustus 2016","30 Agustus 2016","30 Agustus 2016","30 Agustus 2016","30 Agustus 2016",
                "30 Agustus 2016","30 Agustus 2016","30 Agustus 2016","30 Agustus 2016","30 Agustus 2016"
        };

        return text3;
    }
}
