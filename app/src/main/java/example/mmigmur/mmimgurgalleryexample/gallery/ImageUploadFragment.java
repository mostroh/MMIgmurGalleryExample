package example.mmigmur.mmimgurgalleryexample.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;

/**
 * Created by migarcma on 18/3/18.
 */

public class ImageUploadFragment extends BaseFragment implements ImageUploadView {

    @Inject
    UploadPresenter presenter;

    private OnImageUploadListener onImageUploadListener;

    public static ImageUploadFragment newInstance(OnImageUploadListener onImageUploadListener) {

        Bundle args = new Bundle();

        ImageUploadFragment fragment = new ImageUploadFragment();
        fragment.setArguments(args);
        fragment.onImageUploadListener = onImageUploadListener;
        return fragment;
    }

    @Override
    public void initViews() {

    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_image_upload;
    }

    @Override
    protected void initPresenter() {
        presenter.init();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void initDIComponent() {
        fragmentComponent = (getParentComponent(ActivityComponent.class)).with(new BaseFragmentModule(this));
        fragmentComponent.inject(this);
    }

    @Override
    public void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public interface OnImageUploadListener{

        void imageUploaded();
    }
}
