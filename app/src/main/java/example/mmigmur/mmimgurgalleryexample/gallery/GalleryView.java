package example.mmigmur.mmimgurgalleryexample.gallery;

import java.util.List;

import example.mmigmur.mmimgurgalleryexample.base.BaseFragmentView;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 17/3/18.
 */

public interface GalleryView extends BaseFragmentView {
    void showLoading();
    void hideLoading();
    void showGalleryError();
    void showConnectionError();
    void setUpGallery(List<ImageViewModel> viewGallery);
}
