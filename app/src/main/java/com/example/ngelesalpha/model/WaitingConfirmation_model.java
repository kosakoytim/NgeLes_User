package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class WaitingConfirmation_model {
    private int imageId;
    private String title;
    private String response_1;
    private String response_2;

    //SETTER
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setResponse_1(String response_1){
        this.response_1=response_1;
    }

    public void setResponse_2(String response_2){
        this.response_2=response_2;
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

    public String getResponse_1()
    {
        return response_1;
    }

    public String getResponse_2()
    {
        return response_2;
    }

    public static ArrayList<WaitingConfirmation_model> getData()
    {
        ArrayList<WaitingConfirmation_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text1= getText1();
        String[] text2= getText2();
        String[] text3= getText3();
        for(int i=0;i<images.length;i++)
        {
            WaitingConfirmation_model waitingConfirmation_model =new WaitingConfirmation_model();
            waitingConfirmation_model.setImageId(images[i]);
            waitingConfirmation_model.setTitle(text1[i]);
            waitingConfirmation_model.setResponse_1(text2[i]);
            waitingConfirmation_model.setResponse_2(text3[i]);
            dataList.add(waitingConfirmation_model);
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
                "Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed",
                "Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed","Your Request Has Been Processed"
        };

        return text2;
    }
    public static String[] getText3(){

        String[] text3={
                "You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done",
                "You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done","You will be notified when this process is done"
        };

        return text3;
    }
}
