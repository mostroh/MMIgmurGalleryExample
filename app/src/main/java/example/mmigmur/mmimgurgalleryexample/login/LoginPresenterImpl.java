package example.mmigmur.mmimgurgalleryexample.login;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {


    public LoginPresenterImpl(LoginView baseView) {
        super(baseView);
    }

    public LoginPresenterImpl(BasePresenterComponent basePresenterComponent, LoginView view) {
        super(view);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {

    }
}
