package example.mmigmur.mmimgurgalleryexample.gallery;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenter;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 18/3/18.
 */

public interface ImageDetailPresenter extends BasePresenter {
    void deleteImage(String deleteHash);
}
