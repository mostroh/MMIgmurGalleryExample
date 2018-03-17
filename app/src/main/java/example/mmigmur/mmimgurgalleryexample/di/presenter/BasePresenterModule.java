package example.mmigmur.mmimgurgalleryexample.di.presenter;

import dagger.Module;
import dagger.Provides;
import example.mmigmur.data.di.BaseRepositoryModule;
import example.mmigmur.data.services.LoginService;
import example.mmigmur.domain.boundaries.LoginRepoInterface;
import example.mmigmur.domain.di.BaseInteractorModule;
import example.mmigmur.domain.interactors.LoginInteractor;
import example.mmigmur.domain.interactors.LoginInteractorImpl;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerPresenter;

@Module()
public class BasePresenterModule {

    public BasePresenterModule() {
    }

    @PerPresenter
    @Provides
    LoginInteractor provideLoginInteractor(BasePresenterComponent basePresenterComponent) {
        return new LoginInteractorImpl(basePresenterComponent.with(new BaseInteractorModule()));
    }

    @PerPresenter
    @Provides
    LoginRepoInterface provideLoginRepoInterface(BasePresenterComponent basePresenterComponent) {
        return new LoginService(basePresenterComponent.with(new BaseRepositoryModule()));
    }



}
