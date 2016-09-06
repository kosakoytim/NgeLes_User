package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/30/2016.
 */
public class LearningCategory_model {

    private int imageId_lc;
    private String text_lc;

    //SETTER
    public void setImageId(int imageId){
        this.imageId_lc=imageId;
    }

    public void setTitle(String title){
        this.text_lc=title;
    }

    //GETTER
    public int getImageId()
    {
        return imageId_lc;
    }

    public String getTitle()
    {
        return text_lc;
    }

    public static ArrayList<LearningCategory_model> getData()
    {
        ArrayList<LearningCategory_model> dataList = new ArrayList<>();
        int[] images = getImages();
        String[] text = getText();
        for(int i=0;i<images.length;i++)
        {
            LearningCategory_model learningCategory_model =new LearningCategory_model();
            learningCategory_model.setImageId(images[i]);
            learningCategory_model.setTitle(text[i]);
            dataList.add(learningCategory_model);
        }

        return dataList;
    }

    public static int[] getImages(){

        int[] images={
                R.drawable.assignment,R.drawable.assignment,R.drawable.assignment,R.drawable.assignment,R.drawable.assignment
        };

        return images;
    }

    public static String[] getText(){

        String[] text={
                "Bimbel SMA X","Bimbel SMA XI","Bimbel SMA XII","Graphic Design","Software Development"
        };

        return text;
    }
}
