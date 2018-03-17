package example.mmigmur.mmimgurgalleryexample.base;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.mmigmur.mmimgurgalleryexample.MMImgurGalleryApp;
import example.mmigmur.mmimgurgalleryexample.di.application.AppComponent;


public abstract class BaseActivity<T> extends AppCompatActivity {

    protected T activityComponent;
    protected int containerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
    }

    protected abstract void initDI();

    public AppComponent getAppComponent() {
        AppComponent appComponent = ((MMImgurGalleryApp) getApplication()).getComponent();
        return appComponent;
    }

    public Object getActivityComponent() {
        if (activityComponent == null)
            initDI();
        return activityComponent;
    }

    public int getContainerId()
    {
        return containerId;
    }
}
