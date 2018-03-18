package example.mmigmur.mmimgurgalleryexample.gallery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.adapters.ImageGalleryAdapter;
import example.mmigmur.mmimgurgalleryexample.base.BaseActivity;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 17/3/18.
 */

public class GalleryFragment extends BaseFragment implements GalleryView, ImageGalleryAdapter.OnImageClickedListener,
        ImageDetailFragment.OnDeleteImageListener, ImageUploadFragment.OnImageUploadListener {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    @Inject
    GalleryPresenter presenter;

    private List<ImageViewModel> gallery = null;

    private ImageGalleryAdapter adapter;

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
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvGallery.setHasFixedSize(true);
        rvGallery.setLayoutManager(layoutManager);
    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_gallery;
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

    @Override
    public void showGalleryError() {
        DialogManager.showSnackBarMessage(getView(), getString(R.string.ERROR_CONECTION_MSG), Snackbar.LENGTH_LONG);
    }

    @Override
    public void showConnectionError() {
        DialogManager.showSnackBarMessage(getView(), getString(R.string.ERROR_CONECTION_MSG), Snackbar.LENGTH_LONG);
    }

    @Override
    public void setUpGallery() {
        adapter = new ImageGalleryAdapter(getActivity(), gallery, this);
        rvGallery.setAdapter(adapter);
    }

    @Override
    public void refreshGallery(List<ImageViewModel> viewGallery) {
        this.gallery = viewGallery;
        adapter.setGalleryList(gallery);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void imageClicked(ImageViewModel imageViewModelClicked) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_gallery_content, ImageDetailFragment.newInstance(imageViewModelClicked,this))
                .addToBackStack("GalleryFragment")
                .commit();
    }

    @OnClick(R.id.button_upload)
    void goToUpload(){
        getFragmentManager().beginTransaction()
                .replace(R.id.fl_gallery_content, ImageUploadFragment.newInstance(this))
                .addToBackStack("GalleryFragment")
                .commit();
    }

    @Override
    public void imageDeleted() {
        presenter.refreshGallery();
    }

    @Override
    public void imageUploaded() {
        presenter.refreshGallery();
    }
}
