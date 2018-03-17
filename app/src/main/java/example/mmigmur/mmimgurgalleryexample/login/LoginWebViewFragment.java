package example.mmigmur.mmimgurgalleryexample.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginWebViewFragment extends BaseFragment implements LoginWebViewView {

    private static final Pattern accessTokenPattern = Pattern.compile("access_token=([^&]*)");
    private static final Pattern refreshTokenPattern = Pattern.compile("refresh_token=([^&]*)");
    private static final Pattern accountUsernamePattern = Pattern.compile("account_username=([^&]*)");
    private static final Pattern accountIdPattern = Pattern.compile("account_username=(\\d+)");

    @BindView(R.id.wv_login)
    WebView loginWebView;

    @Inject
    LoginWebViewPresenter presenter;

    public static LoginWebViewFragment newInstance() {

        Bundle args = new Bundle();

        LoginWebViewFragment fragment = new LoginWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initViews() {
        setupWebView();
        loginWebView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = loginWebView.getSettings();
        settings.setDomStorageEnabled(true);
        loginWebView.loadUrl("https://api.imgur.com/oauth2/authorize?client_id=" + Constants.MY_IMGUR_CLIENT_ID + "&response_type=token");
    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_webview_login;
    }

    @Override
    protected void initPresenter() {
        presenter.init();
    }

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @Override
    protected void initDIComponent() {
        fragmentComponent = (getParentComponent(ActivityComponent.class)).with(new BaseFragmentModule(this));
        fragmentComponent.inject(this);
    }

    private void setupWebView() {
        loginWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // intercept the tokens
                // http://example.com#access_token=ACCESS_TOKEN&token_type=Bearer&expires_in=3600
                boolean tokensURL = false;
                if (url.startsWith(Constants.MY_IMGUR_REDIRECT_URL)) {
                    tokensURL = true;
                    Matcher m;

                    m = refreshTokenPattern.matcher(url);
                    m.find();
                    String refreshToken = m.group(1);

                    m = accessTokenPattern.matcher(url);
                    m.find();
                    String accessToken = m.group(1);

                    m = accountUsernamePattern.matcher(url);
                    m.find();
                    String username = m.group(1);

                    presenter.saveCredentials(refreshToken, accessToken, username);

                } else {
                    showIncorrectLoginError();
                }
                hideKeyboard();
                return tokensURL;
            }
        });
    }

    private void showIncorrectLoginError(){
        //to be completed
        DialogManager.showSnackBarMessage(getView(),getString(R.string.wrong_credentials), Snackbar.LENGTH_LONG);
    }

    @Override
    public void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showGallery() {
        
    }

}
