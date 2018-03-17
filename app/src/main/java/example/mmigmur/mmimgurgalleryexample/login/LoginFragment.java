package example.mmigmur.mmimgurgalleryexample.login;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginFragment extends BaseFragment implements LoginView {

    @Inject
    LoginPresenter presenter;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initViews() {
        //nothing to do here
    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_login;
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

    @OnClick(R.id.button_login)
    void loginClicked(){
        presenter.tryLogin();
    }


    @Override
    public void showLoading() {
        DialogManager.getProgressDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        DialogManager.hideCurrentDialog();
    }

    @Override
    public void showConnectionError() {
        DialogManager.showSnackBarMessage(getView(), getString(R.string.ERROR_CONECTION_MSG), Snackbar.LENGTH_LONG);

    }


    @Override
    public void showLoginWebViewFragment() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fl_login_content, LoginWebViewFragment.newInstance())
                .commit();
    }
}
