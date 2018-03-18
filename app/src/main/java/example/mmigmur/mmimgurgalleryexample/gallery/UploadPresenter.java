package example.mmigmur.mmimgurgalleryexample.gallery;

import example.mmigmur.mmimgurgalleryexample.base.BasePresenter;

/**
 * Created by migarcma on 18/3/18.
 */

public interface UploadPresenter extends BasePresenter {
    void uploadImage(String b64Photo,String title, String description);
}
