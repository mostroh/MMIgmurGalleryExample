package example.mmigmur.mmimgurgalleryexample.login;

import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;

/**
 * Created by migarcma on 17/3/18.
 */

public interface LoginView extends BaseFragmentView {
    void showLoading();
    void hideLoading();
    void showConnectionError();
    void showLoginWebViewFragment();
    void goToGallery(String username);
}
