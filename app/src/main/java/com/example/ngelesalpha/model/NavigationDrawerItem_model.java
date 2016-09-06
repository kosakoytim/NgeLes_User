package com.example.ngelesalpha.model;

import com.example.ngelesalpha.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Timothy on 7/4/2016.
 */
public class NavigationDrawerItem_model {

    private int navbar_user_profile_image;
    private String navbar_user_first_name;
    private String navbar_list_item_title;
    private int navbar_list_item_icon;

    //SETTER
    public void setNavbar_user_profile_image(int navbar_user_profile_image) {
        this.navbar_user_profile_image = navbar_user_profile_image;
    }
    public void setNavbar_user_first_name(String navbar_user_first_name)
    {
        this.navbar_user_first_name=navbar_user_first_name;
    }
    public void setNavbar_list_item_title(String navbar_list_item_title)
    {
        this.navbar_list_item_title = navbar_list_item_title;
    }
    public void setNavbar_list_item_icon(int navbar_list_item_icon)
    {
        this.navbar_list_item_icon = navbar_list_item_icon;
    }

    //GETTER
    public String getNavbar_user_first_name()
    {
        return navbar_user_first_name;
    }
    public int getNavbar_user_profile_image()
    {
        return navbar_user_profile_image;
    }
    public String getNavbar_list_item_title()
    {
        return navbar_list_item_title;
    }
    public int getNavbar_list_item_icon()
    {
        return navbar_list_item_icon;
    }



    public static ArrayList<NavigationDrawerItem_model> getData()
    {
        ArrayList<NavigationDrawerItem_model> dataList = new ArrayList<>();
//        String user_name= "Nurul";
//        int img_profile = R.drawable.test_profile_photo;
        int[] navbar_icon =getIcons();
        String[] navbar_titles = getTitles();

        NavigationDrawerItem_model navItemprofile= new NavigationDrawerItem_model();
//        navItemprofile.setNavbar_user_first_name(user_name);
//        navItemprofile.setNavbar_user_profile_image(img_profile);
        for (int i=0; i<navbar_titles.length; i++)
        {
            NavigationDrawerItem_model navItem= new NavigationDrawerItem_model();
            navItem.setNavbar_list_item_title(navbar_titles[i]);
            navItem.setNavbar_list_item_icon(navbar_icon[i]);
            dataList.add(navItem);
        }

        return dataList;
    }

//    private static String[] getUser_name()
//    {
//        String[] user_name={
//                "Nurul"
//        };
//        return user_name;
//    }

//    private static int[] getImg_profile()
//    {
//        int[] img_profile= {
//                        R.drawable.test_profile_photo
//                };
//        return img_profile;
//    }

    private static int[] getIcons()
    {
        return new int[]
                {
                        R.drawable.schedule, R.drawable.assignment, R.drawable.class_active_icon,
                        R.drawable.class_history_icon, R.drawable.class_status_icon,
                        R.drawable.settings_icon, R.drawable.customer_support_icon,R.drawable.post_a_course_program_icon,R.drawable.log_out_icon
                };
    }

    private static String[] getTitles()
    {
        return new String[]
                {
                        "My Schedule", "My Assignment", "Active Class",
                        "Class History", "Class Status",
                        "Settings", "Help and Support","Post A Course Program","Log Out"
                };
    }

}
