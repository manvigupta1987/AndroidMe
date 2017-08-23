package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by manvi on 22/8/17.
 */

public class MasterListFragment extends Fragment{

    public MasterListFragment(){}

    //interface which triggers call back in the host activity.
    public onImageSelectedInterface mCallBack;

    //The callback is a method named onImageSelected that contains information about the image selected in the grid view.
    public interface onImageSelectedInterface{
        void onImageSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list,container,false);

        GridView gridView = (GridView) rootView.findViewById(R.id.image_grid_view);
        MasterListAdapter adapter = new MasterListAdapter(getActivity(), AndroidImageAssets.getAll());

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallBack.onImageSelected(position);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try{
            mCallBack = (onImageSelectedInterface)context;
        }catch (ClassCastException ex){
            throw new ClassCastException(context.toString() + "must implement onImageSelectedInterface");
        }
    }

    @Override
    public void onDetach() {
        mCallBack = null; //avoids memory leak
        super.onDetach();
    }
}
