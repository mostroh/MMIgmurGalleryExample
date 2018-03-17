package example.mmigmur.mmimgurgalleryexample.di.fragment;

import dagger.Module;
import dagger.Provides;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterModule;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerFragment;
import example.mmigmur.mmimgurgalleryexample.login.LoginPresenter;
import example.mmigmur.mmimgurgalleryexample.login.LoginPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.login.LoginView;

@Module()
public class BaseFragmentModule {

    private BaseFragmentView baseView;

    public BaseFragmentModule(BaseFragmentView baseView) {
        this.baseView = baseView;
    }

    @PerFragment
    @Provides
    BaseFragmentView provideBaseInjectionFragment() {
        return baseView;
    }

    @PerFragment
    @Provides
    LoginPresenter provideLoginPresenter(BaseFragmentComponent baseFragmentComponent) {
        return new LoginPresenterImpl(baseFragmentComponent.with(new BasePresenterModule()), (LoginView) baseView);
    }

}
