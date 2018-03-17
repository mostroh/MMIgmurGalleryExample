package example.mmigmur.mmimgurgalleryexample.gallery;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseActivity;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;

/**
 * Created by migarcma on 17/3/18.
 */

public class GalleryFragment extends BaseFragment implements GalleryView {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    @Inject
    GalleryPresenter presenter;

    public static GalleryFragment newInstance(String username) {

        Bundle args = new Bundle();

        args.putString(Constants.USER_NAME_KEY, username);

        GalleryFragment fragment = new GalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        String username = getArguments().getString(Constants.USER_NAME_KEY);
        ((BaseActivity) getActivity())
                .setActionBarTitle("Galería de " + username);
    }

    @Override
    public void initViews() {
        //to be completed
    }

    @Override
    protected int getXMLToInflate() {
        return 0;
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

    @Override
    public void showLoading() {
        DialogManager.getProgressDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        DialogManager.hideCurrentDialog();
    }
}
