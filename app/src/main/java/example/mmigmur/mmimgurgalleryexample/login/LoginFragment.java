package example.mmigmur.mmimgurgalleryexample.login;

import android.os.Bundle;

import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;

/**
 * Created by migarcma on 17/3/18.
 */

public class LoginFragment extends BaseFragment implements LoginView {


    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initViews() {

    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void initDIComponent() {
        fragmentComponent = (getParentComponent(ActivityComponent.class)).with(new BaseFragmentModule(this));
        fragmentComponent.inject(this);
    }


}
