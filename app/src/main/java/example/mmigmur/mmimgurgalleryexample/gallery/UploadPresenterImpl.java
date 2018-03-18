package example.mmigmur.mmimgurgalleryexample.gallery;

import javax.inject.Inject;

import example.mmigmur.domain.interactors.GalleryInteractor;
import example.mmigmur.mmimgurgalleryexample.base.BasePresenterImpl;
import example.mmigmur.mmimgurgalleryexample.di.presenter.BasePresenterComponent;

/**
 * Created by migarcma on 18/3/18.
 */

public class UploadPresenterImpl extends BasePresenterImpl<ImageUploadView> implements UploadPresenter,
        GalleryInteractor.ImageUploadInteractorResponse {

    @Inject
    GalleryInteractor galleryInteractor;

    public UploadPresenterImpl(BasePresenterComponent basePresenterComponent, ImageUploadView baseView) {
        super(baseView);
        basePresenterComponent.injectPresenter(this);
    }

    @Override
    public void init() {
        view.initViews();
    }

    @Override
    public void uploadImage(String b64Photo, String title, String description) {
        view.showLoading();
        galleryInteractor.uploadImage(b64Photo,title,description, this);
    }

    @Override
    public void onUploadSuccess() {
        view.hideLoading();
        view.notifyUploadSuccess();
    }

    @Override
    public void onUploadFailure() {
        view.hideLoading();
        view.showErrorUploading();
    }
}
