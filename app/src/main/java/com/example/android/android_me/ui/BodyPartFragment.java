package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manvi on 22/8/17.
 */

public class BodyPartFragment extends Fragment{

    private static final String TAG = BodyPartFragment.class.getSimpleName();
    private List<Integer>mImageIds;
    private int mListIndex;

    private static final String IMAGE_LIST = "image_list";
    private static final String IMAGE_ID = "image_id";
    //required to instantiate the fragment
    public BodyPartFragment(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //extract the information saved during the rotation.
        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_LIST);
            mListIndex = savedInstanceState.getInt(IMAGE_ID);
        }
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image);
        if(mImageIds !=null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            //set on click listener on all the images so that when user clicks on a image, image can be changed.
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    }else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }else {
            Log.d(TAG,"empty image list");
        }
        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setListIndex(int listIndex) {
        this.mListIndex = listIndex;
    }


    //On rotation the device, images are reset to the original state. It doesn't restore the images which are set currently.
    //To restore the image and image index during rotation, onSaveInstanceState method is used.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(IMAGE_LIST, (ArrayList<Integer>)mImageIds);
        outState.putInt(IMAGE_ID,mListIndex);
    }
}
