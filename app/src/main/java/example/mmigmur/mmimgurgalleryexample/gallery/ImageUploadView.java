package example.mmigmur.mmimgurgalleryexample.gallery;

import android.graphics.Bitmap;

import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;

/**
 * Created by migarcma on 18/3/18.
 */

public interface ImageUploadView extends BaseFragmentView {

    void hideKeyboard();
    void showLoading();
    void hideLoading();
    void showErrorUploading();
    void updateImage(Bitmap bitmap);
    void notifyUploadSuccess();
}
