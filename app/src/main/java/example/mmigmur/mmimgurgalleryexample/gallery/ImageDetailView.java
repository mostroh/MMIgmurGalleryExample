package example.mmigmur.mmimgurgalleryexample.gallery;

import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;

/**
 * Created by migarcma on 18/3/18.
 */

public interface ImageDetailView extends BaseFragmentView {

    void showLoading();
    void hideLoading();
    void showErrorDeleting();
    void notifyImageDeleted();
}
