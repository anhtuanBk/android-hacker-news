package com.innovatube.hackernew.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.innovatube.hackernew.InnovatubeApplication;
import com.innovatube.hackernew.injection.component.ActivityComponent;
import com.innovatube.hackernew.injection.component.DaggerActivityComponent;


/**
 * Created by TOIDV on 4/4/2016.
 */
public class BaseActivity extends AppCompatActivity {

    ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(InnovatubeApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }
}
