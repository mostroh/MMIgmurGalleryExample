package example.mmigmur.mmimgurgalleryexample.login;

import javax.inject.Inject;

import example.mmigmur.domain.interactors.LoginInteractor;
import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginWebViewPresenterImpl extends BasePresenterImpl<LoginWebViewView> implements LoginWebViewPresenter {

    @Inject
    LoginInteractor loginInteractor;

    public LoginWebViewPresenterImpl(BasePresenterComponent basePresenterComponent, LoginWebViewView view) {
        super(view);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
    }

    @Override
    public void saveCredentials(String refreshToken, String accessToken, String userName) {
        loginInteractor.saveCredentials(refreshToken, accessToken, userName);
        view.showGallery();
    }
}
