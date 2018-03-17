package example.mmigmur.mmimgurgalleryexample.gallery;

import android.os.Bundle;

import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseActivity;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;

/**
 * Created by migarcma on 17/3/18.
 */

public class GalleryActivity extends BaseActivity<ActivityComponent> {


    @Override
    protected void initDI() {
        activityComponent = getAppComponent().with(new ActivityModule(this));
        activityComponent.injectActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        String username = getIntent().getStringExtra(Constants.USER_NAME_KEY);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_gallery_content, GalleryFragment.newInstance(username))
                .commit();
    }
}
