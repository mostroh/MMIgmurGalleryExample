package example.mmigmur.mmimgurgalleryexample.gallery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.Constants;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 18/3/18.
 */

public class ImageDetailFragment extends BaseFragment implements ImageDetailView {

    @BindView(R.id.tv_image_title_value)
    TextView tvTitle;

    @BindView(R.id.tv_image_description_value)
    TextView tvDescription;

    @BindView((R.id.iv_image))
    ImageView imageView;

    @Inject
    ImageDetailPresenter presenter;

    private ImageViewModel imageViewModel;

    private OnDeleteImageListener onDeleteImageListener;

    public static ImageDetailFragment newInstance(ImageViewModel imageViewModel, OnDeleteImageListener onDeleteImageListener) {

        Bundle args = new Bundle();
        args.putSerializable(Constants.IMAGE_MODEL, imageViewModel);
        ImageDetailFragment fragment = new ImageDetailFragment();
        fragment.onDeleteImageListener = onDeleteImageListener;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initViews() {
        imageViewModel = (ImageViewModel) getArguments().getSerializable(Constants.IMAGE_MODEL);
        if (imageViewModel.getTitle()!=null && !imageViewModel.getTitle().isEmpty()){
            tvTitle.setText(imageViewModel.getTitle());
        }
        if (imageViewModel.getDescription()!=null && !imageViewModel.getDescription().isEmpty()){
            tvDescription.setText(imageViewModel.getDescription());
        }
        Picasso.get().load(imageViewModel.getLink())
                .resize(500, 500)
                .centerCrop()
                .into(imageView);
    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_image_detail;
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
    public void showErrorDeleting() {
        DialogManager.showSnackBarMessage(getView(), getString(R.string.ERROR_DELETING), Snackbar.LENGTH_LONG);
    }

    @Override
    public void notifyImageDeleted() {
        if (onDeleteImageListener!=null){
            onDeleteImageListener.imageDeleted();
            getActivity().onBackPressed();
        }
    }

    @OnClick(R.id.iv_delete)
    void deleteImage(){
        presenter.deleteImage(imageViewModel.getDeletehash());
    }

    public interface OnDeleteImageListener {
        void imageDeleted();
    }
}
