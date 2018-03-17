package example.mmigmur.mmimgurgalleryexample.login;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenter;

/**
 * Created by migarcma on 17/3/18.
 */

public interface LoginWebViewPresenter extends BasePresenter {
    void saveCredentials(String refreshToken, String accessToken, String userName);
}
