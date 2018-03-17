package example.mmigmur.mmimgurgalleryexample.login;

import android.os.Bundle;

import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseActivity;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityModule;

public class LoginActivity extends BaseActivity<ActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initDI() {
        activityComponent = getAppComponent().with(new ActivityModule(this));
        activityComponent.injectActivity(this);
    }
}
