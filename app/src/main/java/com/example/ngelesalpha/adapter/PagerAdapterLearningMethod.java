package com.example.ngelesalpha.adapter;

        import android.content.Context;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.ngelesalpha.R;
        import com.example.ngelesalpha.learning_method_data_model;

        import java.util.List;

/**
 * Created by Timothy on 7/15/2016.
 */
public class PagerAdapterLearningMethod extends PagerAdapter {

    private Context context;
    private List<learning_method_data_model> itemList;

    private LayoutInflater inflater;

    public PagerAdapterLearningMethod(Context context, List<learning_method_data_model> itemList)
    {
        this.context=context;
        this.itemList=itemList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    //Checks whether view is associated with object or object is associated with page view or not
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //Get the view of the single view pager item
        View itemView = inflater.inflate(R.layout.viewpager_item_learning_method,container,false);

        //Locate the imageview and textview
        ImageView imageView = (ImageView) itemView.findViewById(R.id.vp_lm_image);
        TextView textViewTitle1 = (TextView) itemView.findViewById(R.id.vp_lm_titles);
        TextView textViewTitle2 = (TextView) itemView.findViewById(R.id.vp_lm_titles2);

        //Get the datamodel for current position
        learning_method_data_model dataModel = itemList.get(position);

        //Set the data for image and text
        imageView.setImageResource(dataModel.imageId);
        textViewTitle1.setText(dataModel.title);
        textViewTitle2.setText(dataModel.title2);

        //Add viewpager item xml to viewpager
        container.addView(itemView);

        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object)
    {
        //Remove view of viewpager xml
        container.removeView((FrameLayout)object);
    }
}
