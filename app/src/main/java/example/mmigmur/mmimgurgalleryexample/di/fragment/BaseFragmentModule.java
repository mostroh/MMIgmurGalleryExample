package example.mmigmur.mmimgurgalleryexample.di.fragment;

import dagger.Module;
import dagger.Provides;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterModule;
import example.mmigmur.mmimgurgalleryexample.di.scopes.PerFragment;
import example.mmigmur.mmimgurgalleryexample.gallery.GalleryPresenter;
import example.mmigmur.mmimgurgalleryexample.gallery.GalleryPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.gallery.GalleryView;
import example.mmigmur.mmimgurgalleryexample.gallery.ImageDetailPresenter;
import example.mmigmur.mmimgurgalleryexample.gallery.ImageDetailPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.gallery.ImageDetailView;
import example.mmigmur.mmimgurgalleryexample.gallery.ImageUploadView;
import example.mmigmur.mmimgurgalleryexample.gallery.UploadPresenter;
import example.mmigmur.mmimgurgalleryexample.gallery.UploadPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.login.LoginPresenter;
import example.mmigmur.mmimgurgalleryexample.login.LoginPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.login.LoginView;
import example.mmigmur.mmimgurgalleryexample.login.LoginWebViewPresenter;
import example.mmigmur.mmimgurgalleryexample.login.LoginWebViewPresenterImpl;
import example.mmigmur.mmimgurgalleryexample.login.LoginWebViewView;

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

    @PerFragment
    @Provides
    LoginWebViewPresenter provideLoginWebViewPresenter(BaseFragmentComponent baseFragmentComponent) {
        return new LoginWebViewPresenterImpl(baseFragmentComponent.with(new BasePresenterModule()), (LoginWebViewView) baseView);
    }

    @PerFragment
    @Provides
    GalleryPresenter provideGalleryPresenter(BaseFragmentComponent baseFragmentComponent) {
        return new GalleryPresenterImpl(baseFragmentComponent.with(new BasePresenterModule()), (GalleryView) baseView);
    }

    @PerFragment
    @Provides
    ImageDetailPresenter provideImageDetailPresenter(BaseFragmentComponent baseFragmentComponent) {
        return new ImageDetailPresenterImpl(baseFragmentComponent.with(new BasePresenterModule()), (ImageDetailView) baseView);
    }

    @PerFragment
    @Provides
    UploadPresenter provideUploadPresenter(BaseFragmentComponent baseFragmentComponent) {
        return new UploadPresenterImpl(baseFragmentComponent.with(new BasePresenterModule()), (ImageUploadView) baseView);
    }

}
