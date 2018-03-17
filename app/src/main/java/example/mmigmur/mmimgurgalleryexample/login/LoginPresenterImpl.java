package example.mmigmur.mmimgurgalleryexample.login;

import javax.inject.Inject;

import example.mmigmur.domain.interactors.LoginInteractor;
import example.mmigmur.domain.model.Authorization;
import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter, LoginInteractor.LoginInteractorResponse {

    @Inject
    LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView baseView) {
        super(baseView);
    }

    public LoginPresenterImpl(BasePresenterComponent basePresenterComponent, LoginView view) {
        super(view);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
    }

    @Override
    public void tryLogin() {
        view.showLoading();
        loginInteractor.login(Constants.MY_IMGUR_CLIENT_ID,Constants.MY_IMGUR_CLIENT_SECRET, this);
    }

    @Override
    public void onLoginInteractorResponseSuccess(Authorization authorization) {
        view.hideLoading();
    }

    @Override
    public void onLoginInteractorResponseFailureConnection() {
        view.hideLoading();
        view.showConnectionError();
    }

    @Override
    public void onLoginInteractorResponseFailureAuth() {
        view.hideLoading();
        view.showLoginWebViewFragment();
    }
}
