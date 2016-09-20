package com.innovatube.hackernew.injection.component;



import com.innovatube.hackernew.injection.PerActivity;
import com.innovatube.hackernew.injection.module.ActivityModule;
import com.innovatube.hackernew.ui.signup.CreateAccountActivity;

import dagger.Component;

/**
 * Created by TOIDV on 4/4/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)


public interface ActivityComponent {

    void inject(CreateAccountActivity createAccountActivity);


}
