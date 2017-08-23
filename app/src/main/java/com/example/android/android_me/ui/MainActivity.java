package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.onImageSelectedInterface{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout)!=null){
            mTwoPane = true;
            Button button = (Button) findViewById(R.id.next_button);
            button.setVisibility(View.GONE);
            GridView gridView = (GridView) findViewById(R.id.image_grid_view);
            gridView.setNumColumns(2);
            if(savedInstanceState == null) {
                //Instantiate a Fragment to load the head part
                BodyPartFragment headPartFragment = new BodyPartFragment();
                //set the head images to the image list.
                headPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                if (headPartFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction().add(R.id.head_container, headPartFragment).commit();
                    }
                }

                //Instantiate a Fragment to load the body part
                BodyPartFragment bodyPartFragment = new BodyPartFragment();
                //set the body images to the image list.
                bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                if (bodyPartFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction().add(R.id.body_container, bodyPartFragment).commit();
                    }
                }

                //Instantiate a Fragment to load the leg part
                BodyPartFragment legPartFragment = new BodyPartFragment();
                //set the leg images to the image list.
                legPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                if (legPartFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction().add(R.id.leg_container, legPartFragment).commit();
                    }
                }
            }

        }else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {

        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int bodyPartNumber = position /12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listIndex = position - 12*bodyPartNumber;

        // Handle the two-pane case and replace existing fragments right when a new image is selected from the master list
        if(mTwoPane){
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, bodyPartFragment).commit();
                    break;
                case 1:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, bodyPartFragment).commit();
                    break;
                case 2:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container, bodyPartFragment).commit();
                    break;
                default:
                    break;
            }
        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);

            intent.putExtras(bundle);
            Button button = (Button) findViewById(R.id.next_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
