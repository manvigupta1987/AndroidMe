/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if(savedInstanceState == null) {
            //Instantiate a Fragment to load the head part
            BodyPartFragment headPartFragment = new BodyPartFragment();
            //set the head images to the image list.
            headPartFragment.setmImageIds(AndroidImageAssets.getHeads());
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headPartFragment.setListIndex(headIndex);
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
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyPartFragment.setListIndex(bodyIndex);
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
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legPartFragment.setListIndex(legIndex);
            if (legPartFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().add(R.id.leg_container, legPartFragment).commit();
                }
            }
        }
    }
}
