package example.mmigmur.mmimgurgalleryexample.di.fragment;

import dagger.Subcomponent;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterModule;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerFragment;
import example.mmigmur.mmimgurgalleryexample.login.LoginFragment;


@PerFragment
@Subcomponent(
        modules = {
                BaseFragmentModule.class
        })
public interface BaseFragmentComponent {

    //LOGIN
    void inject(LoginFragment loginFragment);


    BasePresenterComponent with(BasePresenterModule basePresenterModule);
}
