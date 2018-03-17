package example.mmigmur.mmimgurgalleryexample.di.presenter;

import dagger.Subcomponent;
import example.mmigmur.data.di.BaseRepositoryComponent;
import example.mmigmur.data.di.BaseRepositoryModule;
import example.mmigmur.domain.di.BaseInteractorComponent;
import example.mmigmur.domain.di.BaseInteractorModule;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerPresenter;
import example.mmigmur.mmimgurgalleryexample.login.LoginPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.login.LoginWebViewPresenterImpl;


@PerPresenter
@Subcomponent(
        modules = {
                BasePresenterModule.class
        })
public interface BasePresenterComponent {

    //LOGIN
    void injectPresenter(LoginPresenterImpl loginPresenter);

    void injectPresenter(LoginWebViewPresenterImpl loginWebViewPresenter);

    //interactor providers
    BaseInteractorComponent with(BaseInteractorModule baseInteractorModule);

    //repo providers
    BaseRepositoryComponent with(BaseRepositoryModule baseRepositoryModule);

}
