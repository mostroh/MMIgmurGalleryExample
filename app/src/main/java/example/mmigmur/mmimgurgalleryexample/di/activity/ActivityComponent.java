package example.mmigmur.mmimgurgalleryexample.di.activity;

import dagger.Subcomponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerActivity;
import example.mmigmur.mmimgurgalleryexample.gallery.GalleryActivity;
import example.mmigmur.mmimgurgalleryexample.login.LoginActivity;

@PerActivity
@Subcomponent(
        modules = {ActivityModule.class})
public interface ActivityComponent {

    void injectActivity(LoginActivity loginActivity);

    void injectActivity(GalleryActivity galleryActivity);

    BaseFragmentComponent with(BaseFragmentModule baseFragmentModule);

}
